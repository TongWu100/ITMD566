drop table if exists sequence;     
create table sequence (         
seq_name        VARCHAR(50) NOT NULL,         
current_val     INT         NOT NULL,        
increment_val   INT         NOT NULL    DEFAULT 1,         
PRIMARY KEY (seq_name)   ); 

create function currval(v_seq_name VARCHAR(50))     
returns integer    
begin        
    declare value integer;         
    set value = 0;         
    select current_val into value  from sequence where seq_name = v_seq_name;   
   return value;   
end;

create function nextval (v_seq_name VARCHAR(50))  
    returns integer  
begin  
    update sequence set current_val = current_val + increment_val  where seq_name = v_seq_name;  
    return currval(v_seq_name);  
end;

INSERT INTO sequence VALUES ('seq_car', '1', '1');  
INSERT INTO sequence VALUES ('seq_carpart', '1', '1'); 
INSERT INTO sequence VALUES ('seq_rights', '2', '1');  

CREATE TABLE user(
   Email VARCHAR(45) NOT NULL primary key,
   UserName VARCHAR(45) NOT NULL,
   Password VARCHAR(45) NOT NULL,
   State VARCHAR(45) NULL,
   City VARCHAR(45) NULL,
   StreetAddress VARCHAR(45) NULL,
   Role INT(1) default 1);

CREATE TABLE admin (
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NULL,
  PRIMARY KEY (email));

CREATE TABLE customeraccount (
  email VARCHAR(45) NOT NULL,
  rentcarnumber INT NULL,
  buycarnumber INT NULL,
  balance DOUBLE NULL,
  PRIMARY KEY (`email`));
  

CREATE TABLE car (
  id VARCHAR(45) NOT NULL,
  name VARCHAR(45) NULL,
  type VARCHAR(45) NULL,
  buyprice VARCHAR(45) NULL,
  rentprice VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE carpart (
  id VARCHAR(45) NOT NULL,
  name VARCHAR(45) NULL,
  buyprice VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE rights (
  id int(4) NOT NULL,
  rights_name VARCHAR(45) NOT NULL,
  user_power int(4) NOT NULL,
  car_power int(4) NOT NULL,
  car_part_power int(4) NOT NULL,
  PRIMARY KEY (`id`));
  
INSERT INTO rights values(1,'admin',1,1,1);
INSERT INTO user values('123456789@qq.com','admin','admin',1,'中国','中国','0');









