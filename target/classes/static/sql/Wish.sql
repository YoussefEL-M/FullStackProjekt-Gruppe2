CREATE DATABASE IF NOT EXISTS wishlistdatabase;

USE wishlistdatabase;

DROP TABLE IF EXISTS wishes;
DROP TABLE IF EXISTS wishlists;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
                                     id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                     name VARCHAR(255),
                                     username VARCHAR(255),
                                     password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS wishlists (
                                         id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                         name VARCHAR(50) NOT NULL,
                                         user_id INT REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS wishes (
                                      id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                      name VARCHAR(255),
                                      price DOUBLE,
                                      amount INT,
                                      description VARCHAR(255),
                                      url VARCHAR(255),
                                      image_url VARCHAR(255),
                                      wishlist_id INT REFERENCES wishlists(id),
                                      reserved BOOLEAN,
                                      reserved_by INT REFERENCES users(id)
);

ALTER TABLE wishes MODIFY reserved BOOLEAN DEFAULT FALSE;
ALTER TABLE wishes MODIFY reserved_by BOOLEAN DEFAULT FALSE;


SELECT * FROM users;
SELECT * FROM wishes;
SELECT * FROM wishlists;
