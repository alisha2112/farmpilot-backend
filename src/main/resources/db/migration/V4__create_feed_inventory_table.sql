CREATE TABLE feed_inventory (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cost_per_kg DECIMAL(15,2) NOT NULL,
    number_in_kg DECIMAL(10,2) NOT NULL CHECK (number_in_kg >= 0),
    farm_id BIGINT NOT NULL REFERENCES farm(id)
);