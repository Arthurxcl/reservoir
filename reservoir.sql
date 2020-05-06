/*
Navicat MySQL Data Transfer

Source Server         : 5
Source Server Version : 50645
Source Host           : localhost:3306
Source Database       : reservoir

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2020-05-06 18:09:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cal_model
-- ----------------------------
DROP TABLE IF EXISTS `cal_model`;
CREATE TABLE `cal_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '蓄水量模型表',
  `reservoir_id` int(11) NOT NULL COMMENT '水库id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cal_model
-- ----------------------------

-- ----------------------------
-- Table structure for cut_algo
-- ----------------------------
DROP TABLE IF EXISTS `cut_algo`;
CREATE TABLE `cut_algo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分割算法表',
  `name` varchar(20) NOT NULL COMMENT '算法名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cut_algo
-- ----------------------------
INSERT INTO `cut_algo` VALUES ('1', 'FCM');
INSERT INTO `cut_algo` VALUES ('2', 'OTSU');
INSERT INTO `cut_algo` VALUES ('3', 'ACM');

-- ----------------------------
-- Table structure for fitting_formula
-- ----------------------------
DROP TABLE IF EXISTS `fitting_formula`;
CREATE TABLE `fitting_formula` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `orders` varchar(255) NOT NULL,
  `firstDate` datetime NOT NULL,
  `date` datetime NOT NULL COMMENT '拟合时间',
  `type` varchar(20) NOT NULL COMMENT 'measured, radar, sar_measured, sar_radar, optical_measured, optical_radar',
  `reservoirId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reservoirId` (`reservoirId`),
  CONSTRAINT `fitting_formula_ibfk_1` FOREIGN KEY (`reservoirId`) REFERENCES `reservoir_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fitting_formula
-- ----------------------------
INSERT INTO `fitting_formula` VALUES ('7', '实测水位模型', '156.50778701734055,0.08111511353150222,-0.0025151004183121438,1.6039556621238264E-5,-3.4200634873926335E-8,2.1961654932361325E-11', '2018-12-04 00:00:00', '2020-05-05 22:08:41', 'measured', '3');
INSERT INTO `fitting_formula` VALUES ('8', '遥测水位模型', '155.46422478330746,-0.002064915780931615,-0.0018828239460458582,1.9395430476620882E-5,-5.8976016391658966E-8,5.5550370775446396E-11', '2019-01-24 00:00:00', '2020-05-04 10:54:17', 'radar', '3');
INSERT INTO `fitting_formula` VALUES ('18', '蓄1模型', '1779,-16.17,-3.263,0,0.03885,-0.002178', '2020-04-01 21:10:37', '2020-04-01 21:10:42', 'storage', '3');
INSERT INTO `fitting_formula` VALUES ('26', '遥测水位+异源面积', '4.78547521650584E12,-9.926250035464583E10,5.874581758748366E8,567854.0311222239,-15512.234726914841,37.178014668550134', '2020-05-03 22:57:31', '2020-05-03 22:57:31', 'area', '3');
INSERT INTO `fitting_formula` VALUES ('27', '遥测水位+光学面积', '-8.370959295311535E14,2.655260435357677E13,-3.367979199710563E11,2.135365710783015E9,-6767305.677854644,8576.108208780297', '2020-05-03 22:57:31', '2020-05-03 22:57:31', 'area', '3');
INSERT INTO `fitting_formula` VALUES ('28', '遥测水位+SAR面积', '2.8366327099054056E14,-8.956221990324691E12,1.1306682217236688E11,-7.134150909163913E8,2249804.6473688437,-2836.814260603295', '2020-05-03 22:57:31', '2020-05-03 22:57:31', 'area', '3');
INSERT INTO `fitting_formula` VALUES ('29', '实测水位模型', '258.04248695652177,0.0,0.0,0.0,-0.14544347826088014,0.04295652173913373', '2020-05-04 00:00:00', '2020-05-06 18:02:33', 'measured', '4');

-- ----------------------------
-- Table structure for measured_result
-- ----------------------------
DROP TABLE IF EXISTS `measured_result`;
CREATE TABLE `measured_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '实测水位结果表',
  `water_level` varchar(20) NOT NULL COMMENT '实测水位结果',
  `measured_storage` varchar(20) NOT NULL COMMENT '实测蓄水量',
  `site_name` varchar(20) NOT NULL COMMENT '站点名称',
  `date` datetime NOT NULL COMMENT '时间',
  `reservoir_id` int(11) NOT NULL COMMENT '水库id',
  PRIMARY KEY (`id`),
  KEY `fk_reservoir_id1` (`reservoir_id`),
  CONSTRAINT `fk_reservoir_id1` FOREIGN KEY (`reservoir_id`) REFERENCES `reservoir_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=807 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of measured_result
-- ----------------------------
INSERT INTO `measured_result` VALUES ('414', '157.55', '161.0', '丹江口水库', '2018-12-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('415', '157.53', '161.0', '丹江口水库', '2018-12-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('416', '157.49', '160.0', '丹江口水库', '2018-12-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('417', '157.46', '160.0', '丹江口水库', '2018-12-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('418', '157.41', '160.0', '丹江口水库', '2018-12-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('419', '157.41', '160.0', '丹江口水库', '2018-12-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('420', '157.37', '160.0', '丹江口水库', '2018-12-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('421', '157.34', '159.0', '丹江口水库', '2018-12-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('422', '157.25', '159.0', '丹江口水库', '2018-12-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('423', '157.17', '158.0', '丹江口水库', '2018-12-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('424', '157.1', '158.0', '丹江口水库', '2018-12-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('425', '156.99', '157.0', '丹江口水库', '2018-12-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('426', '156.97', '157.0', '丹江口水库', '2018-12-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('427', '156.91', '156.0', '丹江口水库', '2018-12-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('428', '156.35', '152.0', '丹江口水库', '2018-12-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('429', '156.82', '155.0', '丹江口水库', '2018-12-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('430', '156.8', '155.0', '丹江口水库', '2018-12-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('431', '156.81', '155.0', '丹江口水库', '2018-12-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('432', '156.8', '155.0', '丹江口水库', '2018-12-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('433', '156.8', '155.0', '丹江口水库', '2018-12-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('434', '156.77', '155.0', '丹江口水库', '2018-12-31 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('435', '156.72', '155.0', '丹江口水库', '2019-01-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('436', '156.67', '154.0', '丹江口水库', '2019-01-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('437', '156.65', '154.0', '丹江口水库', '2019-01-03 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('438', '156.59', '154.0', '丹江口水库', '2019-01-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('439', '156.85', '156.0', '丹江口水库', '2019-01-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('440', '156.55', '154.0', '丹江口水库', '2019-01-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('441', '156.49', '153.0', '丹江口水库', '2019-01-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('442', '156.47', '153.0', '丹江口水库', '2019-01-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('443', '156.42', '153.0', '丹江口水库', '2019-01-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('444', '156.37', '152.0', '丹江口水库', '2019-01-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('445', '156.32', '152.0', '丹江口水库', '2019-01-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('446', '156.28', '152.0', '丹江口水库', '2019-01-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('447', '156.22', '151.0', '丹江口水库', '2019-01-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('448', '156.18', '151.0', '丹江口水库', '2019-01-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('449', '156.13', '150.0', '丹江口水库', '2019-01-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('450', '156.07', '150.0', '丹江口水库', '2019-01-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('451', '156.02', '150.0', '丹江口水库', '2019-01-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('452', '156.03', '150.0', '丹江口水库', '2019-01-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('453', '155.92', '149.0', '丹江口水库', '2019-01-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('454', '155.87', '149.0', '丹江口水库', '2019-01-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('455', '155.82', '148.0', '丹江口水库', '2019-01-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('456', '155.75', '148.0', '丹江口水库', '2019-01-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('457', '155.71', '147.0', '丹江口水库', '2019-01-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('458', '155.63', '147.0', '丹江口水库', '2019-01-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('459', '155.57', '146.0', '丹江口水库', '2019-01-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('460', '155.52', '146.0', '丹江口水库', '2019-01-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('461', '155.45', '146.0', '丹江口水库', '2019-01-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('462', '155.4', '145.0', '丹江口水库', '2019-01-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('463', '155.35', '145.0', '丹江口水库', '2019-01-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('464', '155.3', '145.0', '丹江口水库', '2019-01-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('465', '155.27', '144.0', '丹江口水库', '2019-01-31 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('466', '155.22', '144.0', '丹江口水库', '2019-02-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('467', '155.2', '144.0', '丹江口水库', '2019-02-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('468', '155.1', '143.0', '丹江口水库', '2019-02-03 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('469', '155.02', '143.0', '丹江口水库', '2019-02-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('470', '154.91', '142.0', '丹江口水库', '2019-02-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('471', '154.88', '142.0', '丹江口水库', '2019-02-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('472', '154.84', '141.0', '丹江口水库', '2019-02-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('473', '154.79', '141.0', '丹江口水库', '2019-02-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('474', '154.75', '141.0', '丹江口水库', '2019-02-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('475', '154.7', '140.0', '丹江口水库', '2019-02-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('476', '154.62', '140.0', '丹江口水库', '2019-02-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('477', '154.56', '139.0', '丹江口水库', '2019-02-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('478', '154.53', '139.0', '丹江口水库', '2019-02-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('479', '154.5', '139.0', '丹江口水库', '2019-02-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('480', '154.43', '139.0', '丹江口水库', '2019-02-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('481', '154.4', '138.0', '丹江口水库', '2019-02-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('482', '154.3', '138.0', '丹江口水库', '2019-02-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('483', '154.2', '137.0', '丹江口水库', '2019-02-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('484', '154.16', '137.0', '丹江口水库', '2019-02-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('485', '154.07', '136.0', '丹江口水库', '2019-02-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('486', '154.0', '136.0', '丹江口水库', '2019-02-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('487', '153.96', '135.0', '丹江口水库', '2019-02-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('488', '153.86', '135.0', '丹江口水库', '2019-02-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('489', '153.81', '134.0', '丹江口水库', '2019-02-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('490', '153.7', '134.0', '丹江口水库', '2019-02-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('491', '153.66', '133.0', '丹江口水库', '2019-02-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('492', '153.59', '133.0', '丹江口水库', '2019-02-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('493', '153.46', '132.0', '丹江口水库', '2019-02-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('494', '153.3', '131.0', '丹江口水库', '2019-03-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('495', '153.2', '130.0', '丹江口水库', '2019-03-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('496', '153.18', '130.0', '丹江口水库', '2019-03-03 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('497', '153.16', '130.0', '丹江口水库', '2019-03-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('498', '153.11', '130.0', '丹江口水库', '2019-03-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('499', '153.05', '129.0', '丹江口水库', '2019-03-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('500', '152.93', '129.0', '丹江口水库', '2019-03-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('501', '152.85', '128.0', '丹江口水库', '2019-03-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('502', '152.78', '128.0', '丹江口水库', '2019-03-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('503', '152.7', '127.0', '丹江口水库', '2019-03-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('504', '152.65', '127.0', '丹江口水库', '2019-03-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('505', '152.59', '127.0', '丹江口水库', '2019-03-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('506', '152.53', '126.0', '丹江口水库', '2019-03-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('507', '152.39', '125.0', '丹江口水库', '2019-03-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('508', '152.24', '124.0', '丹江口水库', '2019-03-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('509', '152.17', '124.0', '丹江口水库', '2019-03-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('510', '152.1', '123.0', '丹江口水库', '2019-03-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('511', '152.02', '123.0', '丹江口水库', '2019-03-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('512', '151.98', '123.0', '丹江口水库', '2019-03-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('513', '151.92', '122.0', '丹江口水库', '2019-03-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('514', '151.9', '122.0', '丹江口水库', '2019-03-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('515', '151.65', '122.0', '丹江口水库', '2019-03-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('516', '151.83', '122.0', '丹江口水库', '2019-03-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('517', '151.8', '121.0', '丹江口水库', '2019-03-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('518', '151.78', '121.0', '丹江口水库', '2019-03-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('519', '151.77', '121.0', '丹江口水库', '2019-03-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('520', '151.75', '121.0', '丹江口水库', '2019-03-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('521', '151.74', '121.0', '丹江口水库', '2019-03-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('522', '151.71', '121.0', '丹江口水库', '2019-03-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('523', '151.67', '121.0', '丹江口水库', '2019-04-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('524', '151.61', '120.0', '丹江口水库', '2019-04-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('525', '151.6', '120.0', '丹江口水库', '2019-04-03 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('526', '151.58', '120.0', '丹江口水库', '2019-04-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('527', '151.55', '120.0', '丹江口水库', '2019-04-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('528', '151.51', '120.0', '丹江口水库', '2019-04-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('529', '151.48', '120.0', '丹江口水库', '2019-04-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('530', '151.44', '119.0', '丹江口水库', '2019-04-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('531', '151.4', '119.0', '丹江口水库', '2019-04-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('532', '151.35', '119.0', '丹江口水库', '2019-04-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('533', '151.29', '118.0', '丹江口水库', '2019-04-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('534', '151.21', '118.0', '丹江口水库', '2019-04-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('535', '151.12', '117.0', '丹江口水库', '2019-04-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('536', '151.08', '117.0', '丹江口水库', '2019-04-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('537', '151.04', '117.0', '丹江口水库', '2019-04-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('538', '151.0', '117.0', '丹江口水库', '2019-04-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('539', '150.97', '116.0', '丹江口水库', '2019-04-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('540', '150.94', '116.0', '丹江口水库', '2019-04-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('541', '150.89', '116.0', '丹江口水库', '2019-04-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('542', '150.85', '116.0', '丹江口水库', '2019-04-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('543', '150.77', '115.0', '丹江口水库', '2019-04-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('544', '150.71', '115.0', '丹江口水库', '2019-04-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('545', '150.73', '115.0', '丹江口水库', '2019-04-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('546', '150.73', '115.0', '丹江口水库', '2019-04-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('547', '150.73', '115.0', '丹江口水库', '2019-04-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('548', '150.73', '115.0', '丹江口水库', '2019-04-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('549', '150.75', '115.0', '丹江口水库', '2019-04-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('550', '150.75', '115.0', '丹江口水库', '2019-05-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('551', '150.74', '115.0', '丹江口水库', '2019-05-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('552', '150.73', '115.0', '丹江口水库', '2019-05-03 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('553', '150.72', '115.0', '丹江口水库', '2019-05-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('554', '150.74', '115.0', '丹江口水库', '2019-05-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('555', '150.73', '115.0', '丹江口水库', '2019-05-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('556', '150.8', '115.0', '丹江口水库', '2019-05-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('557', '150.79', '115.0', '丹江口水库', '2019-05-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('558', '150.88', '116.0', '丹江口水库', '2019-05-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('559', '150.97', '116.0', '丹江口水库', '2019-05-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('560', '151.07', '117.0', '丹江口水库', '2019-05-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('561', '151.2', '118.0', '丹江口水库', '2019-05-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('562', '151.27', '118.0', '丹江口水库', '2019-05-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('563', '151.35', '119.0', '丹江口水库', '2019-05-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('564', '151.45', '119.0', '丹江口水库', '2019-05-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('565', '151.47', '120.0', '丹江口水库', '2019-05-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('566', '151.49', '120.0', '丹江口水库', '2019-05-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('567', '151.55', '120.0', '丹江口水库', '2019-05-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('568', '151.59', '120.0', '丹江口水库', '2019-05-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('569', '151.57', '120.0', '丹江口水库', '2019-05-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('570', '151.57', '120.0', '丹江口水库', '2019-05-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('571', '151.56', '120.0', '丹江口水库', '2019-05-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('572', '151.52', '120.0', '丹江口水库', '2019-05-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('573', '151.48', '120.0', '丹江口水库', '2019-05-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('574', '151.42', '119.0', '丹江口水库', '2019-05-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('575', '151.39', '119.0', '丹江口水库', '2019-05-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('576', '151.32', '119.0', '丹江口水库', '2019-05-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('577', '151.25', '119.0', '丹江口水库', '2019-05-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('578', '151.16', '118.0', '丹江口水库', '2019-05-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('579', '151.1', '117.0', '丹江口水库', '2019-05-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('580', '151.01', '117.0', '丹江口水库', '2019-05-31 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('581', '150.95', '116.0', '丹江口水库', '2019-06-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('582', '150.9', '116.0', '丹江口水库', '2019-06-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('583', '150.86', '116.0', '丹江口水库', '2019-06-03 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('584', '150.8', '115.0', '丹江口水库', '2019-06-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('585', '150.86', '116.0', '丹江口水库', '2019-06-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('586', '150.94', '116.0', '丹江口水库', '2019-06-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('587', '150.99', '116.0', '丹江口水库', '2019-06-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('588', '151.03', '117.0', '丹江口水库', '2019-06-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('589', '151.07', '117.0', '丹江口水库', '2019-06-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('590', '151.08', '117.0', '丹江口水库', '2019-06-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('591', '151.09', '117.0', '丹江口水库', '2019-06-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('592', '151.11', '117.0', '丹江口水库', '2019-06-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('593', '151.13', '117.0', '丹江口水库', '2019-06-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('594', '151.12', '117.0', '丹江口水库', '2019-06-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('595', '151.15', '117.0', '丹江口水库', '2019-06-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('596', '151.19', '118.0', '丹江口水库', '2019-06-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('597', '151.22', '118.0', '丹江口水库', '2019-06-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('598', '151.24', '118.0', '丹江口水库', '2019-06-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('599', '151.26', '118.0', '丹江口水库', '2019-06-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('600', '151.29', '118.0', '丹江口水库', '2019-06-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('601', '151.42', '119.0', '丹江口水库', '2019-06-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('602', '151.56', '120.0', '丹江口水库', '2019-06-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('603', '151.62', '120.0', '丹江口水库', '2019-06-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('604', '151.76', '121.0', '丹江口水库', '2019-06-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('605', '151.98', '123.0', '丹江口水库', '2019-06-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('606', '152.1', '123.0', '丹江口水库', '2019-06-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('607', '152.4', '125.0', '丹江口水库', '2019-06-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('608', '152.44', '126.0', '丹江口水库', '2019-06-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('609', '152.62', '127.0', '丹江口水库', '2019-06-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('610', '152.79', '128.0', '丹江口水库', '2019-06-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('611', '152.95', '129.0', '丹江口水库', '2019-07-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('612', '153.09', '130.0', '丹江口水库', '2019-07-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('613', '153.23', '131.0', '丹江口水库', '2019-07-03 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('614', '153.37', '131.0', '丹江口水库', '2019-07-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('615', '153.51', '132.0', '丹江口水库', '2019-07-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('616', '153.76', '134.0', '丹江口水库', '2019-07-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('617', '153.84', '134.0', '丹江口水库', '2019-07-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('618', '153.98', '135.0', '丹江口水库', '2019-07-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('619', '154.12', '136.0', '丹江口水库', '2019-07-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('620', '154.22', '137.0', '丹江口水库', '2019-07-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('621', '154.36', '138.0', '丹江口水库', '2019-07-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('622', '154.46', '139.0', '丹江口水库', '2019-07-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('623', '154.54', '139.0', '丹江口水库', '2019-07-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('624', '154.65', '140.0', '丹江口水库', '2019-07-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('625', '154.73', '141.0', '丹江口水库', '2019-07-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('626', '154.86', '141.0', '丹江口水库', '2019-07-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('627', '155.01', '142.0', '丹江口水库', '2019-07-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('628', '155.12', '143.0', '丹江口水库', '2019-07-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('629', '155.27', '144.0', '丹江口水库', '2019-07-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('630', '155.38', '145.0', '丹江口水库', '2019-07-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('631', '155.5', '146.0', '丹江口水库', '2019-07-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('632', '155.64', '147.0', '丹江口水库', '2019-07-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('633', '155.77', '148.0', '丹江口水库', '2019-07-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('634', '155.94', '149.0', '丹江口水库', '2019-07-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('635', '156.11', '150.0', '丹江口水库', '2019-07-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('636', '156.16', '151.0', '丹江口水库', '2019-07-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('637', '156.24', '151.0', '丹江口水库', '2019-07-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('638', '156.33', '152.0', '丹江口水库', '2019-07-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('639', '156.42', '152.0', '丹江口水库', '2019-07-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('640', '156.54', '153.0', '丹江口水库', '2019-07-31 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('641', '156.68', '154.0', '丹江口水库', '2019-08-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('642', '156.8', '155.0', '丹江口水库', '2019-08-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('643', '156.9', '156.0', '丹江口水库', '2019-08-03 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('644', '157.22', '159.0', '丹江口水库', '2019-08-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('645', '157.58', '161.0', '丹江口水库', '2019-08-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('646', '158.04', '164.0', '丹江口水库', '2019-08-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('647', '158.32', '166.0', '丹江口水库', '2019-08-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('648', '158.66', '169.0', '丹江口水库', '2019-08-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('649', '158.95', '172.0', '丹江口水库', '2019-08-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('650', '159.36', '175.0', '丹江口水库', '2019-08-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('651', '159.51', '176.0', '丹江口水库', '2019-08-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('652', '159.66', '178.0', '丹江口水库', '2019-08-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('653', '159.81', '179.0', '丹江口水库', '2019-08-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('654', '160.06', '180.0', '丹江口水库', '2019-08-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('655', '160.15', '181.0', '丹江口水库', '2019-08-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('656', '160.22', '182.0', '丹江口水库', '2019-08-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('657', '160.28', '182.0', '丹江口水库', '2019-08-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('658', '160.34', '183.0', '丹江口水库', '2019-08-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('659', '160.4', '183.0', '丹江口水库', '2019-08-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('660', '160.46', '184.0', '丹江口水库', '2019-08-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('661', '160.52', '184.0', '丹江口水库', '2019-08-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('662', '160.58', '185.0', '丹江口水库', '2019-08-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('663', '160.64', '185.0', '丹江口水库', '2019-08-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('664', '160.7', '186.0', '丹江口水库', '2019-08-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('665', '160.77', '186.0', '丹江口水库', '2019-08-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('666', '160.84', '187.0', '丹江口水库', '2019-08-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('667', '160.9', '187.0', '丹江口水库', '2019-08-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('668', '160.97', '188.0', '丹江口水库', '2019-08-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('669', '161.03', '188.0', '丹江口水库', '2019-08-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('670', '161.05', '189.0', '丹江口水库', '2019-08-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('671', '161.07', '189.0', '丹江口水库', '2019-08-31 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('672', '161.12', '189.0', '丹江口水库', '2019-09-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('673', '161.06', '189.0', '丹江口水库', '2019-09-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('674', '161.22', '190.0', '丹江口水库', '2019-09-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('675', '163.59', '210.0', '丹江口水库', '2019-09-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('676', '163.66', '211.0', '丹江口水库', '2019-09-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('677', '163.71', '212.0', '丹江口水库', '2019-09-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('678', '163.94', '213.0', '丹江口水库', '2019-09-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('679', '164.04', '214.0', '丹江口水库', '2019-09-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('680', '164.32', '217.0', '丹江口水库', '2019-10-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('681', '164.33', '217.0', '丹江口水库', '2019-10-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('682', '164.45', '218.0', '丹江口水库', '2019-10-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('683', '164.56', '219.0', '丹江口水库', '2019-10-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('684', '164.7', '220.0', '丹江口水库', '2019-10-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('685', '164.84', '221.0', '丹江口水库', '2019-10-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('686', '164.98', '222.0', '丹江口水库', '2019-10-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('687', '165.1', '224.0', '丹江口水库', '2019-10-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('688', '165.25', '225.0', '丹江口水库', '2019-10-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('689', '165.34', '226.0', '丹江口水库', '2019-10-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('690', '165.51', '228.0', '丹江口水库', '2019-10-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('691', '165.55', '228.0', '丹江口水库', '2019-10-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('692', '165.65', '229.0', '丹江口水库', '2019-10-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('693', '165.72', '230.0', '丹江口水库', '2019-10-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('694', '165.79', '230.0', '丹江口水库', '2019-10-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('695', '165.85', '231.0', '丹江口水库', '2019-10-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('696', '165.91', '232.0', '丹江口水库', '2019-10-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('697', '165.97', '232.0', '丹江口水库', '2019-10-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('698', '166.03', '233.0', '丹江口水库', '2019-10-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('699', '166.08', '233.0', '丹江口水库', '2019-10-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('700', '166.13', '234.0', '丹江口水库', '2019-10-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('701', '166.16', '234.0', '丹江口水库', '2019-10-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('702', '166.19', '234.0', '丹江口水库', '2019-10-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('703', '166.22', '234.0', '丹江口水库', '2019-10-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('704', '166.26', '235.0', '丹江口水库', '2019-10-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('705', '166.28', '235.0', '丹江口水库', '2019-10-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('706', '166.31', '235.0', '丹江口水库', '2019-10-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('707', '166.35', '236.0', '丹江口水库', '2019-10-31 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('708', '166.37', '236.0', '丹江口水库', '2019-11-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('709', '166.39', '236.0', '丹江口水库', '2019-11-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('710', '166.44', '237.0', '丹江口水库', '2019-11-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('711', '166.46', '237.0', '丹江口水库', '2019-11-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('712', '166.49', '237.0', '丹江口水库', '2019-11-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('713', '166.49', '237.0', '丹江口水库', '2019-11-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('714', '166.47', '237.0', '丹江口水库', '2019-11-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('715', '166.43', '236.0', '丹江口水库', '2019-11-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('716', '166.38', '236.0', '丹江口水库', '2019-11-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('717', '166.34', '236.0', '丹江口水库', '2019-11-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('718', '166.31', '235.0', '丹江口水库', '2019-11-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('719', '166.28', '235.0', '丹江口水库', '2019-11-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('720', '166.23', '235.0', '丹江口水库', '2019-11-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('721', '166.19', '234.0', '丹江口水库', '2019-11-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('722', '166.15', '234.0', '丹江口水库', '2019-11-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('723', '166.06', '233.0', '丹江口水库', '2019-11-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('724', '166.05', '233.0', '丹江口水库', '2019-11-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('725', '166.02', '233.0', '丹江口水库', '2019-11-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('726', '166.0', '232.0', '丹江口水库', '2019-11-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('727', '165.98', '232.0', '丹江口水库', '2019-11-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('728', '165.94', '232.0', '丹江口水库', '2019-11-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('729', '165.91', '232.0', '丹江口水库', '2019-11-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('730', '165.88', '231.0', '丹江口水库', '2019-11-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('731', '165.87', '231.0', '丹江口水库', '2019-11-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('732', '165.86', '231.0', '丹江口水库', '2019-11-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('733', '165.83', '231.0', '丹江口水库', '2019-11-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('734', '165.82', '231.0', '丹江口水库', '2019-11-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('735', '165.81', '231.0', '丹江口水库', '2019-11-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('736', '165.81', '231.0', '丹江口水库', '2019-11-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('737', '165.82', '231.0', '丹江口水库', '2019-12-01 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('738', '165.81', '231.0', '丹江口水库', '2019-12-02 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('739', '165.78', '230.0', '丹江口水库', '2019-12-03 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('740', '165.76', '230.0', '丹江口水库', '2019-12-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('741', '165.73', '230.0', '丹江口水库', '2019-12-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('742', '165.7', '229.0', '丹江口水库', '2019-12-06 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('743', '165.67', '229.0', '丹江口水库', '2019-12-07 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('744', '165.62', '229.0', '丹江口水库', '2019-12-08 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('745', '165.59', '229.0', '丹江口水库', '2019-12-09 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('746', '165.55', '228.0', '丹江口水库', '2019-12-10 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('747', '165.5', '228.0', '丹江口水库', '2019-12-11 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('748', '165.46', '227.0', '丹江口水库', '2019-12-12 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('749', '165.41', '227.0', '丹江口水库', '2019-12-13 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('750', '165.37', '227.0', '丹江口水库', '2019-12-14 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('751', '165.34', '226.0', '丹江口水库', '2019-12-15 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('752', '165.31', '226.0', '丹江口水库', '2019-12-16 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('753', '165.27', '226.0', '丹江口水库', '2019-12-17 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('754', '165.23', '225.0', '丹江口水库', '2019-12-18 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('755', '165.17', '225.0', '丹江口水库', '2019-12-19 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('756', '165.11', '224.0', '丹江口水库', '2019-12-20 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('757', '165.07', '224.0', '丹江口水库', '2019-12-21 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('758', '165.05', '224.0', '丹江口水库', '2019-12-22 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('759', '164.98', '223.0', '丹江口水库', '2019-12-23 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('760', '164.93', '222.0', '丹江口水库', '2019-12-24 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('761', '164.87', '222.0', '丹江口水库', '2019-12-25 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('762', '164.83', '222.0', '丹江口水库', '2019-12-26 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('763', '164.78', '221.0', '丹江口水库', '2019-12-27 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('764', '164.72', '221.0', '丹江口水库', '2019-12-28 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('765', '164.67', '220.0', '丹江口水库', '2019-12-29 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('766', '164.61', '220.0', '丹江口水库', '2019-12-30 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('767', '164.55', '219.0', '丹江口水库', '2019-12-31 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('792', '157.7', '162.0', '丹江口水库', '2018-12-04 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('793', '157.65', '162.0', '丹江口水库', '2018-12-05 00:00:00', '3');
INSERT INTO `measured_result` VALUES ('796', '257.94', '53.8', '小浪底水库', '2020-05-04 00:00:00', '4');
INSERT INTO `measured_result` VALUES ('804', '257.09', '52.03', '小浪底水库', '2020-05-05 00:00:00', '4');
INSERT INTO `measured_result` VALUES ('806', '256.70', '51.23', '小浪底水库', '2020-05-06 00:00:00', '4');

-- ----------------------------
-- Table structure for optical_img
-- ----------------------------
DROP TABLE IF EXISTS `optical_img`;
CREATE TABLE `optical_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '光学影像',
  `reservoir_id` int(11) NOT NULL COMMENT '水库id',
  `satellite_name` varchar(20) NOT NULL COMMENT '卫星名称',
  `date` datetime NOT NULL COMMENT '时间',
  `cycle` int(11) NOT NULL COMMENT '所属周期',
  `path` varchar(100) NOT NULL COMMENT '存储路径',
  `topL_longitude` varchar(20) NOT NULL COMMENT '左上经度',
  `lowerR_longitude` varchar(20) NOT NULL COMMENT '右下经度',
  `topL_latitude` varchar(20) NOT NULL COMMENT '左上纬度',
  `lowerR_latitude` varchar(20) NOT NULL COMMENT '右下纬度',
  `algo_name` varchar(20) NOT NULL COMMENT '分割算法名称',
  PRIMARY KEY (`id`),
  KEY `fk_reservoir` (`reservoir_id`),
  CONSTRAINT `fk_reservoir` FOREIGN KEY (`reservoir_id`) REFERENCES `reservoir_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of optical_img
-- ----------------------------
INSERT INTO `optical_img` VALUES ('5', '3', '9', '2019-03-12 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\20200401231647146\\90312.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('8', '3', '9', '2019-08-19 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012320411219\\90819.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('10', '3', '9', '2019-08-29 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012325473299\\90829.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('12', '3', '9', '2019-04-11 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012336205420\\90411.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('15', '3', '9', '2019-09-08 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012338217144\\90908.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('16', '3', '9', '2019-09-28 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012339005619\\90928.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('17', '3', '9', '2019-10-28 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012339383907\\91028.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('18', '3', '9', '2019-02-15 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012340166624\\B90215.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('19', '3', '9', '2019-04-06 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012340545866\\B90406.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('20', '3', '9', '2019-04-16 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012341188121\\B90416.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('21', '3', '9', '2019-05-26 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012341461578\\B90526.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('22', '3', '9', '2019-07-05 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012342128094\\B90705.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('23', '3', '9', '2019-09-23 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\202004012342554661\\B90923.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `optical_img` VALUES ('24', '3', '9', '2019-12-02 00:00:00', '20', 'reservoir-data\\opticalAfterCut\\20200401234329218\\B91202.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');

-- ----------------------------
-- Table structure for radar_level
-- ----------------------------
DROP TABLE IF EXISTS `radar_level`;
CREATE TABLE `radar_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '雷达高度计',
  `satellite_name` varchar(20) NOT NULL COMMENT '卫星名称',
  `date` datetime NOT NULL COMMENT '测量时间',
  `cycle` int(11) NOT NULL COMMENT '所属周期',
  `reservoir_id` int(11) NOT NULL COMMENT '水库id',
  PRIMARY KEY (`id`),
  KEY `fk_reservoir_id2` (`reservoir_id`),
  CONSTRAINT `fk_reservoir_id2` FOREIGN KEY (`reservoir_id`) REFERENCES `reservoir_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of radar_level
-- ----------------------------
INSERT INTO `radar_level` VALUES ('1', '卫星名称', '2019-01-01 00:00:00', '10', '3');

-- ----------------------------
-- Table structure for radar_result
-- ----------------------------
DROP TABLE IF EXISTS `radar_result`;
CREATE TABLE `radar_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '遥测水位结果表',
  `date` datetime NOT NULL COMMENT '测量时间',
  `water_level` varchar(20) NOT NULL COMMENT '水位结果',
  `radar_level_id` int(11) NOT NULL COMMENT '雷达高度计id',
  `site_longitude` varchar(20) NOT NULL COMMENT '虚拟水站经度',
  `site_latitude` varchar(20) NOT NULL COMMENT '虚拟水站纬度',
  `reservoir_id` int(11) NOT NULL COMMENT '水库id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of radar_result
-- ----------------------------
INSERT INTO `radar_result` VALUES ('6', '2019-01-24 00:00:00', '155.549\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('7', '2019-02-20 00:00:00', '154.176\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('8', '2019-03-19 00:00:00', '152.188\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('9', '2019-04-15 00:00:00', '151.044\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('10', '2019-05-12 00:00:00', '151.054\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('11', '2019-06-08 00:00:00', '151.17\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('12', '2019-07-05 00:00:00', '153.645\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('13', '2019-08-01 00:00:00', '156.762\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('14', '2019-08-28 00:00:00', '160.972\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('15', '2019-09-24 00:00:00', '163.632\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('16', '2019-10-21 00:00:00', '165.85\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('17', '2019-11-17 00:00:00', '166.061\r\n', '1', '113.8', '32.95', '3');
INSERT INTO `radar_result` VALUES ('18', '2019-12-14 00:00:00', '165.299\r\n', '1', '113.8', '32.95', '3');

-- ----------------------------
-- Table structure for reservoir_info
-- ----------------------------
DROP TABLE IF EXISTS `reservoir_info`;
CREATE TABLE `reservoir_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '水库名称',
  `location` varchar(50) NOT NULL COMMENT '水库所在位置',
  `longitude_left` varchar(20) NOT NULL COMMENT '左经度',
  `longitude_right` varchar(20) NOT NULL COMMENT '右经度',
  `latitude_left` varchar(20) NOT NULL COMMENT '左纬度',
  `latitude_right` varchar(20) NOT NULL COMMENT '右纬度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reservoir_info
-- ----------------------------
INSERT INTO `reservoir_info` VALUES ('3', '丹江口水库', '湖北', '111.0479', '111.735499', '33.049681', '32.4979');
INSERT INTO `reservoir_info` VALUES ('4', '小浪底水库', '湖北', '112.120086', '112.426516', '35.033079', '34.864593');

-- ----------------------------
-- Table structure for sar_img
-- ----------------------------
DROP TABLE IF EXISTS `sar_img`;
CREATE TABLE `sar_img` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservoir_id` int(11) NOT NULL COMMENT '水库id',
  `satellite_name` varchar(20) NOT NULL COMMENT '卫星名称',
  `date` datetime NOT NULL COMMENT '时间',
  `cycle` int(11) NOT NULL COMMENT '所属周期',
  `path` varchar(100) NOT NULL COMMENT '存储路径',
  `topL_longitude` varchar(20) NOT NULL COMMENT '左上经度',
  `lowerR_longitude` varchar(20) NOT NULL COMMENT '右下经度',
  `topL_latitude` varchar(20) NOT NULL COMMENT '左上纬度',
  `lowerR_latitude` varchar(20) NOT NULL COMMENT '右下纬度',
  `algo_name` varchar(20) NOT NULL COMMENT '分割算法名称',
  PRIMARY KEY (`id`),
  KEY `fk_reservoir_id3` (`reservoir_id`),
  CONSTRAINT `fk_reservoir_id3` FOREIGN KEY (`reservoir_id`) REFERENCES `reservoir_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sar_img
-- ----------------------------
INSERT INTO `sar_img` VALUES ('9', '3', '1', '2019-01-01 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012243498418\\0101   695.618.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('10', '3', '1', '2019-01-13 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012244385583\\0113   683.560.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('11', '3', '1', '2019-01-25 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012246239944\\0125   671.341.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('12', '3', '1', '2019-02-06 00:00:00', '12', 'reservoir-data\\SARAfterCut\\2020040122471094\\0206   641.654.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('13', '3', '1', '2019-02-18 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012247477804\\0218   619.296.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('14', '3', '1', '2019-03-02 00:00:00', '12', 'reservoir-data\\SARAfterCut\\20200401224819698\\0302   607.745.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('15', '3', '1', '2019-03-14 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012248476222\\0314   588.345.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('16', '3', '1', '2019-03-26 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012249403958\\0326   598.577.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('17', '3', '1', '2019-04-07 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012250105943\\0407   591.351.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('18', '3', '1', '2019-04-19 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012250407334\\0419   592.075.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('19', '3', '1', '2019-05-01 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012251109981\\0501   573.342.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('20', '3', '1', '2019-05-13 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012252452470\\0513   579.961.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('21', '3', '1', '2019-06-06 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012253308903\\0606   597.445.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('22', '3', '1', '2019-06-18 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012254149229\\0618   585.065.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('23', '3', '1', '2019-06-30 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012254464979\\0630   585.994.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('24', '3', '1', '2019-07-12 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012255335854\\0712   617.228.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('25', '3', '1', '2019-07-12 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012300078899\\0712   617.228.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('26', '3', '1', '2019-07-24 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012301099664\\0724   688.931.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('27', '3', '1', '2019-08-05 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012301424322\\0805   672.975.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('28', '3', '1', '2019-08-17 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012303442404\\0817   696.980.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('29', '3', '1', '2019-08-29 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012304147718\\0829   697.537.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('30', '3', '1', '2019-09-10 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012304523167\\0910   735.507.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('31', '3', '1', '2019-09-22 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012306259784\\0922   759.858.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('32', '3', '1', '2019-10-04 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012306555229\\1004   771.830.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('33', '3', '1', '2019-10-16 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012307444035\\1016   783.433.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('34', '3', '1', '2019-10-28 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012308166495\\1028   794.287.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('35', '3', '1', '2019-11-09 00:00:00', '12', 'reservoir-data\\SARAfterCut\\2020040123084823\\1109   795.550.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('36', '3', '1', '2019-11-21 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012309241405\\1121   786.748.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('37', '3', '1', '2019-12-03 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012309472904\\1203   789.050.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');
INSERT INTO `sar_img` VALUES ('38', '3', '1', '2019-12-15 00:00:00', '12', 'reservoir-data\\SARAfterCut\\202004012310124295\\1215   768.722.png', '111.000', '111.775', ' 33.070', ' 32.447', 'FCM');

-- ----------------------------
-- Table structure for water_area
-- ----------------------------
DROP TABLE IF EXISTS `water_area`;
CREATE TABLE `water_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '水域面积结果表',
  `reservoir_id` int(11) NOT NULL COMMENT '水库id',
  `area` varchar(20) NOT NULL COMMENT '光学/SAR面积',
  `img_id` int(11) NOT NULL COMMENT '光学/SAR影像id',
  `algo_id` int(11) NOT NULL COMMENT '分割算法id',
  `date` datetime NOT NULL COMMENT '时间',
  `is_sar_area` tinyint(4) NOT NULL COMMENT '光学水域面积(0)   SAR水域面积(1)',
  PRIMARY KEY (`id`),
  KEY `fk_reservoir_id4` (`reservoir_id`),
  KEY `fk_cut_algo` (`algo_id`),
  CONSTRAINT `fk_cut_algo` FOREIGN KEY (`algo_id`) REFERENCES `cut_algo` (`id`),
  CONSTRAINT `fk_reservoir_id4` FOREIGN KEY (`reservoir_id`) REFERENCES `reservoir_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of water_area
-- ----------------------------
INSERT INTO `water_area` VALUES ('11', '3', '6.262951224084132E8', '9', '1', '2019-01-01 00:00:00', '1');
INSERT INTO `water_area` VALUES ('12', '3', '6.258995936158916E8', '10', '1', '2019-01-13 00:00:00', '1');
INSERT INTO `water_area` VALUES ('13', '3', '6.227848043747841E8', '11', '1', '2019-01-25 00:00:00', '1');
INSERT INTO `water_area` VALUES ('14', '3', '6.171114382570524E8', '12', '1', '2019-02-06 00:00:00', '1');
INSERT INTO `water_area` VALUES ('15', '3', '5.883243583263401E8', '13', '1', '2019-02-18 00:00:00', '1');
INSERT INTO `water_area` VALUES ('16', '3', '5.838499388609395E8', '14', '1', '2019-03-02 00:00:00', '1');
INSERT INTO `water_area` VALUES ('17', '3', '5.70265996892776E8', '15', '1', '2019-03-14 00:00:00', '1');
INSERT INTO `water_area` VALUES ('18', '3', '5.724414052516447E8', '16', '1', '2019-03-26 00:00:00', '1');
INSERT INTO `water_area` VALUES ('19', '3', '5.694131379339013E8', '17', '1', '2019-04-07 00:00:00', '1');
INSERT INTO `water_area` VALUES ('20', '3', '5.586102577881552E8', '18', '1', '2019-04-19 00:00:00', '1');
INSERT INTO `water_area` VALUES ('21', '3', '5.497850216050172E8', '19', '1', '2019-05-01 00:00:00', '1');
INSERT INTO `water_area` VALUES ('22', '3', '5.456690501078393E8', '20', '1', '2019-05-13 00:00:00', '1');
INSERT INTO `water_area` VALUES ('23', '3', '5.4800514203867E8', '21', '1', '2019-06-06 00:00:00', '1');
INSERT INTO `water_area` VALUES ('24', '3', '5.2830286406118804E8', '22', '1', '2019-06-18 00:00:00', '1');
INSERT INTO `water_area` VALUES ('25', '3', '5.379685989284345E8', '23', '1', '2019-06-30 00:00:00', '1');
INSERT INTO `water_area` VALUES ('26', '3', '5.434565609246716E8', '25', '1', '2019-07-12 00:00:00', '1');
INSERT INTO `water_area` VALUES ('27', '3', '5.66842200782511E8', '26', '1', '2019-07-24 00:00:00', '1');
INSERT INTO `water_area` VALUES ('28', '3', '5.826015511095432E8', '27', '1', '2019-08-05 00:00:00', '1');
INSERT INTO `water_area` VALUES ('29', '3', '6.211656083803988E8', '28', '1', '2019-08-17 00:00:00', '1');
INSERT INTO `water_area` VALUES ('30', '3', '6.527831912325938E8', '29', '1', '2019-08-29 00:00:00', '1');
INSERT INTO `water_area` VALUES ('31', '3', '6.581475504811679E8', '30', '1', '2019-09-10 00:00:00', '1');
INSERT INTO `water_area` VALUES ('32', '3', '6.569115230045379E8', '31', '1', '2019-09-22 00:00:00', '1');
INSERT INTO `water_area` VALUES ('33', '3', '7.131878540155011E8', '32', '1', '2019-10-04 00:00:00', '1');
INSERT INTO `water_area` VALUES ('34', '3', '7.191702270023903E8', '33', '1', '2019-10-16 00:00:00', '1');
INSERT INTO `water_area` VALUES ('35', '3', '7.417400887256538E8', '34', '1', '2019-10-28 00:00:00', '1');
INSERT INTO `water_area` VALUES ('36', '3', '7.473269329200213E8', '35', '1', '2019-11-09 00:00:00', '1');
INSERT INTO `water_area` VALUES ('37', '3', '7.46325750663951E8', '36', '1', '2019-11-21 00:00:00', '1');
INSERT INTO `water_area` VALUES ('38', '3', '7.365734938733405E8', '37', '1', '2019-12-03 00:00:00', '1');
INSERT INTO `water_area` VALUES ('39', '3', '7.37339830908851E8', '38', '1', '2019-12-15 00:00:00', '1');
INSERT INTO `water_area` VALUES ('44', '3', '5.744017128679448E8', '11', '1', '2019-03-12 00:00:00', '0');
INSERT INTO `water_area` VALUES ('45', '3', '5.604396045951874E8', '12', '1', '2019-04-11 00:00:00', '0');
INSERT INTO `water_area` VALUES ('46', '3', '6.283014584158355E8', '13', '1', '2019-08-19 00:00:00', '0');
INSERT INTO `water_area` VALUES ('47', '3', '6.524242705658197E8', '14', '1', '2019-08-29 00:00:00', '0');
INSERT INTO `water_area` VALUES ('48', '3', '6.509228006220413E8', '15', '1', '2019-09-08 00:00:00', '0');
INSERT INTO `water_area` VALUES ('49', '3', '6.544141855280012E8', '16', '1', '2019-09-28 00:00:00', '0');
INSERT INTO `water_area` VALUES ('50', '3', '7.475532377487483E8', '17', '1', '2019-10-28 00:00:00', '0');
INSERT INTO `water_area` VALUES ('51', '3', '5.821837981260965E8', '18', '1', '2019-02-15 00:00:00', '0');
INSERT INTO `water_area` VALUES ('52', '3', '5.692441372549968E8', '19', '1', '2019-04-06 00:00:00', '0');
INSERT INTO `water_area` VALUES ('53', '3', '5.513288096584156E8', '20', '1', '2019-04-16 00:00:00', '0');
INSERT INTO `water_area` VALUES ('54', '3', '5.411096034205464E8', '21', '1', '2019-05-26 00:00:00', '0');
INSERT INTO `water_area` VALUES ('55', '3', '5.338188490204569E8', '22', '1', '2019-07-05 00:00:00', '0');
INSERT INTO `water_area` VALUES ('56', '3', '6.577126405753906E8', '23', '1', '2019-09-23 00:00:00', '0');
INSERT INTO `water_area` VALUES ('57', '3', '7.308966697160764E8', '24', '1', '2019-12-02 00:00:00', '0');

-- ----------------------------
-- Table structure for water_storage
-- ----------------------------
DROP TABLE IF EXISTS `water_storage`;
CREATE TABLE `water_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '蓄水量结果表',
  `cal_storage` varchar(20) NOT NULL COMMENT '计算出的蓄水量结果',
  `model_id` int(11) NOT NULL COMMENT '模型id',
  `measured_storage` varchar(20) NOT NULL COMMENT '实测蓄水量',
  `date` datetime NOT NULL COMMENT '日期',
  `reservoir_id` int(11) NOT NULL COMMENT '水库id',
  `sar_area` varchar(20) DEFAULT NULL COMMENT 'SAR水域面积',
  `optical_area` varchar(20) DEFAULT NULL COMMENT '光学水域面积',
  `measured_level` varchar(20) DEFAULT NULL COMMENT '实测水位',
  `radar_level` varchar(20) DEFAULT NULL COMMENT '遥测水位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of water_storage
-- ----------------------------
