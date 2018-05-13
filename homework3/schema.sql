CREATE TABLE users (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

DESCRIBE users;

CREATE TABLE generators (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description TEXT,
	rate INTEGER,
	base_cost INTEGER,
	unlock_at INTEGER,
	created_by INTEGER REFERENCES user(id),
	index(created_by)
);

DESCRIBE generators;

CREATE TABLE events (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	description TEXT,
	trigger_at INTEGER,
	created_by INTEGER REFERENCES users(id),
	index(created_by)
);

DESCRIBE events;

CREATE TABLE quantities (
	generator_id INTEGER NOT NULL,
	token VARCHAR(255),
	quantity INTEGER DEFAULT 0,
	CONSTRAINT uc_gen_id_token UNIQUE (generator_id, token)
);

DESCRIBE quantities;

INSERT INTO users VALUES 
	(1, 'admin', 'cs3220password'), 
	(2, 'me', 'notapassword');

SELECT * FROM users;

INSERT INTO generators VALUES 
	(1, 'FARMER', 'You hire a worker to tend to your crops. Your worker will then harvest crops that are ready to sell to people. But they do not sell for much.', 5, 10, 10, 1),
	(2, 'HUNTER', 'You hire a experienced hunter to go out and kill monsters and other wild entities. The hunter will gather their spoils and sell them in village for you. They are work a reasonable amount of coins.', 10, 50, 50, 1),
	(3, 'THEIVE', 'You hire a theive to go out to villages and steal from any civilian they can find. A big risk for a big win.', 20, 200, 200, 2);

SELECT * FROM generators;

INSERT INTO events VALUES
	(1, 'FARMER', 'Picking crops...', 10, 1),
	(2, 'HUNTER', 'Hunting for wild animals...', 15, 1),
	(3, 'THEIVE', 'Pickpocketing random people with heavy pockets...', 25, 2);

SELECT * FROM events;

INSERT INTO quantities VALUES
	(1, 'c7a69d44e0b9b415b2d9956cb26b944a', 2),
	(2, 'c7a69d44e0b9b415b2d9956cb26b944a', 1),
	(1, '80516ce4663c3bd0c8385309a2fe226e', 20),
	(2, '80516ce4663c3bd0c8385309a2fe226e', 30);

SELECT * FROM quantities;

-- UPDATE generators SET 
-- 	unlock_at=10,
-- 	rate= 1
-- WHERE
-- 	name='Grandma';

-- SELECT * FROM generators;

-- SELECT 
-- 	q.quantity, g.name, g.description, g.rate, g.base_cost, g.unlock_at 
-- FROM 
-- 	generators g 
-- JOIN
-- 	quantities q 
-- ON
-- 	g.id = q.generator_id
-- WHERE
-- 	q.token = '80516ce4663c3bd0c8385309a2fe226e';

-- SELECT * FROM generators WHERE unlock_at = (SELECT MAX(unlock_at) FROM generators);

-- SELECT * FROM generators ORDER BY unlock_at ASC;

-- DELETE FROM events WHERE name = 'sample event';

-- SELECT * FROM events;


