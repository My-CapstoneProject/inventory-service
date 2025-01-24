CREATE TABLE inventory_details (
    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    prod_quantity INT NOT NULL,
    in_store_id INT NOT NULL,
    in_product_id INT NOT NULL
);
