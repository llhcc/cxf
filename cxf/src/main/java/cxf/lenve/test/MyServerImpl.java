package cxf.lenve.test;

import javax.jws.WebService;

@WebService(endpointInterface="cxf.lenve.test.IMyServer")
public class MyServerImpl implements IMyServer {

    @Override
    public int add(int a, int b) {
        System.out.println("服务端..."+a+"+"+b+"="+(a+b));
        return a+b;
    }

}
