CREATE TABLE USER
(
    ID INTEGER auto_increment,
    E_MAIL CHARACTER(150),
    FIRST_NAME CHARACTER(100),
    LAST_NAME CHARACTER(100),
    CREATED_AT TIMESTAMP NOT NULL,
    DELETED_AT TIMESTAMP,
    PRIMARY KEY (ID)
);