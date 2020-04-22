
DROP SCHEMA IF EXISTS netflix;

DROP USER IF EXISTS 'spq'@'localhost';

CREATE SCHEMA netflix;

CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';
GRANT ALL ON netflix.* TO 'spq'@'localhost';
