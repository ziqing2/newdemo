-- ==============================================
-- MySQL 初始化脚本
-- 首次使用前执行: mysql -u root -p < init-mysql.sql
-- ==============================================

-- 创建数据库（如已存在则跳过）
CREATE DATABASE IF NOT EXISTS demo_db
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE demo_db;

-- 建表
CREATE TABLE IF NOT EXISTS t_user (
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(64)  NOT NULL,
    age   INT          NOT NULL,
    email VARCHAR(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入初始数据（幂等：已存在则跳过）
INSERT IGNORE INTO t_user (id, name, age, email) VALUES (1, '张三', 25, 'zhangsan@example.com');
INSERT IGNORE INTO t_user (id, name, age, email) VALUES (2, '李四', 30, 'lisi@example.com');
INSERT IGNORE INTO t_user (id, name, age, email) VALUES (3, '王五', 28, 'wangwu@example.com');
