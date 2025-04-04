INSERT INTO products (name, description, price, category_id, available, category_path)
VALUES ('Laptop', 'High-performance laptop for gaming', 1299.99, (SELECT id FROM categories WHERE name = 'Computers' LIMIT 1), TRUE, 'Electronics > Computers');

INSERT INTO products (name, description, price, category_id, available, category_path)
VALUES ('Smartphone', 'Latest model with 5G support', 799.99, (SELECT id FROM categories WHERE name = 'Smartphones' LIMIT 1), TRUE, 'Electronics > Smartphones');

INSERT INTO products (name, description, price, category_id, available, category_path)
VALUES ('T-Shirt', 'Comfortable cotton t-shirt', 19.99, (SELECT id FROM categories WHERE name = 'Shirts' LIMIT 1), TRUE, 'Clothing > Shirts');

INSERT INTO products (name, description, price, category_id, available, category_path)
VALUES ('Jeans', 'Stylish blue jeans', 49.99, (SELECT id FROM categories WHERE name = 'Pants' LIMIT 1), TRUE, 'Clothing > Pants');

INSERT INTO products (name, description, price, category_id, available, category_path)
VALUES ('Tablet', '10-inch tablet with stylus', 499.99, (SELECT id FROM categories WHERE name = 'Computers' LIMIT 1), FALSE, 'Electronics > Computers');