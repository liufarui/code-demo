-- 增加列column_B
ALTER TABLE table_A ADD column_B VARCHAR(30);

ALTER TABLE table_A ADD column_B VARCHAR(20) NULL, column_C INT NULL;

-- 修改列类型
ALTER TABLE table_A ALTER column VARCHAR(30);

-- 修改列名称
EXEC sp_rename 'table_A.column_B' , 'column_C';

-- 删除列
ALTER TABLE table_A DROP column column_B;

-- 指定表中某列默认数据
ALTER TABLE table_A alter column column_B SET DEFAULT "XXXXXX";

-- 添加唯一性约束
alter table table_A add unique(name)
alter table table_A add unique key(name, age);

show index from table_A;

ALTER TABLE app_biz_unit ADD UNIQUE KEY uk_biz_unit_app(biz_unit_code, app_key);
ALTER TABLE app_biz_unit ADD UNIQUE uk_biz_unit_app(biz_unit_code, app_key);
