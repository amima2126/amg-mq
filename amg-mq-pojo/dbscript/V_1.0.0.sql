DROP DATABASE IF EXISTS `amg-jms`;
CREATE DATABASE IF NOT EXISTS `amg-jms` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `amg-jms`;

CREATE TABLE `amg_jms_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `eventMode` varchar(20) DEFAULT NULL COMMENT '消息发送模式：Queue，Topic',
  `eventType` varchar(20) DEFAULT NULL COMMENT '消息发送模式名称，即事件类型',
  `eventState` varchar(20) DEFAULT NULL COMMENT '消息状态',
  `source` varchar(200) DEFAULT NULL COMMENT '消息在哪里生产：调用消息生产者的类名',
  `resourceId` varchar(20) DEFAULT NULL COMMENT '资源id，如订单，秒杀单等id',
  `content` varchar(3500) DEFAULT NULL COMMENT '：存放相关实体其他属性，如有多个则以json字符串格式保存',
  `createTime` datetime DEFAULT NULL COMMENT '消息生产时间',
  `createBy` varchar(20) DEFAULT NULL COMMENT '消息生产者',
  `updateTime` datetime DEFAULT NULL COMMENT '消息更新时间',
  `updateBy` varchar(20) DEFAULT NULL COMMENT '消息更新者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
