CREATE TABLE lots (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(2000) NOT NULL,
    start_price NUMERIC(19, 2) NOT NULL,
    current_price NUMERIC(19, 2) NOT NULL,
    seller_id BIGINT,
    winner_id BIGINT,
    status VARCHAR(50) NOT NULL,
    ends_at TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL
);