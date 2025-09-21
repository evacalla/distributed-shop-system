CREATE TABLE products (
       product_id VARCHAR(50) NOT NULL PRIMARY KEY,
       product_name VARCHAR(255) NOT NULL,
       retail_id VARCHAR(255) NOT NULL,
       description VARCHAR(1000),
       price DECIMAL(19,4) NOT NULL,
       created_at TIMESTAMP NOT NULL,
       updated_at TIMESTAMP NOT NULL
);

CREATE TABLE stock (
       stock_id VARCHAR(50) NOT NULL PRIMARY KEY,
       product_id VARCHAR(50) NOT NULL,
       retail_id VARCHAR(255) NOT NULL,
       warehouse_id VARCHAR(255),
       available_quantity INT NOT NULL,
       committed_quantity INT NOT NULL,
       on_hand_quantity INT NOT NULL,
       version BIGINT,
       created_at TIMESTAMP NOT NULL,
       updated_at TIMESTAMP NOT NULL,
       CONSTRAINT fk_stock_product FOREIGN KEY (product_id) REFERENCES products(product_id)
);