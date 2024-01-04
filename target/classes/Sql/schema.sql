DROP DATABASE IF EXISTS GMS;

CREATE DATABASE IF NOT EXISTS GMS;

SHOW DATABASES;

USE GMS;

CREATE TABLE user(
    username VARCHAR(15) PRIMARY KEY,
    password VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE employee(
    id VARCHAR(15) PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    nic VARCHAR(15) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    tel VARCHAR(15) NOT NULL,
    email VARCHAR(30) NOT NULL,
    job VARCHAR(35) NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE customer(
    c_id VARCHAR(15) PRIMARY KEY,
    c_name VARCHAR(50) NOT NULL,
    c_address TEXT NOT NULL,
    c_contact VARCHAR(15) NOT NULL,
    e_id VARCHAR(15) NOT NULL,
    date DATE NOT NULL,
    CONSTRAINT FOREIGN KEY (e_id) REFERENCES employee(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE supplier(
    sup_id VARCHAR(15) PRIMARY KEY ,
    name VARCHAR(50) NOT NULL ,
    address TEXT NOT NULL ,
    contact VARCHAR(15) NOT NULL ,
    email VARCHAR(35) NOT NULL ,
    e_id VARCHAR(15) NOT NULL ,
    date DATE NOT NULL ,
    CONSTRAINT FOREIGN KEY (e_id) REFERENCES employee(id)
        ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TABLE tools(
    t_code VARCHAR(15) PRIMARY KEY ,
    description VARCHAR(45) NOT NULL ,
    status VARCHAR(30) NOT NULL
);

CREATE TABLE item_stock(
    item_code VARCHAR(15) PRIMARY KEY ,
    qty INT(10) NOT NULL,
    price DOUBLE(10,4) NOT NULL ,
    description VARCHAR(40) NOT NULL
);

CREATE TABLE attendance(
    e_id VARCHAR(15) NOT NULL ,
    name VARCHAR(40) NOT NULL ,
    status VARCHAR(50) NOT NULL ,
    time VARCHAR(10) NOT NULL ,
    date DATE NOT NULL ,
    CONSTRAINT FOREIGN KEY (e_id) REFERENCES employee(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE salary(
    s_id VARCHAR(15) PRIMARY KEY ,
    day_count INT(15) NOT NULL ,
    bonus DOUBLE,
    daily_salary DOUBLE NOT NULL ,
    amount DOUBLE NOT NULL ,
    date DATE NOT NULL ,
    e_id VARCHAR(15) NOT NULL ,
    CONSTRAINT FOREIGN KEY (e_id) REFERENCES employee(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE vehicle(
    plate_no VARCHAR(15) PRIMARY KEY ,
    type VARCHAR(20) NOT NULL ,
    cust_id VARCHAR(15) NOT NULL ,
    CONSTRAINT FOREIGN KEY (cust_id) REFERENCES customer(c_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE repair(
    r_id VARCHAR(15) PRIMARY KEY ,
    status TEXT NOT NULL ,
    cost DOUBLE(10,5) NOT NULL ,
    plate_no VARCHAR(10) NOT NULL ,
    e_id VARCHAR(20) NOT NULL ,
    date DATE NOT NULL ,
    CONSTRAINT FOREIGN KEY (plate_no) REFERENCES vehicle(plate_no)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (e_id) REFERENCES employee(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE supplier_order(
    order_id VARCHAR(15) PRIMARY KEY ,
    sup_id VARCHAR(15) NOT NULL ,
    date DATE NOT NULL ,
    CONSTRAINT FOREIGN KEY (sup_id) REFERENCES supplier(sup_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE supplier_order_details(
    order_id VARCHAR(15) NOT NULL ,
    item_code VARCHAR(15) NOT NULL ,
    qty INT NOT NULL ,
    unit_price DOUBLE NOT NULL ,
    CONSTRAINT FOREIGN KEY (order_id) REFERENCES supplier_order(order_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (item_code) REFERENCES item_stock(item_code)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE repair_details(
    r_id VARCHAR(15) NOT NULL ,
    item_code VARCHAR(15) NOT NULL ,
    CONSTRAINT FOREIGN KEY (r_id) REFERENCES repair(r_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (item_code) REFERENCES item_stock(item_code)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tool_details(
  t_code VARCHAR(15) NOT NULL ,
  e_id VARCHAR(15) NOT NULL ,
  CONSTRAINT FOREIGN KEY (t_code) REFERENCES tools(t_code)
      ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FOREIGN KEY (e_id) REFERENCES employee(id)
      ON DELETE CASCADE ON UPDATE CASCADE
);

USE GMS;
SELECT * FROM user;