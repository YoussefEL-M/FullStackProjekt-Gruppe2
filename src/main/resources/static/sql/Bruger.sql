
CREATE DATABASE if not exists wishlistdatabase;

USE wishlistdatabase;

CREATE TABLE if not exists users (
    id int PRIMARY KEY AUTO_INCREMENT not null,
    `name` varchar(255),
    username varchar(255),
    `password` varchar(255)
);

CREATE TABLE if not exists wishes (
    id int PRIMARY KEY AUTO_INCREMENT not null,
    `name` varchar(255),
    price double,
    amount int,
    `description` varchar(255),
    url varchar(255),
    user_id int, foreign key(user_id) references `users`(id)
);


    
