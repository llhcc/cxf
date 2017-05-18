package com.llh.awt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server {
	
	private static JTextField msgInput;
	
	private static BlockingQueue<String> sendBuffer;

	public static void main(String[] args) {
		JFrame frame = new JFrame("服务端");
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JLabel label = new JLabel("消息内容：");
		JTextArea  contentArea = new JTextArea(10,40);
		panel1.add(label,BorderLayout.NORTH);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(contentArea,BorderLayout.CENTER);
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
                    sendBuffer.put(msgInput.getText().toString());
                    System.out.println("sendBuffer=" + sendBuffer.size());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        
	}

}
