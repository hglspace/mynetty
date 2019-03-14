package com.hgldp.four;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-14 16:24
 **/
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        /*
        * 服务器端 ：读不到消息、没有写消息、两者之一没有
        * 读:服务器端没有读到消息
        * 写：服务器端没有往客户端写消息
        *
        * 因为此案例 IdleStateHandler配置到服务端了，所以是以服务端这边来判断
        *
        * */
        pipeline.addLast(new IdleStateHandler(0,8,10, TimeUnit.SECONDS));

        pipeline.addLast(new MyServerHandler());
    }
}
