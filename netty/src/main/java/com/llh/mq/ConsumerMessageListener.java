package com.llh.mq;

import org.apache.commons.lang.builder.ToStringBuilder;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * created on 2015/6/4
 * @author dennisit@163.com
 * @version 1.0
 */
public class ConsumerMessageListener implements MessageListener{
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("---------消息消费---------");
            System.out.println("消息内容:\t" + tm.getText());
            System.out.println("消息ID:\t" + tm.getJMSMessageID());
            System.out.println("消息Destination:\t" + tm.getJMSDestination());
            System.out.println("---------更多信息---------");
            System.out.println(ToStringBuilder.reflectionToString(tm));
            System.out.println("-------------------------");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
