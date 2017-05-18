package com.llh.count;

import java.io.File;

public class CalcDemo {
	// the number of calc threads number  
    public static final int CALC_THREADS_NUM = 8;  
    // the src file   
    private static final File file = new File(Constants.OUTPUT_FILE_NAME);  
    // total length  
    private static final Long totalBytes = file.length();  
    // bytes per thread  
    private static final Long bytesPerThread = totalBytes / CALC_THREADS_NUM;  
    // bytes left  
    private static final Long bytesLeft = totalBytes % bytesPerThread;  
      
    private static void initInfo(){  
        System.out.printf("file size: %d bytes\n", totalBytes);  
        System.out.printf("per thread: %d bytes\n", bytesPerThread);  
        System.out.printf("bytes left: %d bytes\n", bytesLeft);  
    }  
      
    public static void doCalc() {  
        initInfo();  
        // calc thread start  
        for(int threadId=0; threadId<CALC_THREADS_NUM; threadId++){  
            Long start = threadId * bytesPerThread;  
            Long end = start + bytesPerThread;  
            new CalcThread(file, start, end, threadId).start();  
        }  
          
        // for the bytes left  
        CalcThread calcThread = new CalcThread(file  
                , bytesPerThread*CALC_THREADS_NUM, totalBytes, CALC_THREADS_NUM);  
        calcThread.start();  
          
    }  
}
