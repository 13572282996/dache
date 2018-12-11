/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.7.22 : Database - dache
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dache` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `dache`;

/*Table structure for table `cms_activity` */

DROP TABLE IF EXISTS `cms_activity`;

CREATE TABLE `cms_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `begin_date` datetime DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `hits` bigint(20) DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_publication` tinyint(1) NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_activity` */

/*Table structure for table `cms_ad` */

DROP TABLE IF EXISTS `cms_ad`;

CREATE TABLE `cms_ad` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `begin_date` datetime DEFAULT NULL,
  `content` longtext COLLATE utf8_unicode_ci,
  `end_date` datetime DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ad_position` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAF257ED923F7F790` (`ad_position`),
  CONSTRAINT `FKAF257ED923F7F790` FOREIGN KEY (`ad_position`) REFERENCES `cms_ad_position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_ad` */

/*Table structure for table `cms_ad_position` */

DROP TABLE IF EXISTS `cms_ad_position`;

CREATE TABLE `cms_ad_position` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `height` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `template` longtext COLLATE utf8_unicode_ci NOT NULL,
  `width` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_ad_position` */

/*Table structure for table `cms_admin` */

DROP TABLE IF EXISTS `cms_admin`;

CREATE TABLE `cms_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `is_locked` bit(1) NOT NULL,
  `locked_date` datetime DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_failure_count` int(11) NOT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `cms_admin` */

insert  into `cms_admin`(`id`,`create_date`,`modify_date`,`department`,`email`,`is_enabled`,`is_locked`,`locked_date`,`login_date`,`login_failure_count`,`login_ip`,`name`,`password`,`username`) values (1,'2013-01-01 13:24:32','2018-12-05 14:04:38','技术部','admin@test.com','','\0',NULL,'2018-12-05 14:04:38',0,NULL,'管理员','591aee627268a33697239b50e5536cf3','admin'),(5,'2018-06-07 19:00:58','2018-11-08 14:24:00',NULL,'test@qq.com','','\0',NULL,'2018-07-09 00:30:13',2,NULL,NULL,'098f6bcd4621d373cade4e832627b4f6','test'),(6,'2018-07-11 23:59:17','2018-11-08 10:36:44',NULL,'269525417@qq.com','','\0',NULL,'2018-07-18 23:01:24',1,NULL,NULL,'e10adc3949ba59abbe56e057f20f883e','hotian'),(8,'2018-11-08 14:28:36','2018-11-08 14:44:43',NULL,'111@123.com','','\0',NULL,'2018-11-08 14:29:05',0,NULL,'\'','b59c67bf196a4758191e42f76670ceba','4444');

/*Table structure for table `cms_admin_role` */

DROP TABLE IF EXISTS `cms_admin_role`;

CREATE TABLE `cms_admin_role` (
  `admins` bigint(20) NOT NULL,
  `roles` bigint(20) NOT NULL,
  PRIMARY KEY (`admins`,`roles`),
  KEY `FKD291D6053FF548F7` (`roles`),
  KEY `FKD291D605A022690F` (`admins`),
  CONSTRAINT `FKD291D6053FF548F7` FOREIGN KEY (`roles`) REFERENCES `cms_role` (`id`),
  CONSTRAINT `FKD291D605A022690F` FOREIGN KEY (`admins`) REFERENCES `cms_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_admin_role` */

insert  into `cms_admin_role`(`admins`,`roles`) values (1,1),(6,1),(5,2),(8,2);

/*Table structure for table `cms_area` */

DROP TABLE IF EXISTS `cms_area`;

CREATE TABLE `cms_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `full_name` longtext COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tree_path` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7BC16DC3BBCC97D6` (`parent`),
  CONSTRAINT `FK7BC16DC3BBCC97D6` FOREIGN KEY (`parent`) REFERENCES `cms_area` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_area` */

insert  into `cms_area`(`id`,`create_date`,`modify_date`,`orders`,`full_name`,`name`,`tree_path`,`parent`) values (1,'2018-06-21 14:38:34','2018-06-21 14:38:34',1,'四川','四川',',',NULL),(2,'2018-06-21 14:39:38','2018-06-21 14:39:38',2,'陕西','陕西',',',NULL);

/*Table structure for table `cms_article` */

DROP TABLE IF EXISTS `cms_article`;

CREATE TABLE `cms_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `article_form` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `author` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` longtext COLLATE utf8_unicode_ci,
  `hits` bigint(20) NOT NULL,
  `info` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_publication` tinyint(1) NOT NULL,
  `is_top` tinyint(1) NOT NULL,
  `picture` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seo_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seo_keywords` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seo_title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `article_category` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `line_mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK935C9C00C473263C` (`article_category`),
  CONSTRAINT `FK935C9C00C473263C` FOREIGN KEY (`article_category`) REFERENCES `cms_article_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_article` */

insert  into `cms_article`(`id`,`create_date`,`modify_date`,`article_form`,`author`,`content`,`hits`,`info`,`is_publication`,`is_top`,`picture`,`seo_description`,`seo_keywords`,`seo_title`,`title`,`article_category`,`address`,`image`,`line_mobile`,`time_date`) values (1,'2018-07-11 18:05:44','2018-07-12 12:38:45',NULL,'小孙专车','关于我们\r\n延安追风科技',0,NULL,1,0,NULL,NULL,NULL,NULL,'关于我们',1,NULL,NULL,NULL,NULL),(2,'2018-07-11 18:08:22','2018-07-11 18:08:22',NULL,'小孙专车','用户协议',0,NULL,1,0,NULL,NULL,NULL,NULL,'用户协议',1,NULL,NULL,NULL,NULL),(3,'2018-07-11 18:11:08','2018-07-12 12:37:32',NULL,'小孙专车','客服029-3333',0,NULL,1,0,NULL,NULL,NULL,NULL,'客服',1,NULL,NULL,NULL,NULL),(4,'2018-07-11 18:11:23','2018-07-11 18:11:23',NULL,'小孙专车','用户指南',0,NULL,1,0,NULL,NULL,NULL,NULL,'用户指南',1,NULL,NULL,NULL,NULL),(5,'2018-07-11 18:11:42','2018-07-11 18:11:42',NULL,'小孙专车','司机操作手册',0,NULL,1,0,NULL,NULL,NULL,NULL,'司机操作手册',1,NULL,NULL,NULL,NULL),(6,'2018-07-11 18:12:02','2018-07-11 18:12:02',NULL,'小孙专车','法律条款',0,NULL,1,0,NULL,NULL,NULL,NULL,'法律条款',1,NULL,NULL,NULL,NULL),(8,'2018-12-05 09:50:19','2018-12-05 09:50:19',NULL,'q','q',0,NULL,1,0,NULL,NULL,NULL,NULL,'q',1,NULL,NULL,NULL,NULL),(9,'2018-12-05 10:00:30','2018-12-05 10:00:30',NULL,'哇哇哇','<strong>反对法</strong>',0,NULL,0,1,NULL,NULL,NULL,NULL,'问问',1,NULL,NULL,NULL,NULL),(10,'2018-12-05 11:11:12','2018-12-05 11:11:12',NULL,NULL,NULL,0,NULL,1,0,NULL,NULL,NULL,NULL,'1',1,NULL,NULL,NULL,NULL);

/*Table structure for table `cms_article_category` */

DROP TABLE IF EXISTS `cms_article_category`;

CREATE TABLE `cms_article_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `before_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `en_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `seo_description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seo_keywords` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seo_title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tree_path` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3D7AD25DAD56D8BF` (`parent`),
  CONSTRAINT `FK3D7AD25DAD56D8BF` FOREIGN KEY (`parent`) REFERENCES `cms_article_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_article_category` */

insert  into `cms_article_category`(`id`,`create_date`,`modify_date`,`orders`,`before_path`,`en_name`,`grade`,`name`,`seo_description`,`seo_keywords`,`seo_title`,`tree_path`,`parent`) values (1,'2018-07-11 17:32:11','2018-07-11 17:32:13',NULL,'1','1',1,'1','1','1','1','1',1);

/*Table structure for table `cms_business_permit` */

DROP TABLE IF EXISTS `cms_business_permit`;

CREATE TABLE `cms_business_permit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `certificate` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `certify_date` int(11) NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` int(11) NOT NULL,
  `operation_area` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `organization` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `owner_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `start_date` int(11) NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stop_date` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_business_permit` */

/*Table structure for table `cms_car_info` */

DROP TABLE IF EXISTS `cms_car_info`;

CREATE TABLE `cms_car_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `gpsbrand` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gpsimei` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gpsinstall_date` int(11) NOT NULL,
  `gpsmodel` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `vin` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` int(11) NOT NULL,
  `brand` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `certificate` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `certify_datea` int(11) NOT NULL,
  `certify_dateb` int(11) NOT NULL,
  `check_state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `commercial_type` int(11) NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `engine_displace` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fare_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fee_print_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fix_state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` int(11) NOT NULL,
  `fuel_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `model` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `next_fix_date` int(11) DEFAULT NULL,
  `owner_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `plate_color` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `register_date` int(11) NOT NULL,
  `seats` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `trans_agency` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `trans_area` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `trans_date_start` int(11) NOT NULL,
  `trans_date_stop` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  `v_engine_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `vehicle_color` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `vehicle_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_car_info` */

/*Table structure for table `cms_car_insurance_info` */

DROP TABLE IF EXISTS `cms_car_insurance_info`;

CREATE TABLE `cms_car_insurance_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` int(11) NOT NULL,
  `insur_com` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `insur_count` decimal(19,2) NOT NULL,
  `insur_eff` int(11) NOT NULL,
  `insur_exp` int(11) NOT NULL,
  `insur_num` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `insur_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` int(11) NOT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_car_insurance_info` */

/*Table structure for table `cms_car_mileage_info` */

DROP TABLE IF EXISTS `cms_car_mileage_info`;

CREATE TABLE `cms_car_mileage_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` int(11) NOT NULL,
  `total_mile` decimal(19,2) NOT NULL,
  `update_time` int(11) NOT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_car_mileage_info` */

/*Table structure for table `cms_company_info` */

DROP TABLE IF EXISTS `cms_company_info`;

CREATE TABLE `cms_company_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `business_scope` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `contact_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `economic_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` int(11) NOT NULL,
  `identifier` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `legalid` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `legal_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `legal_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `legal_photo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reg_capital` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_company_info` */

/*Table structure for table `cms_driver_app` */

DROP TABLE IF EXISTS `cms_driver_app`;

CREATE TABLE `cms_driver_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `app_version` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `driver_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` int(11) NOT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `map_type` int(11) NOT NULL,
  `net_type` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_driver_app` */

/*Table structure for table `cms_driver_educate` */

DROP TABLE IF EXISTS `cms_driver_educate`;

CREATE TABLE `cms_driver_educate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `course_date` int(11) NOT NULL,
  `course_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `duration` int(11) NOT NULL,
  `flag` int(11) NOT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `start_time` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `stop_time` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_driver_educate` */

/*Table structure for table `cms_driver_info` */

DROP TABLE IF EXISTS `cms_driver_info`;

CREATE TABLE `cms_driver_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `certificate_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `commercial_type` int(11) NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `contract_company` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `contract_off` int(11) NOT NULL,
  `contract_on` int(11) NOT NULL,
  `driver_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_birthday` int(11) NOT NULL,
  `driver_census` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_contact_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `driver_education` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_gender` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `driver_language_level` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_license_off` int(11) NOT NULL,
  `driver_license_on` int(11) NOT NULL,
  `driver_marital_status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_nation` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `driver_nationality` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `driver_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emergency_contact` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emergency_contact_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emergency_contact_phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `flag` int(11) NOT NULL,
  `full_time_driver` int(11) DEFAULT NULL,
  `get_driver_license_date` int(11) NOT NULL,
  `get_network_car_proof_date` int(11) NOT NULL,
  `in_driver_blacklist` int(11) DEFAULT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `license_photo_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `network_car_issue_date` int(11) NOT NULL,
  `network_car_issue_organization` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `network_car_proof_off` int(11) NOT NULL,
  `network_car_proof_on` int(11) NOT NULL,
  `photo_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `register_date` int(11) NOT NULL,
  `state` int(11) NOT NULL,
  `taxi_driver` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_driver_info` */

/*Table structure for table `cms_driver_statistics` */

DROP TABLE IF EXISTS `cms_driver_statistics`;

CREATE TABLE `cms_driver_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `complained_count` int(11) NOT NULL,
  `cycle` int(11) NOT NULL,
  `flag` int(11) NOT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `order_count` int(11) NOT NULL,
  `traffic_violation_count` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_driver_statistics` */

/*Table structure for table `cms_fare_info` */

DROP TABLE IF EXISTS `cms_fare_info`;

CREATE TABLE `cms_fare_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `evening_peak_time_off` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `evening_peak_time_on` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fare_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fare_type_note` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fare_valid_off` int(11) DEFAULT NULL,
  `fare_valid_on` int(11) NOT NULL,
  `flag` int(11) NOT NULL,
  `low_speed_price_per_minute` decimal(19,2) DEFAULT NULL,
  `morning_peak_time_off` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `morning_peak_time_on` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `night_price_per_mile` decimal(19,2) DEFAULT NULL,
  `night_price_per_minute` decimal(19,2) DEFAULT NULL,
  `other_peak_time_off` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `other_peak_time_on` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `other_price` decimal(19,2) DEFAULT NULL,
  `peak_price_start_mile` decimal(19,2) NOT NULL,
  `peak_unit_price` decimal(19,2) NOT NULL,
  `start_fare` decimal(19,2) NOT NULL,
  `start_mile` decimal(19,2) NOT NULL,
  `state` int(11) NOT NULL,
  `unit_price_per_mile` decimal(19,2) NOT NULL,
  `unit_price_per_minute` decimal(19,2) NOT NULL,
  `up_price` decimal(19,2) DEFAULT NULL,
  `up_price_start_mile` decimal(19,2) DEFAULT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_fare_info` */

/*Table structure for table `cms_friend_link` */

DROP TABLE IF EXISTS `cms_friend_link`;

CREATE TABLE `cms_friend_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `logo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_friend_link` */

/*Table structure for table `cms_log` */

DROP TABLE IF EXISTS `cms_log`;

CREATE TABLE `cms_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `content` longtext COLLATE utf8_unicode_ci,
  `ip` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `operation` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `operator` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parameter` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_log` */

/*Table structure for table `cms_member` */

DROP TABLE IF EXISTS `cms_member`;

CREATE TABLE `cms_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `education` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `head_file` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_enabled` tinyint(1) NOT NULL,
  `is_locked` tinyint(1) NOT NULL,
  `locked_date` datetime DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_failure_count` int(11) NOT NULL,
  `login_ip` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `major` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `register_ip` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `school` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_member` */

/*Table structure for table `cms_navigation` */

DROP TABLE IF EXISTS `cms_navigation`;

CREATE TABLE `cms_navigation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `is_blank_target` tinyint(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `position` int(11) NOT NULL,
  `tree_path` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB4066CAA35D1E1D` (`parent`),
  CONSTRAINT `FKB4066CAA35D1E1D` FOREIGN KEY (`parent`) REFERENCES `cms_navigation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_navigation` */

/*Table structure for table `cms_operate_arrive` */

DROP TABLE IF EXISTS `cms_operate_arrive`;

CREATE TABLE `cms_operate_arrive` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dest_latitude` int(11) NOT NULL,
  `dest_longitude` int(11) NOT NULL,
  `dest_time` int(11) NOT NULL,
  `drive_mile` int(11) NOT NULL,
  `drive_time` int(11) NOT NULL,
  `encrypt` int(11) NOT NULL,
  `order_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `biz_status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `direction` int(11) DEFAULT NULL,
  `driver_region_code` int(11) DEFAULT NULL,
  `e_encrypt` int(11) DEFAULT NULL,
  `elevation` int(11) DEFAULT NULL,
  `latitude` int(11) DEFAULT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` int(11) DEFAULT NULL,
  `position_time` int(11) DEFAULT NULL,
  `speed` int(11) DEFAULT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_operate_arrive` */

insert  into `cms_operate_arrive`(`id`,`create_date`,`modify_date`,`company_id`,`dest_latitude`,`dest_longitude`,`dest_time`,`drive_mile`,`drive_time`,`encrypt`,`order_id`,`biz_status`,`direction`,`driver_region_code`,`e_encrypt`,`elevation`,`latitude`,`license_id`,`longitude`,`position_time`,`speed`,`vehicle_no`) values (1,'2018-11-22 17:55:04','2018-11-22 17:55:07','4',4,5,5,5,5,5,'5','5',5,5,5,5,5,'5',5,5,5,'5');

/*Table structure for table `cms_operate_depart` */

DROP TABLE IF EXISTS `cms_operate_depart`;

CREATE TABLE `cms_operate_depart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dep_latitude` int(11) NOT NULL,
  `dep_longitude` int(11) NOT NULL,
  `dep_time` int(11) NOT NULL,
  `encrypt` int(11) NOT NULL,
  `fare_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `order_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `wait_mile` int(11) DEFAULT NULL,
  `wait_time` int(11) DEFAULT NULL,
  `benfit_price` int(11) DEFAULT NULL,
  `book_dep_time` int(11) NOT NULL,
  `book_model` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `book_tip` int(11) DEFAULT NULL,
  `cash_price` int(11) DEFAULT NULL,
  `dep_area` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dest_area` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dest_latitude` int(11) NOT NULL,
  `dest_longitude` int(11) NOT NULL,
  `dest_time` int(11) NOT NULL,
  `drive_mile` int(11) NOT NULL,
  `drive_time` int(11) NOT NULL,
  `driver_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fact_price` int(11) NOT NULL,
  `far_up_price` int(11) NOT NULL,
  `invoice_status` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `line_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `line_price` int(11) DEFAULT NULL,
  `model` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `night_up_price` int(11) DEFAULT NULL,
  `on_area` int(11) NOT NULL,
  `order_match_time` int(11) DEFAULT NULL,
  `other_up_price` int(11) NOT NULL,
  `passenger_tip` int(11) DEFAULT NULL,
  `pay_state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pay_time` int(11) DEFAULT NULL,
  `peak_up_price` int(11) DEFAULT NULL,
  `pos_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pos_price` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_operate_depart` */

insert  into `cms_operate_depart`(`id`,`create_date`,`modify_date`,`company_id`,`dep_latitude`,`dep_longitude`,`dep_time`,`encrypt`,`fare_type`,`license_id`,`order_id`,`vehicle_no`,`wait_mile`,`wait_time`,`benfit_price`,`book_dep_time`,`book_model`,`book_tip`,`cash_price`,`dep_area`,`dest_area`,`dest_latitude`,`dest_longitude`,`dest_time`,`drive_mile`,`drive_time`,`driver_name`,`fact_price`,`far_up_price`,`invoice_status`,`line_name`,`line_price`,`model`,`night_up_price`,`on_area`,`order_match_time`,`other_up_price`,`passenger_tip`,`pay_state`,`pay_time`,`peak_up_price`,`pos_name`,`pos_price`,`price`) values (1,'2018-11-27 15:58:46','2018-11-27 15:58:50','4',4,4,4,4,'4','4','4','4',4,4,4,4,'4',4,NULL,'4','4',4,4,44,44,4,'4',4,4,'4','4',4,NULL,NULL,0,NULL,0,NULL,'',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `cms_operate_login` */

DROP TABLE IF EXISTS `cms_operate_login`;

CREATE TABLE `cms_operate_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `encrypt` int(11) NOT NULL,
  `latitude` int(11) DEFAULT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `login_time` int(11) NOT NULL,
  `longitude` int(11) DEFAULT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_operate_login` */

insert  into `cms_operate_login`(`id`,`create_date`,`modify_date`,`company_id`,`encrypt`,`latitude`,`license_id`,`login_time`,`longitude`,`vehicle_no`) values (1,'2018-11-22 10:34:10','2018-11-22 10:34:13','1',1,1,'1',1,1,'1');

/*Table structure for table `cms_operate_logout` */

DROP TABLE IF EXISTS `cms_operate_logout`;

CREATE TABLE `cms_operate_logout` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `encrypt` int(11) NOT NULL,
  `latitude` int(11) DEFAULT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `logout_time` int(11) NOT NULL,
  `longitude` int(11) DEFAULT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_operate_logout` */

insert  into `cms_operate_logout`(`id`,`create_date`,`modify_date`,`company_id`,`encrypt`,`latitude`,`license_id`,`logout_time`,`longitude`,`vehicle_no`) values (1,'2018-11-22 11:20:52','2018-11-22 11:20:55','3',3,3,'3',3,3,'3');

/*Table structure for table `cms_operate_pay` */

DROP TABLE IF EXISTS `cms_operate_pay`;

CREATE TABLE `cms_operate_pay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dest_latitude` int(11) NOT NULL,
  `dest_longitude` int(11) NOT NULL,
  `dest_time` int(11) NOT NULL,
  `drive_mile` int(11) NOT NULL,
  `drive_time` int(11) NOT NULL,
  `encrypt` int(11) NOT NULL,
  `order_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_operate_pay` */

insert  into `cms_operate_pay`(`id`,`create_date`,`modify_date`,`company_id`,`dest_latitude`,`dest_longitude`,`dest_time`,`drive_mile`,`drive_time`,`encrypt`,`order_id`) values (1,'2018-11-22 17:55:23','2018-11-22 17:55:26','6',6,6,6,6,6,6,'6');

/*Table structure for table `cms_order_cancel` */

DROP TABLE IF EXISTS `cms_order_cancel`;

CREATE TABLE `cms_order_cancel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `cancel_reason` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cancel_time` int(11) NOT NULL,
  `cancel_type_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `operator` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `order_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `order_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_order_cancel` */

insert  into `cms_order_cancel`(`id`,`create_date`,`modify_date`,`address`,`cancel_reason`,`cancel_time`,`cancel_type_code`,`company_id`,`operator`,`order_id`,`order_time`) values (1,'2018-11-21 17:11:23','2018-11-21 17:11:25',2,'2',2,'2','2','2','2',2);

/*Table structure for table `cms_order_create` */

DROP TABLE IF EXISTS `cms_order_create`;

CREATE TABLE `cms_order_create` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) DEFAULT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dep_latitude` int(11) NOT NULL,
  `dep_longitude` int(11) NOT NULL,
  `depart_time` int(11) NOT NULL,
  `departure` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dest_latitude` int(11) NOT NULL,
  `dest_longitude` int(11) NOT NULL,
  `destination` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `encrypt` int(11) NOT NULL,
  `fare_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `order_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `order_time` int(11) NOT NULL,
  `passenger_note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_order_create` */

insert  into `cms_order_create`(`id`,`create_date`,`modify_date`,`address`,`company_id`,`dep_latitude`,`dep_longitude`,`depart_time`,`departure`,`dest_latitude`,`dest_longitude`,`destination`,`encrypt`,`fare_type`,`order_id`,`order_time`,`passenger_note`) values (1,'2018-11-21 10:48:25','2018-11-21 10:48:28',22444,'1',1,1,1,'1',1,1,'1',1,'1','1',1,'1');

/*Table structure for table `cms_order_match` */

DROP TABLE IF EXISTS `cms_order_match`;

CREATE TABLE `cms_order_match` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `distribute_time` int(11) NOT NULL,
  `driver_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `encrypt` int(11) NOT NULL,
  `latitude` int(11) DEFAULT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `longitude` int(11) DEFAULT NULL,
  `order_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `login_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_order_match` */

insert  into `cms_order_match`(`id`,`create_date`,`modify_date`,`address`,`company_id`,`distribute_time`,`driver_phone`,`encrypt`,`latitude`,`license_id`,`longitude`,`order_id`,`vehicle_no`,`login_time`) values (1,'2018-11-21 16:32:17','2018-11-27 16:32:24',333,'1',1,'1',1,1,'1',1,'1','  1',0);

/*Table structure for table `cms_passenger` */

DROP TABLE IF EXISTS `cms_passenger`;

CREATE TABLE `cms_passenger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `passenger_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `passenger_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `passenger_sex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `register_date` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_passenger` */

/*Table structure for table `cms_passenger_info` */

DROP TABLE IF EXISTS `cms_passenger_info`;

CREATE TABLE `cms_passenger_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `passenger_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `passenger_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `passenger_sex` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `register_date` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `update_time` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_passenger_info` */

/*Table structure for table `cms_pay_info` */

DROP TABLE IF EXISTS `cms_pay_info`;

CREATE TABLE `cms_pay_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `count_date` int(11) NOT NULL,
  `flag` int(11) NOT NULL,
  `pay_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pay_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pay_scope` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `pay_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prepare_bank` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_pay_info` */

/*Table structure for table `cms_position_driver` */

DROP TABLE IF EXISTS `cms_position_driver`;

CREATE TABLE `cms_position_driver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `biz_status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `direction` int(11) DEFAULT NULL,
  `driver_region_code` int(11) NOT NULL,
  `elevation` int(11) DEFAULT NULL,
  `encrypt` int(11) DEFAULT NULL,
  `latitude` int(11) NOT NULL,
  `license_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `longitude` int(11) NOT NULL,
  `order_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `position_time` int(11) NOT NULL,
  `speed` int(11) DEFAULT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_position_driver` */

insert  into `cms_position_driver`(`id`,`create_date`,`modify_date`,`biz_status`,`company_id`,`direction`,`driver_region_code`,`elevation`,`encrypt`,`latitude`,`license_id`,`longitude`,`order_id`,`position_time`,`speed`,`vehicle_no`) values (1,'2018-11-23 13:26:05','2018-11-23 13:26:08','9','9',9,9,9,9,9,'9',9,'99',9,9,'9');

/*Table structure for table `cms_position_vehicle` */

DROP TABLE IF EXISTS `cms_position_vehicle`;

CREATE TABLE `cms_position_vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `biz_status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `direction` int(11) DEFAULT NULL,
  `elevation` int(11) DEFAULT NULL,
  `encrypt` int(11) DEFAULT NULL,
  `latitude` int(11) DEFAULT NULL,
  `longitude` int(11) DEFAULT NULL,
  `mileage` int(11) DEFAULT NULL,
  `order_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `position_time` int(11) DEFAULT NULL,
  `speed` int(11) DEFAULT NULL,
  `veh_status` int(11) DEFAULT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vehicle_region_code` int(11) DEFAULT NULL,
  `warn_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_position_vehicle` */

insert  into `cms_position_vehicle`(`id`,`create_date`,`modify_date`,`biz_status`,`company_id`,`direction`,`elevation`,`encrypt`,`latitude`,`longitude`,`mileage`,`order_id`,`position_time`,`speed`,`veh_status`,`vehicle_no`,`vehicle_region_code`,`warn_status`) values (1,'2018-11-26 16:35:48','2018-11-26 16:35:51','1','1',1,1,1,1,1,1,'1',1,1,1,'1',11,1);

/*Table structure for table `cms_reference` */

DROP TABLE IF EXISTS `cms_reference`;

CREATE TABLE `cms_reference` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `dloaded` int(11) NOT NULL,
  `file_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `file_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reference_class` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `article_category` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD62773D5C473263C` (`article_category`),
  CONSTRAINT `FKD62773D5C473263C` FOREIGN KEY (`article_category`) REFERENCES `cms_article_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_reference` */

/*Table structure for table `cms_resource` */

DROP TABLE IF EXISTS `cms_resource`;

CREATE TABLE `cms_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `cms_resource` */

insert  into `cms_resource`(`id`,`create_date`,`modify_date`,`orders`,`group_name`,`mark`,`name`,`url`) values (6,'2014-10-28 16:45:16','2014-11-12 11:12:17',NULL,'系统设置','admin:setting','系统设置','/admin/setting/edit.jhtml'),(7,'2014-10-28 16:45:16','2014-11-12 11:13:15',NULL,'系统设置','admin:areaList','地区管理列表','/admin/area/list.jhtml'),(8,'2014-11-12 11:13:32','2014-11-12 11:13:32',NULL,'系统设置','admin:areaAdd','地区管理添加','/admin/area/add.jhtml'),(9,'2014-11-12 11:14:51','2014-11-12 11:14:51',NULL,'系统设置','admin:areaEdit','地区管理编辑','/admin/area/edit.jhtml'),(10,'2014-11-12 11:15:13','2014-11-12 11:15:13',NULL,'系统设置','admin:areaDelete','地区管理删除','/admin/area/delete.jhtml'),(11,'2014-10-28 16:45:16','2014-11-12 11:15:47',NULL,'系统设置','admin:adminList','管理员管理列表','/admin/admin/list.jhtml'),(12,'2014-11-12 11:16:02','2014-11-12 11:16:02',NULL,'系统设置','admin:adminAdd','管理员管理添加','/admin/admin/add.jhtml'),(13,'2014-11-12 11:16:33','2014-11-12 11:16:33',NULL,'系统设置','admin:adminEdit','管理员管理编辑','/admin/admin/edit.jhtml'),(14,'2014-11-12 11:17:32','2014-11-12 11:17:32',NULL,'系统设置','admin:adminDelete','管理员管理删除','/admin/admin/delete.jhtml'),(15,'2014-10-28 16:45:16','2014-11-12 11:18:33',NULL,'系统设置','admin:roleList','角色管理列表','/admin/role/list.jhtml'),(16,'2014-11-12 11:18:47','2014-11-12 11:18:47',NULL,'系统设置','admin:roleAdd','角色管理添加','/admin/role/add.jhtml'),(17,'2014-11-12 11:19:11','2014-11-12 11:19:11',NULL,'系统设置','admin:roleEdit','角色管理编辑','/admin/role/edit.jhtml'),(18,'2014-11-12 11:19:39','2014-11-12 11:19:39',NULL,'系统设置','admin:roleDelete','角色管理删除','/admin/role/delete.jhtml'),(19,'2014-10-28 16:45:16','2014-11-12 11:25:41',NULL,'系统设置','admin:logList','日志管理列表','/admin/log/list.jhtml'),(20,'2014-11-12 11:26:09','2014-11-12 11:26:09',NULL,'系统设置','admin:logView','日志管理查看','/admin/log/view.jhtml'),(21,'2014-11-12 11:27:02','2014-11-12 11:27:02',NULL,'系统设置','admin:logDelete','日志管理删除','/admin/log/delete.jhtml'),(22,'2014-11-12 11:27:27','2014-11-12 11:27:27',NULL,'系统设置','admin:logClear','日志管理清空','/admin/log/clear.jhtml'),(23,'2014-10-29 10:53:09','2014-11-12 11:22:26',NULL,'系统设置','admin:resourceList','资源管理列表','/admin/resource/list.jhtml'),(24,'2014-11-12 11:23:01','2014-11-12 11:23:01',NULL,'系统设置','admin:resourceAdd','资源管理添加','/admin/resource/add.jhtml'),(25,'2014-11-12 11:23:20','2014-11-12 11:23:20',NULL,'系统设置','admin:resourceEdit','资源管理编辑','/admin/resource/edit.jhtml'),(26,'2014-11-12 11:23:49','2014-11-12 11:23:49',NULL,'系统设置','admin:resourceCopy','资源管理复制','/admin/resource/copy.jhtml'),(57,'2014-10-28 16:45:16','2014-11-12 11:05:39',NULL,'内容管理','admin:cache','缓存管理','/admin/cache/clear.jhtml'),(58,'2014-10-28 16:45:16','2014-11-12 11:05:59',NULL,'内容管理','admin:static','静态化管理','/admin/static/build.jhtml'),(59,'2014-10-28 16:45:16','2014-11-12 11:06:17',NULL,'内容管理','admin:index','索引管理','/admin/index/build.jhtml'),(60,'2018-07-08 19:08:42','2018-07-08 19:08:42',NULL,'司机管理','admin:driverList','司机列表','/driver/list.jhtml'),(61,'2018-07-09 00:03:20','2018-07-09 00:03:20',NULL,'司机管理','admin:carList','车辆管理','/car/list.jhtml'),(62,'2018-07-09 00:10:40','2018-07-09 00:10:40',NULL,'乘客管理','admin:memberList','乘客列表','/member/list.jhtml'),(63,'2018-07-09 00:14:08','2018-07-09 00:14:08',NULL,'订单管理','admin:orderList','订单列表','/order/list.jhtml'),(64,'2018-07-09 00:14:47','2018-07-09 00:14:47',NULL,'配置管理','admin:priceConfig','计价配置','/priceConfig/list.jhtml'),(65,'2018-07-11 17:24:34','2018-07-11 17:24:34',NULL,'内容管理','admin:articleList','文章列表','/admin/article/list.jhtml'),(66,'2018-07-17 08:36:04','2018-07-17 08:36:19',NULL,'内容管理','admin:messageList','消息列表','/admin/message/list.jhtml'),(67,'2018-07-17 19:51:06','2018-07-17 19:51:06',NULL,'财务管理','admin:inComeList','财务列表','/admin/income/incomeInfo.jhtml'),(68,'2018-07-23 21:51:38','2018-07-23 21:51:38',NULL,'发票管理','admin:billList','申请列表','/admin/bill/list.jhtml'),(69,'2018-07-24 01:54:55','2018-07-24 01:54:55',NULL,'配置管理','admin:causeConfig','订单取消原因','/admin/cause/list.jhtml'),(70,'2018-07-25 15:18:28','2018-07-25 15:18:28',NULL,'财务管理','admin:driverIcomeList','未结算列表','/admin/income/driverIcomeList.jhtml'),(71,'2018-07-27 02:24:19','2018-07-27 02:24:19',NULL,'财务管理','admin:driverInComeed','已结算列表','/admin/income/driverInComeed.jhtml');

/*Table structure for table `cms_role` */

DROP TABLE IF EXISTS `cms_role`;

CREATE TABLE `cms_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_system` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `cms_role` */

insert  into `cms_role`(`id`,`create_date`,`modify_date`,`description`,`is_system`,`name`) values (1,'2013-01-01 10:49:19','2013-01-01 10:49:23','拥有管理后台最高权限','','超级管理员'),(2,'2013-08-19 18:05:28','2013-08-19 18:05:28','网站编辑','\0','网站编辑');

/*Table structure for table `cms_role_authority` */

DROP TABLE IF EXISTS `cms_role_authority`;

CREATE TABLE `cms_role_authority` (
  `role` bigint(20) NOT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  KEY `FKE06165D939B03AB0` (`role`),
  CONSTRAINT `FKE06165D939B03AB0` FOREIGN KEY (`role`) REFERENCES `cms_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_role_authority` */

insert  into `cms_role_authority`(`role`,`authorities`) values (1,'admin:product'),(1,'admin:productCategory'),(1,'admin:parameterGroup'),(1,'admin:attribute'),(1,'admin:specification'),(1,'admin:brand'),(1,'admin:productNotify'),(1,'admin:order'),(1,'admin:print'),(1,'admin:payment'),(1,'admin:refunds'),(1,'admin:shipping'),(1,'admin:returns'),(1,'admin:deliveryCenter'),(1,'admin:deliveryTemplate'),(1,'admin:member'),(1,'admin:memberRank'),(1,'admin:memberAttribute'),(1,'admin:review'),(1,'admin:consultation'),(1,'admin:navigation'),(1,'admin:article'),(1,'admin:articleCategory'),(1,'admin:tag'),(1,'admin:friendLink'),(1,'admin:adPosition'),(1,'admin:ad'),(1,'admin:template'),(1,'admin:cache'),(1,'admin:static'),(1,'admin:index'),(1,'admin:promotion'),(1,'admin:coupon'),(1,'admin:seo'),(1,'admin:sitemap'),(1,'admin:statistics'),(1,'admin:sales'),(1,'admin:salesRanking'),(1,'admin:purchaseRanking'),(1,'admin:deposit'),(1,'admin:setting'),(1,'admin:area'),(1,'admin:paymentMethod'),(1,'admin:shippingMethod'),(1,'admin:deliveryCorp'),(1,'admin:paymentPlugin'),(1,'admin:storagePlugin'),(1,'admin:admin'),(1,'admin:role'),(1,'admin:message'),(1,'admin:log'),(2,'admin:navigation'),(2,'admin:article'),(2,'admin:articleCategory'),(2,'admin:tag'),(2,'admin:friendLink'),(2,'admin:adPosition'),(2,'admin:ad'),(2,'admin:template'),(2,'admin:cache'),(2,'admin:static'),(2,'admin:index');

/*Table structure for table `cms_role_resource` */

DROP TABLE IF EXISTS `cms_role_resource`;

CREATE TABLE `cms_role_resource` (
  `roles` bigint(20) NOT NULL,
  `resources` bigint(20) NOT NULL,
  KEY `FK1A907941B7D57C43` (`roles`),
  KEY `FK1A907941D00E743` (`resources`),
  CONSTRAINT `FK1A907941B7D57C43` FOREIGN KEY (`roles`) REFERENCES `cms_role` (`id`),
  CONSTRAINT `FK1A907941D00E743` FOREIGN KEY (`resources`) REFERENCES `cms_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cms_role_resource` */

insert  into `cms_role_resource`(`roles`,`resources`) values (2,60),(2,61),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,57),(1,58),(1,59),(1,65),(1,66),(1,60),(1,61),(1,62),(1,63),(1,64),(1,69),(1,67),(1,70),(1,71),(1,68);

/*Table structure for table `cms_scale_info` */

DROP TABLE IF EXISTS `cms_scale_info`;

CREATE TABLE `cms_scale_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `driver_num` int(11) NOT NULL,
  `flag` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  `vehicle_num` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_scale_info` */

/*Table structure for table `cms_service` */

DROP TABLE IF EXISTS `cms_service`;

CREATE TABLE `cms_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` int(11) NOT NULL,
  `company_id` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `contact_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `create_service_date` int(11) NOT NULL,
  `detail_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `flag` int(11) NOT NULL,
  `mail_address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manager_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manager_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `responsible_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `responsible_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `service_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `service_no` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `state` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_service` */

/*Table structure for table `cms_video` */

DROP TABLE IF EXISTS `cms_video`;

CREATE TABLE `cms_video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `video_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `video_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `video_picture` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `video_said` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `article_category` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFD901E05C473263C` (`article_category`),
  CONSTRAINT `FKFD901E05C473263C` FOREIGN KEY (`article_category`) REFERENCES `cms_article_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cms_video` */

/*Table structure for table `cus_acu` */

DROP TABLE IF EXISTS `cus_acu`;

CREATE TABLE `cus_acu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `company_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `complaint_time` int(11) NOT NULL,
  `detail` longtext COLLATE utf8_unicode_ci NOT NULL,
  `order_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `result` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cus_acu` */

/*Table structure for table `cus_asm` */

DROP TABLE IF EXISTS `cus_asm`;

CREATE TABLE `cus_asm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `company_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `detail` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_score` int(11) DEFAULT NULL,
  `evaluate_time` int(11) NOT NULL,
  `order_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `service_score` int(11) NOT NULL,
  `vehicle_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `cus_asm` */

/*Table structure for table `dc_bill` */

DROP TABLE IF EXISTS `dc_bill`;

CREATE TABLE `dc_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `bill_status` int(11) DEFAULT NULL,
  `bill_type` int(11) DEFAULT NULL,
  `classify` int(11) DEFAULT NULL,
  `duty_sign` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `member` bigint(20) DEFAULT NULL,
  `num` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5847C7C78883A813` (`member`),
  CONSTRAINT `FK5847C7C78883A813` FOREIGN KEY (`member`) REFERENCES `dc_member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_bill` */

insert  into `dc_bill`(`id`,`create_date`,`modify_date`,`address`,`amount`,`bill_status`,`bill_type`,`classify`,`duty_sign`,`email`,`mobile`,`name`,`title`,`member`,`num`) values (1,'2018-07-21 02:33:49','2018-09-28 15:35:24','11111','100.00',0,0,1,'','22097002@qq.com','15881087682','11111','袁文波(个人)',14,'1234567894'),(2,'2018-07-21 02:34:00','2018-07-22 03:36:40','11111','100.00',1,0,1,'','22097002@qq.com','13311111111','11111','123',14,'1234567892');

/*Table structure for table `dc_cancel_cause` */

DROP TABLE IF EXISTS `dc_cancel_cause`;

CREATE TABLE `dc_cancel_cause` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `cause` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cause_status` tinyint(1) NOT NULL,
  `cause_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_cancel_cause` */

insert  into `dc_cancel_cause`(`id`,`create_date`,`modify_date`,`cause`,`cause_status`,`cause_type`) values (6,'2018-12-04 15:31:21','2018-12-04 15:31:21',NULL,1,0),(7,'2018-12-05 09:22:43','2018-12-05 09:22:43',NULL,0,NULL),(8,'2018-12-05 09:34:20','2018-12-05 09:34:20',NULL,0,NULL),(9,'2018-12-05 11:10:36','2018-12-05 11:10:36',NULL,0,NULL),(10,'2018-12-05 11:11:02','2018-12-05 11:11:02',NULL,0,NULL),(11,'2018-12-05 11:24:23','2018-12-05 11:24:23',NULL,0,NULL),(12,'2018-12-05 11:24:51','2018-12-05 11:24:51',NULL,0,NULL);

/*Table structure for table `dc_car` */

DROP TABLE IF EXISTS `dc_car`;

CREATE TABLE `dc_car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `brand` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `certify_datea` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `company` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver` tinyblob,
  `driver_phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `engine_displace` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `engine_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fuel_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `model` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `owner_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo_url` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `plate_color` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seats` int(11) NOT NULL,
  `state` tinyint(1) NOT NULL,
  `vin` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vehicle_color` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vehicle_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_car` */

insert  into `dc_car`(`id`,`create_date`,`modify_date`,`address`,`brand`,`certify_datea`,`company`,`driver`,`driver_phone`,`engine_displace`,`engine_id`,`fuel_type`,`model`,`owner_name`,`photo_url`,`plate_color`,`seats`,`state`,`vin`,`vehicle_color`,`vehicle_no`,`vehicle_type`) values (1,'2018-06-21 13:41:46','2018-06-21 13:41:46','四川省成都市锦江区二环路东五段366号3','大众',NULL,'小孙专车',NULL,'15881087681',NULL,NULL,NULL,'大众','小孙',NULL,NULL,5,1,NULL,'白色','川Ｂ111111',NULL),(2,'2018-06-21 21:13:00','2018-06-21 21:13:00','陕西西安','宝马',NULL,'小孙',NULL,'15196676800',NULL,NULL,NULL,'530','刘新军',NULL,NULL,5,1,NULL,'白','陕A66666',NULL),(3,'2018-07-19 15:23:27','2018-07-19 15:23:27','四川省成都市','奥迪',NULL,'小孙专车',NULL,'18380492823',NULL,NULL,NULL,'Ｑ7','2222',NULL,NULL,7,1,NULL,'黑','川A88888',NULL),(4,'2018-07-31 16:01:20','2018-07-31 16:01:20','四川省成都市锦江区二环路东五段366号3','大众',NULL,'小孙专车',NULL,'15881087682',NULL,NULL,NULL,'222','小孙',NULL,NULL,5,1,NULL,'白色','川Ｂ111111',NULL),(14,'2018-12-04 13:31:25','2018-12-04 13:31:25','3','4',NULL,'2',NULL,'8',NULL,NULL,NULL,'5',NULL,NULL,NULL,7,1,NULL,'6','1',NULL),(15,'2018-12-04 14:04:14','2018-12-04 14:04:14','222','22',NULL,'2',NULL,'2',NULL,NULL,NULL,'22',NULL,NULL,NULL,2,1,NULL,'25555','2362262',NULL),(16,'2018-12-04 14:04:39','2018-12-04 14:04:39','0','0',NULL,'100',NULL,'0',NULL,NULL,NULL,'0',NULL,NULL,NULL,0,1,NULL,'0','0',NULL),(17,'2018-12-04 14:11:51','2018-12-04 14:11:51','1','1',NULL,'1001',NULL,NULL,NULL,NULL,NULL,'11','小子',NULL,NULL,1,1,NULL,'1','1',NULL);

/*Table structure for table `dc_code` */

DROP TABLE IF EXISTS `dc_code`;

CREATE TABLE `dc_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_code` */

insert  into `dc_code`(`id`,`create_date`,`modify_date`,`code`,`mobile`) values (10,'2018-09-26 15:16:33','2018-09-26 15:16:33','752822','15991680323'),(11,'2018-11-08 12:02:01','2018-11-08 12:02:01','715728','18511344396');

/*Table structure for table `dc_driver` */

DROP TABLE IF EXISTS `dc_driver`;

CREATE TABLE `dc_driver` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `admin` tinyblob,
  `car` tinyblob,
  `company_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dirver_license` tinyblob,
  `driver_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_birthday` datetime DEFAULT NULL,
  `driver_census` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_contact_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_education` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_gender` int(11) DEFAULT NULL,
  `driver_language_level` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_marital_status` int(11) DEFAULT NULL,
  `driver_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_nation` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_nationality` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emergency_contact` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emergency_contact_address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `emergency_contact_phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `net_work_car_license` tinyblob,
  `state` tinyint(1) NOT NULL,
  `pass_word` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_card` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `score` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_driver` */

insert  into `dc_driver`(`id`,`create_date`,`modify_date`,`address`,`admin`,`car`,`company_name`,`dirver_license`,`driver_address`,`driver_birthday`,`driver_census`,`driver_contact_address`,`driver_education`,`driver_gender`,`driver_language_level`,`driver_marital_status`,`driver_name`,`driver_nation`,`driver_nationality`,`driver_phone`,`emergency_contact`,`emergency_contact_address`,`emergency_contact_phone`,`net_work_car_license`,`state`,`pass_word`,`token`,`id_card`,`vehicle_no`,`score`) values (2,'2018-06-21 11:14:22','2018-08-08 12:00:00',NULL,NULL,NULL,'小孙专车',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'小孙',NULL,NULL,'15881087682',NULL,NULL,NULL,NULL,1,'200212','82f34ae8-cf9b-472a-80f4-381a42bf3ea4','510000000000',NULL,5),(3,'2018-06-21 11:40:20','2018-07-25 03:53:11',NULL,NULL,NULL,'小孙公司',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,'袁',NULL,NULL,'13311111111',NULL,NULL,NULL,NULL,1,NULL,NULL,'510000000000','川Ｂ111111',5),(4,'2018-06-21 21:12:44','2018-11-07 10:20:48',NULL,NULL,NULL,'小孙',NULL,NULL,'1985-01-09 00:00:00',NULL,NULL,NULL,0,NULL,NULL,'刘新军',NULL,NULL,'15196676800',NULL,NULL,NULL,NULL,1,'123456','fb8397f2-4de7-4d41-9bc5-0379e450a122','511322198411194113','陕A66666',0),(16,'2018-11-30 17:06:09','2018-11-30 17:06:09',NULL,NULL,NULL,'55',NULL,NULL,'2018-11-05 00:00:00',NULL,NULL,NULL,0,NULL,NULL,'5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,5),(34,'2018-12-04 11:39:34','2018-12-04 11:39:34',NULL,NULL,NULL,'a',NULL,NULL,'2018-12-19 00:00:00',NULL,NULL,NULL,0,NULL,NULL,'q',NULL,NULL,'a',NULL,NULL,NULL,NULL,1,NULL,NULL,'a','a',5),(36,'2018-12-04 12:13:03','2018-12-04 12:13:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,5),(37,'2018-12-04 12:13:06','2018-12-04 12:13:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,5),(38,'2018-12-04 12:13:09','2018-12-04 12:13:09',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,5),(39,'2018-12-04 12:13:11','2018-12-04 12:13:11',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,5),(45,'2018-12-04 15:20:22','2018-12-04 15:20:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,5),(47,'2018-12-04 15:20:36','2018-12-04 15:20:36',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,5),(49,'2018-12-05 09:12:36','2018-12-05 09:12:36',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,5),(51,'2018-12-05 12:27:24','2018-12-05 12:27:24',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,5),(52,'2018-12-05 12:27:35','2018-12-05 12:27:35',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,5),(53,'2018-12-05 12:28:20','2018-12-05 12:28:20',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,5);

/*Table structure for table `dc_drivers` */

DROP TABLE IF EXISTS `dc_drivers`;

CREATE TABLE `dc_drivers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `id_card` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vehicle_no` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_drivers` */

insert  into `dc_drivers`(`id`,`create_date`,`modify_date`,`orders`,`age`,`id_card`,`mobile`,`name`,`vehicle_no`) values (17,'2018-11-27 14:38:24','2018-11-27 14:38:24',NULL,18,'610121199610010479','17629012196','杨国庆','010101');

/*Table structure for table `dc_income` */

DROP TABLE IF EXISTS `dc_income`;

CREATE TABLE `dc_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `fee_proportion` decimal(19,2) DEFAULT NULL,
  `order_amount` decimal(19,2) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `settlement` tinyint(1) NOT NULL,
  `driver` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK71A955696B37C32F` (`driver`),
  CONSTRAINT `FK71A955696B37C32F` FOREIGN KEY (`driver`) REFERENCES `dc_driver` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_income` */

/*Table structure for table `dc_member` */

DROP TABLE IF EXISTS `dc_member`;

CREATE TABLE `dc_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `age` int(11) NOT NULL,
  `auth_status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `head` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `i_dcard` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pass_word` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_member` */

insert  into `dc_member`(`id`,`create_date`,`modify_date`,`age`,`auth_status`,`head`,`i_dcard`,`mobile_phone`,`name`,`pass_word`,`sex`,`status`,`token`) values (1,'2018-05-01 16:47:11','2018-07-20 00:31:07',1,'1','1','1','15881087682','1','1','1','1','92c8789b-9ec6-4d32-b185-27ea49b39e5f'),(8,'2018-06-07 19:45:59','2018-06-07 19:45:59',0,NULL,NULL,NULL,'18380942823','张三','dsjwsg',NULL,NULL,NULL),(9,'2018-06-07 19:53:59','2018-08-16 14:47:29',0,NULL,NULL,NULL,'18380492823','张三','123',NULL,NULL,'3bd6ba29-4dd7-485a-98b0-991f5752c3ff'),(10,'2018-06-07 21:11:12','2018-06-07 21:11:12',0,NULL,NULL,NULL,'18380492826','张三','123',NULL,NULL,NULL),(11,'2018-06-08 15:37:07','2018-06-08 15:37:07',0,NULL,NULL,NULL,'10380492823','张三','sgddj',NULL,NULL,NULL),(12,'2018-06-08 15:39:46','2018-06-10 21:40:04',0,NULL,NULL,NULL,'18380492824','张三','123',NULL,NULL,'5a755779-b538-48f9-9acd-e480e6738503'),(13,'2018-06-21 21:08:36','2018-11-07 10:27:54',0,NULL,NULL,NULL,'17092846009','小孙专车','123456',NULL,NULL,'6b487206-5676-4ab2-a391-98bad7b6942f'),(14,'2018-06-25 23:16:03','2018-06-25 23:16:49',0,NULL,NULL,NULL,'15196676800','小孙专车','123456',NULL,NULL,'5e884671-24d9-455d-98aa-6658dd200e38'),(15,'2018-09-26 15:16:59','2018-09-26 15:17:08',0,NULL,NULL,NULL,'15991680323','15991680323','123456',NULL,NULL,'d25a02eb-76be-49f1-bafd-0189447a6c7e');

/*Table structure for table `dc_message` */

DROP TABLE IF EXISTS `dc_message`;

CREATE TABLE `dc_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver` tinyblob,
  `member` tinyblob,
  `message_type` int(11) DEFAULT NULL,
  `sender` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_message` */

insert  into `dc_message`(`id`,`create_date`,`modify_date`,`content`,`driver`,`member`,`message_type`,`sender`,`status`,`title`) values (5,'2018-11-08 11:45:31','2018-11-08 11:45:31','&lt;script&gt;alert(xss)&lt;/script&gt;<br />',NULL,NULL,0,'系统管理员',1,'1111'),(6,'2018-11-08 15:21:42','2018-11-08 15:21:42',NULL,NULL,NULL,NULL,'系统管理员',NULL,NULL);

/*Table structure for table `dc_o2d` */

DROP TABLE IF EXISTS `dc_o2d`;

CREATE TABLE `dc_o2d` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `driver_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `driver_phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end_point` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `member_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_id` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_point` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_o2d` */

insert  into `dc_o2d`(`id`,`create_date`,`modify_date`,`driver_id`,`driver_phone`,`end_point`,`member_name`,`mobile`,`order_id`,`start_point`,`status`) values (231,'2018-09-13 09:30:54','2018-09-13 09:31:06','4','15196676800','西安站','小孙专车','17092846009','262','旺座国际城-D座',1);

/*Table structure for table `dc_order` */

DROP TABLE IF EXISTS `dc_order`;

CREATE TABLE `dc_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `appointment` datetime DEFAULT NULL,
  `dirver` tinyblob,
  `dirver_mobile` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dirver_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `end_latitude` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `end_longitude` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `end_point` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `est_amount` decimal(19,2) DEFAULT NULL,
  `latitude` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `longitude` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `member_mobile` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `member_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mileage` double NOT NULL,
  `num` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `order_status` int(11) NOT NULL,
  `payment_status` int(11) NOT NULL,
  `plate_num` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `start_point` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tip` decimal(19,2) DEFAULT NULL,
  `member` bigint(20) NOT NULL,
  `order_type` int(11) DEFAULT NULL,
  `cancell_type` int(11) DEFAULT NULL,
  `complete_date` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `settlement_status` int(11) DEFAULT NULL,
  `dirvers` bigint(20) DEFAULT NULL,
  `bill` bigint(20) DEFAULT NULL,
  `cancel_cause` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `score` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB16C5B8E8883A813` (`member`),
  KEY `FKB16C5B8E1DB4CE44` (`dirvers`),
  KEY `FKB16C5B8EF75006AD` (`bill`),
  CONSTRAINT `FKB16C5B8E1DB4CE44` FOREIGN KEY (`dirvers`) REFERENCES `dc_driver` (`id`),
  CONSTRAINT `FKB16C5B8E8883A813` FOREIGN KEY (`member`) REFERENCES `dc_member` (`id`),
  CONSTRAINT `FKB16C5B8EF75006AD` FOREIGN KEY (`bill`) REFERENCES `dc_bill` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=265 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_order` */

insert  into `dc_order`(`id`,`create_date`,`modify_date`,`amount`,`appointment`,`dirver`,`dirver_mobile`,`dirver_name`,`end_latitude`,`end_longitude`,`end_point`,`est_amount`,`latitude`,`longitude`,`member_mobile`,`member_name`,`mileage`,`num`,`order_status`,`payment_status`,`plate_num`,`start_point`,`tip`,`member`,`order_type`,`cancell_type`,`complete_date`,`start_date`,`settlement_status`,`dirvers`,`bill`,`cancel_cause`,`score`) values (262,'2018-09-13 09:30:54','2018-09-13 09:32:13','8.00',NULL,NULL,'15196676800','刘新军','34.283494','108.969122','西安站','8.34','34.242223317949','108.89815728688','17092846009','小孙专车',0,'2018091312930',3,0,'陕A66666','旺座国际城-D座',NULL,13,NULL,NULL,'2018-09-13 09:32:13','2018-09-13 09:31:12',0,NULL,NULL,NULL,0),(263,'2018-11-07 10:09:52','2018-11-07 10:10:09',NULL,NULL,NULL,NULL,NULL,'34.229055','108.953196','小寨','8.09','34.242142','108.898471','15991680323','15991680323',0,'2018110712931',4,0,NULL,'旺座国际城-D座',NULL,15,NULL,0,NULL,NULL,0,NULL,NULL,NULL,0),(264,'2018-11-07 10:55:19','2018-11-07 10:55:19',NULL,NULL,NULL,NULL,NULL,'34.258497','108.964395','BAR XI AN','8.14','34.242211835253','108.89824429501','17092846009','小孙专车',0,'2018110712932',0,0,NULL,'旺座国际城-D座',NULL,13,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,0);

/*Table structure for table `dc_price_config` */

DROP TABLE IF EXISTS `dc_price_config`;

CREATE TABLE `dc_price_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `base_price` decimal(19,2) DEFAULT NULL,
  `base_time` decimal(19,2) DEFAULT NULL,
  `car_type` int(11) DEFAULT NULL,
  `haulback_mil` decimal(19,2) DEFAULT NULL,
  `haulback_price` decimal(19,2) DEFAULT NULL,
  `mil_price` decimal(19,2) DEFAULT NULL,
  `miles` decimal(19,2) DEFAULT NULL,
  `morning_peak` decimal(19,2) DEFAULT NULL,
  `night_peak` decimal(19,2) DEFAULT NULL,
  `night_price` decimal(19,2) DEFAULT NULL,
  `time_price` decimal(19,2) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dc_price_config` */

insert  into `dc_price_config`(`id`,`create_date`,`modify_date`,`base_price`,`base_time`,`car_type`,`haulback_mil`,`haulback_price`,`mil_price`,`miles`,`morning_peak`,`night_peak`,`night_price`,`time_price`,`type`) values (1,'2018-07-01 05:01:47','2018-07-18 23:02:08','8.00','1.00',0,'5.00','0.01','0.01','1.00','0.00','0.00','0.00','0.01',0),(2,'2018-07-11 23:41:56','2018-07-11 23:41:56','1.00','1.00',0,'1.00','1.00','1.00','1.00','1.00','1.00','1.00','1.00',1);

/*Table structure for table `dr_credit` */

DROP TABLE IF EXISTS `dr_credit`;

CREATE TABLE `dr_credit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `company_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `level` int(11) NOT NULL,
  `license_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `test_date` int(11) NOT NULL,
  `test_department` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dr_credit` */

/*Table structure for table `dr_punish` */

DROP TABLE IF EXISTS `dr_punish`;

CREATE TABLE `dr_punish` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `company_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `license_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `punish_reason` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `punish_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `dr_punish` */

insert  into `dr_punish`(`id`,`create_date`,`modify_date`,`orders`,`company_id`,`license_id`,`punish_reason`,`punish_time`) values (1,'2018-11-21 00:00:00','2018-11-21 00:00:00',3,'01','01','闯红灯',1986);

/*Table structure for table `private_car` */

DROP TABLE IF EXISTS `private_car`;

CREATE TABLE `private_car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `address` int(11) NOT NULL,
  `company_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `company_name` longtext COLLATE utf8_unicode_ci NOT NULL,
  `contact_address` longtext COLLATE utf8_unicode_ci NOT NULL,
  `economic_type` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `flag` int(11) NOT NULL,
  `identifier` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `legal_name` longtext COLLATE utf8_unicode_ci NOT NULL,
  `legal_phone` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `state` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `private_car` */

/*Table structure for table `private_cusinfo` */

DROP TABLE IF EXISTS `private_cusinfo`;

CREATE TABLE `private_cusinfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `address` int(11) NOT NULL,
  `book_depart_time` int(11) NOT NULL,
  `company_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dep_latitude` int(11) NOT NULL,
  `dep_longitude` int(11) NOT NULL,
  `departure` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `dest_latitude` int(11) NOT NULL,
  `dest_longitude` int(11) NOT NULL,
  `destination` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `encrypt` int(11) NOT NULL,
  `order_ensure_time` int(11) NOT NULL,
  `order_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `passenger_note` longtext COLLATE utf8_unicode_ci,
  `passenger_num` int(11) NOT NULL,
  `route_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `private_cusinfo` */

/*Table structure for table `private_cuspay` */

DROP TABLE IF EXISTS `private_cuspay`;

CREATE TABLE `private_cuspay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `address` int(11) DEFAULT NULL,
  `benfit_price` int(11) DEFAULT NULL,
  `book_depart_time` int(11) NOT NULL,
  `cash_price` int(11) NOT NULL,
  `company_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dep_latitude` int(11) NOT NULL,
  `dep_longitude` int(11) NOT NULL,
  `depart_time` int(11) NOT NULL,
  `departure` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `dest_latitude` int(11) NOT NULL,
  `dest_longitude` int(11) NOT NULL,
  `dest_time` int(11) NOT NULL,
  `destination` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `drive_mile` int(11) NOT NULL,
  `drive_time` int(11) NOT NULL,
  `driver_phone` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `encrypt` int(11) NOT NULL,
  `fact_price` int(11) NOT NULL,
  `fare_type` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `license_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `line_name` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `line_price` int(11) NOT NULL,
  `order_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `order_match_time` int(11) DEFAULT NULL,
  `passenger_num` int(11) NOT NULL,
  `passenger_tip` int(11) DEFAULT NULL,
  `pay_state` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `pay_time` int(11) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `route_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `share_fuel_fee` int(11) DEFAULT NULL,
  `share_highway_toll` int(11) DEFAULT NULL,
  `share_other` int(11) DEFAULT NULL,
  `vehicle_no` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `private_cuspay` */

/*Table structure for table `private_route` */

DROP TABLE IF EXISTS `private_route`;

CREATE TABLE `private_route` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `address` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `company_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `dep_latitude` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `dep_longitude` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `departure` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `dest_latitude` int(11) NOT NULL,
  `dest_longitude` int(11) NOT NULL,
  `destination` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `driver_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `driver_phone` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `encrypt` int(11) NOT NULL,
  `license_id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `route_create_time` int(11) NOT NULL,
  `route_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `route_mile` int(11) DEFAULT NULL,
  `route_note` longtext COLLATE utf8_unicode_ci,
  `vehicle_no` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `private_route` */

/*Table structure for table `yun_send_message` */

DROP TABLE IF EXISTS `yun_send_message`;

CREATE TABLE `yun_send_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `orders` int(11) DEFAULT NULL,
  `content` longtext COLLATE utf8_unicode_ci,
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `send_status` tinyint(1) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `send_type` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time_alarm` datetime DEFAULT NULL,
  `timing` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `yun_send_message` */

/*Table structure for table `yun_sn` */

DROP TABLE IF EXISTS `yun_sn`;

CREATE TABLE `yun_sn` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `last_value` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `yun_sn` */

insert  into `yun_sn`(`id`,`create_date`,`modify_date`,`last_value`,`type`) values (1,'2013-01-01 10:14:13','2018-02-27 11:54:11',83,0),(2,'2013-01-01 10:14:31','2018-07-31 16:15:42',129,1),(3,'2013-01-01 10:14:44','2018-05-02 13:19:40',85,2),(4,'2013-01-01 12:25:31','2018-04-12 13:13:49',19,3),(5,'2013-01-01 12:26:10','2016-11-29 10:59:41',15,4),(6,'2013-01-01 12:26:55','2013-01-01 09:34:55',0,5),(7,'2015-07-21 13:08:29','2016-07-01 09:37:07',22,6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
