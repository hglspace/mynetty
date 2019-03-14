package com.hgldp.third;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-14 15:15
 **/
public class MyChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

        System.out.println(s);
    }
}
