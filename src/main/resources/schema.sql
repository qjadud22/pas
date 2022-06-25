DROP TABLE IF EXISTS goods;

CREATE TABLE company (
    com_id varchar(20) NOT NULL COMMENT '업체 아이디',
    com_nm varchar(20) NOT NULL COMMENT '업체명',
    PRIMARY KEY (com_id)
);

CREATE TABLE goods (
    goods_no int NOT NULL DEFAULT 0 AUTO_INCREMENT COMMENT '상품번호',
    goods_nm varchar(100) DEFAULT NULL COMMENT '상품명',
    goods_const text COMMENT '상품설명',
    com_id varchar(20) DEFAULT NULL COMMENT '업체 아이디',
    reg_dm datetime DEFAULT NULL COMMENT '상품정보 최초등록일시',
    upd_dm datetime DEFAULT NULL COMMENT '수정일시',
    PRIMARY KEY (goods_no),
    foreign key (com_id) references company(com_id)
);

INSERT INTO company
    (com_id, com_nm)
values
    ('EE115', '옥션'),
    ('EE116', '11번가');

INSERT INTO goods
    (goods_nm, goods_const, com_id, reg_dm, upd_dm)
values
    ('상품1', '상품 설명1', 'EE115', now(), now()),
    ('상품2', '상품 설명2', 'EE115', now(), now()),
    ('상품3', '상품 설명3', 'EE115', now(), now());