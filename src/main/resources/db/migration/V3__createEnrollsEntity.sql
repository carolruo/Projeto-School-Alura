CREATE TABLE Enrolls (
    user_id VARCHAR(20) NOT NULL UNIQUE,
    course_id VARCHAR(100) NOT NULL,
    data TIMESTAMP NOT NULL
);