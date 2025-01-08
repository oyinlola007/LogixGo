-- Table: user_usr
CREATE TABLE user_usr (
    usr_id INT(5) AUTO_INCREMENT,
    usr_first_name VARCHAR(20),
    usr_last_name VARCHAR(20),
    usr_email VARCHAR(30),
    usr_phone_number VARCHAR(11),
    usr_password VARCHAR(20),
    usr_role VARCHAR(10),
    PRIMARY KEY (usr_id),
    UNIQUE KEY uq_usr_email (usr_email)
);

-- Table: driver_drv
CREATE TABLE driver_drv (
    drv_id INT(5),
    drv_truck_registration VARCHAR(10),
    drv_truck_capacity INT(5),
    PRIMARY KEY (drv_id),
    UNIQUE KEY uq_drv_truck_registration (drv_truck_registration),
    CONSTRAINT fk_drv_usr_id FOREIGN KEY (drv_id) REFERENCES user_usr (usr_id)
);

-- Table: product_prd
CREATE TABLE product_prd (
    prd_id INT(5) AUTO_INCREMENT,
    prd_name VARCHAR(30),
    prd_unit_weight INT(4),
    PRIMARY KEY (prd_id),
    UNIQUE KEY uq_prd_name (prd_name)
);

-- Table: delivery_dlv
CREATE TABLE delivery_dlv (
    dlv_id INT(5) AUTO_INCREMENT,
    dlv_usr_id INT(5),
    dlv_date DATE,
    dlv_status VARCHAR(10),
    dlv_address_line_1 VARCHAR(30),
    dlv_address_line_2 VARCHAR(30),
    dlv_city VARCHAR(30),
    dlv_region VARCHAR(20),
    dlv_zip_code VARCHAR(5),
    PRIMARY KEY (dlv_id),
    CONSTRAINT fk_dlv_usr_id FOREIGN KEY (dlv_usr_id) REFERENCES user_usr (usr_id)
);

-- Table: delivery_item_dli
CREATE TABLE delivery_item_dli (
    dli_delivery_id INT(5),
    dli_product_id INT(5),
    dli_total_weight INT(5),
    PRIMARY KEY (dli_delivery_id, dli_product_id),
    CONSTRAINT fk_dli_delivery_id FOREIGN KEY (dli_delivery_id) REFERENCES delivery_dlv (dlv_id),
    CONSTRAINT fk_dli_product_id FOREIGN KEY (dli_product_id) REFERENCES product_prd (prd_id)
);

-- Table: route_rte
CREATE TABLE route_rte (
    rte_id INT(5) AUTO_INCREMENT,
    rte_date DATE,
    rte_driver_id INT(5),
    rte_status VARCHAR(10),
    PRIMARY KEY (rte_id),
    CONSTRAINT fk_rte_driver_id FOREIGN KEY (rte_driver_id) REFERENCES driver_drv (drv_id)
);

-- Table: route_delivery_rtd
CREATE TABLE route_delivery_rtd (
    rtd_route_id INT(5),
    rtd_delivery_id INT(5),
    PRIMARY KEY (rtd_route_id, rtd_delivery_id),
    CONSTRAINT fk_rtd_route_id FOREIGN KEY (rtd_route_id) REFERENCES route_rte (rte_id),
    CONSTRAINT fk_rtd_delivery_id FOREIGN KEY (rtd_delivery_id) REFERENCES delivery_dlv (dlv_id)
);

-- Table: constant_cst
CREATE TABLE constant_cst (
    cst_constant_name VARCHAR(30),
    cst_constant_value VARCHAR(30),
    PRIMARY KEY (cst_constant_name)
);
