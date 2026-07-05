CREATE TABLE pregnancies (
    id BIGSERIAL PRIMARY KEY,
    pig_id BIGINT NOT NULL REFERENCES pigs(id),
    insemination_date DATE NOT NULL,
    expected_birth_date DATE NOT NULL,
    actual_birth_date DATE,
    status VARCHAR(20) NOT NULL CHECK (status IN ('PLANNED', 'SUCCESS', 'FAILED'))
);