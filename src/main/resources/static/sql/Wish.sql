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
                                      wishlist_id int references wishlists(id),
                                      reserved boolean
);

CREATE TABLE if not exists wishlists (
                                         id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                         name varchar(50) NOT NULL,
                                         user_id int references users(id)
);

INSERT INTO users (id, name, username, password) VALUES (1, 'Aspen', 'ABenoit', 'test');
INSERT INTO wishlists (id, name, user_id) VALUES (1, 'Test', 1);
INSERT INTO wishes (id, name, price, amount, description, url, wishlist_id, reserved) VALUES (1, 'Please Be Happy', 150, 1, 'The most excellent visual novel.', 'https://cdn.akamai.steamstatic.com/steam/apps/844670/capsule_616x353.jpg?t=1691302877', 1, false);
INSERT INTO wishes (name, price, amount, description, url, wishlist_id, reserved) VALUES ('Please Be Happy', 150, 1, 'The most excellent visual novel.', 'https://cdn.akamai.steamstatic.com/steam/apps/844670/capsule_616x353.jpg?t=1691302877', 1, false);
INSERT INTO wishes (name, price, amount, description, url, wishlist_id, reserved) VALUES ('Please Be Happy', 150, 1, 'The most excellent visual novel.', 'https://cdn.akamai.steamstatic.com/steam/apps/844670/capsule_616x353.jpg?t=1691302877', 1, false);
INSERT INTO wishlists (name, user_id) VALUES ('Test2', 2);
INSERT INTO wishes (name, price, amount, description, url, wishlist_id, reserved) VALUES ('Lars', 150, 1, 'The most excellent visual novel.', 'https://ih1.redbubble.net/image.602376580.5344/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.u2.jpg', 2, false);
INSERT INTO wishes (name, price, amount, description, url, wishlist_id, reserved) VALUES ('er', 150, 1, 'The most excellent visual novel.', 'https://ih1.redbubble.net/image.602376580.5344/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.u2.jpg', 2, false);
INSERT INTO wishes (name, price, amount, description, url, wishlist_id, reserved) VALUES ('sej', 150, 1, 'The most excellent visual novel.', 'https://ih1.redbubble.net/image.602376580.5344/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.u2.jpg', 2, false);

SELECT * FROM users;
SELECT * FROM wishlists;
SELECT * FROM wishes;