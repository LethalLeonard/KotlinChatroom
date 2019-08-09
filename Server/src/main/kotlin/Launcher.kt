import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import java.net.InetSocketAddress
import io.netty.util.CharsetUtil
import io.netty.buffer.Unpooled
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelFutureListener





/**
 * @Author: LethalLeonard
 */
 
fun main(){
    val group = NioEventLoopGroup()

    try{
        val bootstrap = ServerBootstrap()
        bootstrap.group(group)
        bootstrap.channel(NioServerSocketChannel::class.java)
        bootstrap.localAddress(InetSocketAddress(9999))

        bootstrap.childHandler(object : ChannelInitializer<SocketChannel>() {
            @Throws(Exception::class)
            override fun initChannel(socketChannel: SocketChannel) {
                socketChannel.pipeline().addLast(HelloServerHandler())
            }
        })
    }catch (e: Exception)
    {

    } finally {
        group.shutdownGracefully().sync()
    }
}

class HelloServerHandler: ChannelInboundHandlerAdapter(){

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val inBuffer = msg as ByteBuf

        val received = inBuffer.toString(CharsetUtil.UTF_8)
        println("Server received: $received")

        ctx.write(Unpooled.copiedBuffer("Hello $received", CharsetUtil.UTF_8))
    }

    @Throws(Exception::class)
    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE)
    }

    @Throws(Exception::class)
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }
}