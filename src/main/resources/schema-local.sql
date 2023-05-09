drop table IF EXISTS bookmark CASCADE;
drop table IF EXISTS comments CASCADE;
drop table IF EXISTS product_image CASCADE;
drop table IF EXISTS product CASCADE;
drop table IF EXISTS company CASCADE;
drop table IF EXISTS questionnaire CASCADE;
drop table IF EXISTS community CASCADE;
drop table IF EXISTS ranking CASCADE;
drop table IF EXISTS product_user CASCADE;

create TABLE product_user (
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

create TABLE company (
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

create TABLE product (
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

create TABLE product_image (
id BIGINT NOT NULL AUTO_INCREMENT,
url VARCHAR(255),
product_id BIGINT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (product_id) REFERENCES product(id)
) ENGINE=InnoDB;

create TABLE bookmark (
id BIGINT NOT NULL AUTO_INCREMENT,
created_at DATETIME(6) NOT NULL,
updated_at DATETIME(6) NOT NULL,
product_id BIGINT,
user_id BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (product_id) REFERENCES product(id),
FOREIGN KEY (user_id) REFERENCES product_user(id)
) ENGINE=InnoDB;

create TABLE comments (
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

create TABLE community (
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

create TABLE questionnaire (
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

create table ranking (
    id bigint not null auto_increment,
    product_id bigint not null,
    user_id bigint not null,
    created_at datetime(6) not null,
    updated_at datetime(6) not null,
    primary key (id)
 ) engine=InnoDB;