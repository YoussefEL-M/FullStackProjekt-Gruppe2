
CREATE DATABASE if not exists wishlistdatabase;

USE wishlistdatabase;

CREATE TABLE if not exists users (
    id int PRIMARY KEY AUTO_INCREMENT not null,
    name varchar(255),
    username varchar(255),
    password varchar(255)
);

CREATE TABLE if not exists wishes (
    id int PRIMARY KEY AUTO_INCREMENT not null,
    name varchar(255),
    price double,
    amount int,
    description varchar(255),
    url varchar(255),
    owner int, foreign key(owner) references users(id),
    wishlist_nr int
);

CREATE TABLE if not exists follows (
    id int PRIMARY KEY AUTO_INCREMENT not null,
    follower_id int, foreign key(follower_id) references users(id),
    followed_id int,
    wishlist_nr int
);

