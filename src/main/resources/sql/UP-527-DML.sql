use jinrongjia;
alter table finance_product_main add settle_unit VARCHAR(8) NOT NULL COMMENT '结算单位，month:月，week:周,day:日';
alter table finance_product_main add settle_period int(5) NOT NULL COMMENT '结算周期';
alter table finance_product_main add settle_day VARCHAR(5) NOT NULL COMMENT '结算日';
alter table finance_product_main add pre_pay_rate decimal(18,4)  COMMENT '预付比例';
alter table finance_product_main add product_desc VARCHAR(1000)  COMMENT '产品说明';
alter table finance_product_main add promotion_url VARCHAR(500)  COMMENT '推广页';

insert into finance_dictionary_info (business_code, business_name, business_type) values (1, '主页', 1);
insert into finance_dictionary_info (business_code, business_name, business_type) values (2, '排行榜', 1);
insert into finance_dictionary_info (business_code, business_name, business_type) values (3, '发现', 1);
insert into finance_dictionary_info (business_code, business_name, business_type) values (4, '首页', 1);
insert into finance_dictionary_info (business_code, business_name, business_type) values (5, '保险页', 1);

insert into finance_dictionary_info (business_code, business_name, business_type) values (1, '排行榜上BANNER', 2);
insert into finance_dictionary_info (business_code, business_name, business_type) values (2, '发现页上BANNER', 2);
insert into finance_dictionary_info (business_code, business_name, business_type) values (3, '推广页BANNER', 2);
insert into finance_dictionary_info (business_code, business_name, business_type) values (4, '活动入口BANNER', 2);
insert into finance_dictionary_info (business_code, business_name, business_type) values (5, '金币兑换入口BANNER', 2);
insert into finance_dictionary_info (business_code, business_name, business_type) values (6, '登录背景BANNER', 2);
insert into finance_dictionary_info (business_code, business_name, business_type) values (7, '保险页BANNER', 2);
insert into finance_dictionary_info (business_code, business_name, business_type) values (8, '主页上BANNER', 2);


insert into finance_banner_info (page_code, banner_type, title, seq_no, banner_url, redirect_url, creator, updator)
    values (5,7,'新概念保险',1,'xx','xx' ,'','');
insert into finance_banner_info (page_code, banner_type, title, seq_no, banner_url, redirect_url, creator, updator)
    values (5,7,'众安保险',2,'xx','xx' ,'','');
insert into finance_banner_info (page_code, banner_type, title, seq_no, banner_url, redirect_url, creator, updator)
    values (5,7,'代缴社保公积金',3,'xx','xx' ,'','');

INSERT INTO finance_coin_game ( task_type, task_name, effect, logo_url, redirect_url, seq_no, game_type,num )
VALUES
	( 'earlySign', '早起打卡', '+', 'xxxx', 'xxxxx', 1, 2,100 );


alter table finance_credit_card_detail add max_amount  varchar(100) NOT NULL COMMENT '最高额度';
alter table finance_credit_card_detail add audit_length  varchar(200) NOT NULL COMMENT '审核时长';

INSERT INTO finance_coin_game ( task_type, task_name, effect, logo_url, redirect_url, seq_no, game_type,num )
 VALUES
	( 'everydayClock', 'everydayClock', '+', 'xxxx', 'xxxxx', 1, 1,100 );

INSERT INTO finance_coin_game ( task_type, task_name, effect, logo_url, redirect_url, seq_no, game_type,num )
 VALUES
	( 'newRegisterTask', 'perfectInfoTask', '+', 'xxxx', 'xxxxx', 1, 1,1500 );

INSERT INTO finance_coin_game ( task_type, task_name, effect, logo_url, redirect_url, seq_no, game_type,num )
 VALUES
	( 'newRegisterTask', 'withdrawalTask', '+', 'xxxx', 'xxxxx', 2, 1,500 );

INSERT INTO finance_coin_game ( task_type, task_name, effect, logo_url, redirect_url, seq_no, game_type,num )
 VALUES
	( 'growTask', 'invitePerson', '+', 'xxxx', 'xxxxx', 1, 1,800 );

INSERT INTO finance_coin_game ( task_type, task_name, effect, logo_url, redirect_url, seq_no, game_type,num )
 VALUES
	( 'exchangeFee', '抵扣手续费', '-', 'xxxx', 'xxxxx', 1, 2,2 );

INSERT INTO `jinrongjia`.`finance_step_rewards_amount` (`current_amount`)
VALUES
(200000.000000);

ALTER TABLE `finance_user_invite_info`ADD `invite_type`  int(2) NOT NULL DEFAULT 0 COMMENT '邀请类型(0:普通邀请，1:阶梯红包邀请，2:固定红包邀请)';

UPDATE finance_product_main set settle_unit = 'month',settle_period =1 , settle_day ='15', pre_pay_rate = 0 where 1=1;

