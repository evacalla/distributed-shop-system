INSERT INTO products (product_id, product_name, retail_id, description, price, created_at, updated_at) VALUES
            ('a8f3d6b0-7d9a-4e2c-9a2c-1b7f3d8a7c2e', 'Wireless Mouse', 'R-01', 'Compact wireless mouse with USB receiver', 19.9900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('c3e2f9d5-1a8c-4b6e-8e4d-3b9c7a4f6e1d', 'Mechanical Keyboard', 'R-01', 'RGB mechanical keyboard, blue switches', 79.9900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('e1d9c7b4-3a2f-8e5d-1b9c-7a6f3e4d8c5b', '27" Monitor', 'R-02', '27 inch 1440p IPS monitor', 249.9900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('4b3c7a1f-8e2d-9c5b-6f1d-a3c7e4d8b9f2', 'USB-C Hub', 'R-02', '6-in-1 USB-C hub with HDMI and Ethernet', 49.9900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('9a8c7b6f-5e4d-3c2b-1a9f-8e7d6c5b4a3f', 'Wireless Headphones', 'R-03', 'Over-ear Bluetooth headphones with noise cancellation', 129.9900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('7f6e5d4c-3b2a-1f8e-9d7c-6b5a4f3e2d1c', 'Portable SSD 1TB', 'R-03', 'USB 3.2 external solid state drive 1TB', 159.9900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('5d4e3c2b-1a9f-8e7d-6c5b-4a3f2e1d9c8b', 'Webcam 1080p', 'R-04', 'Full HD webcam with built-in microphone', 39.9900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('1b2c3d4e-5f6a-7b8c-9d0e-1f2a3b4c5d6e', 'Laptop Stand', 'R-04', 'Aluminum adjustable laptop stand', 29.9900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO stock (stock_id, product_id, retail_id, warehouse_id, available_quantity, committed_quantity, on_hand_quantity, version, created_at, updated_at) VALUES
            ('f7e6d5c4-b3a2-1e9d-8c7b-6a5f4d3c2b1a', 'a8f3d6b0-7d9a-4e2c-9a2c-1b7f3d8a7c2e', 'R-01', 'W-10', 150, 5, 155, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('d8c7b6a5-f4e3-d2c1-b0a9-f8e7d6c5b4a3', 'c3e2f9d5-1a8c-4b6e-8e4d-3b9c7a4f6e1d', 'R-01', 'W-10', 80, 2, 82, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('a1b2c3d4-e5f6-a7b8-c9d0-e1f2a3b4c5d6', 'e1d9c7b4-3a2f-8e5d-1b9c-7a6f3e4d8c5b', 'R-02', 'W-11', 40, 0, 40, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('8e7d6c5b-4a3f-2e1d-9c8b-7a6f5e4d3c2b', '4b3c7a1f-8e2d-9c5b-6f1d-a3c7e4d8b9f2', 'R-02', 'W-11', 120, 10, 130, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('6c5b4a3f-2e1d-9c8b-7a6f-5e4d3c2b1a9f', '9a8c7b6f-5e4d-3c2b-1a9f-8e7d6c5b4a3f', 'R-03', 'W-12', 60, 4, 64, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('1e2d3c4b-5a6f-7e8d-9c0b-1a2d3e4f5c6b', '7f6e5d4c-3b2a-1f8e-9d7c-6b5a4f3e2d1c', 'R-03', 'W-12', 50, 1, 51, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('b7a6f5e4-d3c2-b1a9-f8e7-d6c5b4a3f2e1', '5d4e3c2b-1a9f-8e7d-6c5b-4a3f2e1d9c8b', 'R-04', 'W-13', 95, 0, 95, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
            ('c3d4e5f6-a7b8-c9d0-e1f2-a3b4c5d6e7f8', '1b2c3d4e-5f6a-7b8c-9d0e-1f2a3b4c5d6e', 'R-04', 'W-13', 200, 20, 220, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);