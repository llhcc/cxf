
package cxf.lenve.test;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "MyServerImplService", targetNamespace = "http://test.lenve.cxf/", wsdlLocation = "http://10.10.40.60:8086/cxf/ws/MyServer?wsdl")
public class MyServerImplService
    extends Service
{

    private final static URL MYSERVERIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException MYSERVERIMPLSERVICE_EXCEPTION;
    private final static QName MYSERVERIMPLSERVICE_QNAME = new QName("http://test.lenve.cxf/", "MyServerImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://10.10.40.60:8086/cxf/ws/MyServer?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MYSERVERIMPLSERVICE_WSDL_LOCATION = url;
        MYSERVERIMPLSERVICE_EXCEPTION = e;
    }

    public MyServerImplService() {
        super(__getWsdlLocation(), MYSERVERIMPLSERVICE_QNAME);
    }

    public MyServerImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MYSERVERIMPLSERVICE_QNAME, features);
    }

    public MyServerImplService(URL wsdlLocation) {
        super(wsdlLocation, MYSERVERIMPLSERVICE_QNAME);
    }

    public MyServerImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MYSERVERIMPLSERVICE_QNAME, features);
    }

    public MyServerImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MyServerImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns IMyServer
     */
    @WebEndpoint(name = "MyServerImplPort")
    public IMyServer getMyServerImplPort() {
        return super.getPort(new QName("http://test.lenve.cxf/", "MyServerImplPort"), IMyServer.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IMyServer
     */
    @WebEndpoint(name = "MyServerImplPort")
    public IMyServer getMyServerImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://test.lenve.cxf/", "MyServerImplPort"), IMyServer.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MYSERVERIMPLSERVICE_EXCEPTION!= null) {
            throw MYSERVERIMPLSERVICE_EXCEPTION;
        }
        return MYSERVERIMPLSERVICE_WSDL_LOCATION;
    }

}