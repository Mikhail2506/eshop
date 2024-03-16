-- INSERT INTO users(id, archive, email, name, password,  role, bucket_id)
-- VALUES (1, false, 'mail@mail.ru', 'admin', 'pass', 'ADMIN', null);

INSERT INTO users(id, archive, email, name, password,  role)
VALUES (1, false, 'mail@mail.ru', 'admin', '$2a$10$GpKiN.BQ0kEwNiumLKsdVuh.PWpWl9Agaj3hQhwO29kBeBPGq7eeq', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;