

CREATE TABLE IF NOT EXISTS `every`.`user` (
  `USER_ID` VARCHAR(30) NOT NULL COMMENT '用户ID',
  `USER_NAME` VARCHAR(45) NULL COMMENT '用户名字',
  `PHONE` VARCHAR(11) NULL COMMENT '电话',
  `REAL_NAME` VARCHAR(45) NULL COMMENT '真实名字',
  `STATUS` TINYINT(2) NULL,
  `REC_STATUS` TINYINT(2) NULL DEFAULT 1,
  `CREATED_DATE` DATETIME NULL,
  `MODIFIED_DATE` DATETIME NULL,
  `CREATED_BY` VARCHAR(45) NULL,
  `MODIFIED_BY` VARCHAR(45) NULL,
  PRIMARY KEY (`USER_ID`),
  INDEX `INDEX_PHONE` (`PHONE` ASC)
) COMMENT '用户信息表';



CREATE TABLE IF NOT EXISTS `every`.`user_t_account` (
  `USER_ACCOUNT_ID` VARCHAR(30) NOT NULL,
  `ACCOUNT_TYPE` TINYINT(2) NULL COMMENT '账号类型: 1: 登录名, 2. 手机号码, 3. 邮箱, 4. 微信, 5.QQ',
  `ACCOUNT_VALUE` VARCHAR(45) NULL,
  `ACCOUNT_PASSWORD` VARCHAR(45) NULL,
  `REC_STATUS` TINYINT(2) NULL DEFAULT 1,
  `CREATED_DATE` DATETIME NULL,
  `MODIFIED_DATE` DATETIME NULL,
  `CREATED_BY` VARCHAR(45) NULL,
  `MODIFIED_BY` VARCHAR(45) NULL,
  PRIMARY KEY (`USER_ACCOUNT_ID`),
  INDEX `INDEX_ACCOUNT_VALUE` (`ACCOUNT_VALUE` ASC)
) COMMENT '用户账户表';


CREATE TABLE IF NOT EXISTS `every`.`project_t_project` (
  `PROJECT_ID` VARCHAR(30) NOT NULL,
  `PROJECT_NAME` VARCHAR(100) NULL COMMENT '项目名字',
  `PROJECT_SUMMARY` VARCHAR(1000) NULL COMMENT '项目概要',
  `PROJECT_OWNER` VARCHAR(30) NOT NULL COMMENT '项目拥有者(责任人)',
  `STATUS_TYPE` TINYINT(2) DEFAULT 0 COMMENT '状态[0: 未开始, 1. 进行中, ]',
  `STATUS` TINYINT(2) DEFAULT 0 COMMENT '状态[0: 未开始, 1. 进行中, ]',
  `REC_STATUS` TINYINT(2) NULL DEFAULT 1,
  `CREATED_DATE` DATETIME NULL,
  `MODIFIED_DATE` DATETIME NULL,
  `CREATED_BY` VARCHAR(45) NULL,
  `MODIFIED_BY` VARCHAR(45) NULL,
  PRIMARY KEY (`PROJECT_ID`)
) COMMENT '项目信息表';

CREATE TABLE IF NOT EXISTS `every`.`project_t_module` (
  `MODULE_ID` VARCHAR(30) NOT NULL,
  `PROJECT_ID` VARCHAR(30) NOT NULL COMMENT '项目ID',
  `PARENT_MODULE_ID` VARCHAR(30) NULL COMMENT '父级别模块',
  `MODULE_NAME` VARCHAR(100) NOT NULL,
  `REC_STATUS` TINYINT(2) NULL DEFAULT 1,
  `CREATED_DATE` DATETIME NULL,
  `MODIFIED_DATE` DATETIME NULL,
  `CREATED_BY` VARCHAR(45) NULL,
  `MODIFIED_BY` VARCHAR(45) NULL,
  PRIMARY KEY (`MODULE_ID`)
) COMMENT '项目模块表';

CREATE TABLE IF NOT EXISTS `every`.`project_t_time_info` (
  `TIME_INFO_ID` VARCHAR(30) NOT NULL,
  `ENTITY_TYPE` TINYINT(1) NOT NULL COMMENT '关联实体类型[1:项目,2:模块]',
  `ENTITY_ID` VARCHAR(30) NOT NULL COMMENT '关联实体ID',
  `ESTIMATED_START_DATE` DATETIME NULL COMMENT '预计开始时间',
  `ESTIMATED_END_DATE` DATETIME NULL COMMENT '预计结束时间',
  `ESTIMATED_COST_TIME` INT(4) NULL COMMENT '预计花费时间',
  `ESTIMATED_TIME_TYPE` INT(4) NULL COMMENT '预计花费时间类型[minute, hour, day, month, year]',
  `REC_STATUS` TINYINT(2) NULL DEFAULT 1,
  `CREATED_DATE` DATETIME NULL,
  `MODIFIED_DATE` DATETIME NULL,
  `CREATED_BY` VARCHAR(45) NULL,
  `MODIFIED_BY` VARCHAR(45) NULL,
  PRIMARY KEY (`TIME_INFO_ID`)
) COMMENT '项目/模块时间信息';

CREATE TABLE IF NOT EXISTS `every`.`project_t_status_type` (
  `STATUS_TYPE_ID` VARCHAR(30) NOT NULL,
  `STATUS_TYPE_NAME` VARCHAR(30) NOT NULL,
  `STATUS_TYPE_DESC` VARCHAR(200) NULL COMMENT '状态类型描述',
  `REC_STATUS` TINYINT(2) NULL DEFAULT 1,
  `CREATED_DATE` DATETIME NULL,
  `MODIFIED_DATE` DATETIME NULL,
  `CREATED_BY` VARCHAR(45) NULL,
  `MODIFIED_BY` VARCHAR(45) NULL,
  PRIMARY KEY (`STATUS_TYPE_ID`)
) COMMENT '项目/模块状态信息';

CREATE TABLE IF NOT EXISTS `every`.`project_t_status` (
  `STATUS_ID` VARCHAR(30) NOT NULL,
  `STATUS_TYPE_ID` VARCHAR(30) NOT NULL,
  `STATUS_VALUE` TINYINT(2) NOT NULL COMMENT '状态值',
  `STATUS_NAME` VARCHAR(20) NOT NULL COMMENT '状态名字',
  `STATUS_DESC` VARCHAR(200) NULL COMMENT '状态信息描述',
  `REC_STATUS` TINYINT(2) NULL DEFAULT 1,
  `CREATED_DATE` DATETIME NULL,
  `MODIFIED_DATE` DATETIME NULL,
  `CREATED_BY` VARCHAR(45) NULL,
  `MODIFIED_BY` VARCHAR(45) NULL,
  PRIMARY KEY (`STATUS_ID`)
) COMMENT '项目/模块状态信息';



ALTER TABLE `every`.`user_t_account`
  ADD COLUMN `USER_ID` VARCHAR(30) NULL AFTER `ACCOUNT_PASSWORD`;