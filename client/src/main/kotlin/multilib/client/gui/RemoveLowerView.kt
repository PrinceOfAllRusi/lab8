package multilib.client.gui

import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import tools.CommandProcessor
import tornadofx.*

class RemoveLowerView : View("Remove lower") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private var lengthField: TextField by singleAssign()

    override val root = form {
        fieldset("Remove lower") {
            field("Length") {
                lengthField = textfield()
            }
            button("Remove") {
                useMaxWidth = true
                action {
                    try {
                        input = InputFile("remove_lower ${lengthField.text}")
                        commandProcessor.process(input)
                        find<ResultFragment>().openModal()
                        lengthField.clear()
                        replaceWith<HomeView>()
                    } catch (e: NoSuchElementException) {
                        commandProcessor.getResult().setMessage("Invalid data")
                        find<ResultFragment>().openModal()
                    }

                }
                shortcut("Enter")
            }
        }
        button("Back") {
            action {
                lengthField.clear()
                replaceWith<HomeView>()
            }
        }
    }
    override fun onDock() {
        primaryStage.x = 650.0
        primaryStage.y = 400.0
        primaryStage.width = 230.0
        primaryStage.height = 185.0
    }
}
