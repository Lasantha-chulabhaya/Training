-- Create categories table
CREATE TABLE categories (
                            category_id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT
);

-- Create authors table
CREATE TABLE authors (
                         author_id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE,
                         birth_year INTEGER
);

-- Create books table
CREATE TABLE books (
                       book_id BIGSERIAL PRIMARY KEY,
                       isbn VARCHAR(255),
                       title VARCHAR(255) NOT NULL,
                       publication_year INTEGER,
                       total_copies INTEGER,
                       available_copies INTEGER,
                       status VARCHAR(50) DEFAULT 'ACTIVE',
                       category_id BIGINT,
                       CONSTRAINT fk_books_category FOREIGN KEY (category_id) REFERENCES categories(category_id),
                       CONSTRAINT chk_book_status CHECK (status IN ('ACTIVE', 'INACTIVE', 'DELETED'))
);

-- Create members table
CREATE TABLE members (
                         member_id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         phone VARCHAR(255),
                         join_date DATE DEFAULT CURRENT_DATE,
                         status VARCHAR(50) DEFAULT 'ACTIVE',
                         CONSTRAINT chk_member_status CHECK (status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED'))
);

-- Create borrowings table
CREATE TABLE borrowings (
                            borrow_id BIGSERIAL PRIMARY KEY,
                            member_id BIGINT NOT NULL,
                            book_id BIGINT NOT NULL,
                            borrow_date DATE NOT NULL DEFAULT CURRENT_DATE,
                            due_date DATE NOT NULL,
                            return_date DATE,
                            status VARCHAR(50) DEFAULT 'ACTIVE',
                            CONSTRAINT fk_borrowings_member FOREIGN KEY (member_id) REFERENCES members(member_id),
                            CONSTRAINT fk_borrowings_book FOREIGN KEY (book_id) REFERENCES books(book_id),
                            CONSTRAINT chk_borrowing_status CHECK (status IN ('ACTIVE', 'RETURNED', 'OVERDUE', 'LOST'))
);

-- Create book_authors junction table for many-to-many relationship
CREATE TABLE book_authors (
                              book_id BIGINT NOT NULL,
                              author_id BIGINT NOT NULL,
                              PRIMARY KEY (book_id, author_id),
                              CONSTRAINT fk_book_authors_book FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE,
                              CONSTRAINT fk_book_authors_author FOREIGN KEY (author_id) REFERENCES authors(author_id) ON DELETE CASCADE
);

-- Create essential indexes for foreign keys (PostgreSQL doesn't auto-create these)
-- Primary keys and unique constraints are automatically indexed by PostgreSQL
CREATE INDEX idx_books_category_id ON books(category_id);
CREATE INDEX idx_borrowings_member_id ON borrowings(member_id);
CREATE INDEX idx_borrowings_book_id ON borrowings(book_id);