package cxf.lenve.test;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface IMyServer {

    @WebResult(name="addResult")
    public int add(@WebParam(name="a")int a,@WebParam(name="b")int b);
}
