package com.aomygod.mq.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aomygod.mq.entity.MessageBean;

@Component
@Transactional
public class MessageBeanDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询
	 */
	public List<Map<String,Object>> getList(){
		return jdbcTemplate.queryForList("select * from amg_jms_message ");
	}
	
	/**
	 * 新增
	 */
	public int save(MessageBean message){
		int flag = this.jdbcTemplate.update("insert into amg_jms_message "
				+ "(eventMode,eventType,eventState,source,resourceId,content,createTime,createBy) "
				+ "values ("
				+ "'"+message.getEventMode()+"',"
				+ "'"+message.getEventType()+"',"
				+ "'"+message.getEventState()+"',"
				+ "'"+message.getSource()+"',"
				+ "'"+message.getResourceId()+"',"
				+ "'"+message.getContent()+"',"
				+ "'"+message.getCreateTime()+"',"
				+ "'"+message.getCreateBy()+"'"
				+ ") ");
		return flag;
	}
	
	/**
	 * 删除
	 */
	public int delete(int id){
		int flag = this.jdbcTemplate.update("delete from amg_jms_message where id = "+id);
		return flag;
	}
	
	/**
	 * 修改
	 */
	public int update(MessageBean message){
		int flag = this.jdbcTemplate.update("update amg_jms_message set "
				+ "eventMode='" + message.getEventMode() + "',"
				+ "eventType='" + message.getEventType() + "', "
				+ "eventState='" + message.getEventState() + "', "
				+ "source='" + message.getSource() + "', "
				+ "resourceId='" + message.getResourceId() + "', "
				+ "content='" + message.getContent() + "', "
				+ "updateTime='" + message.getUpdateTime() + "', "
				+ "updateBy='" + message.getUpdateBy() + "' "
				+ "where id=" + message.getId());
		return flag;
	}
	
}
