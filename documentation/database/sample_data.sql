-- ==========================================
-- Smart E-Commerce Backend Sample Data
-- ==========================================

USE ecommerce_db;

-- ==========================================
-- Insert Sample Products
-- ==========================================

INSERT INTO product(name, category, price, stock)
VALUES
('Laptop', 'Electronics', 55000, 20),
('Mouse', 'Accessories', 500, 100),
('Keyboard', 'Accessories', 1200, 50),
('Headphones', 'Electronics', 2500, 15),
('Charger', 'Electronics', 2000, 3),
('Watch', 'Electronics', 10000, 2);

-- ==========================================
-- Insert Sample Customers
-- ==========================================

INSERT INTO customer(name, email, phone)
VALUES
('Kunal Solanke', 'kunalsolanke@email.com', '+918476543210'),
('Aanya Sharma', 'aanya.sharma@example.com', '+919123456789'),
('Rahul Verma', 'rahul.v@domain.com', '+919988776655'),
('Priya Nair', 'priya.nair@webmail.com', '+919555123456'),
('Amit Patel', 'amit.patel@techcorp.com', '+918888899999'),
('Sagar Kirtakar', 'sagar10@gmail.com', '+919579036731');

-- ==========================================
-- Insert Sample Cart Data
-- ==========================================

INSERT INTO cart(customer_id, product_id, quantity)
VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 3);

-- ==========================================
-- Insert Sample Orders
-- ==========================================

INSERT INTO orders(customer_id, total_amount)
VALUES
(1, 110000),
(2, 500);

-- ==========================================
-- Insert Sample Order Items
-- ==========================================

INSERT INTO order_items(order_id, product_id, quantity, price)
VALUES
(1, 1, 2, 55000),
(2, 2, 1, 500);

-- ==========================================
-- Sample Data Inserted Successfully
-- ==========================================