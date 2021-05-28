CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE articles(
    id SERIAL PRIMARY KEY,
    title TEXT,
    body TEXT,
    authorId INTEGER REFERENCES users(id) ON DELETE SET NULL
);

CREATE TABLE tags(
    id SERIAL PRIMARY KEY,
    article_id INTEGER,
    name TEXT
);

CREATE TABLE

CREATE TABLE comments (
    id TEXT PRIMARY KEY,
    body TEXT,
    articleId INTEGER NOT NULL REFERENCES articles(id) ON DELETE CASCADE,
    userId INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE
);