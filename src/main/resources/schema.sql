CREATE TABLE PRODUCT_CATEGORY (
  SEQ bigint PRIMARY KEY,
  CREATE_USER VARCHAR(12) NOT NULL,
  CREATED_DATE TIMESTAMP NOT NULL,
  NAME VARCHAR(30) DEFAULT NULL,
  "TYPE" VARCHAR(10) DEFAULT NULL,
  ENABLE VARCHAR(1),
  UPDATE_DATE TIMESTAMP,
  UPDATE_USER VARCHAR(255)
);

CREATE TABLE PRODUCT_DETIAL (
  SEQ bigint PRIMARY KEY,
  CREATE_USER VARCHAR(12) NOT NULL,
  CREATED_DATE TIMESTAMP NOT NULL,
  NAME VARCHAR(30) DEFAULT NULL,
  "CATEGORY_TYPE" VARCHAR(10) DEFAULT NULL,
  DISPLAY VARCHAR(1) DEFAULT NULL,
  UPDATE_DATE TIMESTAMP,
  UPDATE_USER VARCHAR(255)
);

CREATE SEQUENCE productCategorySequence
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1;

CREATE SEQUENCE productDetailSequence
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1;