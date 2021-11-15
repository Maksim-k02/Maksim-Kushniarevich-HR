DROP TABLE IF EXISTS employee;

DROP TABLE IF EXISTS department;

CREATE TABLE department
(
    department_id int NOT NULL AUTO_INCREMENT,
    department_name varchar(50) NOT NULL UNIQUE,
    CONSTRAINT department_pk PRIMARY KEY (department_id)
);