package com.hgldp.seven;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-15 16:20
 **/
public class TestServerHandler extends SimpleChannelInboundHandler<MyData.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyData.MyMessage msg) throws Exception {
        MyData.MyMessage.DataType dataType = msg.getDataType();

        if(dataType == MyData.MyMessage.DataType.PersonType){
            MyData.Person person = msg.getPerson();
            System.out.println(person);
        }else if(dataType == MyData.MyMessage.DataType.DogType){

            MyData.Dog dog = msg.getDog();
            System.out.println(dog);
        }else {

            MyData.Cat cat = msg.getCat();
            System.out.println(cat);
        }
    }
}
