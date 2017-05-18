package com.llh.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteIntf extends Remote {
    public void regist(DispTimeIntf client, int second) throws RemoteException;
    public void unregist(DispTimeIntf client) throws RemoteException;
}
