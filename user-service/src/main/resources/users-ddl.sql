CREATE DATABASE users_db;
CREATE USER users_admin WITH PASSWORD 'users_password';
GRANT ALL PRIVILEGES ON DATABASE users_db TO users_admin;

/* Important note! Table should be created under 'users_admin' user, not under 'postgres' user. */
CREATE TABLE public.users (
  id       BIGSERIAL PRIMARY KEY,
  name     VARCHAR(40) NOT NULL,
  email    VARCHAR(40) NOT NULL,
  password VARCHAR(80) NOT NULL,
  UNIQUE (email)
);