CREATE TABLE drug_inventory_list (
 product_id INT AUTO_INCREMENT PRIMARY KEY,
 product_name VARCHAR(255) NOT NULL,
 category VARCHAR(50),
 quantity_on_hand INT NOT NULL,
 expiry_date VARCHAR(50) NOT NULL,
 unit_price DECIMAL(10, 2) NOT NULL
);

-- CREATE TABLE drug_inventory_list (
--  product_id INT AUTO_INCREMENT PRIMARY KEY,
--  product_name VARCHAR(255) NOT NULL UNIQUE,
--  category VARCHAR(50),
--  quantity_on_hand INT NOT NULL,
--  expiry_date DATE NOT NULL,
--  unit_price DECIMAL(10, 2) NOT NULL
-- );