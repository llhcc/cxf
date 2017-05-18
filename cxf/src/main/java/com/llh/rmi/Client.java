package com.llh.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client {
	 public static void main(String[] args) {
	        try {
	            String ip = "127.0.0.1";
	            //在指定地址查找远程对象实例
	            RemoteIntf service = (RemoteIntf) Naming.lookup("//" + ip + ":9001/server");
	            DispTimeImpl dispTime = new DispTimeImpl();
	            service.regist(dispTime, 1);
	        } catch (RemoteException e1) {
	            e1.printStackTrace();
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	    }
}


class DispTimeImpl extends UnicastRemoteObject implements DispTimeIntf {
    protected DispTimeImpl() throws RemoteException {
        super();
    }

    public String dispTime(final String time) throws RemoteException {
        System.err.println("get from Server: " + time);
        return time;
    }
}
