CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR(100)        NOT NULL,
    last_name   VARCHAR(100)        NOT NULL,
    middle_name VARCHAR(100),
    email       VARCHAR(255) UNIQUE NOT NULL,
    phone       VARCHAR(50) UNIQUE  NOT NULL,
    created_at  TIMESTAMP           NOT NULL
);