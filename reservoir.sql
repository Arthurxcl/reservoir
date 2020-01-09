/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50645
Source Host           : localhost:3306
Source Database       : reservoir

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2020-01-09 11:19:11
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cut_algo
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of measured_result
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of optical_img
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of radar_level
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of radar_result
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reservoir_info
-- ----------------------------
INSERT INTO `reservoir_info` VALUES ('1', '丹江口水库', 'sy', '111.046606', '111.718108', '33.085003', '32.521754');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sar_img
-- ----------------------------

-- ----------------------------
-- Table structure for water_area
-- ----------------------------
DROP TABLE IF EXISTS `water_area`;
CREATE TABLE `water_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '光学水域面积结果表',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of water_area
-- ----------------------------

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
