CREATE DATABASE my_unsplash_clone 
WITH OWNER postgres ENCODING 'utf-8';

\c my_unsplash_clone

SET client_encoding = 'UTF8';

CREATE DOMAIN email_domain AS VARCHAR NOT NULL CHECK (VALUE ~ 
'^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$' );

CREATE DOMAIN label_domain AS VARCHAR(100) NOT NULL;

CREATE TABLE users(
	pk_email email_domain,
	password VARCHAR NOT NULL,
	name VARCHAR NOT NULL,
	create_at TIMESTAMP,
	PRIMARY KEY (pk_email)
);

CREATE TABLE photos(
	pk_photo VARCHAR NOT NULL,
	fk_user email_domain,
	url VARCHAR NOT NULL,
	label label_domain,
	create_at TIMESTAMP,
	PRIMARY KEY(pk_photo,url),
	FOREIGN KEY (fk_user) REFERENCES users (pk_email)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);