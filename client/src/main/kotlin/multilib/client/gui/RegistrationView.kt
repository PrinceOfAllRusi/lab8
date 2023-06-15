package multilib.client.gui

import tornadofx.*

class RegistrationView : View("Registration") {
    override val root = vbox {
        form {
            fieldset("Personal Info") {
                field("Login") {
                    textfield()
                }
                field("Password") {
                    passwordfield() {
                        requestFocus()
                    }
                }
                button("Registration") {
                    useMaxWidth = true
                    action {
                        find<MyFragment>().openModal()
                        replaceWith<LoginView>()
                    }
                }
            }
        }
    }
}
class MyFragment: Fragment() {
    override val root = gridpane {
        row {
            label ("You are registered") {
                gridpaneConstraints {
                    marginTop = 10.0
                    marginBottom = 10.0
                    marginLeft = 10.0
                    marginRight = 10.0
                }
                useMaxWidth = true
                style {
                    fontSize = 16.px
                }
            }
        }
    }
}
