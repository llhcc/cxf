package com.llh.awt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Test1 {

	private static JTextArea msgContent;
	
	private static JTextArea msgContent1;
	
	private static String http;
	
	public static void main(String[] args) {
		JFrame f = new JFrame("H5访问地址");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		msgContent = new JTextArea(10,40);
		msgContent.setLineWrap(true);        //激活自动换行功能 
		msgContent.setWrapStyleWord(true);            // 激活断行不断字功能
		JButton btn = new JButton("获取");
		msgContent1 = new JTextArea(10,40);
		msgContent1.setLineWrap(true);        //激活自动换行功能 
		msgContent1.setWrapStyleWord(true);            // 激活断行不断字功能
		panel.add(msgContent);
		panel.add(btn);
		panel.add(msgContent1);
		
		f.add(panel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("H5访问地址");
        f.setResizable(false);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String url = msgContent.getText();
				String urls[] = url.split("\\?");
				http = urls[0];
				String ret = checkMd5(urls[1]);
				msgContent1.setText(http + "?" + ret);
			}
        	
        });

	}
	//http://tmc1.mangocity.com/mtmc/f/fly.shtml?arrDate=20170110&depDate=20170107&timeStamp=20170106105412&aliasId=010000312010549&classLevel=&categoryCode=0000000100&isNegotiatedPrice=N&type=01&depCity=%E5%B9%BF%E5%B7%9E&companyCode=PNW&arrCity=%E4%B8%8A%E6%B5%B7&lineType=rt&cmd=list&source=ck_888010&deptCode=WL&encryptKey=312CBE3CEB7A3D4080E7FD651CBAA79A
	private static String checkMd5(String postData) {
		String url = "";
		try {
			String[] data = postData.split("&encryptKey=");
			String queryString = data[0];
			String encryptKey = data[1];
			String encryptKey2 = MD5Utils.sign(queryString + "&FCC3251D1DA02B5841C1666F0604EE1A").toUpperCase();
			System.out.println("encryptKey:"+encryptKey+"  encryptKey2:"+encryptKey2);
			url = queryString + "&encryptKey=" + encryptKey2;
			
		} catch (Exception e) {
		}
		return url;
	}	

}
