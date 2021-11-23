CREATE DATABASE book_alert_db;
USE book_alert_db;

INSERT INTO authors (name) VALUES  ('Anthony Robbins');
INSERT INTO authors (name) VALUES  ('Neil Gaiman');
INSERT INTO authors (name) VALUES  ('Henry Rollins');
INSERT INTO authors (name) VALUES  ('Terry Pratchett');
INSERT INTO authors (name) VALUES  ('James Patterson');
INSERT INTO authors (name) VALUES  ('Anne Rice');

INSERT INTO books (artwork_url, description, itunes_url, release_date, title, author_id)
VALUES ('https://is4-ssl.mzstatic.com/image/thumb/Music114/v4/81/8c/90/818c9001-9c3d-f138-fb09-f03001b08b00/9780061967078.jpg/100x100bb.jpg',
        '<b>Anthony Robbins calls it the new science of personal achievement. You''ll call it the best thing that ever happened to you.</b> <br /> If you have ever dreamed of a better life, <i>Unlimited Power</i> will show you how to achieve the extraordinary quality of life you desire and deserve, and how to master your personal and professional life. Anthony Robbins has proven to millions through his books, tapes, and seminars that by harnessing the power of the mind you can do, have, achieve, and create anything you want for your life. He has shown heads of state, royalty, Olympic and professional athletes, movie stars, and children how to achieve. With <i>Unlimited Power,</i> he passionately and eloquently reveals the science of personal achievement and teaches you: <br /> * How to find out what you <i>really</i> want<br /> * The Seven Lies of Success<br /> * How to reprogram your mind in minutes to eliminate fears and phobias<br /> * The secret of creating instant rapport with anyone you meet<br /> * How to duplicate the success of others<br /> * The Five Keys to Wealth and Happiness <br /> <i>Unlimited Power</i> is a revolutionary fitness book for the mind. It will show you, step by step, how to perform at your peak while gaining emotional and financial freedom, attaining leadership and self-confidence, and winning the cooperation of others. It will give you the knowledge and the courage to remake yourself and your world. <i>Unlimited Power</i> is a guidebook to superior performance in an age of success.',
        'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4',
        '2008-06-30T07:00:00Z',
        'Unlimited Power', 1);

insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/cc0000/ffffff', 'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Acid House, The', 4);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/dddddd/000000', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Every Other Weekend (Un week-end sur deux)', 3);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/ff4444/ffffff', 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Do You Wanna Know a Secret?', 4);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/cc0000/ffffff', 'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'What Goes Up', 1);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/dddddd/000000', 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Bending the Rules', 6);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/dddddd/000000', 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Terror on a Train (Time Bomb)', 2);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/5fa2dd/ffffff', 'Nullam porttitor lacus at turpis. Donec posuere metus vitae ipsum. Aliquam non mauris.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Nukes in Space', 3);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/cc0000/ffffff', 'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Wolf Children (Okami kodomo no ame to yuki)', 3);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/ff4444/ffffff', 'Fusce consequat. Nulla nisl. Nunc nisl.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'The Heart Machine', 5);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/cc0000/ffffff', 'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Wilson', 4);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/5fa2dd/ffffff', 'Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Midnight Dancers (Sibak)', 5);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/dddddd/000000', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Maid in Manhattan', 2);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/cc0000/ffffff', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Kapitalism: Our Improved Formula (Kapitalism - Reteta noastra secreta)', 3);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/dddddd/000000', 'Fusce consequat. Nulla nisl. Nunc nisl.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Parental Guidance', 1);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/dddddd/000000', 'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Beyond the Black Rainbow', 6);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/ff4444/ffffff', 'Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Pelican Brief, The', 1);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/5fa2dd/ffffff', 'Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'To Gillian on Her 37th Birthday', 4);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/cc0000/ffffff', 'Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Plough and the Stars, The', 1);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/cc0000/ffffff', 'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'The Hound of the Baskervilles', 1);
insert into books (artwork_url, description, itunes_url, release_date, title, author_id) values ('http://dummyimage.com/x.png/dddddd/000000', 'In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus.', 'https://books.apple.com/us/book/unlimited-power/id381545225?uo=4', '2008-06-30T07:00:00Z', 'Pieta', 2);

insert into users (email, first_name, is_private, last_name, password, username) values ('rpetegre0@sakura.ne.jp', 'Romola', 1, 'Petegre', 'WBwuXZ', 'rpetegre0');
insert into users (email, first_name, is_private, last_name, password, username) values ('xiddenden1@kickstarter.com', 'Xymenes', 1, 'Iddenden', 'Pw829os', 'xiddenden1');
insert into users (email, first_name, is_private, last_name, password, username) values ('gedowes2@weather.com', 'Goddard', 1, 'Edowes', 'zj40ftkn2sk', 'gedowes2');
insert into users (email, first_name, is_private, last_name, password, username) values ('babbot3@aol.com', 'Becky', 1, 'Abbot', 'tHdwIB0Xk', 'babbot3');
insert into users (email, first_name, is_private, last_name, password, username) values ('wbach4@japanpost.jp', 'Wade', 1, 'Bach', 'vjIfqu', 'wbach4');

insert into author_user (user_id, author_id) values (1, 1);
insert into author_user (user_id, author_id) values (2, 1);
insert into author_user (user_id, author_id) values (3, 1);
insert into author_user (user_id, author_id) values (1, 2);
insert into author_user (user_id, author_id) values (2, 2);
insert into author_user (user_id, author_id) values (3, 3);
insert into author_user (user_id, author_id) values (1, 5);
insert into author_user (user_id, author_id) values (2, 6);
insert into author_user (user_id, author_id) values (3, 4);

insert into book_user(book_id, user_id) values (3, 2);
insert into book_user(book_id, user_id) values (5, 2);
insert into book_user(book_id, user_id) values (7, 2);
insert into book_user(book_id, user_id) values (9, 2);
insert into book_user(book_id, user_id) values (4, 3);
insert into book_user(book_id, user_id) values (6, 3);
insert into book_user(book_id, user_id) values (8, 3);
insert into book_user(book_id, user_id) values (10, 3);
insert into book_user(book_id, user_id) values (12, 3);






