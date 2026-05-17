-- 课件02 配套表结构

DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS dept;

CREATE TABLE dept (
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(64)  NOT NULL,
    company_id  BIGINT       NOT NULL,
    active      INT          NOT NULL DEFAULT 1 COMMENT '1=启用 0=停用',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE employee (
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    name            VARCHAR(64)  NOT NULL,
    department      VARCHAR(64)  NULL,
    department_id   BIGINT       NULL,
    position        VARCHAR(64)  NULL,
    salary          DECIMAL(12,2) NULL,
    status          INT          NOT NULL DEFAULT 1 COMMENT '0=待定 1=在职 2=离职',
    hire_date       DATE         NULL,
    create_time     DATETIME     NULL,
    update_time     DATETIME     NULL,
    description     TEXT         NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
