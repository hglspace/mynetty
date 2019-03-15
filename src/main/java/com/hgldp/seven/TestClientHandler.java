package com.hgldp.seven;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-15 16:24
 **/
public class TestClientHandler extends SimpleChannelInboundHandler<MyData.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyData.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int random = new Random().nextInt(3);

        MyData.MyMessage myMessage = null;
        MyData.MyMessage.Builder builder = MyData.MyMessage.newBuilder();
        if(random == 0){
            myMessage = builder.setDataType(MyData.MyMessage.DataType.PersonType)
            .setPerson(MyData.Person.newBuilder().setName("zhansan")
            .setAge(12)
            .setAddress("china").build())
            .build();

        }else if(random == 1){

            myMessage = builder.setDataType(MyData.MyMessage.DataType.DogType)
                    .setDog(MyData.Dog.newBuilder().setName("hsq")
                    .setAge(3))
                    .build();
        }else {

            myMessage = builder.setDataType(MyData.MyMessage.DataType.CatType)
                    .setCat(MyData.Cat.newBuilder().setName("xm")
                    .setCity("shpd"))
                    .build();
        }

        ctx.channel().writeAndFlush(myMessage);

    }
}
