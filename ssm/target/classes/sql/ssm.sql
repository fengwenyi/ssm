/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-05-05 03:55:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '张三', '20');
INSERT INTO `user` VALUES ('2', '李四', '29');
INSERT INTO `user` VALUES ('4', '王五', '25');
INSERT INTO `user` VALUES ('5', 'Hello', '20');
INSERT INTO `user` VALUES ('6', '小冯少爷', '25');
