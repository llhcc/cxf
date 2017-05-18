package com.llh.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.jms.*;
import java.io.IOException;
 
 
/**
 * ActiveMQ消息接收示例(使用MessageListener)
 * Author:菩提树下的杨过 http://yjmyzz.cnblogs.com
 */
public class MessageListenerConsumer {
 
    public static void main(String[] args) throws JMSException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        PooledConnectionFactory connectionFactory = context.getBean(PooledConnectionFactory.class);
        ActiveMQQueue destination = context.getBean(ActiveMQQueue.class);
        Connection connection = connectionFactory.createConnection();
        connection.start();
       // Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);//手动确认
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new ActiveMQListener());
        System.in.read();
    }
 
 
    /*static class ActiveMQListener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            try {
                if (message instanceof TextMessage) {
                    System.out.println(((TextMessage) message).getText());
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }*/
    
    static class ActiveMQListener implements MessageListener {
        @Override
        public void onMessage(Message message) {
            try {
                if (message instanceof TextMessage) {
                    System.out.println(((TextMessage) message).getText());
                    message.acknowledge(); //手动确认消息
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
