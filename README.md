# PRODUCT RANK API

## Environment
### Spring 3.0.1

---
### Open JDK 17.0.2
 Download : https://jdk.java.net/archive/

---
### Java 17

---

## Database
### MySQL  
TODO : Should be Dockerized
 - version : TODO
 - database : prodcut_rank_db
 - port : 3306
 - user : pr_user
 - pw : pr_user

Query to create user
 ```
 create user 'pr_user'@'localhost' identified by 'pr_user';
 grant all privileges on *.* to 'pr_user'@'localhost';
 ```
 