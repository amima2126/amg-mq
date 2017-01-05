package com.aomygod.mq.service.send;

import javax.annotation.Resource;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.aomygod.mq.dao.MessageBeanDao;
import com.aomygod.mq.entity.MessageBean;
import com.aomygod.mq.util.AmqUtils;
import com.aomygod.mq.util.JSONUtils;

/**
 * 消息生产者
 */
@Service("sendMessageServiceImpl")
public class SendMessageServiceImpl implements SendMessageService{
	private static final Logger log = LoggerFactory.getLogger(SendMessageServiceImpl.class);

	@Resource
    private JmsTemplate jmsTemplate;
	@Resource
	private MessageBeanDao messageDao;

	/**
	 * 发送topic消息，默认消息不作持久化
	 * @param topic
	 * @param message
	 */
	@Override
    public boolean sendTopic(final String topic, final MessageBean message) {
		boolean result = false;
        try {
            if (jmsTemplate != null) {
                ActiveMQTextMessage textMsg = AmqUtils.createMsg(message);
                if (textMsg != null) {
                	jmsTemplate.setPubSubDomain(true);//消息模式：Topics
                    jmsTemplate.convertAndSend(topic, textMsg);
                    
                    result = true;
                    log.info("已发送Topic：" + JSONUtils.toJson(message));
                }
            } else {
                log.warn("发送Topic失败：" + JSONUtils.toJson(message));
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                log.warn("发送Topic失败：" + JSONUtils.toJson(message), e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 发送topic消息，根据isPersistent参数判断是否持久化消息
     * @param topic
     * @param message
     * @param isPersistent：是否持久化消息
     */
	@Override
    public void sendTopic(final String topic, final MessageBean message, final boolean isPersistent) {
    	boolean result = sendTopic(topic,message);
    	
    	if(result && isPersistent && message instanceof MessageBean) {
    		messageDao.save(message);
    	}
    }
    
    /**
	 * 发送queue消息，默认消息不作持久化
	 * @param topic
	 * @param message
	 */
	@Override
    public boolean sendQueue(final String queue, final MessageBean message) {
		boolean result = false;
        try {
            if (jmsTemplate != null) {
                ActiveMQTextMessage textMsg = AmqUtils.createMsg(message);
                if (textMsg != null) {
                	jmsTemplate.setPubSubDomain(false);//消息模式：Queues
                	jmsTemplate.convertAndSend(queue, textMsg);
                    
                    result = true;
                    log.info("已发送Queue：" + JSONUtils.toJson(message));
                }
            } else {
                log.warn("发送Queue失败：" + JSONUtils.toJson(message));
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                log.warn("发送Queue失败：" + JSONUtils.toJson(message), e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 发送queue消息，根据isPersistent参数判断是否持久化消息
     * @param queue
     * @param message
     * @param isPersistent：是否持久化消息
     */
	@Override
    public void sendQueue(final String queue, final MessageBean message, final boolean isPersistent) {
    	boolean result = sendQueue(queue,message);
    	
    	if(result && isPersistent && message instanceof MessageBean) {
    		messageDao.save(message);
    	}
    }

}
