package com.llh.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class RemoteService extends UnicastRemoteObject implements RemoteIntf {
    //保存客服端远程对象
    Map<DispTimeIntf, Runnable> clients = new HashMap<DispTimeIntf, Runnable>();

    //必须显式写出构造方法并且抛出异常
    public RemoteService() throws RemoteException {
    }

    //服务器端需要持有客户端的远程对象(接口),以便回调
    @Override
    public void regist(final DispTimeIntf client, final int seconds) {
        Thread t;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                Timer t = new Timer();
                TimerTask callback_task = new TimerTask() {
                    public void run() {
                        try {
                            Date now = new Date();
                            DateFormat d = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
                            String time = d.format(now);
                            //调用客户端应用
                            try {
                                client.dispTime(time);
                            } catch (Exception e) {
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.schedule(callback_task, 0, seconds * 1000);
            }
        });
        t.run();
        clients.put(client, t);
    }

    @Override
    public void unregist(DispTimeIntf client) {
        Thread t = (Thread) clients.remove(client);
        t.interrupt();
    }    
}
