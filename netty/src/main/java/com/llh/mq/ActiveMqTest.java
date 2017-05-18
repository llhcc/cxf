package com.llh.mq;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqTest {

	private static String queueName = "activemq_queue_";

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Sender sender = new Sender();
		try {
			//sender.send();
			receiver.receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class Receiver {
		public static void receive() throws Exception {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.TRUE,
					Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			MessageConsumer consumer = session.createConsumer(destination);
			// 第一种情况
			int i = 0;
			while (i < 3) {
				i++;
				TextMessage message = (TextMessage) consumer.receive();
				session.commit();
				// TODO something....
				System.out.println("收到消息：" + message.getText());
				Thread.sleep(3000);
			}
			session.close();
			connection.close();
			// ----------------第一种情况结束----------------------
			// 第二种方式
			// consumer.setMessageListener(new MessageListener() {
			// public void onMessage(Message arg0) {
			// if(arg0 instanceof TextMessage){
			// try {
			// System.out.println("arg0="+((TextMessage)arg0).getText());
			// } catch (JMSException e) {
			// e.printStackTrace();
			// }
			// }
			// }
			// });
			// 第三种情况
			// while (true) {
			// Message msg = consumer.receive(1000);
			// TextMessage message = (TextMessage) msg;
			// if (null != message) {
			// System.out.println("收到消息:" + message.getText());
			// }
			// }
		}
	}

	static class Sender {
		public static void send() throws Exception {
			ConnectionFactory connectionFactory = null;
			connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnection.DEFAULT_USER, // null
					ActiveMQConnection.DEFAULT_PASSWORD, // null
					"tcp://localhost:61616");

			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(Boolean.TRUE,
					Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);

			MessageProducer producer = session.createProducer(destination);
			for (int i = 0; i < 3; i++) {
				TextMessage message = session.createTextMessage("count"
						+ new Date().getTime());
				Thread.sleep(1000);
				// 通过消息生产者发出消息
				System.out.println("发送消息" + i + new Date());
				producer.send(message);
			}
			session.commit();
			session.close();
			connection.close();
		}
	}
}
