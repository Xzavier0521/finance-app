DROP TABLE IF EXISTS `finance_announcement_info`;

CREATE TABLE `finance_announcement_info` (
	`id` bigint(20)  AUTO_INCREMENT NOT NULL  COMMENT '主键',
	`announcement_title` varchar(300)  NOT NULL  COMMENT '公告标题',
	`announcement_context` varchar(1000)  NOT NULL  COMMENT '公告内容',
	`is_show` int(1)  NOT NULL  DEFAULT '1' COMMENT '是否启用(0:否；1:是)',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='公告信息表';

DROP TABLE IF EXISTS `finance_banner_info`;
CREATE TABLE `finance_banner_info` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`page_code` int(2)  NOT NULL  COMMENT '页面代码',
	`banner_type` int(2)  NOT NULL  COMMENT 'banner类型',
	`title` varchar(300)  NOT NULL  COMMENT '标题',
	`seq_no` int(10)  NOT NULL  COMMENT '排序',
	`banner_url` varchar(500)  NOT NULL  COMMENT 'banner',
	`redirect_url` varchar(500)  COMMENT '跳转链接',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='banner信息表';

DROP TABLE IF EXISTS `finance_dictionary_info`;
CREATE TABLE `finance_dictionary_info` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`business_code` int(10)  NOT NULL  COMMENT '业务代码',
	`business_name` varchar(300)  NOT NULL  COMMENT '业务名称',
	`business_type` int(5)  NOT NULL  COMMENT '业务分类,1:页面,2:banner类型',
	`status` varchar(1)  NOT NULL  DEFAULT '1' COMMENT '状态(1:有效,0:无效)',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`is_delete` int(1)  NOT NULL  DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
	`creater` varchar(20) COMMENT '创建人',
	`updater` varchar(20) COMMENT '更新人',
	`version` int(11)  NOT NULL  DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`),
	KEY `idx_code_type` (`business_code`,`business_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

DROP TABLE IF EXISTS `finance_coin_game`;
CREATE TABLE `finance_coin_game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_type` varchar(100)  COMMENT '类型',
  `task_name` varchar(200) COMMENT '名称',
  `task_desc` varchar(500)  COMMENT '说明',
  `effect` varchar(1)  COMMENT '产生影响（加：+、减：-）',
  `num` int(10) COMMENT '金币数量，0：根据算法生成',
  `logo_url` varchar(500)  COMMENT'图标地址',
  `redirect_url` varchar(500) COMMENT '跳转地址',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '是否有效(0:否；1:是)',
	`plan_invalid_date` date COMMENT '计划失效时间',
	`seq_no` int(10) COMMENT '排序',
	`game_type` int(2) NOT NULL COMMENT '类型,1:任务,2:活动',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_task_type` (`task_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='金币任务和活动';

DROP TABLE IF EXISTS `finance_coin_log`;
CREATE TABLE `finance_coin_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `task_id` bigint(20) NOT NULL COMMENT '类型ID,finance_coin_task',
  `task_name` varchar(50)  COMMENT '备注（变化原因）',
  `num` int(11) NOT NULL DEFAULT '0' COMMENT '增加或减少的数量（负数：减少、正数：增加）',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_task_id` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='金币记录';

DROP TABLE IF EXISTS `finance_coin_game_log`;
CREATE TABLE `finance_coin_game_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `out_num` int(10) NOT NULL DEFAULT '0' COMMENT '投入金币数',
  `in_num` int(10) NOT NULL DEFAULT '0' COMMENT '赚取金币数',
  `join_date` date NOT NULL COMMENT '参加日期',
  `join_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '参加时间',
  `sign_time` datetime DEFAULT NULL COMMENT '打卡时间',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '是否已打卡（领取）(0:否；1:是)',
  `clock_count` int(10) NOT NULL DEFAULT '0' COMMENT '连续打卡天数',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_user_join_date` (`user_id`,`join_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='金币游戏';

DROP TABLE IF EXISTS `finance_user_task_info`;
CREATE TABLE `finance_user_task_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `task_id` bigint(20) NOT NULL COMMENT '任务id',
  `finish_status` int(1) NOT NULL DEFAULT 0 COMMENT '完成状态,0:未完成,1:完成待领取金币,2:已领取金币',
  `next_stage_num` int(10) NULL DEFAULT 0 COMMENT '下一阶段需金币数',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '版本号',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
	KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户任务信息表';

DROP TABLE IF EXISTS `finance_gift_info`;
CREATE TABLE `finance_gift_info` (
	`id` bigint(20) AUTO_INCREMENT NOT NULL  COMMENT '主键',
	`gift_name` varchar(300)  NOT NULL  COMMENT '礼品名称',
	`banner_url` varchar(500)  NOT NULL  COMMENT 'banner地址',
	`thumbnail_url` varchar(500)  NOT NULL  COMMENT 'banner缩略图',
	`need_coin_num` int(10)  NOT NULL  COMMENT '需金币数',
	`status` int(1)  NOT NULL  DEFAULT '1' COMMENT '状态(1:有效,0:无效)',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='礼品信息表';

DROP TABLE IF EXISTS `finance_user_gift_info`;
CREATE TABLE `finance_user_gift_info` (
	`id` bigint(20) AUTO_INCREMENT NOT NULL  COMMENT '主键',
	`user_id` bigint(20)  NOT NULL  COMMENT '用户ID',
	`gift_id` bigint(20)  NOT NULL  COMMENT '礼品ID',
	`gift_status` int(1)  NOT NULL  DEFAULT '0' COMMENT '礼品发放状态,0:待发放,1:已发放',
	`gift_provide_time` datetime  COMMENT '发放确认时间',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`),
	KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户礼品信息表';

drop table `finance_log`;

alter table `finance_user_account` change `version_num` `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号';
alter table `finance_user_account` change `create_time` `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';
alter table `finance_user_account` change `update_time` `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';
alter table `finance_user_account` change `can_withdraw_money` `can_withdraw_money` decimal(18,6) DEFAULT '0.000000' COMMENT '可提现金额';
alter table `finance_user_account` change `withdrawed_money` `withdrawed_money` decimal(18,6) DEFAULT '0.000000' COMMENT '已提现金额';
alter table `finance_user_account` change `income_money` `income_money` decimal(18,6) DEFAULT '0.000000' COMMENT '收入金额（冻结金额）';
alter table `finance_user_account` change `sum_charge_money` `sum_charge_money` decimal(18,6) DEFAULT '0.000000' COMMENT '总收入金额';

alter table `finance_user_login_log` add `type` varchar(30) not null DEFAULT 'img_mobile' COMMENT '登录方式' after user_id;
alter table `finance_user_login_log` change `login_name` `login_name` varchar(100) NOT NULL COMMENT '登录名称';

alter table `finance_profit` change `version_num` `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号';
alter table `finance_profit` change `update_time` `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';
alter table `finance_profit` change `create_time` `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';
alter table `finance_profit` change `is_flag` `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)';

alter table `finance_order` drop `type`;
alter table `finance_order` add `profit_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '分润表ID' after `serial_id`;
alter table `finance_order` add `settle_date` date NULL  COMMENT '结算日期' after `bank_type`;
#alter table `finance_order` add `settle_date` date NOT NULL  COMMENT '结算日期' after `bank_type`;
alter table `finance_order` add `settle_patch` int(11) NOT NULL DEFAULT 2  COMMENT '结算批次（默认第二批、尾款）' after `bank_type`;
alter table `finance_order` change `version_num` `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号';
alter table `finance_order` change `bank_code` `bank_card_id` bigint(11) NULL COMMENT '银行卡记录ID';
alter table `finance_order` add `account_no` varchar(50) NULL DEFAULT '' COMMENT '银行卡号' after `bank_fullname`;
alter table `finance_order` change `create_time` `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';
alter table `finance_order` change `update_time` `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';

alter table `finance_user_first_login_log` ADD UNIQUE INDEX uniq_user_platform ( `user_id`,`platform_code` );

alter table `finance_excel_detail` change `prod_name` `prod_id` bigint(20) DEFAULT 0 COMMENT '产品ID';
alter table `finance_excel_detail` change `user_name` `real_name` varchar(50) DEFAULT NULL COMMENT '姓名';
alter table `finance_excel_detail` add `transact_date` datetime NULL COMMENT '业务办理时间' after `money`;
alter table `finance_excel_detail` change `create_time` `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';
alter table `finance_excel_detail` change `update_time` `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';
alter table `finance_excel_detail` change `version_num` `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号';
alter table `finance_excel_detail` change `is_flag` `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)';

alter table `finance_excel_all` change `create_time` `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';
alter table `finance_excel_all` change `update_time` `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';
alter table `finance_excel_all` change `version_num` `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号';
alter table `finance_excel_all` change `is_flag` `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)';

update finance_profit set create_time = update_time;
ALTER TABLE `finance_profit` MODIFY COLUMN `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';

DROP TABLE IF EXISTS `finance_channel_pay_log`;

CREATE TABLE `finance_channel_pay_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `bill_no` varchar(50) NOT NULL COMMENT '交易单号',
  `channel` varchar(30) NOT NULL COMMENT '渠道：beeCloud',
  `money` decimal(18,2) NOT NULL COMMENT '金额（单位：分）',
  `account_name` varchar(60) NULL COMMENT '账户名',
  `account_no` varchar(60) NOT NULL COMMENT '账号',
  `bank_name` varchar(60) NULL COMMENT '银行名称（针对银行转账）',
  `result_code` varchar(30) NULL COMMENT '请求返回编码',
  `result_msg` varchar(300) NULL COMMENT '请求结果说明',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_bill_no` (`bill_no`),
  KEY `idx_channel` (`channel`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='通过第三方渠道转账日志表';


DROP TABLE IF EXISTS `finance_third_account_info`;

CREATE TABLE `finance_third_account_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `channel` varchar(30) NOT NULL COMMENT '第三方渠道类型（qq、wechat、wechat_public……）',
  `public_name` varchar(30) NOT NULL DEFAULT '' COMMENT '公众号名',
  `open_id` varchar(90) NOT NULL COMMENT '第三方账户（唯一标识）',
  `status` varchar(30) NOT NULL DEFAULT '1' COMMENT '状态（1：已绑定；非1：已解绑）',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_channel_pub_open_status` (`channel`,`public_name`,`open_id`,`status`),
  UNIQUE KEY `uniq_channel_pub_user` (`channel`,`public_name`,`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户第三方账户信息表';


DROP TABLE IF EXISTS `finance_third_account_action_log`;

CREATE TABLE `finance_third_account_action_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `channel` varchar(30) NOT NULL COMMENT '第三方渠道类型（qq、wechat、wechat_public……）',
  `public_name` varchar(30) NOT NULL DEFAULT '' COMMENT '公众号名',
  `open_id` varchar(90) NOT NULL COMMENT '第三方账户（唯一标识）',
  `action_type` varchar(30) NOT NULL COMMENT '操作类型（bind、unbind……）',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户第三方账户操作日志表';


DROP TABLE IF EXISTS `finance_coin_money_log`;
CREATE TABLE `finance_coin_money_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `coin_num` int(11) NOT NULL COMMENT '金币数量',
  `money` decimal(18,6) NOT NULL COMMENT '金币兑换的金额',
  `order_id` bigint(20) NOT NULL COMMENT '流水单ID',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  UNIQUE KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='金币兑换钱记录';

DROP TABLE IF EXISTS `finance_data_change_log`;
CREATE TABLE `finance_data_change_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `table_name` varchar(90) NOT NULL COMMENT '表名称',
  `table_id` bigint(20) NULL COMMENT '表记录ID（或者唯一索引）',
  `before_data` varchar(1000) NOT NULL DEFAULT '' COMMENT '修改前数据',
  `after_data` varchar(1000) NOT NULL COMMENT '修改后数据',
  `reason` varchar(90) NOT NULL COMMENT '变更原因',
  `remark` varchar(150) NOT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creater` bigint(20) NOT NULL DEFAULT '1' COMMENT '创建者',
  `updater` bigint(20) NOT NULL DEFAULT '1' COMMENT '更新者',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否已删除(0:否;1:是)',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `idx_data_id` (`table_name`,`table_id`) USING BTREE,
  KEY `idx_reason` (`reason`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='信息变更日志';

DROP TABLE IF EXISTS `finance_id_card_info`;
CREATE TABLE `finance_id_card_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `real_name` varchar(50) NOT NULL COMMENT '用户姓名',
  `id_num` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `auth_status` int(1) NOT NULL DEFAULT '1' COMMENT '验证状态:0未完善，1已认证，(-userId)已保存',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `creater` varchar(20) DEFAULT NULL COMMENT '创建人',
  `updater` varchar(20) DEFAULT NULL COMMENT '更新人',
  `version` bigint(22) NOT NULL DEFAULT '1' COMMENT '版本号',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_user_id` (`user_id`),
  UNIQUE KEY `uniq_id_num_status` (`id_num`,`auth_status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='身份证信息表';

DROP TABLE IF EXISTS `finance_id_auth_info_log`;
CREATE TABLE `finance_id_auth_info_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `real_name` varchar(50) NOT NULL COMMENT '用户姓名',
  `id_num` varchar(20) NOT NULL COMMENT '身份证号',
  `account_no` varchar(50) NOT NULL COMMENT '银行卡号',
  `code` varchar(20) DEFAULT NULL COMMENT '返回码(10000:成功)',
  `message` varchar(255) DEFAULT NULL COMMENT '返回码说明',
  `data_state` varchar(1) DEFAULT NULL COMMENT '返回数据体状态(1:验证一致,2:验证不一致,3:异常情况)',
  `seq_no` varchar(20) DEFAULT NULL COMMENT '调用唯一标识',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `creater` varchar(20) DEFAULT NULL COMMENT '创建人',
  `updater` varchar(20) DEFAULT NULL COMMENT '更新人',
  `version` bigint(22) NOT NULL DEFAULT '1' COMMENT '版本号',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='身份认证记录表';

DROP TABLE IF EXISTS `finance_news_info`;
CREATE TABLE `finance_news_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(150) NOT NULL COMMENT '资讯标题',
  `tag1` varchar(50) DEFAULT NULL COMMENT '标签1',
  `tag2` varchar(20) DEFAULT NULL COMMENT '标签2',
  `banner_url` varchar(500) DEFAULT NULL COMMENT 'banner路径',
  `redirect_url` varchar(150) DEFAULT NULL COMMENT '跳转路径',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '启用状态:1,启用;0,失效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `creater` varchar(20) DEFAULT NULL COMMENT '创建人',
  `updater` varchar(20) DEFAULT NULL COMMENT '更新人',
  `version` bigint(22) NOT NULL DEFAULT '1' COMMENT '版本号',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='资讯信息表';

DROP TABLE IF EXISTS `finance_step_rewards_activity`;
CREATE TABLE `finance_step_rewards_activity` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` bigint(20)   NOT NULL  COMMENT '用户id',
	`invite_num` int(11)   NOT NULL DEFAULT 0  COMMENT '邀请人数',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`creater` varchar(20)  COMMENT '创建人',
	`updater` varchar(20)  COMMENT '更新人',
	`version_num` bigint(22)  NOT NULL  DEFAULT '1' COMMENT '版本号',
	`is_delete` int(1)   NOT NULL  DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
	PRIMARY KEY(`id`),
	UNIQUE KEY `uniq_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='阶梯奖励活动表';


DROP TABLE IF EXISTS `finance_step_rewards_amount`;
CREATE TABLE `finance_step_rewards_amount` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT  COMMENT '主键',
	`current_amount` decimal(18,6)  NOT NULL  COMMENT '当前总金额',
	`create_time` datetime  NOT NULL  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`creater` varchar(20)  COMMENT '创建人',
	`updater` varchar(20)  COMMENT '更新人',
	`version_num` bigint(22)  NOT NULL  DEFAULT '1' COMMENT '版本号',
	`is_delete` int(1)   NOT NULL  DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
	PRIMARY KEY(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='阶梯奖励总金额表';

DROP TABLE IF EXISTS `finance_activity_fixed_amount_main`;
CREATE TABLE `finance_activity_fixed_amount_main` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` bigint(20)  NOT NULL  COMMENT '发起人Id',
	`total_amount` decimal(18,6)  NOT NULL  COMMENT '总金额',
	`state` bigint(20)  NOT NULL  COMMENT '状态,1:已完成,0:待完成',
	`join_num` int(11)  NOT NULL  DEFAULT '0' COMMENT '已参加活动人数',
	`divided_amount` decimal(18,6)  NOT NULL  COMMENT '发起人拆得金额',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`is_delete` int(1)  NOT NULL  DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
	`creater` varchar(20) COMMENT '创建人',
	`updater` varchar(20) COMMENT '更新人',
	`version` int(11)  NOT NULL  DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`),
	KEY `idx_user_id` (`user_id`),
	UNIQUE KEY `idx_user_id_status` (`user_id`,`state`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='拆红包固定金额主表';

DROP TABLE IF EXISTS `finance_activity_fixed_amount_detail`;
CREATE TABLE `finance_activity_fixed_amount_detail` (
	`id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` bigint(20) COMMENT '参与人Id',
	`activity_id` bigint(20)  NOT NULL  COMMENT '活动Id',
	`divided_amount` decimal(18,6)  COMMENT '拆得金额',
	`create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`is_delete` int(1)  NOT NULL  DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
	`creater` varchar(20) COMMENT '创建人',
	`updater` varchar(20) COMMENT '更新人',
	`version` int(11)  NOT NULL  DEFAULT '1' COMMENT '版本号',
	PRIMARY KEY(`id`),
	KEY `idx_user_id` (`user_id`),
	KEY `idx_activity_id` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='拆红包固定金额明细表';
