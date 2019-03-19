package com.hgldp.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @program:mynetty
 * @description:客户端
 * @author:hgl
 * @crate:2019-03-19 15:54
 **/
public class NioTest4 {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));

        while (true){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for(SelectionKey selectionKey : keys){
                if(selectionKey.isConnectable()){ //可连接的

                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    if(client.isConnectionPending()){ //正在连接
                        client.finishConnect();  //完成连接

                        socketChannel.register(selector,SelectionKey.OP_READ);
                        final ByteBuffer wirterBuffer = ByteBuffer.allocate(1024);
                        wirterBuffer.put((System.nanoTime() + ":"+ "连接成功").getBytes());
                        wirterBuffer.flip();
                        client.write(wirterBuffer);
                        ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());

                        executorService.submit(() ->{
                            while (true){
                                wirterBuffer.clear();
                                InputStreamReader isr = new InputStreamReader(System.in);
                                BufferedReader br = new BufferedReader(isr);
                                String sendMessage = br.readLine();
                                wirterBuffer.put(sendMessage.getBytes());
                                wirterBuffer.flip();
                                client.write(wirterBuffer);
                            }
                        });
                    }
                }else if(selectionKey.isReadable()){
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = client.read(buffer);
                    if(count > 0){
                        String message = new String(buffer.array(),0,count);
                        System.out.println(message);
                    }
                }
            }
            keys.clear();
        }
    }
}
