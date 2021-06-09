CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    username      TEXT NOT NULL UNIQUE,
    email         TEXT NOT NULL UNIQUE,
    password      TEXT NOT NULL,
    profile_name  TEXT,
    profile_image TEXT
);

CREATE TABLE articles(
                         id      SERIAL PRIMARY KEY,
                         title   TEXT    NOT NULL,
                         body    TEXT    NOT NULL,
                         user_id INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE tags(
                     id SERIAL PRIMARY KEY,
                     name TEXT UNIQUE
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