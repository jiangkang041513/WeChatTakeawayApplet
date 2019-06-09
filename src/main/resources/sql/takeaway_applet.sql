/*
Navicat MySQL Data Transfer

Source Server         : hobo
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : takeaway_applet

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-06-09 21:16:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '对应masterId',
  `detail_id` bigint(20) NOT NULL COMMENT '订单详情Id',
  `product_id` bigint(20) NOT NULL COMMENT '商品id',
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_quantity` int(8) NOT NULL COMMENT '商品数量',
  `product_icon` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商品图片',
  `creat_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1', '1551789981556', '1551789847953', '2', '稀饭', '5.00', '2', 'www.dksahdl.com', '2019-03-05 20:35:43', null);
INSERT INTO `order_detail` VALUES ('2', '1551789981556', '1551790096019', '3', '馒头', '6.00', '1', 'www.dksahdl.com', '2019-03-05 20:35:43', null);

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_id` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信接收id',
  `user_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户收货地址',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` int(4) NOT NULL COMMENT '订单状态  ‘0’为新下单',
  `pay_status` int(4) NOT NULL COMMENT '支付状态  ‘0’为未支付',
  `creat_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1551789981557 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1551789981556', '110110', '小刘', '12346123', 'C1108', '16.00', '0', '0', '2019-03-05 20:35:43', null);

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类目名',
  `category_type` int(32) NOT NULL COMMENT '类目编号',
  `creat_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of product_category
-- ----------------------------

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `category_type` int(32) NOT NULL COMMENT '商品类别编号',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品单价',
  `product_icon` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品图片',
  `product_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品描述',
  `creat_time` varbinary(64) DEFAULT NULL,
  `update_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('1', '饺子', '1', '8.00', 'www.dksahdl.com', 'kkk', 0x323031392D30332D30352031333A32383A3131, null);
INSERT INTO `product_info` VALUES ('2', '稀饭', '2', '5.00', 'www.dksahdl.com', 'kkk', 0x323031392D30332D30352031373A32323A3135, null);
INSERT INTO `product_info` VALUES ('3', '馒头', '1', '6.00', 'www.dksahdl.com', 'kkk', 0x323031392D30332D30352031373A32323A3335, null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `open_id` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` int(3) NOT NULL,
  `user_phone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_address` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123456123', '罗百万', '1', '12345678912', 'C1108');
