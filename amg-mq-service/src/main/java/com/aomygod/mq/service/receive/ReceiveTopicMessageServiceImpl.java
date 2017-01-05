package com.aomygod.mq.service.receive;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * topic消息消费者：topic消息接收仅供参考,订阅者可以自己实现listener来监听消息
 */
@Service("receiveTopicMessageServiceImpl")
public class ReceiveTopicMessageServiceImpl implements ReceiveTopicMessageService{
	private static final Logger log = LoggerFactory.getLogger(ReceiveTopicMessageServiceImpl.class);

	@Override
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage) message;
		try {
			log.info("TopicReceiver收到了消息：\t" + tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
