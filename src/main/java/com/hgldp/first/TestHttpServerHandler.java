package com.hgldp.first;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) throws Exception {

        System.out.println("请求方法名:"+httpRequest.method().name());
        ByteBuf content = Unpooled.copiedBuffer("Hello world", CharsetUtil.UTF_8);

        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,content);

        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");

        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

        channelHandlerContext.writeAndFlush(response);


        ByteBufAllocator bufAllocator = channelHandlerContext.alloc();
        bufAllocator.buffer();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive被调用了");
        super.channelActive(ctx);
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegisterd被调用了");
        super.channelRegistered(ctx);
    }


    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered被调用了");
        super.channelUnregistered(ctx);
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive被调用了");
        super.channelInactive(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded被调用了");
        super.handlerAdded(ctx);
    }


    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemove被调用了");
        super.handlerRemoved(ctx);
    }
}
