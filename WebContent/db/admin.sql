DROP TABLE ADMIN;
CREATE TABLE ADMIN(
    AID VARCHAR2(50) PRIMARY KEY,
    APW VARCHAR2(50) NOT NULL,
    ANAME VARCHAR2(50) NOT NULL
);

-- dummy data
INSERT INTO ADMIN (AID, APW, ANAME) VALUES ('admin', '111', '임재혁');

-- DAO에 넣을 sql

-- admin loginCheck
SELECT * FROM ADMIN WHERE AID='admin' AND APW = '111';
-- admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AID='admin';

SELECT * FROM ADMIN;

COMMIT;