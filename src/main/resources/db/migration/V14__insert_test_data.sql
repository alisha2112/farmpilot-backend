INSERT INTO farm (name, created_at) VALUES
('Сонячна Долина', CURRENT_TIMESTAMP),
('Світанок', CURRENT_TIMESTAMP);

INSERT INTO users (username, first_name, last_name, role, phone, password_hash, farm_id) VALUES
('farmer_ivan', 'Іван', 'Іваненко', 'FARMER', '+380501234567', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 1),
('vet_olena', 'Олена', 'Петрівна', 'VETERINARIAN', '+380671234567', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG', 1);

INSERT INTO pigs (tag_number, sex, weight, date_of_birth, castration, health, status, farm_id) VALUES
('PG-001', 'FEMALE', 150.50, '2023-05-10', false, '{"vaccinations": ["parvovirus", "erysipelas"], "notes": "Здорова"}', 'ACTIVE', 1),
('PG-002', 'MALE', 120.00, '2023-11-20', true, '{"notes": "Гарний апетит"}', 'ACTIVE', 1),
('PG-003', 'FEMALE', 180.00, '2022-08-15', false, '{"notes": "Свиноматка"}', 'ACTIVE', 1);

INSERT INTO feed_inventory (name, cost_per_kg, number_in_kg, farm_id) VALUES
('Кукурудза', 8.50, 1000.00, 1),
('Соєва макуха', 15.00, 500.00, 1);

INSERT INTO expenses (name, expense, expense_date, category, farm_id) VALUES
('Закупівля вітамінів', 1500.00, CURRENT_DATE - 5, 'MEDICINE', 1),
('Ремонт годівниці', 800.00, CURRENT_DATE - 2, 'EQUIPMENT', 1);

INSERT INTO pregnancies (pig_id, insemination_date, expected_birth_date, actual_birth_date, status) VALUES
(3, '2023-09-01', '2023-12-24', '2023-12-25', 'SUCCESS'),
(1, CURRENT_DATE - 50, CURRENT_DATE + 64, NULL, 'PLANNED');

INSERT INTO feed_consumption (feed_id, amount_kg, date) VALUES
(1, 50.00, CURRENT_DATE - 1),
(2, 20.00, CURRENT_DATE - 1);