package com.aomygod.mq.service.receive;

import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.TextMessage;

public interface ReceiveQueueMessageService {

	/**
     * 接收消息
     */
    public TextMessage receive(Destination destination);
    
    /**
     * 接收消息
     */
    public Message receive(String queue);
	
}
