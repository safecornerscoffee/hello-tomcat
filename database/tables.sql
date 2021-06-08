CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    email    TEXT UNIQUE NOT NULL,
    password TEXT        NOT NULL,
    name     TEXT,
    image    TEXT
);

CREATE TABLE articles(
    id SERIAL PRIMARY KEY,
    title TEXT,
    body TEXT,
    author_id INTEGER REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE tags(
    id SERIAL PRIMARY KEY,
    article_id INTEGER,
    name TEXT
);

CREATE TABLE articles_tags(
    article_id INTEGER,
    tag_id INTEGER,
    PRIMARY KEY (article_id, tag_id)
);

CREATE TABLE comments (
    id TEXT PRIMARY KEY,
    body TEXT,
    articleId INTEGER NOT NULL REFERENCES articles(id) ON DELETE CASCADE,
    userId INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE
);