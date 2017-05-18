package com.llh.count;

import java.io.File;

public class MainTest {
	public static void main(String[] args) {  
        // clear data  
        File file = null;  
        for(int i=0; i<=CalcDemo.CALC_THREADS_NUM; i++){  
            // delete old data file  
            file = new File("E:/tmp/count/result-" + i + ".txt");  
            if(file.isFile())  
                file.delete();  
        }  
  
        file = new File(Constants.RESULTS_FILE);  
        if(file.isFile())  
            file.delete();  
  
  
        // CALC_THREADS_NUM+1（for the bytes-left） Threads to analyze data  
        CalcDemo.doCalc();  
  
        // 20 seconds time waiting for all threads' process   
        try {  
            Thread.sleep(20000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        
        /*try {
			new Thread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
  
        // main thread to sum up data  
        Conclusion.sumUp();  
    }  
}
