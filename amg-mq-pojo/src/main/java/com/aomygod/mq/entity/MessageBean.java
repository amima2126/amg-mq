package com.aomygod.mq.entity;

import java.io.Serializable;

/**
 * 消息实体
 */
public class MessageBean implements Serializable{
	
    private static final long serialVersionUID = 4009232658158976386L;
    
    private Integer id;
    
    /**消息发送模式：Queue，Topic*/
    private EventMode eventMode;
    
    /**消息发送模式名称，即事件类型*/
    private EventType eventType;
    
    /**消息状态*/
    private EventState eventState;
    
    /**消息在哪里生产：调用消息生产者的类名*/
    private String source;
    
    /**
     * 资源id，如订单，秒杀单等id
     */
    private String resourceId;
    
    /**消息内容：存放相关实体其他属性，如有多个则以json字符串格式保存*/
    private String content;
    
    /**消息生产时间*/
    private String createTime;
    
    /**消息生产者*/
    private String createBy;
    
    /**消息更新时间*/
    private String updateTime;
    
    /**消息更新者*/
    private String updateBy;

	public Integer getId() {
		return id;
	}

	public MessageBean setId(Integer id) {
		this.id = id;
		return this;
	}

	public EventMode getEventMode() {
		return eventMode;
	}

	public MessageBean setEventMode(EventMode eventMode) {
		this.eventMode = eventMode;
		return this;
	}

	public EventType getEventType() {
		return eventType;
	}

	public MessageBean setEventType(EventType eventType) {
		this.eventType = eventType;
		return this;
	}

	public EventState getEventState() {
		return eventState;
	}

	public MessageBean setEventState(EventState eventState) {
		this.eventState = eventState;
		return this;
	}

	public String getSource() {
		return source;
	}

	public MessageBean setSource(String source) {
		this.source = source;
		return this;
	}

	public String getResourceId() {
		return resourceId;
	}

	public MessageBean setResourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	public String getContent() {
		return content;
	}

	public MessageBean setContent(String content) {
		this.content = content;
		return this;
	}

	public String getCreateTime() {
		return createTime;
	}

	public MessageBean setCreateTime(String createTime) {
		this.createTime = createTime;
		return this;
	}

	public String getCreateBy() {
		return createBy;
	}

	public MessageBean setCreateBy(String createBy) {
		this.createBy = createBy;
		return this;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public MessageBean setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public MessageBean setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
		return this;
	}
}
