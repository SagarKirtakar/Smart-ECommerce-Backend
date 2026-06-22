-- ==========================================
-- Smart E-Commerce Backend
-- Database Utility Queries
-- ==========================================

USE ecommerce_db;

-- ==========================================
-- Show All Tables
-- ==========================================

SHOW TABLES;

-- ==========================================
-- View Table Structures
-- ==========================================

DESC product;
DESC customer;
DESC cart;
DESC orders;
DESC order_items;

-- ==========================================
-- View All Records
-- ==========================================

SELECT * FROM product;

SELECT * FROM customer;

SELECT * FROM cart;

SELECT * FROM orders;

SELECT * FROM order_items;

-- ==========================================
-- Search Specific Records
-- ==========================================

SELECT *
FROM product
WHERE product_id = 1;

SELECT *
FROM customer
WHERE customer_id = 1;

SELECT *
FROM orders
WHERE order_id = 1;

-- ==========================================
-- Analytics Queries
-- ==========================================

-- Total Revenue
SELECT SUM(total_amount) AS total_revenue
FROM orders;

-- Total Orders
SELECT COUNT(*) AS total_orders
FROM orders;

-- Top Selling Products
SELECT
    p.name,
    SUM(oi.quantity) AS total_sold
FROM order_items oi
JOIN product p
ON oi.product_id = p.product_id
GROUP BY p.product_id, p.name
ORDER BY total_sold DESC;

-- ==========================================
-- Cart Queries
-- ==========================================

-- View Customer Cart
SELECT
    p.name,
    p.price,
    c.quantity
FROM cart c
JOIN product p
ON c.product_id = p.product_id
WHERE c.customer_id = 1;

-- Calculate Cart Total
SELECT
    SUM(p.price * c.quantity) AS total_amount
FROM cart c
JOIN product p
ON c.product_id = p.product_id
WHERE c.customer_id = 1;

-- ==========================================
-- Order Details Query
-- ==========================================

SELECT
    p.name,
    oi.price,
    oi.quantity
FROM order_items oi
JOIN product p
ON oi.product_id = p.product_id
WHERE oi.order_id = 1;

-- ==========================================
-- Delete Data
-- ==========================================

DELETE FROM cart;

DELETE FROM order_items;

DELETE FROM orders;

-- ==========================================
-- Reset Tables
-- ==========================================

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE cart;
TRUNCATE TABLE order_items;
TRUNCATE TABLE orders;

SET FOREIGN_KEY_CHECKS = 1;

-- ==========================================
-- Reset Auto Increment Values
-- ==========================================

TRUNCATE TABLE table_name;

ALTER TABLE cart AUTO_INCREMENT = 1;

ALTER TABLE order_items AUTO_INCREMENT = 1;

ALTER TABLE orders AUTO_INCREMENT = 1;

-- ==========================================
-- Check Auto Increment Values
-- ==========================================

SHOW TABLE STATUS LIKE 'product';

SHOW TABLE STATUS LIKE 'customer';

SHOW TABLE STATUS LIKE 'cart';

SHOW TABLE STATUS LIKE 'orders';

SHOW TABLE STATUS LIKE 'order_items';

-- ==========================================
-- Product Stock Queries
-- ==========================================

SELECT
    product_id,
    name,
    stock
FROM product;

-- Products with Low Stock
SELECT *
FROM product
WHERE stock < 5;

-- ==========================================
-- End of Utility Queries
-- Smart E-Commerce Backend
-- ==========================================