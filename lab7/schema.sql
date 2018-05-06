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
	(1, 'Grandma', 'Grandma likes to make cookies', 5, 10, 10, 1),
	(2, 'Factory', 'Factory to produce cookies', 10, 50, 50, 1),
	(3, 'Mine', 'Mining cookies', 20, 200, 200, 2);

SELECT * FROM generators;

INSERT INTO events VALUES
	(1, 'Grandma shows up', 'You always know grandma likes to make cookies', 10, 1),
	(2, 'You can construct factory now!', 'Factory to produce cookies', 50, 1),
	(3, 'Weve found cookies in deep mountain ... in the mine?', 'Mining cookies', 200, 2),
	(4, 'sample event', 'This is a sample event. Please delete me', 99999, 2);

SELECT * FROM events;

INSERT INTO quantities VALUES
	(1, 'c7a69d44e0b9b415b2d9956cb26b944a', 2),
	(2, 'c7a69d44e0b9b415b2d9956cb26b944a', 1),
	(1, '80516ce4663c3bd0c8385309a2fe226e', 20),
	(2, '80516ce4663c3bd0c8385309a2fe226e', 30);

SELECT * FROM quantities;

UPDATE generators SET 
	unlock_at=10,
	rate= 1
WHERE
	name='Grandma';

SELECT * FROM generators;

SELECT 
	q.quantity, g.name, g.description, g.rate, g.base_cost, g.unlock_at 
FROM 
	generators g 
JOIN
	quantities q 
ON
	g.id = q.generator_id
WHERE
	q.token = '80516ce4663c3bd0c8385309a2fe226e';

SELECT * FROM generators WHERE unlock_at = (SELECT MAX(unlock_at) FROM generators);

SELECT * FROM generators ORDER BY unlock_at ASC;

DELETE FROM events WHERE name = 'sample event';

SELECT * FROM events;


