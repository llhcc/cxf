package com.llh.awt;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.internal.StringUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
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
import com.llh.netty.ClientHandler;
import com.llh.netty.IMMessage;
import com.llh.netty.MsgPackDecode;
import com.llh.netty.MsgPackEncode;

public class Client_  implements Runnable,BaseConfig{

	public static int UID = 8888;
	
    private static ClientHandler clientHandler = new ClientHandler();
    
    private static JTextArea msgContent;
    
    private static JTextField msgInput;
    
    private static JTextField text1;
    
    private static JTextField text2;
    
    private static JButton  sendButton;
    
    private static BlockingQueue<String> sendBuffer;
    
    static IMMessage message = new IMMessage(APP_IM,CLIENT_VERSION,UID,TYPE_MSG_TEXT,UID,EMPTY_MSG);
    
    
	public static void main(String[] args) {
		JFrame frame = new JFrame("客户端");
		JPanel fPanel = new JPanel();
		fPanel.setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("服务器地址：");
		text1 = new JTextField(10);
		JLabel label2 = new JLabel("服务器端口：");
		text2 = new JTextField(4);
		JButton btn = new JButton("连接");
		panel1.setLayout(new FlowLayout());
		panel1.add(label1);panel1.add(new JScrollPane(text1));
		panel1.add(label2);panel1.add(new JScrollPane(text2));
		panel1.add(btn);
		fPanel.add(panel1,BorderLayout.NORTH);
		
		JPanel messagePanel = new JPanel();
		JPanel c_panel = new JPanel();
		JLabel label3 = new JLabel("消息内容：");
		msgContent = new JTextArea(10,40);
		msgContent.setSelectedTextColor(Color.RED);
		msgContent.setLineWrap(true);        //激活自动换行功能 
		msgContent.setWrapStyleWord(true);            // 激活断行不断字功能
		messagePanel.setLayout(new BorderLayout());
		c_panel.add(label3,BorderLayout.NORTH);
		c_panel.add(new JScrollPane(msgContent),BorderLayout.CENTER);
		messagePanel.add(c_panel,BorderLayout.CENTER);
		fPanel.add(messagePanel,BorderLayout.CENTER);
		
		JPanel msgPanel = new JPanel();
		msgPanel.setLayout(new FlowLayout());
		JPanel m_panel = new JPanel();
		m_panel.setLayout(new FlowLayout());
		JLabel label4 = new JLabel("输入消息：");
		msgInput = new JTextField("请在此输入消息",10);
		sendButton = new JButton("发送");
		m_panel.add(label4);
		m_panel.add(msgInput);
		m_panel.add(sendButton);
		msgPanel.add(m_panel);
		fPanel.add(msgPanel,BorderLayout.SOUTH);
		
		
		frame.add(fPanel);
		frame.setSize(600,400);  
        frame.setVisible(true); 
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverIP = text1.getText();
                String serverPort = text2.getText();
                Integer port;
                if (serverIP.isEmpty() || serverPort.isEmpty()) {
                    msgContent.setText(text1.getText());
                    msgContent.setText("The IP or Port is Empty");
                } else {
                    try {
                        port = Integer.valueOf(serverPort);
                        //client.connServer(serverIP, port);
                    } catch (NullPointerException e1) {
                        e1.printStackTrace();
                        msgContent.setText("Connect Server Error");
                    }catch (Exception e2){
                        e2.printStackTrace();
                        msgContent.setText("Invalid Port");
                    }
                }
            }
        });
        Integer SEND_BUFFER_SIZE = 1024;
        sendBuffer = new LinkedBlockingQueue<String>(SEND_BUFFER_SIZE);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //client.sendMsg(msgInput.getText());
               // msgContent.
            	try {
                	String msg = msgInput.getText().toString();
                	message.setMsg(msg);
                	clientHandler.sendMsg(message);
                    sendBuffer.put(msg);
                   // System.out.println("sendBuffer=" + sendBuffer.size());
                    String cStr = msgContent.getText();
                    if(!StringUtil.isNullOrEmpty(cStr)){
                    	cStr = cStr + "\r\n";
                    }
                    msgContent.setText(cStr +  msgInput.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        try {
			new Client_().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 public void start() throws IOException{
	        new Thread(this).start();
	        runServerCMD();
	    }
	 
	 public void sendMsg(IMMessage msg) throws IOException {
	        clientHandler.sendMsg(msg);
	    }
	 
	/**启动客户端控制台*/
    private void runServerCMD() throws IOException {
       // IMMessage message = new IMMessage(APP_IM,CLIENT_VERSION,UID,TYPE_MSG_TEXT,UID,EMPTY_MSG);
       /* @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);*/
        /*do{
            message.setMsg(msgInput.getText());
        }
        while (clientHandler.sendMsg(message));*/
    }
	
	@Override
    public void run() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65536, 0, 2, 0, 2));
                    ch.pipeline().addLast("msgpack decoder",new MsgPackDecode());
                    ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                    ch.pipeline().addLast("msgpack encoder",new MsgPackEncode());
                    ch.pipeline().addLast(clientHandler);
                }
            });
            ChannelFuture f = b.connect(SERVER_HOST, SERVER_PORT).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
