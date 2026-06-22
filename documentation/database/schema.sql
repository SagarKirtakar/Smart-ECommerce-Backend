-- ==========================================
-- Smart E-Commerce Backend Database Schema
-- ==========================================

CREATE DATABASE ecommerce_db;

USE ecommerce_db;

-- ==========================================
-- Product Table
-- ==========================================

CREATE TABLE product (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price DOUBLE NOT NULL,
    stock INT NOT NULL
);

-- ==========================================
-- Customer Table
-- ==========================================

CREATE TABLE customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL
);

-- ==========================================
-- Cart Table
-- ==========================================

CREATE TABLE cart (
    cart_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,

    FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id),

    FOREIGN KEY (product_id)
        REFERENCES product(product_id)
);

-- ==========================================
-- Orders Table
-- ==========================================

CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DOUBLE NOT NULL,

    FOREIGN KEY (customer_id)
        REFERENCES customer(customer_id)
);

-- ==========================================
-- Order Items Table
-- ==========================================

CREATE TABLE order_items (
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,

    FOREIGN KEY (order_id)
        REFERENCES orders(order_id),

    FOREIGN KEY (product_id)
        REFERENCES product(product_id)
);

-- ==========================================
-- Database Schema Completed Successfully
-- ==========================================