package com.hgldp.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-17 20:27
 **/
public class CopyFile {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("input.txt");
        FileOutputStream fos = new FileOutputStream("output.txt");

        FileChannel inChannel = fis.getChannel();

        FileChannel outChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(true){
            buffer.clear();//注释掉这一行会出问题
            int read = inChannel.read(buffer);
            if(read == -1){
                break;
            }
            buffer.flip();

            outChannel.write(buffer);
        }

        inChannel.close();
        outChannel.close();

    }
}
