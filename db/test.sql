/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50743
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50743
 File Encoding         : 65001

 Date: 10/01/2024 10:06:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `t_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `t_age` int(10) NULL DEFAULT NULL COMMENT '年龄',
  `t_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'Tom', 23, '北京');
INSERT INTO `t_user` VALUES (2, 'Linuo', 32, '南京');
INSERT INTO `t_user` VALUES (3, 'Andy', 28, '上海');
INSERT INTO `t_user` VALUES (4, 'Lily', 44, '青岛');
INSERT INTO `t_user` VALUES (5, 'zhangsan', 55, '杭州');
INSERT INTO `t_user` VALUES (6, '李斯', 66, '苏州');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'aaa', 'aaa', 25);
INSERT INTO `user` VALUES (2, 'bbb', 'bbb', 35);
INSERT INTO `user` VALUES (3, 'ccc', 'ccc', 34);
INSERT INTO `user` VALUES (4, '1', '11', 111);
INSERT INTO `user` VALUES (5, '5', '55', 555);
INSERT INTO `user` VALUES (6, '6', '66', 666);

SET FOREIGN_KEY_CHECKS = 1;
