package multilib.client.gui

import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import tools.CommandProcessor
import tornadofx.*

class RemoveByIdView : View("Remove by id") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private var idField: TextField by singleAssign()

    override val root = vbox {
        form {
            fieldset("Personal Info") {
                field("Id") {
                    idField = textfield()
                }
                button("Remove") {
                    useMaxWidth = true
                    action {
                        try {
                            input = InputFile("remove_by_id ${idField.text}")
                            commandProcessor.process(input)
                            find<ResultFragment>().openModal()
                            idField.clear()
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
                    idField.clear()
                    replaceWith<HomeView>()
                }
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
