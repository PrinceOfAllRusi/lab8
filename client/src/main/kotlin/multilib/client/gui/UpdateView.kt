package multilib.client.gui

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import tools.CommandProcessor
import tornadofx.*
import java.lang.NumberFormatException

class UpdateView : View("Update organization") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private val s = StringBuilder()
    private var idField: TextField by singleAssign()
    private var nameField: TextField by singleAssign()
    private var xField: TextField by singleAssign()
    private var yField: TextField by singleAssign()
    private var annualTurnoverField: TextField by singleAssign()
    private var employeesCountField: TextField by singleAssign()
    private val types = FXCollections.observableArrayList(
        "COMMERCIAL", "PUBLIC", "GOVERNMENT", "TRUST", "PRIVATE_LIMITED_COMPANY"
    )
    private val selectedType = SimpleStringProperty("")
    private var streetField: TextField by singleAssign()
    private var zipCodeField: TextField by singleAssign()
    override val root = vbox {
        form {
            fieldset("Organization Info") {
                field("Id") {
                    idField = textfield()
                }
                field("Name") {
                    nameField = textfield("")
                }
                field("Annual Turnover") {
                    annualTurnoverField = textfield("")
                }
                field("Employees Count") {
                    employeesCountField = textfield("")
                }
                field("X coordinates") {
                    xField = textfield("")
                }
                field("Y coordinates") {
                    yField = textfield("")
                }
                field("Type") {
                    combobox(selectedType, types)
                }
                field("Street") {
                    streetField = textfield("")
                }
                field("Zip Code") {
                    zipCodeField = textfield("")
                }
                button("Update") {
                    useMaxWidth = true
                    action {
                        try {
                            idField.text.toInt()
                            val list = listOf<TextField>(nameField, xField,
                                yField, annualTurnoverField, employeesCountField, streetField, zipCodeField)
                            for (field in list) {
                                if (field.text.isBlank()) {
                                    field.text = "."
                                }
                            }
                            if (selectedType.get() != null) {
                                if (selectedType.get().isBlank()) selectedType.set(".")
                            }
                            s.clear()
                            s.append("update\n")
                                .append("${idField.text} \n").append("${nameField.text}\n")
                                .append("${annualTurnoverField.text}\n")
                                .append("${employeesCountField.text}\n").append("${xField.text}\n")
                                .append("${yField.text}\n").append("${selectedType.get()}\n")
                                .append("${streetField.text}\n").append("${zipCodeField.text}\n")
                            input = InputFile(s.toString())
                            commandProcessor.process(input)
                            find<ResultFragment>().openModal()
                            idField.clear()
                            for (field in list) {
                                field.clear()
                            }
                            selectedType.set("")
                            replaceWith<HomeView>()
                        } catch (e: NumberFormatException) {
                            commandProcessor.getResult().setMessage("Invalid data for Id")
                            find<ResultFragment>().openModal()
                        }
                    }
                    shortcut("Enter")
                }
                button("Back") {
                    action {
                        val list = listOf<TextField>(idField, nameField, xField,
                            yField, annualTurnoverField, employeesCountField, streetField, zipCodeField)
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
        primaryStage.x = 550.0
        primaryStage.y = 200.0
        primaryStage.width = 340.0
        primaryStage.height = 460.0
    }
}
