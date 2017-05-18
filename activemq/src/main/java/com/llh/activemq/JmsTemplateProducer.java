package com.llh.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
 
/**
 * ActiveMQ消息发送示例（利用JMSTemplate）
 * Author:菩提树下的杨过 http://yjmyzz.cnblogs.com
 */
public class JmsTemplateProducer {
 
    public static void main(String[] args) {
 
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
 
        System.out.println("准备发送消息...");
        int max = 1000;
        Long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            jmsTemplate.convertAndSend("message test:" + i);
        }
        Long end = System.currentTimeMillis();
        Long elapse = end - start;
        int perform = Double.valueOf(max / (elapse / 1000d)).intValue();
 
        System.out.print("发送 " + max + " 条消息，耗时：" + elapse + "毫秒，平均" + perform + "条/秒");
    }
}
