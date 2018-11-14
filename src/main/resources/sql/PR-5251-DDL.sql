DROP TABLE IF EXISTS `finance_credit_card_detail`;

CREATE TABLE `finance_credit_card_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `pass_rate` varchar(10) NOT NULL COMMENT '通过率',
	`reback_cash_desc` varchar(100) NOT NULL COMMENT '返现说明',
  `detail_page_url` varchar(500) NOT NULL COMMENT '详情页',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='信用卡产品明细表';


DROP TABLE IF EXISTS `finance_excel_all`;

CREATE TABLE `finance_excel_all` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `batch_no` varchar(50) NOT NULL COMMENT '上传批次号',
  `creater` varchar(50) DEFAULT NULL COMMENT '上传用户的id',
  `updater` varchar(50) DEFAULT NULL COMMENT '更新人',
  `details_num` bigint(20) DEFAULT '0' COMMENT '本次上传总数',
  `details_valid_num` bigint(20) DEFAULT '0' COMMENT '本次上传有效数',
  `fail_num` bigint(20) DEFAULT '0' COMMENT '失败的数量',
  `status` varchar(2) DEFAULT '0' COMMENT '状态(0=有效；1=无效;)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `version_num` bigint(22) DEFAULT '1' COMMENT '版本号',
  `is_flag` varchar(2) DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_batch_no` (`batch_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='excel数据总表';


DROP TABLE IF EXISTS `finance_excel_detail`;

CREATE TABLE `finance_excel_detail` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `all_id` varchar(20) NOT NULL COMMENT '总表批次id',
  `prod_name` varchar(200) DEFAULT NULL COMMENT '产品名称',
  `user_name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `money` decimal(18,6) DEFAULT NULL COMMENT '计算金额',
  `error_messge` varchar(100) DEFAULT NULL COMMENT '错误日志',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `type` varchar(8) DEFAULT NULL COMMENT '分润类型：1.百分比、0.金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `version_num` bigint(22) DEFAULT '1' COMMENT '版本号',
  `is_flag` varchar(2) DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='excel数据明细表';


DROP TABLE IF EXISTS `finance_financial_product_detail`;

CREATE TABLE `finance_financial_product_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `mark` varchar(100) NOT NULL COMMENT '标签',
  `ave_revenue` varchar(10) NOT NULL COMMENT '平均收益',
  `product_background` varchar(50) NOT NULL COMMENT '产品背景',
  `grade` varchar(5) NOT NULL COMMENT '级别',
  `background_strength` varchar(50) NOT NULL COMMENT '背景实力',
  `risk_control` varchar(50) NOT NULL COMMENT '平台风控',
  `operation_capability` varchar(50) NOT NULL COMMENT '运营能力',
  `start_amount` varchar(100) NOT NULL COMMENT '起投金额',
  `start_period` varchar(100) NOT NULL COMMENT '起投期限',
  `reback_name` varchar(100) NOT NULL COMMENT '金榕返名称',
  `reback_value` varchar(100) NOT NULL COMMENT '金榕返值',
  `total_return` varchar(100) NOT NULL COMMENT '总回报',
  `cashback_rule` varchar(100) NOT NULL COMMENT '返现规则',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creater` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updater` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='理财产品明细表';



DROP TABLE IF EXISTS `finance_loan_detail`;

CREATE TABLE `finance_loan_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `mark1` varchar(100) NOT NULL COMMENT '标签1',
  `mark2` varchar(100) NOT NULL COMMENT '标签2',
  `amount_scope` varchar(30) NOT NULL COMMENT '额度范围',
  `date_scope` varchar(30) NOT NULL COMMENT '期限范围',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='贷款产品明细表';


DROP TABLE IF EXISTS `finance_log`;

CREATE TABLE `finance_log` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `request` varchar(100) NOT NULL COMMENT '请求体',
  `response` varchar(100) DEFAULT NULL COMMENT '返回体',
  `type` varchar(10) DEFAULT NULL COMMENT '请求类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `version_num` bigint(22) NOT NULL DEFAULT '1' COMMENT '版本号',
  `is_flag` varchar(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='提现log请求表';


DROP TABLE IF EXISTS `finance_operation_record`;

CREATE TABLE `finance_operation_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `real_name` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `mobile_num` varchar(20) NOT NULL COMMENT '手机号',
  `product_id` bigint(20) NOT NULL COMMENT '产品id',
  `product_name` varchar(20) NOT NULL COMMENT '产品名称',
  `product_type` int(2) NOT NULL COMMENT '产品类型',
  `operation_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `status` varchar(2) NOT NULL DEFAULT '0' COMMENT '状态（0=未结算，1=已结算）',
  `reserved_field` varchar(20) DEFAULT NULL COMMENT '预留字段',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `creater` varchar(20) DEFAULT NULL COMMENT '创建人',
  `updater` varchar(20) DEFAULT NULL COMMENT '更新人',
  `version_num` bigint(22) NOT NULL DEFAULT '1' COMMENT '版本号',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`)
  KEY `index_product_id_user_id_create_time` (`product_id`,`user_id`,`create_time`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='操作记录表';


DROP TABLE IF EXISTS `finance_order`;

CREATE TABLE `finance_order` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serial_id` varchar(30) NOT NULL COMMENT '流水编号',
  `title` varchar(100) DEFAULT NULL COMMENT '打款订单标题',
  `user_id` bigint(22) NOT NULL COMMENT '用户id',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名称',
  `trans_type` varchar(30) NOT NULL COMMENT '交易类型',
  `money` decimal(18,6) NOT NULL DEFAULT '0.000000' COMMENT '交易金额',
  `bank_code` varchar(20) DEFAULT NULL COMMENT '开户行id',
  `bank_fullname` varchar(100) DEFAULT NULL COMMENT '提现银行卡全称',
  `phone` varchar(30) DEFAULT NULL COMMENT '提现手机号',
  `bank_type` varchar(10) DEFAULT '1' COMMENT '卡类型，1.借记卡，0.信用卡',
  `audit_name` varchar(120) DEFAULT NULL COMMENT '审核人',
  `audit_id` bigint(22) DEFAULT NULL COMMENT '审核人id',
  `type` varchar(30) DEFAULT NULL COMMENT '交易类型1.提现，2.充值',
  `status` varchar(5) NOT NULL DEFAULT '0' COMMENT '提现状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `version_num` bigint(22) NOT NULL DEFAULT '1' COMMENT '版本号',
  `is_flag` varchar(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  `error_code` varchar(19) DEFAULT NULL COMMENT '错误码',
  `error_message` varchar(100) DEFAULT NULL COMMENT '错误详情',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_serial_id` (`serial_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='充值/提现流水表';


DROP TABLE IF EXISTS `finance_product_main`;

CREATE TABLE `finance_product_main` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_name` varchar(20) NOT NULL COMMENT '产品名称',
  `type` int(2) NOT NULL COMMENT '产品分类，1:理财，2:信用卡，3:贷款，4:保险',
  `redirect_url` varchar(500) NOT NULL COMMENT '跳转链接',
  `amount_type` int(2) NOT NULL COMMENT '金额类型，1:金额值，2:百分比',
  `total_bonus` decimal(18,6) NOT NULL COMMENT '总奖金',
  `terminal_bonus` decimal(18,6) NOT NULL COMMENT '终端奖金',
  `direct_bonus` decimal(18,6) NOT NULL COMMENT '直推奖金',
  `indirect_bonus` decimal(18,6) NOT NULL COMMENT '间推奖金',
  `logo_url` varchar(500) NOT NULL COMMENT 'LOGO',
  `seq_no` int(10) NOT NULL COMMENT '排序',
  `cashback_date` varchar(50) NOT NULL COMMENT '返现日期',
  `is_show` int(1) NOT NULL DEFAULT '1' COMMENT '是否显示(0:否；1:是)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_product_name` (`product_name`),
  KEY `idx_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='产品数据主表';


DROP TABLE IF EXISTS `finance_product_main_page`;

CREATE TABLE `finance_product_main_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_name` varchar(20) NOT NULL COMMENT '产品名称',
  `category` int(2) NOT NULL COMMENT '分类，1:金榕家集合页,2:注册宝',
  `redirect_url` varchar(500) NOT NULL COMMENT '跳转链接',
  `product_des` varchar(50) NOT NULL COMMENT '产品描述',
  `product_type` int(2) NOT NULL COMMENT '产品类型,1:贷款,2:信用卡,3:注册返现,4:下载APP返现,5:核卡返现,6:首刷返现',
  `max_amount` varchar(20) NOT NULL COMMENT '最高金额',
  `fee_rate` varchar(10) NOT NULL COMMENT '费率',
  `logo_url` varchar(500) NOT NULL COMMENT 'LOGO',
  `seq_no` int(10) NOT NULL COMMENT '排序',
  `is_show` int(1) NOT NULL DEFAULT '1' COMMENT '是否显示(0:否；1:是)',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `idx_product_name` (`product_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='金榕家集合页和注册宝信息表';


DROP TABLE IF EXISTS `finance_profit`;

CREATE TABLE `finance_profit` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `detail_id` bigint(22) NOT NULL COMMENT 'excel明细id，产生这条分润的id',
  `prod_id` bigint(22) NOT NULL COMMENT '产品id',
  `prod_name` varchar(190) NOT NULL COMMENT '产品名称',
  `terminal_name` varchar(20) DEFAULT NULL COMMENT '终端用户名称',
  `terminal_id` bigint(22) NOT NULL COMMENT '终端用户id',
  `terminal_phone` varchar(20) NOT NULL COMMENT '终端用户手机号',
  `terminal_money` decimal(18,6) NOT NULL COMMENT '佣金金额',
  `parent_id` bigint(22) DEFAULT NULL COMMENT '父级用户id',
  `parent_phone` varchar(20) DEFAULT NULL COMMENT '父级用户手机号',
  `parent_name` varchar(20) DEFAULT NULL COMMENT '父级名称',
  `parent_money` decimal(18,2) DEFAULT NULL COMMENT '父级佣金金额',
  `grand_parent_id` bigint(22) DEFAULT NULL COMMENT '间接用户id',
  `grand_parent_phone` varchar(20) DEFAULT NULL COMMENT '间接用户手机号',
  `grand_parent_name` varchar(20) DEFAULT NULL COMMENT '间接用户名',
  `grand_parent_money` decimal(18,6) DEFAULT NULL COMMENT '间接佣金金额',
  `status` varchar(2) DEFAULT '0' COMMENT '用户状态(0=有效；1=无效)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `version_num` bigint(22) DEFAULT '1' COMMENT '版本号',
  `is_flag` varchar(2) DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='分润明细表';



DROP TABLE IF EXISTS `finance_question_and_answer`;

CREATE TABLE `finance_question_and_answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(500) NOT NULL COMMENT '标题',
  `content` varchar(2000) NOT NULL COMMENT '内容',
  `image_url` varchar(150) DEFAULT NULL COMMENT '图片路径',
  `seq_no` int(10) NULL DEFAULT 0 COMMENT '排序',
  `reserved_field` varchar(50) DEFAULT NULL COMMENT '预留字段',
  `seq_no` int(10) NOT NULL COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creater` varchar(20) DEFAULT NULL COMMENT '创建人',
  `updater` varchar(20) DEFAULT NULL COMMENT '更新人',
  `version_num` bigint(22) NOT NULL DEFAULT '1' COMMENT '版本号',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='Q&A表';

DROP TABLE IF EXISTS `finance_sms_send_log`;

CREATE TABLE `finance_sms_send_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sms_type` varchar(40) NOT NULL COMMENT '短信类型、用途（login、changePayPwd）',
  `mobile_num` varchar(20) NOT NULL COMMENT '手机号',
  `header` varchar(40) NOT NULL DEFAULT '' COMMENT '短信签名、头',
  `body` varchar(1000) NOT NULL COMMENT '短信内容',
  `send_success` int(1) NOT NULL DEFAULT '1' COMMENT '发送是否成功',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='短信发送日志表';


DROP TABLE IF EXISTS `finance_user_account`;

CREATE TABLE `finance_user_account` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(22) NOT NULL COMMENT '用户id',
  `can_withdraw_money` decimal(18,6) DEFAULT '0.000000' COMMENT '可提现金额',
  `withdrawed_money` decimal(18,6) DEFAULT '0.000000' COMMENT '已提现金额',
  `income_money` decimal(18,6) DEFAULT '0.000000' COMMENT '收入金额',
  `sum_charge_money` decimal(18,6) DEFAULT '0.000000' COMMENT '总收入金额',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `status` varchar(5) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `version_num` bigint(22) NOT NULL DEFAULT '1' COMMENT '版本号',
  `is_flag` varchar(2) NOT NULL DEFAULT '0' COMMENT '是否删除（0=未删除，1=已删除）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户账户表';


DROP TABLE IF EXISTS `finance_user_bank_card_info`;

CREATE TABLE `finance_user_bank_card_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `bank_name` varchar(50) NOT NULL COMMENT '银行全名',
  `account_name` varchar(50) NOT NULL COMMENT '收款账户真实姓名',
  `account_no` varchar(50) NOT NULL COMMENT '银行卡号',
  `account_mobile` varchar(20) DEFAULT NULL COMMENT '银行绑定的手机号',
  `is_default` int(1) NOT NULL DEFAULT '1' COMMENT '是否是默认卡',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_user_account_no` (`user_id`,`account_no`)
  KEY `index_card_user_id` (`user_id`),
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户银行卡信息';


DROP TABLE IF EXISTS `finance_user_first_login_log`;

CREATE TABLE `finance_user_first_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `platform_code` varchar(20) NOT NULL COMMENT '注册平台编码',
  `platform_detail` varchar(40) NOT NULL DEFAULT '' COMMENT '注册平台详情',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户首次登录日志表';


DROP TABLE IF EXISTS `finance_user_info`;

CREATE TABLE `finance_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `mobile_num` varchar(20) NOT NULL COMMENT '手机号',
  `invite_code` varchar(32) NOT NULL COMMENT '邀请码',
  `status` varchar(20) NOT NULL DEFAULT '1' COMMENT '用户状态（1：正常）',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_mobile_num` (`mobile_num`),
  UNIQUE KEY `uniq_invite_code` (`invite_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本信息表';


DROP TABLE IF EXISTS `finance_user_invite_info`;

CREATE TABLE `finance_user_invite_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `parent_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '邀请人用户Id',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_parent_id` (`parent_user_id`),
  UNIQUE KEY `uniq_user_id_parent_id` (`user_id`,`parent_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户邀请人关系';


DROP TABLE IF EXISTS `finance_user_login_log`;

CREATE TABLE `finance_user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `login_name` varchar(20) NOT NULL COMMENT '登录名称',
  `login_status` varchar(10) NOT NULL DEFAULT '' COMMENT '登录状态',
  `login_desc` varchar(50) NOT NULL DEFAULT '' COMMENT '登录结果描述',
  `ip` varchar(128) DEFAULT '' COMMENT '外网IP',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '设备信息',
  `platform_code` varchar(20) NOT NULL DEFAULT '' COMMENT '注册平台编码',
  `platform_detail` varchar(40) NOT NULL DEFAULT '' COMMENT '注册平台详情',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户登录日志表';



DROP TABLE IF EXISTS `finance_user_pwd_info`;

CREATE TABLE `finance_user_pwd_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `pwd_type` varchar(20) NOT NULL COMMENT '密码类型（withdraw、login）',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_user_pwd_type` (`user_id`,`pwd_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户密码表';


DROP TABLE IF EXISTS `finance_user_register_channel_info`;

CREATE TABLE `finance_user_register_channel_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `channel_code` varchar(50) NOT NULL DEFAULT '' COMMENT '渠道编码',
  `channel_detail` varchar(50) NOT NULL DEFAULT '' COMMENT '渠道详情',
  `platform_code` varchar(20) NOT NULL DEFAULT '' COMMENT '注册平台编码',
  `platform_detail` varchar(40) NOT NULL DEFAULT '' COMMENT '注册平台详情',
  `approach1` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道1',
  `approach2` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道2',
  `approach3` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道3',
  `approach4` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道4',
  `approach5` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道5',
  `approach6` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道6',
  `approach7` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道7',
  `approach8` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道8',
  `approach9` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道9',
  `approach10` varchar(40) NOT NULL DEFAULT '' COMMENT '渠道10',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_user_id` (`user_id`)
  KEY `index_channel_code` (`channel_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户注册渠道信息表';


DROP TABLE IF EXISTS `finance_user_register_log`;

CREATE TABLE `finance_user_register_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `mobile_num` varchar(20) NOT NULL COMMENT '手机号',
  `ip` varchar(128) DEFAULT '' COMMENT '外网IP',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '设备信息',
  `is_delete` int(1) NOT NULL DEFAULT '0' COMMENT '是否已删除(0:否；1:是)',
  `creator` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `updator` varchar(20) NOT NULL DEFAULT '' COMMENT '更新人',
  `version` int(11) NOT NULL DEFAULT '1' COMMENT '版本号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `uniq_mobile_num` (`mobile_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户注册日志表';
