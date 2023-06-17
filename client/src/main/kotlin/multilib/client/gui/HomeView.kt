package multilib.client.gui

import multilib.utilities.input.InputFile
import multilib.utilities.result.Result
import tools.CommandProcessor
import tornadofx.*

class HomeView : View("Home") {
    private val commandProcessor: CommandProcessor by inject()
    private var input = InputFile("")
    private var organizations = ""

    override val root = borderpane {
        organizations = commandProcessor.process(InputFile("show")).getMessage()
        left = gridpane {
            row {
                vbox {
                    spacing = 5.0
                    button("Help") {
                        action {
                            input = InputFile("help")
                            commandProcessor.process(input)
                            find<ResultFragment>().openModal()
                        }
                    }
                    button("Info") {
                        action {
                            input = InputFile("info")
                            commandProcessor.process(input)
                            find<ResultFragment>().openModal()
                        }
                    }
                    button("Add") {
                        action {
                            replaceWith<AddView>()
                        }
                    }
                    button("Update") {
                        action {
                            replaceWith<UpdateView>()
                        }
                    }
                    button("Remove by id") {
                        action {
                            replaceWith<RemoveByIdView>()
                        }
                    }
                    button("Insert at") {
                        action {
                            replaceWith<InsertAtView>()
                        }
                    }
                    button("Remove at") {
                        action {
                            replaceWith<RemoveAtView>()
                        }
                    }
                    button("Remove lower") {
                        action {
                            replaceWith<RemoveLowerView>()
                        }
                    }
                    button("Remove all by employees count") {
                        action {
                            replaceWith<RemoveAllByEmployeesCountView>()
                        }
                    }
                    button("Count greater than annual turnover") {
                        action {
                            replaceWith<CountGreaterThanAnnualTurnoverView>()
                        }
                    }
                    button("Filter starts with name") {
                        action {
                            replaceWith<FilterStartsWithNameView>()
                        }
                    }
                    button("Logout") {
                        action {
                            input = InputFile("save")
                            commandProcessor.process(input)
                            input = InputFile("log_out")
                            commandProcessor.process(input)
                            find<ResultFragment>().openModal()
                            replaceWith<LoginView>()
                        }
                    }
                }
                gridpaneConstraints {
                    marginTop = 10.0
                    marginBottom = 10.0
                    marginLeft = 10.0
                    marginRight = 20.0
                }
            }
        }
        center = gridpane {
            row {
                scrollpane {
                    text(organizations) {
                        useMaxWidth = true
                        style {
                            fontSize = 16.px
                        }
                    }
                    gridpaneConstraints {
                        marginTop = 0.0
                        marginBottom = 10.0
                        marginLeft = 10.0
                        marginRight = 0.0
                    }
                }
            }
        }
    }
    init {
        reloadViewsOnFocus()
    }
    override fun onDock() {
        primaryStage.width = 712.0
        primaryStage.height = 600.0
    }
}
