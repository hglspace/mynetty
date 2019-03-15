package com.hgldp.seven;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-15 16:22
 **/
@SuppressWarnings("all")
public class TestClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufDecoder(MyData.MyMessage.getDefaultInstance()));

        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());

        pipeline.addLast(new ProtobufEncoder());


        pipeline.addLast(new TestClientHandler());
    }
}
