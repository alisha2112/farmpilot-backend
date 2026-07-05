CREATE TABLE expenses (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    expense DECIMAL(15,2) NOT NULL CHECK (expense > 0),
    expense_date DATE NOT NULL DEFAULT CURRENT_DATE,
    category VARCHAR(50) NOT NULL,
    farm_id BIGINT NOT NULL REFERENCES farm(id)
);