CREATE TABLE feed_consumption (
    id BIGSERIAL PRIMARY KEY,
    feed_id BIGINT NOT NULL REFERENCES feed_inventory(id),
    amount_kg DECIMAL(10,2) NOT NULL CHECK (amount_kg > 0),
    date DATE NOT NULL DEFAULT CURRENT_DATE
);