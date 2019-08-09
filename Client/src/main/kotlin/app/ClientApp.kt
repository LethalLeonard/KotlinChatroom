package app

import handler.ClientHandler
import io.netty.bootstrap.Bootstrap
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.App
import tornadofx.UIComponent
import view.LoginView
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import java.net.InetSocketAddress
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.EventLoopGroup
import io.netty.channel.socket.SocketChannel


/**
 * @Author: LethalLeonard
 */

class ClientApp : App(LoginView::class) {

    override fun init() {
        super.init()
        Launcher.startNetty()
    }

    override fun start(stage: Stage) {
        stage.minHeight = 200.0
        stage.minWidth = 400.0
        super.start(stage)
    }

    override fun createPrimaryScene(view: UIComponent) = super.createPrimaryScene(view).apply {
        fill = Color.valueOf("#EDEDED")
    }

}