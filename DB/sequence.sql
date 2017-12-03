
DROP TABLE IF EXISTS sys_t_sequence;
CREATE TABLE sys_t_sequence(
	`NAME` VARCHAR(50) NOT NULL,
    `CURRENT_VALUE` INT NOT NULL DEFAULT 0,
    `INCREMENT` INT NOT NULL DEFAULT 1,
    PRIMARY KEY (NAME)
) ENGINE=InnoDB;

-- 获取当前sequence值
DROP FUNCTION IF EXISTS currval;
DELIMITER $
CREATE FUNCTION currval(seq_name VARCHAR(50))
	RETURNS INTEGER
    LANGUAGE SQL
    DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
BEGIN
	DECLARE value INTEGER;
    set value = null;
    SELECT current_value INTO value FROM sequence WHERE NAME = seq_name;
    RETURN value;
END
$
DELIMITER ;


-- nextval函数, 生成下一个sequence值
DROP FUNCTION IF EXISTS nextval;
DELIMITER $
CREATE FUNCTION nextval(seq_name VARCHAR(50)) RETURNS INTEGER
    LANGUAGE SQL
    DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
BEGIN
	DECLARE curr_val INTEGER;
    DECLARE i_increment INTEGER;
    SELECT `CURRENT_VALUE`, `INCREMENT` INTO curr_val, i_increment FROM sys_t_sequence WHERE `NAME` = seq_name;
    if curr_val is null OR curr_val = 0 then
		insert into sys_t_sequence(`NAME`) values (seq_name);
        SELECT `CURRENT_VALUE`, `INCREMENT` INTO curr_val, i_increment FROM sys_t_sequence WHERE `NAME` = seq_name;
	end if;
    set curr_val = curr_val + i_increment;
	update sys_t_sequence set `CURRENT_VALUE` = curr_val where `NAME` = seq_name;
    RETURN curr_val;
END
$
DELIMITER ;

-- 获取sequence函数, 前端调用此函数获取sequence
DROP FUNCTION IF EXISTS getSequence;
DELIMITER $ --
CREATE FUNCTION getSequence(seq_name VARCHAR(50), prefix VARCHAR(20), length INTEGER)
	RETURNS VARCHAR(50)
    LANGUAGE SQL
    DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
BEGIN
	DECLARE v_currvalue VARCHAR(50);
    set v_currvalue = nextval(seq_table_name, seq_name);
    if LENGTH(v_currvalue) < length then
		set v_currvalue = lpad(v_currvalue, length, 0);
    end if;
RETURN concat(UPPER(prefix), v_currvalue) ;
END
$
DELIMITER ;