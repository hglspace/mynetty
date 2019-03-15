package com.hgldp.seven;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-15 16:24
 **/
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                                 .setName("hgl")
                                 .setAge(18)
                                 .setAddress("shpd")
                                 .build();

        ctx.channel().writeAndFlush(person);//必须传送对象，因为客户端编码了，把对象转换成字节数组了
    }
}
