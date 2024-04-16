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
                                      id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
                                      name VARCHAR(255),
    price DOUBLE,
    amount INT,
    description VARCHAR(255),
    url VARCHAR(255),
    wishlist_id INT,
    FOREIGN KEY (wishlist_id) REFERENCES wishlists(id)
    );

INSERT INTO users (id, name, username, password) VALUES (1, 'ABenoit', 'Aspen', '1234');
INSERT INTO wishlists (id, name, user_id) VALUES (1, 'Test', 1);
INSERT INTO wishes (id, name, price, amount, description, url, wishlist_id)
VALUES
    (1, 'Please Be Happy', 150, 1, 'The most excellent visual novel.', 'https://cdn.akamai.steamstatic.com/steam/apps/844670/capsule_616x353.jpg?t=1691302877', 1),
    (2, 'Tandstikker', 10, 20, 'Minde tænder har det ikke for godt', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Holzzahnstocher.jpg/220px-Holzzahnstocher.jpg', 1);

SELECT * FROM users;
SELECT * FROM wishes;
