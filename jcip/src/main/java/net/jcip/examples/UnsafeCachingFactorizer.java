package net.jcip.examples;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.atomic.*;

import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * UnsafeCachingFactorizer
 *
 * Servlet that attempts to cache its last result without adequate atomicity
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {
    private final AtomicReference<BigInteger> lastNumber
            = new AtomicReference<BigInteger>();
    private final AtomicReference<BigInteger[]> lastFactors
            = new AtomicReference<BigInteger[]>();

    public void service(ServletRequest req, ServletResponse resp) {
    	// System.setProperty("sun.net.client.defaultConnectTimeout", "2000");
    	//synchronized(UnsafeCachingFactorizer.class){
    		BigInteger i = extractFromRequest(req);
            if (i.equals(lastNumber.get()))
                encodeIntoResponse(resp, lastFactors.get());
            else {
                BigInteger[] factors = factor(i);
                lastNumber.set(i);
                lastFactors.set(factors);
                encodeIntoResponse(resp, factors);
            }
            System.out.println("i=" + i + ",lastNumber=" + lastNumber + ",lastFactors=" +lastFactors.get()[0] + ",---" + lastFactors.get().length + ",flag=" + (i.compareTo(lastNumber.get())));
            /*BigInteger[] b= lastFactors.get();
            for(int j=0; j < b.length; j++){
            	System.out.print(b[j] + " ");
            }
            System.out.println();*/
    	//}
        
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
    	Random rand = new Random();
    	String str = String.valueOf(rand.nextInt(100));
    	//System.out.println(str);
        return new BigInteger(str);
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}

