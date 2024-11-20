-- src/main/resources/schema.sql

-- Create tables
CREATE TABLE authors (
   author_id BIGINT PRIMARY KEY AUTO_INCREMENT,
   first_name VARCHAR(100) NOT NULL,
   last_name VARCHAR(100) NOT NULL,
   birth_date DATE
);

CREATE TABLE books (
   book_id BIGINT PRIMARY KEY AUTO_INCREMENT,
   title VARCHAR(255) NOT NULL,
   publication_year INT,
   author_id BIGINT,
   available BOOLEAN DEFAULT TRUE,
   FOREIGN KEY (author_id) REFERENCES authors(author_id)
);

CREATE TABLE genres (
   genre_id BIGINT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
   user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
   first_name VARCHAR(100) NOT NULL,
   last_name VARCHAR(100) NOT NULL,
   email VARCHAR(255) NOT NULL UNIQUE,
   member_number VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE admins (
   admin_id BIGINT PRIMARY KEY AUTO_INCREMENT,
   username VARCHAR(50) NOT NULL UNIQUE,
   password VARCHAR(50) NOT NULL,
   role VARCHAR(20) NOT NULL
);

CREATE TABLE loans (
   loan_id BIGINT PRIMARY KEY AUTO_INCREMENT,
   book_id BIGINT,
   user_id BIGINT,
   loan_date DATE NOT NULL,
   due_date DATE NOT NULL,
   returned_date DATE,
   FOREIGN KEY (book_id) REFERENCES books(book_id),
   FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE books_genres (
   book_id BIGINT,
   genre_id BIGINT,
   PRIMARY KEY (book_id, genre_id),
   FOREIGN KEY (book_id) REFERENCES books(book_id),
   FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);
