CREATE DATABASE IF NOT EXISTS wishlistdatabase;

USE wishlistdatabase;

DROP TABLE if exists wishlists;
DROP TABLE if exists wishes;
DROP TABLE if exists users;

CREATE TABLE if not exists users (
                                     id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                     ´name´ varchar(255),
                                     username varchar(255),
                                     ´password´ varchar(255)
);

CREATE TABLE if not exists wishes (
                                      id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                      ´name´ varchar(255),
                                      price double,
                                      amount int,
                                      ´description´ varchar(255),
                                      url varchar(255),
                                      wishlist_id int references wishlists(id)
);

CREATE TABLE if not exists wishlists (
                                         id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                         name varchar(50) NOT NULL,
                                         user_id int references users(id)
);