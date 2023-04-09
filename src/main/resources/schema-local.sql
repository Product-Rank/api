DROP TABLE IF EXISTS bookmark CASCADE;
DROP TABLE IF EXISTS comments CASCADE;
DROP TABLE IF EXISTS product_image CASCADE;
DROP TABLE IF EXISTS product CASCADE;
DROP TABLE IF EXISTS company CASCADE;
DROP TABLE IF EXISTS questionnaire CASCADE;
DROP TABLE IF EXISTS community CASCADE;
DROP TABLE IF EXISTS ranking CASCADE;
DROP TABLE IF EXISTS product_user CASCADE;

CREATE TABLE product_user (
id BIGINT NOT NULL AUTO_INCREMENT,
created_at DATETIME(6) NOT NULL,
updated_at DATETIME(6) NOT NULL,
access_token VARCHAR(255),
email VARCHAR(255),
nick_name VARCHAR(255),
picture VARCHAR(255),
sns_type VARCHAR(255),
user_name VARCHAR(255),
PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE company (
id BIGINT NOT NULL AUTO_INCREMENT,
created_at DATETIME(6) NOT NULL,
updated_at DATETIME(6) NOT NULL,
company_description VARCHAR(255),
company_name VARCHAR(255),
company_number VARCHAR(255),
user_id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES product_user(id)
) ENGINE=InnoDB;

CREATE TABLE product (
id BIGINT NOT NULL AUTO_INCREMENT,
created_at DATETIME(6) NOT NULL,
updated_at DATETIME(6) NOT NULL,
thumbnail_url VARCHAR(255),
vote_cnt BIGINT,
product_description VARCHAR(255),
product_name VARCHAR(255),
company_id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (company_id) REFERENCES company(id)
) ENGINE=InnoDB;

CREATE TABLE product_image (
id BIGINT NOT NULL AUTO_INCREMENT,
url VARCHAR(255),
product_id BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (product_id) REFERENCES product(id)
) ENGINE=InnoDB;

CREATE TABLE bookmark (
id BIGINT NOT NULL AUTO_INCREMENT,
created_at DATETIME(6) NOT NULL,
updated_at DATETIME(6) NOT NULL,
product_id BIGINT,
user_id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (product_id) REFERENCES product(id),
FOREIGN KEY (user_id) REFERENCES product_user(id)
) ENGINE=InnoDB;

CREATE TABLE comments (
id BIGINT NOT NULL AUTO_INCREMENT,
created_at DATETIME(6) NOT NULL,
updated_at DATETIME(6) NOT NULL,
comment VARCHAR(255),
like_cnt BIGINT,
parents_id BIGINT,
product_id BIGINT,
user_id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (parents_id) REFERENCES comments(id),
FOREIGN KEY (product_id) REFERENCES product(id),
FOREIGN KEY (user_id) REFERENCES product_user(id)
) ENGINE=InnoDB;

CREATE TABLE community (
id BIGINT NOT NULL AUTO_INCREMENT,
created_at DATETIME(6) NOT NULL,
updated_at DATETIME(6) NOT NULL,
contents VARCHAR(255),
title VARCHAR(255) NOT NULL,
view INTEGER,
user_id BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES product_user(id)
) ENGINE=InnoDB;

CREATE TABLE questionnaire (
id BIGINT NOT NULL AUTO_INCREMENT,
answer VARCHAR(255),
answered_at DATETIME(6) NOT NULL,
created_at DATETIME(6) NOT NULL,
question VARCHAR(255) NOT NULL,
updated_at DATETIME(6) NOT NULL,
user_id BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES product_user(id)
) ENGINE=InnoDB;

CREATE TABLE ranking (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    product_id BIGINT NOT NULL,
    type VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    month INT,
    week INT,
    day INT,
    `rank` INT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;