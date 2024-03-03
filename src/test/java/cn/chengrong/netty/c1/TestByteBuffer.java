package cn.chengrong.netty.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                // 从 channel 中读，向 buffer 中写入
                int len = channel.read(buffer);
                log.debug("读到的字节：{}", len);
                if (len == -1) {
                    break;
                }
                buffer.flip(); // 切换至读模式
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
                buffer.clear(); // 切换到写模式
            }
        } catch (IOException e) {
        }
    }
}
