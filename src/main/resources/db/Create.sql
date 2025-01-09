CREATE TABLE USERS (
    ID NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    USERNAME VARCHAR2(255) NOT NULL UNIQUE,
    PASSWORD VARCHAR2(255) NOT NULL,
    ENABLED NUMBER(1) DEFAULT 1 NOT NULL,
    EMAIL VARCHAR2(255) NOT NULL UNIQUE
);