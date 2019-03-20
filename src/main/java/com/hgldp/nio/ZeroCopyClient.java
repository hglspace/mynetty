package com.hgldp.nio;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-20 09:48
 **/
public class ZeroCopyClient {

    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",9999));
        socketChannel.configureBlocking(true);//配置成阻塞的
        FileChannel fileChannel = new FileInputStream("copy.txt").getChannel();
        //使用transferTo方法，从一个通道把数据读到另外一个通道中
        //如果两个通道中有一个是FileChannel，可以直接将数据从一个channel传输到另外一个通道中，效率比简单的循环高
        long transfCount = fileChannel.transferTo(0,fileChannel.size(),fileChannel);
        fileChannel.close();
    }
}
