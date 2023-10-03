SET GLOBAL validate_password.length = 6;
SET GLOBAL validate_password.number_count = 0;
SET GLOBAL validate_password.policy=LOW;

ALTER USER 'root'@'localhost' IDENTIFIED BY 'Banco123';
CREATE DATABASE DSaula
