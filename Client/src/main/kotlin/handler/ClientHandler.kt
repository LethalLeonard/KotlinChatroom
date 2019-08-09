package handler

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.util.CharsetUtil

/**
 * @Author: LethalLeonard
 */

class ClientHandler : SimpleChannelInboundHandler<Any>() {
    override fun channelRead0(ctx: ChannelHandlerContext?, msg: Any?) {
        System.out.println("Client received: " + msg.toString())
    }

    override fun channelActive(channelHandlerContext: ChannelHandlerContext)
    {
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks!", CharsetUtil.UTF_8))
    }

    override fun exceptionCaught(channelHandlerContext: ChannelHandlerContext, cause: Throwable)
    {
        cause.printStackTrace();
        channelHandlerContext.close();
    }
}