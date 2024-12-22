CREATE TABLE IF NOT EXISTS card
(
    id            BIGSERIAL PRIMARY KEY,
    project_name  VARCHAR(128) NOT NULL,
    project_code  VARCHAR(32)  NOT NULL,
    start_date    DATE         NOT NULL,
    end_date      DATE         NOT NULL CHECK (end_date > start_date),
    created_date  TIMESTAMP    NOT NULL,
    modified_date TIMESTAMP,
    created_by    VARCHAR(128) NOT NULL,
    modified_by   VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS project
(
    id            BIGSERIAL PRIMARY KEY,
    card_id       BIGINT       NOT NULL UNIQUE,
    status        VARCHAR(128) NOT NULL,
    created_date  TIMESTAMP    NOT NULL,
    modified_date TIMESTAMP,
    created_by    VARCHAR(128) NOT NULL,
    modified_by   VARCHAR(128),
    CONSTRAINT fk_project_card FOREIGN KEY (card_id) REFERENCES card (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS section
(
    id            BIGSERIAL PRIMARY KEY,
    type          VARCHAR(128) NOT NULL UNIQUE,
    created_date  TIMESTAMP    NOT NULL,
    modified_date TIMESTAMP,
    created_by    VARCHAR(128) NOT NULL,
    modified_by   VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS project_sections
(
    id            BIGSERIAL PRIMARY KEY,
    project_id    BIGINT       NOT NULL,
    section_id    BIGINT       NOT NULL,
    created_date  TIMESTAMP    NOT NULL,
    modified_date TIMESTAMP,
    created_by    VARCHAR(128) NOT NULL,
    modified_by   VARCHAR(128),
    UNIQUE (project_id, section_id),
    CONSTRAINT fk_project_sections_project FOREIGN KEY (project_id) REFERENCES project (id) ON DELETE CASCADE,
    CONSTRAINT fk_project_sections_section FOREIGN KEY (section_id) REFERENCES section (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS users
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(128) NOT NULL UNIQUE,
    password      VARCHAR(128) NOT NULL,
    role          VARCHAR(32)  NOT NULL,
    created_date  TIMESTAMP    NOT NULL,
    modified_date TIMESTAMP,
    created_by    VARCHAR(128) NOT NULL,
    modified_by   VARCHAR(128)
);
