DROP TABLE REVIEW;
DROP SEQUENCE REVIEW_SEQ;

CREATE SEQUENCE REVIEW_SEQ
MAXVALUE 9999999
NOCACHE
NOCYCLE;

CREATE TABLE REVIEW(
    RENO NUMBER(7) PRIMARY KEY,
    MID VARCHAR2(50) REFERENCES MEMBER(MID),
    AID VARCHAR2(50) REFERENCES ADMIN(AID),
    PNO NUMBER(7) REFERENCES PRODUCTS(PNO),
    RETITLE VARCHAR2(100) NOT NULL,
    RECONTENT VARCHAR2(4000),
    REPHOTO VARCHAR2(50),
    REPHOTO2 VARCHAR2(50),
    REHIT NUMBER(7) DEFAULT 0,
    REREF NUMBER(7),
    RERE_STEP NUMBER(7),
    RERE_LEVEL NUMBER(7),
    RERDATE DATE DEFAULT SYSDATE);
    
    
SELECT * FROM REVIEW ORDER BY REREF DESC, RERE_STEP; 

-- 고객 리뷰 작성
INSERT INTO REVIEW (RENO, MID, AID, PNO, RETITLE, RECONTENT, REPHOTO, REPHOTO2, REREF, RERE_STEP, RERE_LEVEL) VALUES
    (REVIEW_SEQ.NEXTVAL, 'ccc', null, 1, '만족스러운 구매입니다.', '겁나 잘 샀어요. 만족합니다.', 'review1.jpg', 'review1.jpg', REVIEW_SEQ.CURRVAL, 0, 0);
    
INSERT INTO REVIEW (RENO, MID, AID, PNO, RETITLE, RECONTENT, REPHOTO, REPHOTO2, REREF, RERE_STEP, RERE_LEVEL) VALUES
    (REVIEW_SEQ.NEXTVAL, 'aaa', null, 2, '만족스러운 구매입니다.', '겁나 잘 샀어요. 만족합니다.', 'review1.jpg', 'review1.jpg', REVIEW_SEQ.CURRVAL, 0, 0);    
    
INSERT INTO REVIEW (RENO, MID, AID, PNO, RETITLE, RECONTENT, REPHOTO, REPHOTO2, REREF, RERE_STEP, RERE_LEVEL) VALUES
    (REVIEW_SEQ.NEXTVAL, 'bbb', null, 3, '사장님 땡큐', '겁나 잘 샀어요. 만족합니다.', 'review1.jpg', 'review1.jpg', REVIEW_SEQ.CURRVAL, 0, 0);    


-- 글갯수(고객들이 보는)
SELECT COUNT(*) FROM REVIEW WHERE PNO = 11;

-- 글갯수(관리자가 보는)
SELECT COUNT(*) FROM REVIEW;

-- 고객들이 보는 글 목록(startRow, endRow)
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
    (SELECT V.*, PNAME, PPHOTO FROM REVIEW V, PRODUCTS P WHERE V.PNO = P.PNO AND P.PNO=11 ORDER BY REREF DESC, RERE_STEP) A)
    WHERE RN BETWEEN 2 AND 4;

-- 관리자가 보는 글 목록(startRow, endRow)
SELECT * FROM (SELECT ROWNUM RN, A.* FROM 
    (SELECT V.*, PNAME, PPHOTO FROM REVIEW V, PRODUCTS P WHERE V.PNO = P.PNO ORDER BY REREF DESC, RERE_STEP) A)
    WHERE RN BETWEEN 1 AND 4;
    
-- 히트 수 하나 올리기
UPDATE REVIEW SET REHIT = REHIT + 1 WHERE RENO = 6;

-- RENO로 리뷰 가져오기
SELECT R.*, PNAME, PPHOTO FROM REVIEW R, PRODUCTS P WHERE R.PNO = P.PNO AND RENO = 6;

-- 리뷰 삭제하기
DELETE FROM REVIEW WHERE RENO = 6;

-- 리뷰 답글 전 STEP A
UPDATE REVIEW SET RERE_STEP = RERE_STEP + 1 WHERE REREF = 1 AND RERE_STEP>0;

-- 관리자 리뷰 답글
INSERT INTO REVIEW (RENO, MID, AID, PNO, RETITLE, RECONTENT, REPHOTO, REPHOTO2, REREF, RERE_STEP, RERE_LEVEL) VALUES
    (REVIEW_SEQ.NEXTVAL, null, 'admin', 2, '감사합니다.', '다음 구매도 부탁드립니다.', null, null, 8, 1, 1);

commit;

