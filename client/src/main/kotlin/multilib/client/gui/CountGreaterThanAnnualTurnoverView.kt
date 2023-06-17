package multilib.client.gui

import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import tools.CommandProcessor
import tornadofx.*

class CountGreaterThanAnnualTurnoverView : View("Count greater than annual turnover") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private var annualTurnoverField: TextField by singleAssign()

    override val root = form {
        fieldset("Count greater than annual turnover") {
            field("Annual turnover") {
                annualTurnoverField = textfield()
            }
            button("Count") {
                useMaxWidth = true
                action {
                    try {
                        input = InputFile("count_greater_than_annual_turnover ${annualTurnoverField.text}")
                        commandProcessor.process(input)
                        find<ResultFragment>().openModal()
                        annualTurnoverField.clear()
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
                annualTurnoverField.clear()
                replaceWith<HomeView>()
            }
        }
    }
    override fun onDock() {
        primaryStage.width = 280.0
        primaryStage.height = 185.0
    }
}
