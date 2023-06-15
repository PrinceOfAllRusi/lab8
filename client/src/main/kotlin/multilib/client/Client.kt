package multilib.client

import multilib.client.gui.LoginView
import multilib.client.modul.SingletonObject
import multilib.utilities.input.*
import org.koin.core.context.GlobalContext.startKoin
import tools.CommandProcessor
import tornadofx.*

fun main() {

    startKoin {
        modules(SingletonObject.mod)
    }
    val input= InputSystem()

    val commandProcessor: CommandProcessor = CommandProcessor()
    commandProcessor.process(input)

//    launch<Client>()

}

class Client : App(LoginView::class)
{

}