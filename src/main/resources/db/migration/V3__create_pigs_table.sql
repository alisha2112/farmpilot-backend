CREATE TABLE pigs (
    id BIGSERIAL PRIMARY KEY,
    tag_number VARCHAR(50) NOT NULL,
    sex VARCHAR(6) NOT NULL CHECK (sex IN ('MALE', 'FEMALE')),
    weight DECIMAL(5,2) NOT NULL,
    date_of_birth DATE NOT NULL,
    castration BOOLEAN NOT NULL DEFAULT false,
    health JSONB,
    status VARCHAR(20) NOT NULL CHECK (status IN ('ACTIVE', 'SOLD', 'DECEASED')),
    farm_id BIGINT NOT NULL REFERENCES farm(id),
    UNIQUE (tag_number, farm_id)
);