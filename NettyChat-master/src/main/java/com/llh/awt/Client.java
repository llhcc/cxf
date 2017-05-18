package com.llh.awt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client {

	public static void main(String[] args) {
		JFrame frame = new JFrame("客户端");
		JPanel fPanel = new JPanel();
		fPanel.setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("服务器地址：");
		JTextField text1 = new JTextField(10);
		JLabel label2 = new JLabel("服务器端口：");
		JTextField text2 = new JTextField(4);
		JButton btn = new JButton("连接");
		panel1.setLayout(new FlowLayout());
		panel1.add(label1);panel1.add(new JScrollPane(text1));
		panel1.add(label2);panel1.add(new JScrollPane(text2));
		panel1.add(btn);
		fPanel.add(panel1,BorderLayout.NORTH);
		
		JPanel messagePanel = new JPanel();
		JPanel c_panel = new JPanel();
		JLabel label3 = new JLabel("消息内容：");
		JTextArea msgContent = new JTextArea(10,40);
		messagePanel.setLayout(new BorderLayout());
		c_panel.add(label3,BorderLayout.NORTH);
		c_panel.add(msgContent,BorderLayout.CENTER);
		messagePanel.add(c_panel,BorderLayout.CENTER);
		fPanel.add(messagePanel,BorderLayout.CENTER);
		
		JPanel msgPanel = new JPanel();
		msgPanel.setLayout(new FlowLayout());
		JPanel m_panel = new JPanel();
		m_panel.setLayout(new FlowLayout());
		JLabel label4 = new JLabel("输入消息：");
		JTextField msgInput = new JTextField("请在此输入消息",10);
		JButton  sendButton = new JButton("发送");
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
	}

}
