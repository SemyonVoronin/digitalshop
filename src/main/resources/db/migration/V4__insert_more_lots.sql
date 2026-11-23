INSERT INTO lots (id, created_at, updated_at, title, description, start_price, current_price, seller_id, status, ends_at)
VALUES
-- настолки и подписки
('33333333-3333-4333-8333-333333333331', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'GloomHaven', 'Тёмное фэнтези с сотнями сценариев', 17900.00, 17900.00, 100, 'ACTIVE', CURRENT_TIMESTAMP + INTERVAL '7 days'),
('33333333-3333-4333-8333-333333333332', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Бомба 💣', 'Настольная игра от Magellan', 900.00, 900.00, 100, 'ACTIVE', CURRENT_TIMESTAMP + INTERVAL '1 day'),
('33333333-3333-4333-8333-333333333333', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Descent: Сказания тьмы', 'Кампания продолжает сюжет после событий первого акта', 19900.00, 19900.00, 100, 'ACTIVE', CURRENT_TIMESTAMP + INTERVAL '2 hours'),

('44444444-4444-4444-8444-444444444441', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Яндекс Плюс', 'Подписка на 6 месяцев', 1490.00, 1490.00, 100, 'ACTIVE', CURRENT_TIMESTAMP + INTERVAL '10 days'),
('44444444-4444-4444-8444-444444444442', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Telegram Premium', 'Подписка на 1 год', 2990.00, 2990.00, 100, 'ACTIVE', CURRENT_TIMESTAMP + INTERVAL '5 days'),
('44444444-4444-4444-8444-444444444443', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ИВИ', 'Подписка на 3 месяца', 990.00, 990.00, 100, 'ACTIVE', CURRENT_TIMESTAMP + INTERVAL '3 days'),

-- ЛОТЫ ДЛЯ ТЕСТА @SCHEDULED (закроются через 1 и 2 минуты)
('55555555-5555-4555-8555-555555555551', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Тестовый лот (1 минута)', 'Этот лот закроется через 1 минуту', 100.00, 100.00, 100, 'ACTIVE', CURRENT_TIMESTAMP + INTERVAL '1 minute'),
('55555555-5555-4555-8555-555555555552', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Тестовый лот (2 минуты)', 'Этот лот закроется через 2 минуты', 200.00, 200.00, 100, 'ACTIVE', CURRENT_TIMESTAMP + INTERVAL '2 minutes');