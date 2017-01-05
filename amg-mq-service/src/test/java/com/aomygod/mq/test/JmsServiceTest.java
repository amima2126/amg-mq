package com.aomygod.mq.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

import com.aomygod.mq.dao.MessageBeanDao;
import com.aomygod.mq.entity.EventMode;
import com.aomygod.mq.entity.EventState;
import com.aomygod.mq.entity.EventType;
import com.aomygod.mq.entity.MessageBean;
import com.aomygod.mq.service.DemoService;
import com.aomygod.mq.service.receive.ReceiveQueueMessageServiceImpl;
import com.aomygod.mq.service.receive.ReceiveTopicMessageServiceImpl;
import com.aomygod.mq.service.send.SendMessageServiceImpl;

public class JmsServiceTest extends BaseTest {

	@Resource
	private MessageBeanDao messageDao;
	@Resource
    private JmsTemplate jmsTemplate;
	@Resource
	private SendMessageServiceImpl messageSender;
	@Resource
	private ReceiveQueueMessageServiceImpl queueReceiver;
	@Resource
	private ReceiveTopicMessageServiceImpl topicReceiver;

	@Test
	@Ignore
	public void sendQueue() {
		MessageBean msg = new MessageBean();
		msg.setEventMode(EventMode.Queue)
				.setEventType(EventType.Queues.QUEUE_AMG_SECKILL)
				.setEventState(EventState.succeed).setSource(this.getClass().toString())
				.setResourceId("1")
				.setContent("cqx test send msg").setCreateBy("cqx")
				.setCreateTime(getDate());

		messageSender.sendQueue(EventType.Queues.QUEUE_AMG_SECKILL.toString(), msg, true);
	}

	@Test
	@Ignore
	public void sendTopic() {
		MessageBean msg = new MessageBean();
		msg.setEventMode(EventMode.Topic)
				.setEventType(EventType.Topics.TOPIC_AMG_SECKILL)
				.setEventState(EventState.succeed).setSource(this.getClass().toString())
				.setResourceId("1")
				.setContent("cqx test send msg").setCreateBy("cqx")
				.setCreateTime(getDate());

		messageSender.sendTopic(EventType.Topics.TOPIC_AMG_SECKILL.toString(), msg, true);
	}

	@Test
	@Ignore
	public void queueReceive() {
		javax.jms.Message message = queueReceiver.receive(EventType.Queues.QUEUE_AMG_SECKILL.toString());
		try {
			System.out.println(message.getStringProperty("EventType"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void topicReceive() {
		Connection connection = null;
		try {
			connection = jmsTemplate.getConnectionFactory().createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(EventType.Topics.TOPIC_AMG_SECKILL.toString());
			MessageConsumer consumer = session.createConsumer(topic);
			connection.start();
			consumer.setMessageListener(new MessageListener() {  
				@Override
				public void onMessage(javax.jms.Message message) {
//					TextMessage tm = (TextMessage) message;  
                    try {  
                        System.out.println("Received message: " + message.getStringProperty("EventType"));  
                    } catch (JMSException e) {  
                        e.printStackTrace();  
                    }
				}  
            });
		} catch (JMSException e) {
			if(connection != null) {
				try {
					connection.close();
				} catch (JMSException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void testDao() {
		MessageBean msg = new MessageBean();
		msg.setEventMode(EventMode.Topic)
				.setEventType(EventType.Topics.TOPIC_AMG_SECKILL)
				.setEventState(EventState.succeed).setSource(this.getClass().toString())
				.setResourceId("1")
				.setContent("cqx test send msg").setCreateBy("cqx")
				.setCreateTime(getDate());

		messageDao.save(msg);
	}

	@Resource
    private DemoService demoServiceImplClient;
	
	/**
	 * 测试dubbo接口
	 */
	@Test
	@Ignore
	public void testDubbo() {
		demoServiceImplClient.test();
	}
	
	private String getDate() {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(date);
		return format;
	}
}
