package controller

import tornadofx.Controller
import tornadofx.observable
import view.ChatroomView

/**
 * @Author: LethalLeonard
 */

class ChatroomController: Controller(){
    val messagesList = mutableListOf<String>().observable()
    val users = mutableListOf<String>().observable()
}
