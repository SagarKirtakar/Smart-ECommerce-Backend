# 📌 Smart E-Commerce Backend – Sample Project Output

## 1. Product Management

### Add Product

```
====== Product Module ======

Add Product
------------
Enter Name      : Mobile
Enter Category  : Electronics
Enter Price     : 20000
Enter Stock     : 5

✓ Product Added Successfully
```

### View Products

```
Product ID : 1
Name       : Laptop
Category   : Electronics
Price      : ₹55000
Stock      : 10

Product ID : 2
Name       : Mouse
Category   : Accessories
Price      : ₹500
Stock      : 100

Product ID : 3
Name       : Mechanical Keyboard
Category   : Accessories
Price      : ₹2500
Stock      : 30
```

### Search Product

```
Enter Product ID : 3

Product Found

Product ID : 3
Name       : Mechanical Keyboard
Category   : Accessories
Price      : ₹2500
Stock      : 30
```

### Update Product

```
Enter Product ID : 3

Enter New Name      : Keyboard
Enter New Category  : Accessories
Enter New Price     : 3000
Enter New Stock     : 25

✓ Product Updated Successfully
```

---

## 2. Customer Management

### Register Customer

```
Enter Name  : Dipak Panwar
Enter Email : dipak@gmail.com
Enter Phone : +91957863255

✓ Customer Registered Successfully
```

### Search Customer

```
Enter Customer ID : 10

Customer Found

Customer ID : 10
Name        : Dipak Panwar
Email       : dipak@gmail.com
Phone       : +91957863255
```

### Update Customer

```
Enter Customer ID : 10

Enter New Name  : Dipak
Enter New Email : dipak2002@gmail.com
Enter New Phone : +91789632147

✓ Customer Updated Successfully
```

---

## 3. Cart Management

### Add Product To Cart

```
Enter Customer ID : 3
Enter Product ID  : 3
Enter Quantity    : 1

✓ Product Added To Cart Successfully
```

### View Cart

```
Customer ID : 3

-----------------------------------
Product Name : Mechanical Keyboard
Price        : ₹2500
Quantity     : 1
-----------------------------------
```

### Calculate Cart Total

```
Customer ID : 3

Total Amount = ₹2500
```

### Remove Product From Cart

```
Enter Cart ID : 1

✓ Product Removed From Cart Successfully
```

---

## 4. Order Management

### Place Order

```
Enter Customer ID : 8

✓ Order Placed Successfully
```

### View Order History

```
Order ID     : 2
Customer ID  : 8
Order Date   : 22-06-2026
Total Amount : ₹110000
```

### Search Order

```
Enter Order ID : 2

Order ID     : 2
Customer ID  : 8
Order Date   : 22-06-2026
Total Amount : ₹110000
```

### View Order Details

```
Order ID : 2

-----------------------------------
Product Name : Laptop
Price        : ₹55000
Quantity     : 2
-----------------------------------
```

---

## 5. Analytics Module

### Total Revenue

```
Total Revenue = ₹220000
```

### Total Orders

```
Total Orders = 2
```

### Top Selling Products

```
Top Selling Products

-----------------------------------
Product Name : Laptop
Total Sold   : 4
-----------------------------------
```

---

# Project Flow

```
Product Module
      ↓
Customer Module
      ↓
Cart Module
      ↓
Order Module
      ↓
Analytics Module
```

---

# Database Flow

```
Customer
    ↓
Cart
    ↓
Orders
    ↓
Order Items
    ↓
Analytics Reports
```
