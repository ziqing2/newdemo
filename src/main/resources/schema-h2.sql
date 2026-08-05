-- 建表语句
CREATE TABLE IF NOT EXISTS t_user (
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(64)  NOT NULL,
    age   INT          NOT NULL,
    email VARCHAR(128) NOT NULL
);
