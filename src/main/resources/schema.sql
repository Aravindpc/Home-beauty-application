set foreign_key_checks=0;
drop table if exists customer;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL UNIQUE,
  `number` bigint(10) NOT NULL UNIQUE,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `stylist_id` bigint(10)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

drop table if exists stylist;
CREATE TABLE `stylist` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) NOT NULL UNIQUE,
  `number` bigint(10) NOT NULL UNIQUE,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `customer`
  ADD CONSTRAINT FOREIGN KEY (`stylist_id`) REFERENCES `stylist` (`id`);
  
drop table if exists salon_service_detail;

CREATE TABLE `salon_service_detail` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` bigint(20) NOT NULL,
  `picture_location` varchar(255) NOT NULL,
  `salon_service_category` varchar(255) NOT NULL,
  `salon_service_category_types` varchar(255) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

drop table if exists customer_service;

CREATE TABLE `customer_service` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL,
  `service_id` bigint(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `customer_service`
  ADD CONSTRAINT FOREIGN KEY (`service_id`) REFERENCES `salon_service_detail` (`id`),
  ADD CONSTRAINT FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);
  
drop table if exists role;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

drop table if exists customer_role;

CREATE TABLE `customer_role` (
  `customer_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `customer_role`
  ADD PRIMARY KEY (`customer_id`,`role_id`),
  ADD KEY (`role_id`);
  
ALTER TABLE `customer_role`
  ADD CONSTRAINT FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);

drop table if exists stylist_role;

CREATE TABLE `stylist_role` (
  `stylist_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `stylist_role`
  ADD PRIMARY KEY (`stylist_id`,`role_id`),
  ADD KEY (`role_id`);
  
ALTER TABLE `stylist_role`
  ADD CONSTRAINT FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT FOREIGN KEY (`stylist_id`) REFERENCES `stylist` (`id`);


drop table if exists slot;

CREATE TABLE `slot` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `slot_start_date_time` datetime NOT NULL,
  `slot_end_date_time` datetime NOT NULL,
  `status` TINYINT(1) DEFAULT 0,
  `customer_id` bigint(20) NOT NULL UNIQUE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `slot`
  ADD CONSTRAINT FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);

drop table if exists stylist_slot;

CREATE TABLE `stylist_slot` (
  `stylist_id` bigint(20) NOT NULL,
  `slot_id` bigint(20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `stylist_slot`
  ADD PRIMARY KEY (`stylist_id`,`slot_id`),
  ADD KEY (`slot_id`);
  
ALTER TABLE `stylist_slot`
  ADD CONSTRAINT FOREIGN KEY (`slot_id`) REFERENCES `slot` (`id`),
  ADD CONSTRAINT FOREIGN KEY (`stylist_id`) REFERENCES `stylist` (`id`);

set foreign_key_checks=1;
