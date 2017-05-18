package com.llh.sierpinski;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Test extends JPanel{
	
	private final int dim = 513;
    private final int margin = 20;

	public Test(){
		setPreferredSize(new Dimension(dim + 2 * margin, dim + 2 * margin));
        setBackground(Color.white);
        setForeground(Color.green);
        setBorder(BorderFactory.createLineBorder(Color.green, 5));
        
        
        new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		}).start();
	}
	
	@Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.translate(margin, margin);
        drawCarpet(g, 100, 100, 200);
    }
	
	void drawCarpet(Graphics2D g, int x, int y, int size) {
		g.draw3DRect(x + size, y + size, size, size,true);
		g.drawRoundRect(x, y, x + size, y + size, 15, 15);  
	}
	
	public static void main(String args[]){
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Sierpinski Carpet");
        //f.setResizable(false);
        f.add(new Test(), BorderLayout.CENTER);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        String input = "";
        //while(true){
        /*do{
        	Scanner scan = new Scanner(System.in);
        	input = scan.next();
            System.out.println(input);
        }while(input != "#");*/
        //}
        //test();
        
        
	}
	
	//递归,倒序输出字符串
	public static void test() {
		String input = "";
		System.out.println("---请输入：");
		Scanner scan = new Scanner(System.in);
    	input = scan.next();
        System.out.println(input);
        if(!input.equals("#")){
        	test();
        	System.out.print("---" + input);
        }else{
        	System.out.println("---" + input);
        }
	}
}
