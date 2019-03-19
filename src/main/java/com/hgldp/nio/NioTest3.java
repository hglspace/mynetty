package com.hgldp.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/*
 * @program:mynetty
 * @description:服务端
 * @author:hgl
 * @crate:2019-03-19 15:13
 **/
public class NioTest3 {

    private static Map<String,SocketChannel> info = new HashMap<String,SocketChannel>();
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            keys.forEach(selectionKey -> {
                final SocketChannel socketChannel;
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel ssocketChannel = (ServerSocketChannel) selectionKey.channel();
                    try {
                        socketChannel = ssocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                        String key = "[" + UUID.randomUUID().toString() + "]" ;
                        info.put(key,socketChannel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }else if(selectionKey.isReadable()){
                    socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int count = 0;
                    try {
                        count = socketChannel.read(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(count > 0){
                        buffer.flip();
                        Charset charset = Charset.forName("utf-8");
                        String receiveMessage = String.valueOf(charset.decode(buffer).array());
                        System.out.println(socketChannel + ":"+receiveMessage);

                        String sendKey = null;
                        for(Map.Entry<String,SocketChannel> entry:info.entrySet()){
                            if(socketChannel == entry.getValue()){
                                sendKey = entry.getKey();
                                break;
                            }
                        }
                        for(Map.Entry<String,SocketChannel> entry : info.entrySet()){
                            SocketChannel value = entry.getValue();
                            ByteBuffer writerBuffer = ByteBuffer.allocate(1024);
                            writerBuffer.put((sendKey + ":"+receiveMessage).getBytes());
                            writerBuffer.flip();
                            try {
                                value.write(writerBuffer);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            keys.clear();//清理事件key,否则会出错
        }
    }
}
