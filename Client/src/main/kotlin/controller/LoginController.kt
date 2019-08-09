package controller

import javafx.beans.property.SimpleStringProperty
import session.Session
import tornadofx.Controller
import tornadofx.runLater
import view.ChatroomView
import view.LoginView

/**
 * @Author: LethalLeonard
 */

class LoginController : Controller() {
    val statusProperty = SimpleStringProperty("")
    val chatroomController: ChatroomController by inject()

    fun login(username: String, password: String) {
        runLater { statusProperty.value = "Logging in" }
        Session.username = username
        find(LoginView::class).replaceWith(ChatroomView::class, sizeToScene = true, centerOnScreen = true)
        chatroomController.messagesList.add("$username has joined the chatroom!")
        chatroomController.users.add(username)
    }
}
