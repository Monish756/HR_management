CREATE DATABASE hr_db;
USE hr_db;

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    role VARCHAR(50),
    salary DOUBLE
);