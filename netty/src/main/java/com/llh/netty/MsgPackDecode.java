package com.llh.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;
import org.msgpack.MessagePack;
/**
 * 解码工具
 */
public class MsgPackDecode extends MessageToMessageDecoder<ByteBuf>{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final int length = msg.readableBytes();
        final byte[] array = new byte[length];
        msg.getBytes(msg.readerIndex(), array, 0, length);
        out.add(new MessagePack().read(array,IMMessage.class));
    }
}
