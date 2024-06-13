CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    item VARCHAR(40),
    description TEXT,
    sell_price DECIMAL,
    stock INT,
    img_url TEXT
);