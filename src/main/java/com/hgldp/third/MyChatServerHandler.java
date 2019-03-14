package com.hgldp.third;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Iterator;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-14 14:45
 **/
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

        Channel channel = channelHandlerContext.channel();

        Iterator<Channel> iterator  = channelGroup.iterator();

        while (iterator.hasNext()){

            Channel ch = iterator.next();
            if(ch != channel){
                ch.writeAndFlush(channel.remoteAddress() + "发送的消息："+s + "\n");
            }else {
                ch.writeAndFlush("【自己】" + s + "\n");
            }
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("【服务器】:"+channel.remoteAddress() +" 加入\n");

        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.writeAndFlush("【服务器】:"+channel.remoteAddress() +" 离开\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+ " 上线\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() +" 下线\n");
    }
}
