drop database techshop;
CREATE DATABASE TechShop;
USE TechShop;
show tables;
-- Customers Table
CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Email VARCHAR(255),
    Phone VARCHAR(20),
    Address VARCHAR(255),
    Password VARCHAR(255)
);

-- Products Table
CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    ProductName VARCHAR(255),
    Description VARCHAR(255),
    Price DECIMAL(10, 2)
);

-- Orders Table
CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATE,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- OrderDetails Table
CREATE TABLE OrderDetails (
    OrderDetailID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Inventory Table
CREATE TABLE Inventory (
    InventoryID INT AUTO_INCREMENT PRIMARY KEY,
    ProductID INT,
    QuantityInStock INT,
    LastStockUpdate DATE,
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- PaymentHistory Table
CREATE TABLE PaymentHistory (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT,
    OrderID INT,
    PaymentDate DATETIME,
    Amount DECIMAL(10, 2),
    FOREIGN KEY (UserID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
);

-- Cart Table
CREATE TABLE Cart (
    CartItemId INT AUTO_INCREMENT PRIMARY KEY,
    ProductId INT,
    ProductName VARCHAR(255),
    ProductDescription TEXT,
    Quantity INT,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (ProductId) REFERENCES Products(ProductId)
);

-- CartItems Table
CREATE TABLE CartItems (
    CartItemId INT AUTO_INCREMENT PRIMARY KEY,
    CustomerId INT,
    ProductId INT,
    ProductName VARCHAR(255),
    Quantity INT,
    TotalAmount DECIMAL(10, 2),
    FOREIGN KEY (CustomerId) REFERENCES Customers(CustomerID),
    FOREIGN KEY (ProductId) REFERENCES Products(ProductID)
);


-- Insert data into Customers table
INSERT INTO Customers (CustomerID, FirstName, LastName, Email, Phone, Address, Password)
VALUES 
(1, 'John', 'Doe', 'john.doe@example.com', '123-456-7890', '123 Main St', 'password123'),
(2, 'Jane', 'Smith', 'jane.smith@example.com', '987-654-3210', '456 Oak St', 'securepass'),
(3, 'Alice', 'Johnson', 'alice.johnson@example.com', '555-123-4567', '789 Pine St', 'pass123'),
(4, 'Bob', 'Williams', 'bob.williams@example.com', '789-456-1230', '101 Elm St', 'qwerty'),
(5, 'Eva', 'Anderson', 'eva.anderson@example.com', '321-654-9870', '202 Maple St', 'password567');
select * from customers;
-- Insert data into Products table
INSERT INTO Products (ProductID, ProductName, Description, Price)
VALUES 
(1, 'Laptop', 'High-performance laptop', 999.99),
(2, 'Smartphone', 'Latest smartphone model', 599.99),
(3, 'Headphones', 'Noise-canceling headphones', 149.99),
(4, 'Tablet', '10-inch tablet', 299.99),
(5, 'Smartwatch', 'Fitness and health tracker', 129.99),
(6, 'Desktop Computer', 'Powerful desktop computer', 1299.99),
(7, 'Camera', 'Professional DSLR camera', 899.99),
(8, 'Wireless Mouse', 'Ergonomic wireless mouse', 29.99),
(9, 'External Hard Drive', '1TB external hard drive', 79.99),
(10, 'Gaming Console', 'Latest gaming console', 499.99),
(11, 'Printer', 'All-in-one printer', 149.99),
(12, 'Bluetooth Speaker', 'Portable Bluetooth speaker', 79.99),
(13, 'Fitness Tracker', 'Activity and sleep tracker', 49.99),
(14, '4K TV', 'Smart 4K television', 799.99),
(15, 'Wireless Earbuds', 'True wireless earbuds', 89.99),
(16, 'Coffee Maker', 'Automatic coffee maker', 39.99),
(17, 'Robot Vacuum', 'Smart robotic vacuum cleaner', 199.99),
(18, 'Wireless Router', 'High-speed wireless router', 69.99),
(19, 'Electric Toothbrush', 'Rechargeable electric toothbrush', 34.99),
(20, 'External SSD', '500GB external solid-state drive', 119.99);
-- Insert data into Orders table
INSERT INTO Orders (OrderID, CustomerID, OrderDate, TotalAmount)
VALUES 
(1, 1, '2023-01-15', 999.99),
(2, 2, '2023-02-22', 599.99),
(3, 3, '2023-03-10', 149.99),
(4, 4, '2023-04-05', 299.99),
(5, 5, '2023-05-20', 129.99);

-- Insert data into OrderDetails table
INSERT INTO OrderDetails (OrderDetailID, OrderID, ProductID, Quantity)
VALUES 
(1, 1, 1, 1),
(2, 2, 2, 2),
(3, 3, 3, 3),
(4, 4, 4, 4),
(5, 5, 5, 1);

-- Insert data into Inventory table
INSERT INTO Inventory (InventoryID, ProductID, QuantityInStock, LastStockUpdate)
VALUES 
(1, 1, 10, '2023-01-15'),
(2, 2, 15, '2023-02-22'),
(3, 3, 20, '2023-03-10'),
(4, 4, 8, '2023-04-05'),
(5, 5, 5, '2023-05-20');
select * from inventory;

-- Insert data into PaymentHistory table
INSERT INTO PaymentHistory (UserID, OrderID, PaymentDate, Amount)
VALUES 
(1, 1, '2023-01-16 10:30:00', 999.99),
(2, 2, '2023-02-23 11:45:00', 599.99),
(3, 3, '2023-03-11 15:20:00', 149.99),
(4, 4, '2023-04-06 14:00:00', 299.99),
(5, 5, '2023-05-21 09:00:00', 129.99);

-- Insert data into CartItems table
INSERT INTO cartItems (customerId, productId, productName, quantity, totalAmount)
VALUES
(1, 1, 'Laptop', 2, 1999.98),
(1, 3, 'Headphones', 1, 149.99),
(2, 2, 'Smartphone', 1, 599.99),
(3, 4, 'Tablet', 3, 899.97),
(2, 5, 'Smartwatch', 2, 259.98);

select * from cartitems;
select * from customers;
select * from products;













-- Insert into Customers table
INSERT INTO Customers VALUES(1, 'Suraj', 'Kumar', 'surajkumar@gmail.com', '1234567890', 'Muzaffarpur, BR, IN'),(2, 'Ratnesh', 'Kumar', 'ratneshkumar@gmail.com', '+9876543210', 'Bhopal, MP, IN'),(3, 'Abhishek', 'Negi', 'abhisheknegi@gmail.com', '1235677890', 'Gurgaon, UP, IN'),(4, 'Riju', 'Patidar', 'rijupatidar@gmail.com', '6734567890', 'Delhi, Delhi, IN'),(5, 'Gourav', 'Kumar', 'gouravkumar@gmail.com', '1234562390', 'Patna, BR, IN'),(6, 'Pranay', 'Ippili', 'ippilipranay@gmail.com', '1233433670', 'Hyderabad, TG, IN'),(7, 'Anoop', 'Kumar', 'anoopkumar@gmail.com', '12098567890', 'Indore, MP, IN'),(8, 'Mr. X', 'Kumar', 'mrx@gmail.com', '1234567812', 'Unknown, XX, IN'),(9, 'Kritik', 'Kumar', 'kritikkumar@gmail.com', '1287567890', 'Pune, MH, IN'),(10, 'Arora', 'Kumar', 'arorakumar@gmail.com', '1234562390', 'Bangalore, KK, IN');
-- Insert into Products table
INSERT INTO Products VALUES(1, 'Laptop', 'High-performance laptop', 120000.00),(2, 'Smartphone', 'Latest smartphone model', 80000.00),(3, 'Mobile', 'High-performance Mobile', 2000.00),(4, 'Keyboard', 'High-performance Keyboard', 1200.00),(5, 'Earphone', 'High-performance Earphone', 1200.50),(6, 'Light Bulb', 'Light the Night', 200.00),(7, 'Emergency light', 'In need of power cut', 200.50),(8, 'Adapter', 'High-performance laptop adapter', 1500.00),(9, 'iPad', 'High-performance iPad', 200000.00),(10, 'MacBook', 'High-performance MacBook', 42000.00);
-- Insert into Orders table
INSERT INTO Orders VALUES(101, 1, '2023-01-10', 24000.00),(102, 2, '2023-01-12', 80000.00),(103, 3, '2023-11-12', 3600.15),(104, 6, '2023-10-12', 1000.25),(105, 5, '2022-01-12', 3000.00),(106, 4, '2021-01-12', 1200.00),(107, 10, '2023-5-10', 8000.00),(108, 8, '2023-1-1', 200000.00),(109, 9, '2022-01-01', 168000.00),(110, 7, '2021-11-12', 600.00);
INSERT INTO Orders VALUES(113, 7, '2023-01-10', 22000.00);
-- Insert into OrderDetails table
INSERT INTO OrderDetails VALUES(1001, 101, 1, 2),(1002, 102, 2, 1),(1003, 106, 5, 3),(1004, 104, 7, 5),(1005, 103, 8, 2),(1006, 107, 4, 1),(1007, 109, 3, 4),(1008, 108, 9, 1),(1009, 110, 10, 4),(1010, 105, 6, 3);
-- Insert into Inventory table
INSERT INTO Inventory VALUES(10001, 1, 10, '2023-01-01'),(10002, 2, 20, '2023-01-05'),(10003, 4, 20, '2023-01-08'),(10004, 10, 20, '2022-12-05'),(10005, 6, 20, '2023-02-05'),(10006, 7, 20, '2023-01-20'),(10007, 5, 20, '2023-01-15'),(10008, 9, 20, '2022-11-15'),(10009, 3, 20, '2023-02-07'),(10010, 8, 20, '2023-01-25');

-- Update Products table with new column 'Category';
Alter table Products add column (Category Varchar(50));
insert into products values(11,'iPhone','Latest iPhone 14 pro',80000.00,'Apple Products');
update products set Category = 'Main Gadgets' where ProductName in ('Laptop','Smartphone','Mobile');
update products set Category = 'Accessories' where ProductName in ('Keyboard','Adapter');
update products set Category = 'Apple Products' where ProductName in ('iPad','MacBook','iPhone');
update products set Category = 'Electronics' where ProductName in ('Light Bulb','Emergency Light');
update products set Category = 'Daily Need' where ProductName in ('Earphone','Tablet');

select * from customers;
select * from products;
select * from orders;
select * from orderdetails;
select * from inventory;


######------BASIC QUERIES------######
-- 1. Write an SQL query to retrieve the names and emails of all customers. 
select concat(firstname,' ',lastname) as Name , email from customers;
-- 2. Write an SQL query to list all orders with their order dates and corresponding customer names.
select o.*,concat(c.firstname,' ',c.lastname) as Name from orders o left join customers c on o.customerid = c.customerid;
-- 3. Write an SQL query to insert a new customer record into the "Customers" table. Include customer information such as name, email, and address.
insert into customers (CustomerID ,FirstName ,LastName ,Email ,Phone ,Address) values(11,'Aanchal','Kumari','anchalkumari1900@gmail.com','7254885020','Muzaffarpur BR IN');
-- 4. Write an SQL query to update the prices of all electronic gadgets in the "Products" table by increasing them by 10%.
update products set price = price+price*0.1;
-- 5. Write an SQL query to delete a specific order and its associated order details from the "Orders" and "OrderDetails" tables. Allow users to input the order ID as a parameter.
 
-- 6. Write an SQL query to insert a new order into the "Orders" table. Include the customer ID, order date, and any other necessary information.
insert into orders(OrderID ,CustomerID ,OrderDate,TotalAmount) values(309,8,'2023-05-23',9000.00);
-- 7. Write an SQL query to update the contact information (e.g., email and address) of a specific customer in the "Customers" table. Allow users to input the customer ID and new contact information.
DROP PROCEDURE IF EXISTS update_contact;
delimiter @@
create procedure update_contact(in email varchar(255),in address text,in id int)
begin
update customers c set c.email=email,c.address = address where c.customerid=id;
end @@
delimiter ;
call update_contact('updated@email.com','MFP BR IN',5);
-- 8. Write an SQL query to recalculate and update the total cost of each order in the "Orders" table based on the prices and quantities in the "OrderDetails" table.
 
-- 9. Write an SQL query to delete all orders and their associated order details for a specific customer from the "Orders" and "OrderDetails" tables. Allow users to input the customer ID as a parameter.

-- 10. Write an SQL query to insert a new electronic gadget product into the "Products" table, including product name, category, price, and any other relevant details.
insert into products(ProductID,ProductName,Description ,Price,category) values(19,'Tablet','High performance Lenovo Tablet',12000.00,'Daily Need');
11. Write an SQL query to update the status of a specific order in the "Orders" table (e.g., from "Pending" to "Shipped"). Allow users to input the order ID and the new status.
12. Write an SQL query to calculate and update the number of orders placed by each customer in the "Customers" table based on the data in the "Orders" table.


########--------Joins:---------#########
1. Write an SQL query to retrieve a list of all orders along with customer information (e.g.,customer name) for each order.
2. Write an SQL query to find the total revenue generated by each electronic gadget product.Include the product name and the total revenue.
3. Write an SQL query to list all customers who have made at least one purchase. Include their names and contact information.
-- 4. Write an SQL query to find the most popular electronic gadget, which is the one with the highest total quantity ordered. Include the product name and the total quantity ordered.
SELECT p.ProductName ,SUM(od.Quantity) AS TotalQuantityOrdered FROM OrderDetails od JOIN Products p ON od.ProductID = p.ProductID GROUP BY p.ProductID, p.ProductName ORDER BY TotalQuantityOrdered DESC LIMIT 1;
-- 5. Write an SQL query to retrieve a list of electronic gadgets along with their corresponding categories.
Select ProductID,ProductName,Category from Products; 



# Aggregate Functions and Subqueries
-- 7. Write an SQL query to find the most popular product category, which is the one with the highest total quantity ordered across all orders.
SELECT p.ProductID,p.ProductName AS Most_Pop_Prdkt,SUM(od.Quantity) AS TotalQuantityOrdered FROM OrderDetails od JOIN Products p ON od.ProductID = p.ProductID GROUP BY p.ProductID, p.ProductName ORDER BY TotalQuantityOrdered DESC LIMIT 1;
-- SELECT p.ProductID,p.ProductName,(SELECT SUM(od.Quantity)FROM OrderDetails od WHERE od.ProductID = p.ProductID) AS TotalQuantityOrdered FROM Products p ORDER BYTotalQuantityOrdered DESC LIMIT 1;


-- 8. Write an SQL query to find the customer who has spent the most money (highest total revenue) on electronic gadgets. List their name and total spending.
SELECT c.CustomerID,CONCAT(c.FirstName, ' ', c.LastName) AS CustomerName,SUM(od.Quantity * p.Price) AS TotalSpending FROM Customers c JOIN Orders o ON c.CustomerID = o.CustomerID JOIN OrderDetails od ON o.OrderID = od.OrderID JOIN Products p ON od.ProductID = p.ProductID GROUP BY c.CustomerID, CustomerName ORDER BY TotalSpending DESC LIMIT 1;


-- 9. Write an SQL query to calculate the average order value (total revenue divided by the number of orders) for all customers.
SELECT c.CustomerID,concat(c.FirstName,' ',c.LastName) as CustomerName,ROUND(AVG(o.TotalAmount),2) as AVG_SPND from Orders o join Customers c on c.CustomerId = o.CustomerId group by CustomerID;

-- 10. Write an SQL query to find the total number of orders placed by each customer and list their names along with the order count
SELECT c.CustomerId AS ID,CONCAT(c.FirstName, ' ', c.LastName) AS Cust_Name,SUM(od.Quantity) AS OrderCount FROM OrderDetails od JOIN Orders o ON od.OrderID = o.OrderId
JOIN Customers c ON c.CustomerID = o.CustomerId GROUP BY o.CustomerId, Cust_Name;
