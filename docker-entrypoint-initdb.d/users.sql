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
    authorId INTEGER REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE comments (
    id TEXT PRIMARY KEY,
    body TEXT,
    articleId INTEGER NOT NULL REFERENCES articles(id),
    userId INTEGER NOT NULL REFERENCES users(id)
);