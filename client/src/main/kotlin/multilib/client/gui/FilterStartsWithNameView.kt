package multilib.client.gui

import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import tools.CommandProcessor
import tornadofx.*

class FilterStartsWithNameView : View("Filter starts with name") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private var nameField: TextField by singleAssign()

    override val root = form {
        fieldset("Filter starts with name") {
            field("Name") {
                nameField = textfield()
            }
            button("Filter") {
                useMaxWidth = true
                action {
                    try {
                        input = InputFile("filter_starts_with_name ${nameField.text}")
                        commandProcessor.process(input)
                        find<ResultFragment>().openModal()
                        nameField.clear()
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
                nameField.clear()
                replaceWith<HomeView>()
            }
        }
    }
    override fun onDock() {
        primaryStage.width = 210.0
        primaryStage.height = 185.0
    }
}
