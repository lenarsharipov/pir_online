-- ### INSERT DEMO SECTIONS ###
-- Создаём секции
INSERT INTO section (type, created_date, created_by)
VALUES
    ('AR', CURRENT_TIMESTAMP, 'init_script'),
    ('KR', CURRENT_TIMESTAMP, 'init_script'),
    ('IOS1', CURRENT_TIMESTAMP, 'init_script'),
    ('IOS2', CURRENT_TIMESTAMP, 'init_script'),
    ('IOS3', CURRENT_TIMESTAMP, 'init_script'),
    ('IOS4', CURRENT_TIMESTAMP, 'init_script');

-- ### INSERT DEMO CARDS ###
-- Создаём карты проектов
INSERT INTO card (project_name, project_code, start_date, end_date, created_date, created_by)
VALUES
    ('Demo Project 1', 'PRJ001', CURRENT_DATE, CURRENT_DATE + INTERVAL '6 months', CURRENT_TIMESTAMP, 'init_script'),
    ('Demo Project 2', 'PRJ002', CURRENT_DATE, CURRENT_DATE + INTERVAL '12 months', CURRENT_TIMESTAMP, 'init_script');

-- ### INSERT DEMO PROJECTS ###
-- Создаём проекты и связываем их с картами
INSERT INTO project (card_id, status, created_date, created_by)
SELECT id, 'NEW', CURRENT_TIMESTAMP, 'init_script' FROM card WHERE project_code = 'PRJ001';

INSERT INTO project (card_id, status, created_date, created_by)
SELECT id, 'NEW', CURRENT_TIMESTAMP, 'init_script' FROM card WHERE project_code = 'PRJ002';

-- ### LINK PROJECTS TO SECTIONS ###
-- Создаём связи между проектами и секциями
INSERT INTO project_sections (project_id, section_id, created_date, created_by)
SELECT p.id, s.id, CURRENT_TIMESTAMP, 'init_script'
FROM project p, section s
WHERE p.card_id = (SELECT id FROM card WHERE project_code = 'PRJ001');

INSERT INTO project_sections (project_id, section_id, created_date, created_by)
SELECT p.id, s.id, CURRENT_TIMESTAMP, 'init_script'
FROM project p, section s
WHERE p.card_id = (SELECT id FROM card WHERE project_code = 'PRJ002');

-- encrypted pass = "password"
INSERT INTO users(username, password, role, created_date, created_by)
VALUES ('admin', '$2a$12$0mL7pvgfnWQ3JUpRhg2CfubY5lCg17Qd0w59SV./ecAK2obsjRdWm', 'ROLE_ADMIN', now(), 'init_script');