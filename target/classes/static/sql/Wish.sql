CREATE DATABASE if not exists wishlistdatabase;

USE wishlistdatabase;

DROP TABLE if exists wishes;
DROP TABLE if exists wishlists;
DROP TABLE if exists users;

CREATE TABLE if not exists users (
                                     id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                     name varchar(255),
    username varchar(255),
    password varchar(255)
    );

CREATE TABLE if not exists wishes (
                                      id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                      name varchar(255),
    price double,
    amount int,
    description varchar(255),
    url varchar(255),
    wishlist_id int references wishlists(id)
    );

CREATE TABLE if not exists wishlists (
                                         id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                         name varchar(50) NOT NULL,
    user_id int references users(id)
    );

INSERT INTO users (id, name, username, password) VALUES (1, 'Aspen', 'ABenoit', 'test');
INSERT INTO wishlists (id, name, user_id) VALUES (1, 'Test', 1);
INSERT INTO wishes (id, name, price, amount, description, url, wishlist_id) VALUES (1, 'Please Be Happy', 150, 1, 'The most excellent visual novel.', 'https://cdn.akamai.steamstatic.com/steam/apps/844670/capsule_616x353.jpg?t=1691302877', 1);
INSERT INTO wishes (name, price, amount, description, url, wishlist_id) VALUES ('Please Be Happy', 150, 1, 'The most excellent visual novel.', 'https://cdn.akamai.steamstatic.com/steam/apps/844670/capsule_616x353.jpg?t=1691302877', 1);
INSERT INTO wishes (name, price, amount, description, url, wishlist_id) VALUES ('Please Be Happy', 150, 1, 'The most excellent visual novel.', 'https://cdn.akamai.steamstatic.com/steam/apps/844670/capsule_616x353.jpg?t=1691302877', 1);

SELECT * FROM users;
SELECT * FROM wishes;
SELECT * FROM wishlists;