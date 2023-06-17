package multilib.client.gui

import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import tools.CommandProcessor
import tornadofx.*

class RemoveAtView : View("Remove at") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private var positionField: TextField by singleAssign()

    override val root = vbox {
        form {
            fieldset("Remove at") {
                field("Position") {
                    positionField = textfield()
                }
                button("Remove") {
                    useMaxWidth = true
                    action {
                        try {
                            input = InputFile("remove_at ${positionField.text}")
                            commandProcessor.process(input)
                            find<ResultFragment>().openModal()
                            positionField.clear()
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
                    positionField.clear()
                    replaceWith<HomeView>()
                }
            }
        }
    }
    override fun onDock() {
        primaryStage.width = 230.0
        primaryStage.height = 185.0
    }
}
