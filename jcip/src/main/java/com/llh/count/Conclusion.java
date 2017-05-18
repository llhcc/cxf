package com.llh.count;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;  
import java.util.Iterator;  
import java.util.Map;  
import java.util.TreeMap;  
  
public class Conclusion {  
      
    static Map<String, Integer> resultMap = new TreeMap<String, Integer>();  
    static File file = new File(Constants.RESULTS_FILE);  
      
    /** 
     * 分别统计每个子结果文件 
     */  
    @SuppressWarnings("resource")  
    public static void sumUp() {  
        // include the thread to deal with byte-left  
        for(int i=0; i<CalcDemo.CALC_THREADS_NUM+1; i++){  
            File file = new File("E:/tmp/count/result-" + i + ".txt");  
            FileInputStream fio = null;  
            try {  
                fio = new FileInputStream(file);  
                InputStreamReader inputStreamReader = new InputStreamReader(fio);    
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);   
                String line;  
                while((line = bufferedReader.readLine()) != null){  
                    String letter = line.substring(0,1);  
                    String cntStr = line.substring(2);  
                    if(resultMap.get(letter) == null){  
                        resultMap.put(letter, 0);  
                    }  
                    resultMap.put(letter, resultMap.get(letter) + Integer.parseInt(cntStr));  
                }  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            showResults();  
        }  
    }  
      
      
    /** 
     * 输出所有结果 
     */  
    private static void showResults(){  
        Iterator<String> iter = resultMap.keySet().iterator();  
        StringBuffer sbf = new StringBuffer();  
        while(iter.hasNext()){  
            String letter = iter.next();  
            Integer cnt = resultMap.get(letter);  
            String line = letter + ": " + cnt + "\n";  
            sbf.append(line);  
        }  
          
        FileOutputStream fos;  
        try {  
            fos = new FileOutputStream(file);  
            fos.write(sbf.toString().getBytes("UTF-8"));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public static void main(String[] args) {  
        sumUp();  
    }  
}  
