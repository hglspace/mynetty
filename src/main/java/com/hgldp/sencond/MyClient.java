package com.hgldp.sencond;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-14 14:01
 **/
public class MyClient {

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer());

            ChannelFuture future = bootstrap.connect("localhost",9999).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
