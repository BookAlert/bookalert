CREATE DATABASE book_alert_db;

USE book_alert_db;

INSERT INTO status (name) VALUES ('New_Release');
INSERT INTO status (name) VALUES ('Saved');
INSERT INTO status (name) VALUES ('Purchased');
INSERT INTO status (name) VALUES ('Upcoming_Release');

DELETE from authors Where ID = 5;
DELETE from books where author_id = 5;
DELETE FROM author_user where author_id =5;






