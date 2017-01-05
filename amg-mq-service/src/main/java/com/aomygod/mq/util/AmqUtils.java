package com.aomygod.mq.util;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQTextMessage;

import com.aomygod.mq.entity.MessageBean;

/**
 * ActiveMQ工具类
 */
public class AmqUtils {

    public static ActiveMQTextMessage createMsg(MessageBean msg) {
        try {
            ActiveMQTextMessage text = new ActiveMQTextMessage();
            text.setStringProperty("EventType", msg.getEventType().toString());
            text.setStringProperty("EventMode", msg.getEventMode().toString());
            text.setStringProperty("EventState", msg.getEventState().toString());
            text.setStringProperty("source", msg.getSource());
            text.setStringProperty("content", msg.getContent());
            text.setStringProperty("resourceId", msg.getResourceId());
            text.setStringProperty("createTime", msg.getCreateTime());
            text.setStringProperty("createBy", msg.getCreateBy());
            return text;
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return null;
    }
}
