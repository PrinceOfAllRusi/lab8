package multilib.client.gui

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import tools.CommandProcessor
import tornadofx.*
import java.lang.NumberFormatException

class InsertAtView : View("My View") {

    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private val s = StringBuilder()
    private var positionField: TextField by singleAssign()
    private var nameField: TextField by singleAssign()
    private var xField: TextField by singleAssign()
    private var yField: TextField by singleAssign()
    private var annualTurnoverField: TextField by singleAssign()
    private var employeesCountField: TextField by singleAssign()
    private val types = FXCollections.observableArrayList(
        "COMMERCIAL", "PUBLIC", "GOVERNMENT", "TRUST", "PRIVATE_LIMITED_COMPANY"
    )
    private val selectedType = SimpleStringProperty()
    private var streetField: TextField by singleAssign()
    private var zipCodeField: TextField by singleAssign()
    override val root = vbox {
        form {
            fieldset("Organization Info") {
                field("Position") {
                    positionField = textfield()
                }
                field("Name") {
                    nameField = textfield()
                }
                field("Annual Turnover") {
                    annualTurnoverField = textfield()
                }
                field("Employees Count") {
                    employeesCountField = textfield()
                }
                field("X coordinates") {
                    xField = textfield()
                }
                field("Y coordinates") {
                    yField = textfield()
                }
                field("Type") {
                    combobox(selectedType, types)
                }
                field("Street") {
                    streetField = textfield()
                }
                field("Zip Code") {
                    zipCodeField = textfield()
                }
                button("Insert") {
                    useMaxWidth = true
                    action {
                        try {
                            if (nameField.text.isBlank()) {
                                commandProcessor.getResult().setMessage("Name can not be empty")
                                find<ResultFragment>().openModal()
                            } else {
                                positionField.text.toInt()
                                s.clear()
                                s.append("insert_at\n")
                                    .append("${positionField.text}\n").append("${nameField.text}\n")
                                    .append("${annualTurnoverField.text}\n")
                                    .append("${employeesCountField.text}\n").append("${xField.text}\n")
                                    .append("${yField.text}\n").append("${selectedType.get()}\n")
                                    .append("${streetField.text}\n").append("${zipCodeField.text}\n")
                                input = InputFile(s.toString())
                                commandProcessor.process(input)
                                println(s.toString())
                                find<ResultFragment>().openModal()
                                val list = listOf<TextField>(positionField, nameField, annualTurnoverField,
                                    employeesCountField, xField, yField, streetField, zipCodeField)
                                for (field in list) {
                                    field.clear()
                                }
                                selectedType.set("")
                                replaceWith<HomeView>()
                            }
                        } catch (e: NoSuchElementException) {
                            commandProcessor.getResult().setMessage("Invalid data")
                            find<ResultFragment>().openModal()
                        } catch (e: NumberFormatException) {
                            commandProcessor.getResult().setMessage("Invalid data for Position")
                            find<ResultFragment>().openModal()
                        }
                    }
                    shortcut("Enter")
                }
                button("Back") {
                    action {
                        val list = listOf<TextField>(positionField, nameField, annualTurnoverField,
                            employeesCountField, xField, yField, streetField, zipCodeField)
                        for (field in list) {
                            field.clear()
                        }
                        selectedType.set("")
                        replaceWith<HomeView>()
                    }
                }
            }
        }
    }
    override fun onDock() {
        primaryStage.width = 340.0
        primaryStage.height = 460.0
    }
}