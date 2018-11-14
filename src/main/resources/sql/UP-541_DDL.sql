-- ----------------------------
-- Table structure for finance_barrage_message
-- ----------------------------
CREATE TABLE `finance_barrage_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `message_code` varchar(50) NOT NULL COMMENT '消息代码，具体定义见枚举',
  `message_desc` varchar(255) NOT NULL COMMENT '消息正文',
  `creator` varchar(20) DEFAULT NULL COMMENT '创建者',
  `updator` varchar(20) DEFAULT NULL COMMENT '更新者',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) DEFAULT 0  COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户操作弹幕消息';

CREATE TABLE `finance_operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `log_id` bigint(17) DEFAULT NULL COMMENT '日志id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `op_code` varchar(255) NOT NULL COMMENT '操作码',
  `op_name` varchar(255) DEFAULT NULL COMMENT '操作名称',
  `creator` varchar(20) DEFAULT '' COMMENT '创建者',
  `updator` varchar(20) DEFAULT '' COMMENT '更新者',
  `version` int(11) DEFAULT 1 COMMENT '版本号',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) DEFAULT 0  COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_log_id` (`log_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户操作日志';
--  增加[是否支付金币]字段
alter table finance_user_invite_info add is_pay_coin varchar(2) DEFAULT 'N' COMMENT '是否支付金币';
