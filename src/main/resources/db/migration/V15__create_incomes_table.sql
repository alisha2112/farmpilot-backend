CREATE TABLE incomes (
    id BIGSERIAL PRIMARY KEY,
    source VARCHAR(100) NOT NULL,
    amount DECIMAL(15,2) NOT NULL CHECK (amount > 0),
    income_date DATE NOT NULL DEFAULT CURRENT_DATE,
    category VARCHAR(50) NOT NULL DEFAULT 'OTHER',
    pig_id BIGINT REFERENCES pigs(id),
    farm_id BIGINT NOT NULL REFERENCES farm(id)
);