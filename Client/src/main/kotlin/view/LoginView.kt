package view
import controller.ChatroomController
import controller.LoginController
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.input.KeyCode
import tornadofx.*

/**
 * @Author: LethalLeonard
 */
 
class LoginView: View() {
    val model = ViewModel()
    val username = model.bind { SimpleStringProperty() }
    val password = model.bind { SimpleStringProperty() }

    val controller: LoginController by inject()

    override val root = group {
        form{
            fieldset(labelPosition = Orientation.VERTICAL) {
                field("Username") {
                    val user = textfield()
                    user.bind(username)
                    user.required()
                }
                field("Password") {
                    val pw = passwordfield()
                    pw.bind(password)
                    pw.required()
                    setOnKeyPressed {
                        if(it.code == KeyCode.ENTER)
                            controller.login(username.value, password.value)
                    }
                }
                button("Log in") {
                    action {
                        controller.login(username.value, password.value)
                    }
                }
                label(controller.statusProperty)
            }
        }
    }
}