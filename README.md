# Digital Shop

Маркетплейс цифровых товаров с аукционом.

## Технологии
- Java 21
- Spring Boot 4.1.1
- Spring Data JPA
- PostgreSQL (Docker)
- Flyway (миграции)
- Thymeleaf (веб-интерфейс)
- Maven

## Запуск
1. `docker compose up -d` (поднять БД)
2. `mvn spring-boot:run`
3. Открыть `http://localhost:8080`

## Фичи
- Аукцион (лоты, ставки, автозакрытие).
- Магазин товаров (подписки, игры, DLC).
- Корзина и оформление заказа.
- Регистрация и логин.