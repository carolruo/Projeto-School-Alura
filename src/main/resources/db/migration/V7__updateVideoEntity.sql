ALTER TABLE Video
ADD CONSTRAINT fk_SectionVideo FOREIGN KEY (section_id) REFERENCES Section (id);
