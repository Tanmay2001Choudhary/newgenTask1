--Creating customers table

CREATE TABLE customers(
	cust_id SERIAL PRIMARY KEY,
	cust_name VARCHAR(100) NOT NULL
);


--Creating orders table

CREATE TABLE orders(
	ord_id SERIAL PRIMARY KEY,
	ord_date DATE NOT NULL,
	cust_id INTEGER NOT NULL,
	FOREIGN KEY (cust_id) REFERENCES customers (cust_id)
);


--Creating products table

CREATE TABLE products (
	p_id SERIAL PRIMARY KEY,
	cust_name VARCHAR(100) NOT NULL,
	price NUMERIC NOT NULL
);


--Creating order_items table

CREATE TABLE order_items(
	items_id SERIAL PRIMARY KEY,
	ord_id INTEGER NOT NULL,
	p_id INTEGER NOT NULL,
	quantity INTEGER NOT NULL,
	FOREIGN KEY (ord_id) REFERENCES orders(ord_id),
	FOREIGN KEY (p_id) REFERENCES products(p_id)
);


--Inserting data into customers table

INSERT INTO customers (cust_name)
VALUES
('Raju'), ('Sham'), ('Paul'), ('Alex');


--Inserting data into orders table

INSERT INTO orders (ord_date, cust_id)
VALUES
('2024-01-01', 1),
('2024-02-01', 2),
('2024-03-01', 3),
('2024-04-04', 2)
('2024-05-05', 1);


--Inserting data into products table

INSERT INTO products (p_name, price)
VALUES
('Laptop', 55000.00),
('Mouse', 500),
('Keyboard', 800.00),
('Cable', 250.00)
('Monitor', 150000.00);


--Inserting data into order_items table

INSERT INTO order_items (ord_id, p_id, quantity)
VALUES
	(1, 1, 1),
	(1, 4, 2),
	(2, 1, 1),
	(3, 2, 1),
	(3, 4, 5),
	(4,3, 1);



--PROCEDURE For Inserting data into products table

CREATE OR REPLACE PROCEDURE add_products(
	product_p_name VARCHAR,
	product_p_price NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
	INSERT INTO products(p_name,price)
	VALUES(
		product_p_name,
		product_p_price
	);
END;
$$;

CALL add_products('T.V.',60000.00);


--PROCEDURE For updating price data into products table

CREATE OR REPLACE PROCEDURE update_price(
	product_p_id INTEGER,
	new_price NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
	UPDATE products
	set price = new_price
	WHERE p_id = product_p_id;
END;
$$;

CALL update_price(4,350);

-- Creaing a View

CREATE VIEW billing_info AS
SELECT 
	c.cust_name,
	o.ord_date,
	p.p_name,
	p.price,
	oi.quantity,
	(oi.quantity*p.price) AS total_price
	FROM 
		order_items oi
	JOIN
		products p ON p.p_id = oi.p_id
	JOIN
		orders o ON o.ord_id = oi.ord_id
	JOIN
		customers c ON c.cust_id = o.cust_id;


--Function

CREATE OR REPLACE FUNCTION product_max_price_customer(product_p_name VARCHAR)
RETURNS TABLE(
    customer_name VARCHAR,
    product_price NUMERIC,
    product_quantity INTEGER,
    billing_price NUMERIC
)
LANGUAGE plpgsql
AS $$
BEGIN
    RETURN QUERY
    SELECT
        b.cust_name AS customer_name,
        p.price AS product_price,
        b.quantity AS product_quantity,
        MAX(b.total_price) AS billing_price
    FROM
        billing_info b
    JOIN
        products p ON p.p_name = b.p_name
    WHERE 
        b.p_name = product_p_name
    GROUP BY
        b.cust_name, p.price, b.quantity;
END;
$$;

SELECT * FROM product_max_price_customer('Cable');




--INNER JOIN

SELECT
	c.cust_name,
	o.ord_date,
	p.p_name,
	p.price,
	oi.quantity
FROM
	order_items oi
INNER JOIN
	orders o ON oi.ord_id=o.ord_id
INNER JOIN
	customers c ON o.cust_id=c.cust_id
INNER JOIN
	products p ON oi.p_id=p.p_id;


--LEFT JOIN

SELECT
	c.cust_name,
	o.ord_date,
	p.p_name,
	p.price,
	oi.quantity
FROM
	order_items oi
LEFT JOIN
	orders o ON oi.ord_id=o.ord_id
LEFT JOIN
	customers c ON o.cust_id=c.cust_id
LEFT JOIN
	products p ON oi.p_id=p.p_id;


--RIGHT JOIN

SELECT
	c.cust_name,
	o.ord_date,
	p.p_name,
	p.price,
	oi.quantity
FROM
	order_items oi
RIGHT JOIN
	orders o ON oi.ord_id=o.ord_id
RIGHT JOIN
	customers c ON o.cust_id=c.cust_id
RIGHT JOIN
	products p ON oi.p_id=p.p_id;


--FULL OUTER JOIN

SELECT
	c.cust_name,
	o.ord_date,
	p.p_name,
	p.price,
	oi.quantity
FROM
	order_items oi
FULL OUTER JOIN
	orders o ON oi.ord_id=o.ord_id
FULL OUTER JOIN
	customers c ON o.cust_id=c.cust_id
FULL OUTER JOIN
	products p ON oi.p_id=p.p_id;

SELECT
    c.cust_name,
    p.p_name
FROM
    customers c
CROSS JOIN
    products p;



--SELF JOIN

SELECT
    o1.ord_id AS order1_id,
    o2.ord_id AS order2_id,
    o1.ord_date AS order1_date,
    o2.ord_date AS order2_date
FROM
    orders o1
INNER JOIN
    orders o2 ON o1.cust_id = o2.cust_id AND o1.ord_id <> o2.ord_id;



