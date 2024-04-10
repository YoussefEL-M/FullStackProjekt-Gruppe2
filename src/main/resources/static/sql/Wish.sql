CREATE DATABASE IF NOT EXISTS wishlistdatabase;

USE wishlistdatabase;

CREATE TABLE IF NOT EXISTS users(
                                    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                    `name` VARCHAR(255),
    username VARCHAR(255),
    `password` VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS wishes(
                                     id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                     `name` VARCHAR(255),
    price double,
    amount int,
    `description` VARCHAR(255),
    url VARCHAR(255),
    user_id INT, FOREIGN KEY(user_id) references `users`(id)
    );