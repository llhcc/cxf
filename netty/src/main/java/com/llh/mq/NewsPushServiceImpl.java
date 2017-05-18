package com.llh.mq;

import javax.jms.Destination;  
import javax.jms.JMSException;  
import javax.jms.Message;  
import javax.jms.Session;  
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.factory.annotation.Qualifier;  
import org.springframework.stereotype.Service;  
import org.springframework.jms.core.JmsTemplate;  
import org.springframework.jms.core.MessageCreator;  
  
import com.alibaba.fastjson.JSON;  
  
  
@Service("newsPushService")  
public class NewsPushServiceImpl implements PushService {  
  
      
    @Autowired  
    private JmsTemplate jmsTemplate;  
      
    /** 
     * 这里是根据MQ配置文件定义的queue来注入的，也就是这里将会把不同的内容推送到不同的queue中 
     */  
    @Autowired  
    @Qualifier("newsServiceQueue")  
    private Destination destination;  
      
    @Override  
    public void push(final Object info) {  
        pushExecutor.execute(new Runnable() {  
            @Override  
            public void run() {  
                jmsTemplate.send(destination, new MessageCreator() {  
                    public Message createMessage(Session session) throws JMSException {  
                    	News p = (News) info;  
                        return session.createTextMessage(JSON.toJSONString(p));  
                    }  
                });  
            }             
        });  
    }  
  
}  
