CREATE TABLE Section (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author_username VARCHAR(10) NOT NULL,
    code VARCHAR(20) NOT NULL UNIQUE,
    course_id VARCHAR(20) NOT NULL,
    title VARCHAR(20) NOT NULL
);