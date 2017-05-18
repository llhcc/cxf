package com.llh.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import java.io.IOException;
import java.util.Scanner;

public class Server implements Runnable,BaseConfig{
    ServerHandler serverHandler = new ServerHandler();
    public static void main(String[] args) throws IOException{
        new Server().start();
    }
    public void start() throws IOException{
        new Thread(this).start();
        runServerCMD();
    }
    /**启动服务端控制台
     * @throws IOException */
    private void runServerCMD() throws IOException {
        //服务端主动推送消息
        int toID = 1;
        IMMessage message = new IMMessage(
                APP_IM,
                CLIENT_VERSION,
                SERVER_ID,
                TYPE_MSG_TEXT,
                toID,
                EMPTY_MSG);
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        do{
            message.setMsg(scanner.nextLine());
        }
        while (serverHandler.sendMsg(message));
    }
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024)
//                  .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65536, 0, 2, 0, 2));
                            ch.pipeline().addLast("msgpack decoder",new MsgPackDecode());
                            ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                            ch.pipeline().addLast("msgpack encoder",new MsgPackEncode());
                            ch.pipeline().addLast(serverHandler);
                        }
                    }); 
            ChannelFuture f = b.bind(SERVER_PORT).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
