package com.hgldp.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-19 09:27
 **/
public class NioTest1 {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(9999);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while(true){
            int bytesRead = 0;

            while(bytesRead < messageLength){

                long r = socketChannel.read(buffers);
                bytesRead += r;
                System.out.println("bytesRead:"+bytesRead);
                Arrays.asList(buffers).stream()
                        .map(byteBuffer -> "position:"+byteBuffer.position() + ",limit:"+byteBuffer.limit()).forEach(System.out::println);
            }

            Arrays.asList(buffers).forEach(byteBuffer -> byteBuffer.flip());

            long byteWrite = 0;
            while(byteWrite < messageLength){
                long r = socketChannel.write(buffers);
                byteWrite += r;

            }

            Arrays.asList(buffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println("byteRead:"+bytesRead + ",byteWrite:"+byteWrite + ",messageLength:"+messageLength);
        }
    }
}
