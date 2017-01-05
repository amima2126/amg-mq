package com.aomygod.mq.entity;

public interface EventType { 
	
    enum Topics implements EventType{  
    	/**秒杀中心*/
    	TOPIC_AMG_SECKILL,
    	/**营销中心*/
    	TOPIC_AMG_UMP;
    }  
    
    enum Queues implements EventType{  
    	/**秒杀中心*/
    	QUEUE_AMG_SECKILL,
    	/**营销中心*/
    	QUEUE_AMG_UMP;
    }  
}
