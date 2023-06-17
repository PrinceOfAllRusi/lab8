package multilib.client.gui

import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import multilib.utilities.result.Result
import tools.CommandProcessor
import tornadofx.*

class LoginView : View("Login") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private var result = Result()
    private var loginField: TextField by singleAssign()
    private var passwordField: TextField by singleAssign()

    override val root = vbox {
        if (commandProcessor.getCommandsList().getMapCommands().isEmpty()) {
            commandProcessor.getAllInfo()
        }

        form {
            fieldset("Personal Info") {
                field("Login") {
                    loginField = textfield()
                }
                field("Password") {
                    passwordField = passwordfield() {
                        requestFocus()
                    }
                }
                button("Login") {
                    useMaxWidth = true
                    action {
                        try {
                            input = InputFile("log_in\n${loginField.text}\n${passwordField.text}")
                            result = commandProcessor.process(input)
                            if (result.getToken().getTokenName() != "") {
                                replaceWith<HomeView>()
                            } else {
                                find<ResultFragment>().openModal()
                            }
                        } catch (e: NoSuchElementException) {
                            commandProcessor.getResult().setMessage("This user does not exist")
                            find<ResultFragment>().openModal()
                        }
                    }
                    shortcut("Enter")
                }
                button("Registration") {
                    useMaxWidth = true
                    action {
                        replaceWith<RegistrationView>()
                    }
                }
            }
        }
    }
    override fun onDock() {
        primaryStage.width = 230.0
        primaryStage.height = 210.0
    }
}