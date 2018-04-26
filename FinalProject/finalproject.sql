/*
author:TongWu A20410395
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `email` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for `buy`
-- ----------------------------
DROP TABLE IF EXISTS `buy`;
CREATE TABLE `buy` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `buynum` varchar(255) DEFAULT NULL,
  `buytime` varchar(255) DEFAULT NULL,
  `buyuser` varchar(255) DEFAULT NULL,
  `buyuseremail` varchar(255) DEFAULT NULL,
  `buytype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buy
-- ----------------------------
INSERT INTO `buy` VALUES ('1', 'BMW', '1', '2018-04-25 20:23:15', 'admin', '123456789@qq.com', 'Buying car');
INSERT INTO `buy` VALUES ('2', 'BMW', '1', '2018-04-25 20:23:18', 'admin', '123456789@qq.com', 'Rental car');
INSERT INTO `buy` VALUES ('3', 'Wheel', '1', '2018-04-25 20:23:53', 'admin', '123456789@qq.com', 'Buy car parts');
INSERT INTO `buy` VALUES ('4', 'BMW', '1', '2018-04-25 20:28:10', 'admin', '123456789@qq.com', 'Buying car');
INSERT INTO `buy` VALUES ('5', 'BMW', '1', '2018-04-25 20:28:13', 'admin', '123456789@qq.com', 'Rental car');
INSERT INTO `buy` VALUES ('6', 'Wheel', '1', '2018-04-25 20:28:49', 'admin', '123456789@qq.com', 'Buy car parts');
INSERT INTO `buy` VALUES ('7', 'BMW', '1', '2018-04-25 20:30:11', 'test', '123456784@qq.com', 'Buying car');
INSERT INTO `buy` VALUES ('8', 'BMW', '1', '2018-04-25 20:30:14', 'test', '123456784@qq.com', 'Rental car');
INSERT INTO `buy` VALUES ('9', 'Wheel', '1', '2018-04-25 20:30:21', 'test', '123456784@qq.com', 'Buy car parts');
INSERT INTO `buy` VALUES ('10', 'BMW', '1', '2018-04-25 20:44:09', 'admin', '123456789@qq.com', 'Buying car');
INSERT INTO `buy` VALUES ('11', 'BMW', '1', '2018-04-25 20:44:11', 'admin', '123456789@qq.com', 'Rental car');
INSERT INTO `buy` VALUES ('12', 'Wheel', '1', '2018-04-25 20:44:35', 'admin', '123456789@qq.com', 'Buy car parts');
INSERT INTO `buy` VALUES ('13', 'BMW', '1', '2018-04-25 20:45:48', 'test', '41526378@qq.com', 'Buying car');
INSERT INTO `buy` VALUES ('14', 'BMW', '1', '2018-04-25 20:45:51', 'test', '41526378@qq.com', 'Rental car');
INSERT INTO `buy` VALUES ('15', 'Wheel', '1', '2018-04-25 20:45:57', 'test', '41526378@qq.com', 'Buy car parts');

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `buyprice` varchar(45) DEFAULT '0',
  `rentprice` varchar(45) DEFAULT '0',
  `filecode` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `imgpath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('3', 'BMW', 'limousine', '13000.0', '200.0', '4028829162fca1c50162fcd50c0c0006', 'D:\\software\\tomcat\\apache-tomcat-8.5.9\\wtpwebapps\\FinalProject_CarService\\upload/4028829162fca1c50162fcd50c0c0006.jpg', 'http://192.168.2.17:8080/FinalProject_CarService/upload/4028829162fca1c50162fcd50c0c0006.jpg');

-- ----------------------------
-- Table structure for `carpart`
-- ----------------------------
DROP TABLE IF EXISTS `carpart`;
CREATE TABLE `carpart` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `buyprice` varchar(45) DEFAULT '0',
  `filecode` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `imgpath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of carpart
-- ----------------------------
INSERT INTO `carpart` VALUES ('3', 'Wheel', '100.0', '4028829162fca1c50162fcd579940007', 'D:\\software\\tomcat\\apache-tomcat-8.5.9\\wtpwebapps\\FinalProject_CarService\\upload/4028829162fca1c50162fcd579940007.jpg', 'http://192.168.2.17:8080/FinalProject_CarService/upload/4028829162fca1c50162fcd579940007.jpg');

-- ----------------------------
-- Table structure for `customeraccount`
-- ----------------------------
DROP TABLE IF EXISTS `customeraccount`;
CREATE TABLE `customeraccount` (
  `email` varchar(45) NOT NULL,
  `rentcarnumber` int(11) DEFAULT NULL,
  `buycarnumber` int(11) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customeraccount
-- ----------------------------

-- ----------------------------
-- Table structure for `files`
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `filecode` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of files
-- ----------------------------
INSERT INTO `files` VALUES ('2', '402881e662f8ad0d0162f8ad0dbb0000', 'D:\\software\\tomcat\\apache-tomcat-8.5.9\\wtpwebapps\\FinalProject_CarService\\upload\\402881e662f8ad0d0162f8ad0dbb0000.jpg');
INSERT INTO `files` VALUES ('3', '4028829162fab84a0162fab84a460000', 'D:\\software\\tomcat\\apache-tomcat-8.5.9\\wtpwebapps\\FinalProject_CarService\\upload\\4028829162fab84a0162fab84a460000.jpg');
INSERT INTO `files` VALUES ('4', '4028829162fab84a0162fabb8dbc0001', 'D:\\software\\tomcat\\apache-tomcat-8.5.9\\wtpwebapps\\FinalProject_CarService\\upload\\4028829162fab84a0162fabb8dbc0001.jpg');
INSERT INTO `files` VALUES ('5', '4028829162fabce30162fabce3800000', 'D:\\software\\tomcat\\apache-tomcat-8.5.9\\wtpwebapps\\FinalProject_CarService\\upload\\4028829162fabce30162fabce3800000.jpg');

-- ----------------------------
-- Table structure for `rights`
-- ----------------------------
DROP TABLE IF EXISTS `rights`;
CREATE TABLE `rights` (
  `id` int(4) NOT NULL,
  `rights_name` varchar(45) NOT NULL,
  `user_power` int(4) NOT NULL,
  `car_power` int(4) NOT NULL,
  `car_part_power` int(4) NOT NULL,
  `user_account_power` int(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rights
-- ----------------------------
INSERT INTO `rights` VALUES ('1', 'admin', '1', '1', '1', '1');
INSERT INTO `rights` VALUES ('6', 'yjl', '2', '2', '2', '2');
INSERT INTO `rights` VALUES ('10', 'maomao', '2', '2', '2', '2');
INSERT INTO `rights` VALUES ('11', 'jasmine', '2', '2', '2', '2');
INSERT INTO `rights` VALUES ('14', 'yecw', '2', '2', '2', '2');
INSERT INTO `rights` VALUES ('15', 'jasmine', '2', '2', '2', '2');
INSERT INTO `rights` VALUES ('22', 'test', '2', '2', '2', '2');

-- ----------------------------
-- Table structure for `sequence`
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `seq_name` varchar(50) NOT NULL,
  `current_val` int(11) NOT NULL,
  `increment_val` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`seq_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sequence
-- ----------------------------
INSERT INTO `sequence` VALUES ('seq_buy', '15', '1');
INSERT INTO `sequence` VALUES ('seq_car', '3', '1');
INSERT INTO `sequence` VALUES ('seq_carpart', '3', '1');
INSERT INTO `sequence` VALUES ('seq_files', '5', '1');
INSERT INTO `sequence` VALUES ('seq_rights', '22', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Email` varchar(45) NOT NULL,
  `UserName` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `State` varchar(45) DEFAULT '1',
  `City` varchar(45) DEFAULT NULL,
  `StreetAddress` varchar(45) DEFAULT NULL,
  `Role` int(1) DEFAULT '1',
  `account` varchar(255) DEFAULT '0',
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123456789@qq.com', 'admin', 'admin', 'IL', 'Chicago', '3360 S Emerald', '0', '33300');
INSERT INTO `user` VALUES ('12345678@qq.com', 'maomao', '123456', 'CN', 'Beijing', 'DongCheng Street', '2', '500');
INSERT INTO `user` VALUES ('123456790@qq.com', 'jasmine', '123456', 'IL', 'Chicago', '2951 S Street', '2', '300');
INSERT INTO `user` VALUES ('12456378@qq.com', 'test', '123456', 'CN', 'Beijing', 'XiCheng Street', '2', '0');
INSERT INTO `user` VALUES ('3124522@qq.com', 'yecw', '123456', 'CN', 'Beijing', 'DongCheng Street', '2', '0');
INSERT INTO `user` VALUES ('yjl@qq.com', 'yjl', 'yjl', 'CN', 'Beijing', 'DongCheng Street', '2', '2200');

SET GLOBAL log_bin_trust_function_creators = 1;

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
