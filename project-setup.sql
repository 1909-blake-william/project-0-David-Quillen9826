DROP USER project0 CASCADE;

CREATE USER project0
IDENTIFIED BY red34cat
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT CONNECT TO project0;
GRANT RESOURCE TO project0;
GRANT CREATE SESSION TO project0;
GRANT CREATE TABLE TO project0;
GRANT CREATE VIEW TO project0;

conn project0/red34cat;

CREATE SEQUENCE users_id_seq;
CREATE TABLE project_users (
    user_id INT PRIMARY KEY,
    username VARCHAR2(20) UNIQUE NOT NULL,
    password VARCHAR2(20) NOT NULL,
    role VARCHAR2(15) DEFAULT 'User' NOT NULL 
);

CREATE SEQUENCE accounts_id_seq;
CREATE TABLE project_accounts (
    account_id INT PRIMARY KEY,
    balance FLOAT DEFAULT '0' NOT NULL,
    user_id INT REFERENCES project_users(user_id),
    account_type VARCHAR2(20) DEFAULT 'Savings' NOT NULL,
    is_open VARCHAR2(10) DEFAULT 'Yes' NOT NULL
);

CREATE SEQUENCE transaction_id_seq;
CREATE TABLE project_transactions (
    transaction_id INT PRIMARY KEY,
    transaction_type VARCHAR2(20) NOT NULL,
    amount INT NOT NULL,
    account_id INT REFERENCES project_accounts(account_id),
    transaction_time VARCHAR2(64) NOT NULL
);

INSERT INTO project_users (user_id, username, password, role) 
    VALUES (users_id_seq.nextval, 'Noctis', 'ff15', 'Admin');
INSERT INTO project_users (user_id, username, password) 
    VALUES (users_id_seq.nextval, 'Lightning', 'ff13');
INSERT INTO project_users (user_id, username, password) 
    VALUES (users_id_seq.nextval, 'Titus', 'ff10');
INSERT INTO project_users (user_id, username, password) 
    VALUES (users_id_seq.nextval, 'Squall', 'ff8');
INSERT INTO project_users (user_id, username, password) 
    VALUES (users_id_seq.nextval, 'Cloud', 'ff7');
INSERT INTO project_users (user_id, username, password) 
    VALUES (users_id_seq.nextval, 'Terra', 'ff6');

INSERT INTO project_accounts (account_id, user_id)
    VALUES (accounts_id_seq.nextval, 1);
INSERT INTO project_accounts (account_id, user_id, account_type)
    VALUES (accounts_id_seq.nextval, 1, 'Checking');
INSERT INTO project_accounts (account_id, user_id)
    VALUES (accounts_id_seq.nextval, 2);
INSERT INTO project_accounts (account_id, user_id)
    VALUES (accounts_id_seq.nextval, 3);
INSERT INTO project_accounts (account_id, user_id)
    VALUES (accounts_id_seq.nextval, 4);
INSERT INTO project_accounts (account_id, user_id, account_type)
    VALUES (accounts_id_seq.nextval, 5, 'Checking');
INSERT INTO project_accounts (account_id, user_id, is_open)
    VALUES (accounts_id_seq.nextval, 6, 'No');
INSERT INTO project_accounts (account_id, user_id, account_type)
    VALUES (accounts_id_seq.nextval, 6, 'Checking');

INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'deposit', 100, 1, 'midnight');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'deposit', 100, 2, '1am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'withdraw', 100, 3, '2am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'deposit', 100, 4, '3am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'deposit', 100, 5, '4am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'withdraw', 100, 6, '5am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'deposit', 100, 7, '6am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'deposit', 100, 8, '7am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'withdraw', 100, 1, '8am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'deposit', 100, 2, '9am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'deposit',  100,3, '10am');
INSERT INTO project_transactions (transaction_id, transaction_type, amount, account_id, transaction_time)
    VALUES (transaction_id_seq.nextval, 'withdraw',  100,4, '11am');

commit;