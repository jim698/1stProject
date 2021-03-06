DROP TABLE PRODUCTS;
DROP SEQUENCE PRODUCTS_SEQ;

CREATE SEQUENCE PRODUCTS_SEQ
MAXVALUE 9999999
NOCACHE
NOCYCLE;

CREATE TABLE PRODUCTS(
    PNO NUMBER(7) PRIMARY KEY,
    PKINDS VARCHAR2(50) NOT NULL,
    PNAME VARCHAR2(50) NOT NULL,
    PPHOTOLOGO VARCHAR2(50) DEFAULT 'youngman.jpg',
    PPHOTO VARCHAR2(50),
    PPHOTO2 VARCHAR2(50),
    PCONTENT VARCHAR2(4000),
    PCNT NUMBER(7) NOT NULL,
    PPRICE NUMBER(7) NOT NULL,
    PDISCOUNT NUMBER(3) DEFAULT 0,
    PRDATE DATE DEFAULT SYSDATE);
    
 -- 상품 등록
INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '새장', '이태리새장', NULL, 'cage1.jpg', 'cage1.jpg', '이태리에서 직접 공수해 온 최고급 새장입니다.', 4, 35000, 10 );
    
INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '모이', '이태리모이', NULL, 'food1.jpg', 'food1.jpg', '이태리에서 직접 공수해 온 최고급 새장입니다.',5, 21000, 20 );
    
INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '장난감', '이태리장난감', NULL, 'toy1.jpg', 'toy1.jpg', '이태리에서 직접 공수해 온 최고급 새장입니다.', 7, 20000, 30 );
    
INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '새장', '날림장', NULL, 'cage4.jpg', 'cage4.jpg', '이태리에서 직접 공수해 온 최고급 날림장입니다.', 6, 40000, 0);   
    
INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '새장', '날림장', '', 'cage2.jpg', 'cage2.jpg', '이태리에서 직접 공수해 온 최고급 날림장입니다.', 6, 40000, 0);    
    
INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '새장', '철장', '', 'cage3.jpg', 'cage3.jpg', '이태리에서 직접 공수해 온 최고급 철장입니다.', 6, 5000, 0);

INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '모이', '프랑스모이', NULL, 'food2.jpg', 'food2.jpg', '프랑스에서 직접 공수해 온 최고급 모이입니다.',5, 21000, 20 );
    
INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '모이', '스위스모이', NULL, 'food3.jpg', 'food3.jpg', '스위스에서 직접 공수해 온 최고급 모이입니다.',5, 21000, 20 );    
    
INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '장난감', '프랑스장난감', NULL, 'toy2.jpg', 'toy2.jpg', '프랑스에서 직접 공수해 온 최고급 장난감입니다.',5, 23000, 10 );
    
INSERT INTO PRODUCTS (PNO, PKINDS, PNAME, PPHOTOLOGO, PPHOTO, PPHOTO2, PCONTENT, PCNT, PPRICE, PDISCOUNT) VALUES
    (PRODUCTS_SEQ.NEXTVAL, '장난감', '스위스장난감', NULL, 'toy3.jpg', 'toy3.jpg', '스위스에서 직접 공수해 온 최고급 장난감입니다.',5, 15000, 10 );    
    
    
    
 select * from products;
 
 -- 신상품 출력하기 PAGING 

SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTS ORDER BY PRDATE DESC) A) WHERE RN BETWEEN 3 AND 5;
    
-- 새장 출력하기 PAGING    
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTS WHERE PKINDS='새장' ORDER BY PRDATE DESC) A ) WHERE RN BETWEEN 2 AND 3;    
    
-- 모이 출력하기 PAGING    
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTS WHERE PKINDS='모이' ORDER BY PRDATE DESC) A ) WHERE RN BETWEEN 2 AND 3;    
    
-- 장난감 출력하기 PAGING 
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTS WHERE PKINDS='장난감' ORDER BY PRDATE DESC) A ) WHERE RN BETWEEN 2 AND 3;

-- 세일 상품 출력하기 PAGING
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM PRODUCTS WHERE PDISCOUNT > 0 ORDER BY PRDATE DESC) A ) WHERE RN BETWEEN 2 AND 5;

-- 등록된 상품 갯수
    -- 0. 신상품 
SELECT COUNT(*) FROM PRODUCTS;
    -- 1. 새장
SELECT COUNT(*) FROM PRODUCTS WHERE PKINDS='새장';    
    -- 2. 모이
SELECT COUNT(*) FROM PRODUCTS WHERE PKINDS='모이'; 
    -- 3. 장난감
SELECT COUNT(*) FROM PRODUCTS WHERE PKINDS='장난감'; 
    -- 4. 세일
SELECT COUNT(*) FROM PRODUCTS WHERE PDISCOUNT > 0;   

-- PNO로 상품 정보 가져오기
SELECT * FROM PRODUCTS WHERE PNO = 1;

-- 상품수정
UPDATE PRODUCTS SET PNAME='이태리 아치 이동장', PPHOTOLOGO='youngman.jpg', PPHOTO='cage3', PPHOTO2='cage3', PCONTENT='이태리에서 훔쳐온 아치 이동장입니다',
    PCNT = 10, PPRICE = 35000, PDISCOUNT=20 WHERE PNO = 4;
  
-- 상품 삭제
DELETE FROM PRODUCTS WHERE PNO = 4;

-- 등록 상품 갯수 (PNO) 제이쿼리 이용할 것
SELECT PCNT FROM PRODUCTS WHERE PNO = 7;

commit;

select * from products;
update products set pphoto='toy3.jpg' where pno=9;





