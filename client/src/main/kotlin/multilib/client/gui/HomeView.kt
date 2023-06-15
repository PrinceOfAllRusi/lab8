package multilib.client.gui

import tornadofx.*

class HomeView : View("Home") {
    override val root = borderpane {
        left = vbox {
            button("Help")
            button("Info")
            button("Show")
            button("Add")
            button("Update")
            button("Remove by id")
            button("Insert at")
            button("Remove at")
            button("Remove lower")
            button("Remove all by employees count")
            button("Count greater than annual turnover")
            button("Filter starts with name")
            button("Logout")
        }
        center = gridpane {
            row {
                text("dkjfjkd") {
                    gridpaneConstraints {
                        marginTop = 10.0
                        marginBottom = 10.0
                        marginLeft = 10.0
                        marginRight = 20.0
                    }
                    useMaxWidth = true
                    style {
                        fontSize = 16.px
                    }
                }
            }
        }
    }
}
