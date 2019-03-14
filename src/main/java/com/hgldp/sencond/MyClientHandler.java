package com.hgldp.sencond;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-14 14:08
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress() + ","+ s);

        channelHandlerContext.writeAndFlush("from client:"+ System.nanoTime());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
