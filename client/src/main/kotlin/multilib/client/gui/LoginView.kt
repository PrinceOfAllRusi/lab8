package multilib.client.gui

import javafx.scene.control.TextField
import tornadofx.*

class LoginView : View("Login") {
    var loginField: TextField by singleAssign()
    var passwordField: TextField by singleAssign()

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
                button("Login") {
                    useMaxWidth = true
                    action {
                        replaceWith<HomeView>()
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

//        hbox {
//            label("First Name")
//            firstNameField = textfield()
//        }
//        hbox {
//            label("Last Name")
//            lastNameField = textfield()
//        }
//        button("LOGIN") {
//            useMaxWidth = true
//            action {
//                println("Logging in as ${firstNameField.text} ${lastNameField.text}")
//            }
//            shortcut("Enter")
//        }
//        passwordfield("password123") {
//            requestFocus()
//        }
////        scrollpane {
////        }
//
//        hbox {
//            button("Button 1") {
//                hboxConstraints {
//                    marginRight = 20.0
//                    hGrow = Priority.ALWAYS
//                }
//            }
//            button("Button 2")
//        }
    }
}

