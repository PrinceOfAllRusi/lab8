package tools

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import org.koin.core.component.KoinComponent
import multilib.utilities.input.*
import multilib.utilities.result.Result
import multilib.utilities.commandsData.*
import multilib.client.commandsData.ServerCommandsData
import multilib.client.tools.socket.ClientSocket
import multilib.utilities.serializ.Serializer
import tornadofx.*

class CommandProcessor: KoinComponent, Controller() {

    private var result: Result = Result()
    private val serializer = Serializer()
    private val socket = ClientSocket()

    private var command = ""
    private var commandsList = ServerCommandsData()
    private var sendCommandsData = ClientCommandsData()
    private val dataProcessor = DataProcessing()
    private var xml = serializer.serialize(sendCommandsData)
    private var receivedData = ""
    private val help = "Enter \"help\" to see information about commands\n"

    fun getAllInfo() {
        socket.send(xml)
        xml = socket.receive()

        commandsList = serializer.deserialize(xml)
        sendCommandsData.setCommandsVersion(commandsList.getCommandsVersion())
//        input.outMsg(help)
    }

    fun process(input: Input): Result {

        result.setMessage("")
        sendCommandsData.clearMap()
        sendCommandsData.setToken(result.getToken())

        command = input.getNextWord(null).lowercase()

        if ( !commandsList.getMapCommands().containsKey(command) ) {
            input.outMsg("This command does not exist\n$help")
        }
        else {
            try {
                sendCommandsData = dataProcessor.setData(input,
                    commandsList.getMapCommands()[command]!!, sendCommandsData)
                sendCommandsData.setName(command)
                xml = serializer.serialize(sendCommandsData)

                socket.send(xml)
                receivedData = socket.receive()

                if (receivedData == "") return result

                try {
                    result = serializer.deserialize(receivedData)
                } catch (e: UnrecognizedPropertyException) {
                    try {
                        commandsList = serializer.deserialize(receivedData)
                        input.outMsg("Write command again\n")
                    } catch (e: UnrecognizedPropertyException) {
                        input.outMsg("Wrong data\n")
                    }
                } catch (e: JsonParseException) {
                    input.outMsg("Wrong data\n")
                }


                input.outMsg(result.getMessage())

            } catch ( e: NumberFormatException ) {
                input.outMsg("Wrong data\n")
            } catch ( e: NullPointerException ) {
                input.outMsg("Not all data entered\n")
            }
        }
        if (result.getExit() == true) return result

        return result
    }

    fun getResult() = result
}