CREATE TABLE student
(
    id           INTEGER      NOT NULL PRIMARY KEY,
    name         varchar(255) NOT NULL,
    mail_address varchar(255),
    phone_number varchar(255)
);
INSERT INTO student (id, name, mail_address, phone_number) VALUES (0, 'arif', 'arifjo@kth.se', '0123456789abc');