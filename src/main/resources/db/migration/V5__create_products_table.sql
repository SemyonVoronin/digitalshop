CREATE TABLE products(
    id UUID PRIMARY KEY,
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price NUMERIC(19, 2) NOT NULL,
    type VARCHAR(50) NOT NULL,
    stock INTEGER NOT NULL,
    available BOOLEAN NOT NULL
);

INSERT INTO products (id, created_at, updated_at, title, description, price, type, stock, available)
VALUES
('77777777-7777-4777-8777-777777777771', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Telegram Premium', 'Подписка на 1 год', 2990.00, 'SUBSCRIPTION', 100, true),
('77777777-7777-4777-8777-777777777772', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Яндекс Плюс', 'Подписка на 6 месяцев', 1490.00, 'SUBSCRIPTION', 50, true),
('77777777-7777-4777-8777-777777777773', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Cyberpunk 2077', 'Ролевая игра в открытом мире', 1999.00, 'GAME', 10, true),
('77777777-7777-4777-8777-777777777774', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'The Witcher 3: Blood and Wine', 'DLC для The Witcher 3', 499.00, 'DLC', 20, true),
('77777777-7777-4777-8777-777777777775', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'онлайн-кинотеатр ИВИ', 'Подписка на месяц', 399.00, 'SUBSCRIPTION', 150, true);
