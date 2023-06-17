package multilib.client.gui

import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import tools.CommandProcessor
import tornadofx.*

class RemoveAllByEmployeesCountView : View("Remove all by employees count") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private var employeesCountField: TextField by singleAssign()

    override val root = form {
        fieldset("Remove all by employees count") {
            field("Employees count") {
                employeesCountField = textfield()
            }
            button("Remove") {
                useMaxWidth = true
                action {
                    try {
                        input = InputFile("remove_all_by_employees_count ${employeesCountField.text}")
                        commandProcessor.process(input)
                        find<ResultFragment>().openModal()
                        employeesCountField.clear()
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
                employeesCountField.clear()
                replaceWith<HomeView>()
            }
        }
    }
    override fun onDock() {
        primaryStage.width = 260.0
        primaryStage.height = 185.0
    }
}
