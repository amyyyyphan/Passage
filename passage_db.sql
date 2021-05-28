CREATE DATABASE `passage_db`;

USE passage_db;

CREATE TABLE USER (
	firstName varchar(255) NOT NULL,
	lastName varchar(255) NOT NULL,
	username varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	phone varchar(255) NOT NULL,
	PRIMARY KEY (userName)
);

CREATE TABLE RIDE (
	username varchar(255) NOT NULL,
	start varchar(255) NOT NULL,
	destination varchar(255) NOT NULL,
	date varchar(255) NOT NULL,
	time varchar(255) NOT NULL,
	stopover1 varchar(255) NOT NULL,
	stopover2 varchar(255) NOT NULL,
	arrivalTime varchar(255) NOT NULL,
	vehicle varchar(255) NOT NULL,
	seats varchar(255) NOT NULL,
	price varchar(255) NOT NULL
);