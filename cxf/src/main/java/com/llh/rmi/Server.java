package com.llh.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
	 public static void main(String[] args) {
	        try {
	            //创建接受对9001端口的远程调用的注册表
	            LocateRegistry.createRegistry(9001);
	            //创建远程对象的实例(向客户端提供的服务)
	            final RemoteIntf service = new RemoteService();
	            //将提供给客户端远端调用的远程对象根据指定名称加入远程调用注册表
	            Naming.bind("//localhost:9001/server", service);
	        } catch (final Exception e1) {
	            e1.printStackTrace();
	        }
	    }
}
