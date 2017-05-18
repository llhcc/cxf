package com.llh.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DispTimeIntf extends Remote {
    //提供给Server端调用的回调接口
    public String dispTime(String time) throws RemoteException;
}
