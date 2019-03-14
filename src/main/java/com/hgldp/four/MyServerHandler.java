package com.hgldp.four;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-14 16:25
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){

            IdleStateEvent event = (IdleStateEvent)evt;//这个事件是IdleStateHandler传过来的
            String eventType = null;
            switch (event.state()){
                case WRITER_IDLE:eventType="写空闲";break;
                case READER_IDLE:eventType="读空闲";break;
                case ALL_IDLE:eventType = "读写空闲";break;
            }

            System.out.println(ctx.channel().remoteAddress()+ "超时事件:"+eventType);
            ctx.channel().close();
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //ctx.writeAndFlush("server send message".getBytes());
    }
}
