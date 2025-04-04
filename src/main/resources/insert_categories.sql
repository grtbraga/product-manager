INSERT INTO categories (name) VALUES ('Electronics');
INSERT INTO categories (name) VALUES ('Clothing');

INSERT INTO categories (name, parent_id) VALUES ('Computers', 1);
INSERT INTO categories (name, parent_id) VALUES ('Smartphones', 1);
INSERT INTO categories (name, parent_id) VALUES ('Shirts', 2);
INSERT INTO categories (name, parent_id) VALUES ('Pants', 2);