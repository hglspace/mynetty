package com.hgldp.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/*
 * @program:mynetty
 * @description:
 * @author:hgl
 * @crate:2019-03-19 09:05
 **/
public class NioTestMapper {

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("niotest.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,6);

        /*
        * 直接修改内存（堆外）中的文件，速度很快,操作系统负责把修改写到磁盘
        * */
        mappedByteBuffer.put(0,(byte) 'a');
        mappedByteBuffer.put(3,(byte)'b');

        fileChannel.close();
        randomAccessFile.close();
    }
}
