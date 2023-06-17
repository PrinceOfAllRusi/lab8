package multilib.client.gui

import tools.CommandProcessor
import tornadofx.*

class ResultFragment: Fragment() {
    private val commandProcessor: CommandProcessor by inject()
    private var message = commandProcessor.getResult().getMessage()
    override val root = gridpane {
        row {
            if (message.isBlank()) {
                message = "No result"
            }
            label (message) {
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
