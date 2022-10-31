ALTER TABLE Section
ADD CONSTRAINT fk_CourseSection FOREIGN KEY (course_id) REFERENCES Course (id);
