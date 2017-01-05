package com.aomygod.mq.service.receive;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * queue消息消费者
 */
@Service("receiveQueueMessageServiceImpl")
public class ReceiveQueueMessageServiceImpl implements ReceiveQueueMessageService{
	
	private static final Logger log = LoggerFactory.getLogger(ReceiveQueueMessageServiceImpl.class);

	@javax.annotation.Resource
    private JmsTemplate jmsTemplate;

	/**
     * 接收消息
     */
	@Override
    public TextMessage receive(Destination destination) {
        TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
        try {
            log.info("从队列【" + destination.toString() + "】收到了消息：\t"
            		+ tm.getText());
            
        } catch (JMSException e) {
            e.printStackTrace();
        }
        
        return tm;
    }
    
    /**
     * 从指定queue接收消息
     */
	@Override
    public Message receive(String queue) {
        Message msg = jmsTemplate.receive(queue);
        try {
            log.info("从队列【" + queue + "】收到了消息:\t" + msg.getStringProperty("EventType"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return msg;
    }

}
