package com.aomygod.mq.listener;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JmsExceptionListener implements ExceptionListener{
	private static final Logger log = LoggerFactory.getLogger(JmsExceptionListener.class);
    
    public void onException(JMSException e) {
    	log.error("监听Direcotor的JMS出错：", e);
    }

}
