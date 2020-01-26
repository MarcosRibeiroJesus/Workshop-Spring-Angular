CREATE TABLE opportunity (
    id SERIAL PRIMARY KEY,
    prospect_name VARCHAR(80) NOT NULL,
    description VARCHAR(200) NOT NULL,
    value NUMERIC(10,2),
);