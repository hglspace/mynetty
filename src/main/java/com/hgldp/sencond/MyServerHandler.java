package com.hgldp.sencond;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-14 13:54
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress()+","+s);
        channelHandlerContext.writeAndFlush("from server:"+ UUID.randomUUID());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接建立");
        ctx.writeAndFlush("hello,Server");
    }
}
