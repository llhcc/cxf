package com.llh.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TopicDao_ {
	//①使用ThreadLocal保存Connection变量

	private static ThreadLocal<Connection> connThreadLocal = new ThreadLocal<Connection>();

	public static Connection getConnection(){

	//②如果connThreadLocal没有本线程对应的Connection创建一个新的Connection，

	//并将其保存到线程本地变量中。

	if (connThreadLocal.get() == null) {

	Connection conn = null;
	try {
		conn = DriverManager.getConnection("");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	connThreadLocal.set(conn);

	return conn;

	}else{
	return connThreadLocal.get();//③直接返回线程本地变量
	}
	}

	public void addTopic() {
	//④从ThreadLocal中获取线程对应的Connection
	try {
		Statement stat = getConnection().createStatement();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	}
}
