package com.llh.mq;

import javax.annotation.Resource;  

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.ResponseBody;  
  
@Controller  
@RequestMapping("/push")  
public class PushController {  
  
    @Resource(name="userPushService")  
    private PushService userPushService;  
      
    @Resource(name="newsPushService")  
    private PushService newsPushService;  
      
    @Resource(name="clientPushService")  
    private PushService clientPushService;  
      
    /** 
     * 用户推送 
     * @param info 
     * @return 
     * @author Administrator 
     * @create 2016-8-10 下午4:22:28 
     */  
    @RequestMapping(value="/user",method=RequestMethod.POST)  
    @ResponseBody  
    public ResultRespone userPush(User info){  
        ResultRespone respone = new ResultRespone(true,"");  
        try {  
            userPushService.push(info);  
            respone.setData(info);  
        } catch (Exception e) {  
            e.printStackTrace();  
            respone = new ResultRespone(false, e.getMessage());  
        }  
        return respone;  
    }  
      
    /** 
     * 新闻推送 
     * @param info 
     * @return 
     * @author Administrator 
     * @create 2016-8-10 下午4:22:38 
     */  
    @RequestMapping(value="/news",method=RequestMethod.POST)  
    @ResponseBody  
    public ResultRespone newsPush(News info){  
    	 ResultRespone respone = new ResultRespone(true,"");  
        try {  
            newsPushService.push(info);  
            respone.setData(info);  
        } catch (Exception e) {  
            e.printStackTrace();  
            respone = new ResultRespone(false, e.getMessage());  
        }  
        return respone;  
    }  
    /** 
     * 客户推送 
     * @param info 
     * @return 
     * @author Administrator 
     * @create 2016-8-10 下午4:22:48 
     */  
    @RequestMapping(value="/client",method=RequestMethod.POST)  
    @ResponseBody  
    public ResultRespone clientPush(Client info){  
    	 ResultRespone respone = new ResultRespone(true,"");  
        try {  
            clientPushService.push(info);  
            respone.setData(info);  
        } catch (Exception e) {  
            e.printStackTrace();  
            respone = new ResultRespone(false, e.getMessage());  
        }  
        return respone;  
    }  
}  
