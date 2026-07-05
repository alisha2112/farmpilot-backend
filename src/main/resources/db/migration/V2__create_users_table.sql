CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL CHECK (role IN ('FARMER', 'VETERINARIAN')),
    phone CHAR(13) UNIQUE NOT NULL CHECK (phone LIKE '+380%'),
    password_hash VARCHAR(255) NOT NULL,
    farm_id BIGINT NOT NULL REFERENCES farm(id)
);