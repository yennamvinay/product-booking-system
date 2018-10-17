CREATE TABLE `product` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`price` DECIMAL NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `seller` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(255) NOT NULL,
	`address` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `stock` (
	`id` INT NOT NULL AUTO_INCREMENT, 
	`product_id` INT NOT NULL,
	`count` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `booking` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`product_id` INT NOT NULL,
	`seller_id` INT NOT NULL,
	`count` INT NOT NULL,
	`booking_type` INT NOT NULL,
	`booked_date` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `processed_booking` (
	`id` INT NOT NULL,
	`product_id` INT NOT NULL,
	`seller_id` INT NOT NULL,
	`count` INT NOT NULL,
	`amount` DECIMAL NOT NULL,
	`booked_date` DATETIME NOT NULL,
	`booking_type` INT NOT NULL,
	`payment_status` INT NOT NULL,
	`processed_date` DATETIME NOT NULL
);

CREATE TABLE `payment` (
	`booking_id` INT NOT NULL,
	`amount` DECIMAL NOT NULL,
	`id` INT NOT NULL AUTO_INCREMENT,
	`payment_date` DATETIME NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `pending_payment` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`booking_id` INT NOT NULL,
	`amount` DECIMAL NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `stock` ADD CONSTRAINT `stock_fk0` FOREIGN KEY (`product_id`) REFERENCES `product`(`id`);

ALTER TABLE `booking` ADD CONSTRAINT `booking_fk0` FOREIGN KEY (`product_id`) REFERENCES `product`(`id`);

ALTER TABLE `booking` ADD CONSTRAINT `booking_fk1` FOREIGN KEY (`seller_id`) REFERENCES `seller`(`id`);

ALTER TABLE `processed_booking` ADD CONSTRAINT `processed_booking_fk0` FOREIGN KEY (`product_id`) REFERENCES `product`(`id`);

ALTER TABLE `processed_booking` ADD CONSTRAINT `processed_booking_fk1` FOREIGN KEY (`seller_id`) REFERENCES `seller`(`id`);

ALTER TABLE `payment` ADD CONSTRAINT `payment_fk0` FOREIGN KEY (`booking_id`) REFERENCES `processed_booking`(`id`);

ALTER TABLE `pending_payment` ADD CONSTRAINT `pending_payment_fk0` FOREIGN KEY (`booking_id`) REFERENCES `processed_booking`(`id`);

