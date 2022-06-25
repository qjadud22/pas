DROP TABLE IF EXISTS goods;

CREATE TABLE goods (
    goods_no  int NOT NULL DEFAULT 0 COMMENT '회원ID',
    goods_nm  varchar(100) DEFAULT NULL COMMENT '회원명',
    goods_const text COMMENT '1',
    com_id varchar(20) DEFAULT NULL COMMENT '2',
    reg_dm datetime DEFAULT NULL COMMENT '3',
    upd_dm datetime DEFAULT NULL COMMENT '4',
    PRIMARY KEY (goods_no)
);