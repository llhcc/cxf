package com.llh.awt;

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
import io.netty.util.internal.StringUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.llh.netty.BaseConfig;
import com.llh.netty.IMMessage;
import com.llh.netty.MsgPackDecode;
import com.llh.netty.MsgPackEncode;
import com.llh.netty.ServerHandler;

public class Server_  implements Runnable,BaseConfig{
	
	private static JTextField msgInput;
	
	private static JTextArea  contentArea;
	
	private static BlockingQueue<String> sendBuffer;
	
	private static ServerHandler serverHandler = new ServerHandler();
	
	static int toID = 1;
    static IMMessage message = new IMMessage(APP_IM,CLIENT_VERSION,SERVER_ID,TYPE_MSG_TEXT,toID,EMPTY_MSG);

	public static void main(String[] args) {
		JFrame frame = new JFrame("服务端");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JLabel label = new JLabel("消息内容：");
		contentArea = new JTextArea(10,40);
		contentArea.setSelectedTextColor(Color.RED);
		contentArea.setLineWrap(true);        //激活自动换行功能 
		contentArea.setWrapStyleWord(true);            // 激活断行不断字功能
		
		panel1.add(label,BorderLayout.NORTH);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(new JScrollPane(contentArea),BorderLayout.CENTER);
		panel1.add(panel2,BorderLayout.CENTER);
		panel.add(panel1,BorderLayout.CENTER);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		JLabel label4 = new JLabel("输入消息：");
		msgInput = new JTextField("请在此输入消息",20);
		JButton  sendButton = new JButton("发送");
		panel3.add(label4);
		panel3.add(msgInput);
		panel3.add(sendButton);
		panel.add(panel3,BorderLayout.SOUTH);
		
		frame.add(panel);
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Integer SEND_BUFFER_SIZE = 1024;
        sendBuffer = new LinkedBlockingQueue<String>(SEND_BUFFER_SIZE);
        
        
        
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	String msg = msgInput.getText().toString();
                	message.setMsg(msg);
                	serverHandler.sendMsg(message);
                    sendBuffer.put(msg);
                   // System.out.println("sendBuffer=" + sendBuffer.size());
                    String cStr = contentArea.getText();
                    if(!StringUtil.isNullOrEmpty(cStr)){
                    	cStr = cStr + "\r\n";
                    }
                    contentArea.setText(cStr +  msgInput.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        try {
			new Server_().start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void start() throws IOException{
        new Thread(this).start();
        runServerCMD();
    }
    /**启动服务端控制台
     * @throws IOException */
    private void runServerCMD() throws IOException {
        //服务端主动推送消息
        /*int toID = 1;
        IMMessage message = new IMMessage(APP_IM,CLIENT_VERSION,SERVER_ID,TYPE_MSG_TEXT,toID,EMPTY_MSG);*/
        /*@SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);*/
        /*do{
            message.setMsg(msgInput.getText().toString());
        }
        while (serverHandler.sendMsg(message));*/
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
