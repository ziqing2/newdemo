-- 初始化测试数据
MERGE INTO t_user (id, name, age, email) KEY(id) VALUES (1, '张三', 25, 'zhangsan@example.com');
MERGE INTO t_user (id, name, age, email) KEY(id) VALUES (2, '李四', 30, 'lisi@example.com');
MERGE INTO t_user (id, name, age, email) KEY(id) VALUES (3, '王五', 28, 'wangwu@example.com');
-- 重置自增序列，避免后续插入时主键冲突
ALTER TABLE t_user ALTER COLUMN id RESTART WITH 4;
