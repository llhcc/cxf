package com.llh.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TopicDao {
	private Connection conn;//①一个非线程安全的变量

	public void addTopic(){

	try {
		Statement stat = conn.createStatement();
	} catch (SQLException e) {
		e.printStackTrace();
	}//②引用非线程安全变量

	//…

	}
}
