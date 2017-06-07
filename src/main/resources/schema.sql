CREATE TABLE IF NOT EXISTS encryption_example (
id				BIGSERIAL	PRIMARY KEY	NOT NULL,
clear_text			TEXT, 
encrypted_text			TEXT
);