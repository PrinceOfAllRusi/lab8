package multilib.client.gui

import javafx.scene.control.TextField
import multilib.utilities.input.InputFile
import multilib.utilities.result.Result
import tools.CommandProcessor
import tornadofx.*

class RegistrationView : View("Registration") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private var result = Result()
    private var loginField: TextField by singleAssign()
    private var passwordField: TextField by singleAssign()

    override val root = vbox {
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
                button("Registration") {
                    useMaxWidth = true
                    action {
                        try {
                            input = InputFile("register\n${loginField.text}\n${passwordField.text}")
                            result = commandProcessor.process(input)
                            commandProcessor.getResult().setMessage("You are registered")
                            find<ResultFragment>().openModal()
                            replaceWith<LoginView>()
                        } catch (e: NoSuchElementException) {
                            commandProcessor.getResult().setMessage("Invalid data")
                            find<ResultFragment>().openModal()
                        }
                    }
                    shortcut("Enter")
                }
                button("Back") {
                    useMaxWidth = true
                    action {
                        replaceWith<LoginView>()
                    }
                }
            }
        }
    }
}
