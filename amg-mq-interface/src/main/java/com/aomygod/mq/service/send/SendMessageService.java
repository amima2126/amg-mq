package com.aomygod.mq.service.send;

import com.aomygod.mq.entity.MessageBean;

public interface SendMessageService {

	/**
	 * 发送topic消息，默认消息不作持久化
	 * @param topic
	 * @param message
	 */
    public boolean sendTopic(final String topic, final MessageBean message);
	
    /**
     * 发送topic消息，根据isPersistent参数判断是否持久化消息
     * @param topic
     * @param message
     * @param isPersistent
     */
    public void sendTopic(final String topic, final MessageBean message, final boolean isPersistent);
    
    /**
	 * 发送queue消息，默认消息不作持久化
	 * @param topic
	 * @param message
	 */
    public boolean sendQueue(final String queue, final MessageBean message);
    
    /**
     * 发送queue消息，根据isPersistent参数判断是否持久化消息
     * @param queue
     * @param message
     * @param isPersistent
     */
    public void sendQueue(final String queue, final MessageBean message, final boolean isPersistent);
}
