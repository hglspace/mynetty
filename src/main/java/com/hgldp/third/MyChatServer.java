package com.hgldp.third;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-14 14:40
 **/
@SuppressWarnings("all")
public class MyChatServer {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new MyChatServerInitializer());
        try{
            ChannelFuture future = bootstrap.bind(9999).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
