package view

import controller.ChatroomController
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ListView
import javafx.scene.input.KeyCode
import session.Session
import tornadofx.*

class ChatroomView : View("Chatroom - ${Session.username}") {

    val model = ViewModel()
    val currentMessage = model.bind { SimpleStringProperty() }
    val controller: ChatroomController by inject()
    lateinit var messageListContainer: ListView<String>

    override val root = borderpane {
        center {
            messageListContainer = listview(controller.messagesList) {
                minWidth = 200.0
            }
        }
        right {
            //list of connected users
            listview(controller.users) {
                maxWidth = 100.0
            }
        }
        bottom {
            //creates child borderpane to allow for proper scaling
            borderpane {
                center {
                    //Chat message field
                    textfield("") {
                        bind(currentMessage)
                        //if user presses enter it submits the message to be sent
                        setOnKeyReleased {
                            if (it.code == KeyCode.ENTER)
                                sendMessage()
                        }
                    }
                }
                right {
                    prefWidth = 70.0
                    button("Send") {
                        action {
                            sendMessage()
                        }
                    }
                }
            }
        }
    }

    private fun sendMessage() {
        var cMessage = currentMessage.value
        if (cMessage.isNullOrEmpty())
            return
        while (true) {
            if (cMessage.startsWith("\n") || cMessage.startsWith(" "))
                cMessage = cMessage.substring(1)
            else
                break
        }
        if (cMessage.isEmpty())
            return
        controller.messagesList.add(Session.username + ": " + currentMessage.value)
        currentMessage.value = ""
    }
}
