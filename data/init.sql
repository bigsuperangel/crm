/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.20 : Database - crm_bf
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `bbb` */

DROP TABLE IF EXISTS `bbb`;

CREATE TABLE `bbb` (
  `out_time` varchar(22) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bbb` */

insert  into `bbb`(`out_time`) values ('2015-02-28 17:12:06');
insert  into `bbb`(`out_time`) values ('2015-05-21');
insert  into `bbb`(`out_time`) values ('2015-05-21');
insert  into `bbb`(`out_time`) values ('2015-05-21');
insert  into `bbb`(`out_time`) values ('2015-05-21');

/*Table structure for table `t_agreement` */

DROP TABLE IF EXISTS `t_agreement`;

CREATE TABLE `t_agreement` (
  `agreement_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同ID',
  `agreement_no` varchar(100) DEFAULT NULL COMMENT '合同编号',
  `status` int(3) DEFAULT NULL COMMENT '合同状态 0-新建; 1-已发出; 2-已签约; 3-已到款; 4-款已付清 ;5-已完成',
  `project_name` varchar(30) NOT NULL COMMENT '项目名称',
  `cdate` varchar(22) DEFAULT NULL COMMENT '创建时间',
  `c_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `sign_date` varchar(22) DEFAULT NULL COMMENT '签约日期',
  `payment_clear_date` varchar(22) DEFAULT NULL COMMENT '完款日期',
  `finish_date` varchar(22) DEFAULT NULL COMMENT '完成日期',
  `amount` int(10) DEFAULT NULL COMMENT '合同金额',
  `sub_agreement_count` int(5) DEFAULT NULL COMMENT '子合同数',
  `payment_status` varchar(100) DEFAULT NULL COMMENT '收款状态',
  `my_signer` varchar(22) DEFAULT NULL COMMENT '我方签约人',
  `customer_signer` varchar(22) DEFAULT NULL COMMENT '客户签约人',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  `is_receipt` int(1) DEFAULT NULL COMMENT '是否开票',
  `is_valid` int(1) DEFAULT '1' COMMENT '删除标识 0删除 1正常',
  PRIMARY KEY (`agreement_id`),
  KEY `FK_mp9iix36prbmntydoikwdwuxl` (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='合同';

/*Data for the table `t_agreement` */

insert  into `t_agreement`(`agreement_id`,`agreement_no`,`status`,`project_name`,`cdate`,`c_id`,`sign_date`,`payment_clear_date`,`finish_date`,`amount`,`sub_agreement_count`,`payment_status`,`my_signer`,`customer_signer`,`remark`,`is_receipt`,`is_valid`) values (1,'1',1,'111',NULL,38,'','','',11111,11,NULL,'2','2','1',1,1);
insert  into `t_agreement`(`agreement_id`,`agreement_no`,`status`,`project_name`,`cdate`,`c_id`,`sign_date`,`payment_clear_date`,`finish_date`,`amount`,`sub_agreement_count`,`payment_status`,`my_signer`,`customer_signer`,`remark`,`is_receipt`,`is_valid`) values (8,'',0,'test','2015-02-27 11:22:21',NULL,'15年02月27日',NULL,'15年02月27日',111,1,'111','2','31','1',1,1);
insert  into `t_agreement`(`agreement_id`,`agreement_no`,`status`,`project_name`,`cdate`,`c_id`,`sign_date`,`payment_clear_date`,`finish_date`,`amount`,`sub_agreement_count`,`payment_status`,`my_signer`,`customer_signer`,`remark`,`is_receipt`,`is_valid`) values (9,'',0,'111','2015-03-02 13:43:48',NULL,'2015-03-04',NULL,'2015-03-06',1,11,'1','1','11','1111',1,1);
insert  into `t_agreement`(`agreement_id`,`agreement_no`,`status`,`project_name`,`cdate`,`c_id`,`sign_date`,`payment_clear_date`,`finish_date`,`amount`,`sub_agreement_count`,`payment_status`,`my_signer`,`customer_signer`,`remark`,`is_receipt`,`is_valid`) values (10,'',0,'444','2015-03-02 14:01:02',39,'2015-03-11',NULL,'2015-03-19',2,44,'4','44','44','2222',1,1);
insert  into `t_agreement`(`agreement_id`,`agreement_no`,`status`,`project_name`,`cdate`,`c_id`,`sign_date`,`payment_clear_date`,`finish_date`,`amount`,`sub_agreement_count`,`payment_status`,`my_signer`,`customer_signer`,`remark`,`is_receipt`,`is_valid`) values (11,'c06c4f2a-fa92-4737-9fa0-3b51a08516c5',4,'维权0103合同','2015-03-03 21:28:23',48,'2015-03-04','2015-03-04 20:03:47','2015-03-20',388,6,'fds','aa','bb','',1,1);
insert  into `t_agreement`(`agreement_id`,`agreement_no`,`status`,`project_name`,`cdate`,`c_id`,`sign_date`,`payment_clear_date`,`finish_date`,`amount`,`sub_agreement_count`,`payment_status`,`my_signer`,`customer_signer`,`remark`,`is_receipt`,`is_valid`) values (12,'ef83c2c5-5eb8-4645-a727-ed392213c4f2',0,'test1','2015-03-05 15:20:41',43,'2015-03-05',NULL,'2015-03-28',4,2,'2','23','545','',1,1);
insert  into `t_agreement`(`agreement_id`,`agreement_no`,`status`,`project_name`,`cdate`,`c_id`,`sign_date`,`payment_clear_date`,`finish_date`,`amount`,`sub_agreement_count`,`payment_status`,`my_signer`,`customer_signer`,`remark`,`is_receipt`,`is_valid`) values (13,'19a4f786dc9148e1ad2ac6e73d2b3300',2,'dfsdewrr','2015-03-10 20:24:09',56,'2015-03-10',NULL,'2015-03-28',124,5123,'41212','214','1312','',1,1);

/*Table structure for table `t_agreement_detail` */

DROP TABLE IF EXISTS `t_agreement_detail`;

CREATE TABLE `t_agreement_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '合同细项ID',
  `detail_name` varchar(100) DEFAULT NULL COMMENT '合同款项名称',
  `agreement_id` int(11) DEFAULT NULL COMMENT '合同ID',
  `value1` varchar(22) DEFAULT NULL COMMENT '合同参数',
  `value2` varchar(22) DEFAULT NULL COMMENT '合同参数',
  `value3` varchar(22) DEFAULT NULL COMMENT '合同参数',
  `value4` varchar(22) DEFAULT NULL COMMENT '合同参数',
  `cdate` varchar(22) DEFAULT NULL COMMENT '创建时间',
  `is_valid` int(1) DEFAULT '1' COMMENT '删除标识 0删除 1正常',
  PRIMARY KEY (`detail_id`),
  KEY `FK_1lvf1iet3pe5hfflkn8r6yhem` (`agreement_id`),
  CONSTRAINT `FK_1lvf1iet3pe5hfflkn8r6yhem` FOREIGN KEY (`agreement_id`) REFERENCES `t_agreement` (`agreement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `t_agreement_detail` */

insert  into `t_agreement_detail`(`detail_id`,`detail_name`,`agreement_id`,`value1`,`value2`,`value3`,`value4`,`cdate`,`is_valid`) values (1,'232',1,'2','2','2','2',NULL,1);
insert  into `t_agreement_detail`(`detail_id`,`detail_name`,`agreement_id`,`value1`,`value2`,`value3`,`value4`,`cdate`,`is_valid`) values (7,NULL,8,'1','1','1','11','2015-02-27 11:22:21',1);
insert  into `t_agreement_detail`(`detail_id`,`detail_name`,`agreement_id`,`value1`,`value2`,`value3`,`value4`,`cdate`,`is_valid`) values (8,NULL,9,'1','1','1','1','2015-03-02 13:43:48',1);
insert  into `t_agreement_detail`(`detail_id`,`detail_name`,`agreement_id`,`value1`,`value2`,`value3`,`value4`,`cdate`,`is_valid`) values (9,NULL,10,'2','2','2','2','2015-03-02 14:01:02',1);
insert  into `t_agreement_detail`(`detail_id`,`detail_name`,`agreement_id`,`value1`,`value2`,`value3`,`value4`,`cdate`,`is_valid`) values (10,NULL,11,'2','323','23','23','2015-03-03 21:28:23',1);
insert  into `t_agreement_detail`(`detail_id`,`detail_name`,`agreement_id`,`value1`,`value2`,`value3`,`value4`,`cdate`,`is_valid`) values (11,NULL,12,'2','23','23','23','2015-03-05 15:20:41',1);
insert  into `t_agreement_detail`(`detail_id`,`detail_name`,`agreement_id`,`value1`,`value2`,`value3`,`value4`,`cdate`,`is_valid`) values (12,NULL,13,'23','12','44','12','2015-03-10 20:24:09',1);

/*Table structure for table `t_apply` */

DROP TABLE IF EXISTS `t_apply`;

CREATE TABLE `t_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cdate` varchar(22) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `op_time` varchar(22) DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `auditor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4kyyeqc731ds7l993p1dxgok0` (`customer_id`),
  KEY `FK_npgdkc5eqd2r8ec153vipl6y0` (`u_id`),
  KEY `FK_oai8othgw5wdjig3s64j7e7p2` (`auditor`),
  CONSTRAINT `FK_4kyyeqc731ds7l993p1dxgok0` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`),
  CONSTRAINT `FK_npgdkc5eqd2r8ec153vipl6y0` FOREIGN KEY (`u_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_oai8othgw5wdjig3s64j7e7p2` FOREIGN KEY (`auditor`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_apply` */

insert  into `t_apply`(`id`,`cdate`,`status`,`op_time`,`remarks`,`customer_id`,`u_id`,`auditor`) values (1,'2015-03-03 20:56:34',1,NULL,NULL,48,15,14);
insert  into `t_apply`(`id`,`cdate`,`status`,`op_time`,`remarks`,`customer_id`,`u_id`,`auditor`) values (2,'2015-03-10 20:40:37',1,'2015-03-10 20:40:52',NULL,56,1,1);

/*Table structure for table `t_attachment` */

DROP TABLE IF EXISTS `t_attachment`;

CREATE TABLE `t_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户 ID',
  `path` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '图片真实路径',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `type` tinyint(1) DEFAULT NULL COMMENT '类型 1－图片 2－DOC 3－EXCEL',
  `url` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '图片链接',
  `owner_id` int(11) DEFAULT NULL COMMENT '拥有者id',
  PRIMARY KEY (`id`),
  KEY `FK_bf0c661ylnrfl80jxyy57rik3` (`user_id`),
  CONSTRAINT `FK_bf0c661ylnrfl80jxyy57rik3` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_attachment` */

insert  into `t_attachment`(`id`,`user_id`,`path`,`name`,`description`,`type`,`url`,`owner_id`) values (2,1,'D:\\java\\apache-tomcat-7.0.21\\webapps\\crm\\/upload/1_1425277941091.jpg','222','2222',1,'123',10);
insert  into `t_attachment`(`id`,`user_id`,`path`,`name`,`description`,`type`,`url`,`owner_id`) values (3,1,'D:\\java\\apache-tomcat-7.0.21\\webapps\\crm\\/upload/1_1425278168111.jpg','21313','131313',1,'123',10);
insert  into `t_attachment`(`id`,`user_id`,`path`,`name`,`description`,`type`,`url`,`owner_id`) values (4,1,'D:\\java\\apache-tomcat-7.0.21\\webapps\\crm\\upload\\款项\\20150302\\2_1425281129312.jpg','222','2222',2,'123',0);
insert  into `t_attachment`(`id`,`user_id`,`path`,`name`,`description`,`type`,`url`,`owner_id`) values (5,1,'D:\\java\\apache-tomcat-7.0.21\\webapps\\crm\\upload\\合同\\20150303\\1_1425369563772.doc','11','111',1,'123',10);
insert  into `t_attachment`(`id`,`user_id`,`path`,`name`,`description`,`type`,`url`,`owner_id`) values (7,1,'D:\\java\\apache-tomcat-7.0.21\\webapps\\crm\\upload\\款项\\20150304\\2_1425468316021.jpg','111','111',2,'/upload/款项/20150304/2_1425468316021.jpg',2);
insert  into `t_attachment`(`id`,`user_id`,`path`,`name`,`description`,`type`,`url`,`owner_id`) values (9,1,'D:\\java\\apache-tomcat-7.0.21\\webapps\\crm\\upload/合同/20150304/1_1425469780894.doc','212','1212',1,'/upload/合同/20150304/1_1425469780894.doc',11);
insert  into `t_attachment`(`id`,`user_id`,`path`,`name`,`description`,`type`,`url`,`owner_id`) values (12,1,'C:\\develop\\apache-tomcat-7.0.59\\webapps\\crm\\upload/款项/20150310/2_1425992151523.jpg','111','111',2,'/upload/款项/20150310/2_1425992151523.jpg',3);
insert  into `t_attachment`(`id`,`user_id`,`path`,`name`,`description`,`type`,`url`,`owner_id`) values (13,1,'C:\\develop\\apache-tomcat-7.0.59\\webapps\\crm\\upload/am/20150310/1_1425992563462.doc','11','111',1,'/upload/am/20150310/1_1425992563462.doc',13);
insert  into `t_attachment`(`id`,`user_id`,`path`,`name`,`description`,`type`,`url`,`owner_id`) values (14,1,'C:\\develop\\apache-tomcat-7.0.59\\webapps\\crm\\upload/pm/20150310/2_1425992578572.jpg','11','1111',2,'/upload/pm/20150310/2_1425992578572.jpg',3);

/*Table structure for table `t_contact` */

DROP TABLE IF EXISTS `t_contact`;

CREATE TABLE `t_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `content` longtext,
  `addr` varchar(50) DEFAULT NULL,
  `cdate` varchar(20) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `contactor` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contact_type` int(3) DEFAULT NULL COMMENT '联系类型 1QQ、2旺旺、3手机、4邮箱、5微信',
  `next_visit` varchar(22) DEFAULT NULL COMMENT '下次回访时间',
  `supervisor` varchar(30) DEFAULT NULL COMMENT '负责人',
  `customer_status` varchar(100) DEFAULT NULL COMMENT '客户状态',
  `is_valid` int(1) DEFAULT '1' COMMENT '删除标识 0删除 1正常',
  PRIMARY KEY (`id`),
  KEY `FK_mi0v3pbcruhthix9bpy1tjefg` (`c_id`),
  KEY `FK_5tqck18e29swyl8fb13cihuv6` (`u_id`),
  CONSTRAINT `FK_5tqck18e29swyl8fb13cihuv6` FOREIGN KEY (`u_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_mi0v3pbcruhthix9bpy1tjefg` FOREIGN KEY (`c_id`) REFERENCES `t_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_contact` */

insert  into `t_contact`(`id`,`title`,`content`,`addr`,`cdate`,`c_id`,`u_id`,`contactor`,`contact_type`,`next_visit`,`supervisor`,`customer_status`,`is_valid`) values (1,'12','212	','1212','2013-12-14 21:31:00',3,1,NULL,NULL,NULL,NULL,NULL,1);
insert  into `t_contact`(`id`,`title`,`content`,`addr`,`cdate`,`c_id`,`u_id`,`contactor`,`contact_type`,`next_visit`,`supervisor`,`customer_status`,`is_valid`) values (2,'23, 232323','23232','000','2013-12-14 22:04:03',3,2,NULL,NULL,NULL,NULL,NULL,1);
insert  into `t_contact`(`id`,`title`,`content`,`addr`,`cdate`,`c_id`,`u_id`,`contactor`,`contact_type`,`next_visit`,`supervisor`,`customer_status`,`is_valid`) values (3,'1212','1212','1212','2013-12-14 22:06:59',3,2,NULL,NULL,NULL,NULL,NULL,1);
insert  into `t_contact`(`id`,`title`,`content`,`addr`,`cdate`,`c_id`,`u_id`,`contactor`,`contact_type`,`next_visit`,`supervisor`,`customer_status`,`is_valid`) values (4,'吃鸭子','吃鸭子，签合同','全聚德','2013-12-15 15:25:35',3,2,NULL,NULL,NULL,NULL,NULL,1);
insert  into `t_contact`(`id`,`title`,`content`,`addr`,`cdate`,`c_id`,`u_id`,`contactor`,`contact_type`,`next_visit`,`supervisor`,`customer_status`,`is_valid`) values (5,'11','11','1','2013-12-15 15:33:49',3,2,NULL,NULL,NULL,NULL,NULL,1);
insert  into `t_contact`(`id`,`title`,`content`,`addr`,`cdate`,`c_id`,`u_id`,`contactor`,`contact_type`,`next_visit`,`supervisor`,`customer_status`,`is_valid`) values (6,'111','1111','11','2015-01-29 10:05:40',33,2,'11',NULL,'2015-01-29 10:05:40','11',NULL,0);
insert  into `t_contact`(`id`,`title`,`content`,`addr`,`cdate`,`c_id`,`u_id`,`contactor`,`contact_type`,`next_visit`,`supervisor`,`customer_status`,`is_valid`) values (7,'111','11111','111','2015-02-05 14:52:44',38,3,'111',NULL,'2015-02-05 14:52:44','1111',NULL,1);
insert  into `t_contact`(`id`,`title`,`content`,`addr`,`cdate`,`c_id`,`u_id`,`contactor`,`contact_type`,`next_visit`,`supervisor`,`customer_status`,`is_valid`) values (8,'1111','11111','111','2015-02-05 14:52:44',38,9,'1111',NULL,'2015-02-05 14:52:44','1111',NULL,1);

/*Table structure for table `t_cooperate` */

DROP TABLE IF EXISTS `t_cooperate`;

CREATE TABLE `t_cooperate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '合作的客户ID',
  `cooperator_type` int(1) DEFAULT NULL COMMENT '合作类型: 0-跨区域合作    1-跨大区合作',
  `submitor` int(11) DEFAULT NULL COMMENT '发起合作员工ID',
  `receiver` int(11) DEFAULT NULL COMMENT '接收合作的员工ID',
  `status` int(1) DEFAULT '0' COMMENT '合作状态 新增0, 大区经理审批1, 对方大区经理审批2(跨区合作才会有), 合作成功9, 合作失败-1',
  `submit_dept_id` int(11) DEFAULT NULL COMMENT '发起区域',
  `target_dept_id` int(11) DEFAULT NULL COMMENT '目标区域',
  `cdate` varchar(22) DEFAULT NULL COMMENT '创建时间',
  `op_time` varchar(22) DEFAULT NULL COMMENT '操作时间',
  `is_valid` int(1) DEFAULT '1' COMMENT '是否可用. 0不可用, 1可用',
  PRIMARY KEY (`id`),
  KEY `FK_4lyywuuta5s5kobjvh24rgds5` (`customer_id`),
  KEY `FK_q054y3xj5s0r9n7cjo7ehsexo` (`submitor`),
  KEY `FK_h1p38j3d7g16iilrcnyq9c7yn` (`receiver`),
  KEY `FK_b2yhwuj2lt140dp090okyfpud` (`submit_dept_id`),
  KEY `FK_cv1w6ojnru89uvbw4iq8u6xsn` (`target_dept_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `t_cooperate` */

insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (1,35,0,1,NULL,0,NULL,2,'2015-01-30 21:11:21','2015-01-30 21:11:21',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (2,36,0,1,NULL,0,2,3,NULL,'2015-01-30 22:17:19',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (3,37,1,1,9,9,2,3,'2015-02-05 10:33:34','2015-02-05 14:32:48',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (4,38,0,9,9,9,3,3,'2015-02-05 14:47:08','2015-02-05 14:53:10',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (5,39,0,11,12,9,26,27,'2015-02-06 22:50:31','2015-02-06 22:54:35',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (6,40,1,11,NULL,-1,26,16,'2015-02-06 22:51:28','2015-02-06 22:56:27',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (7,41,0,11,NULL,-1,26,27,'2015-02-06 22:51:56','2015-02-06 22:53:49',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (8,42,1,11,10,9,26,3,'2015-02-06 22:52:22','2015-02-06 22:57:04',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (9,51,0,17,17,9,10,10,'2015-03-10 18:49:41','2015-03-10 19:14:56',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (10,52,0,17,NULL,-1,10,NULL,'2015-03-10 18:50:12','2015-03-10 18:56:19',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (11,54,0,17,NULL,0,10,NULL,'2015-03-10 19:19:28','2015-03-10 19:19:28',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (12,55,0,17,NULL,0,10,NULL,'2015-03-10 19:54:12','2015-03-10 19:54:12',1);
insert  into `t_cooperate`(`id`,`customer_id`,`cooperator_type`,`submitor`,`receiver`,`status`,`submit_dept_id`,`target_dept_id`,`cdate`,`op_time`,`is_valid`) values (13,56,0,17,19,9,10,27,'2015-03-10 20:02:34','2015-03-10 20:05:51',1);

/*Table structure for table `t_cservice` */

DROP TABLE IF EXISTS `t_cservice`;

CREATE TABLE `t_cservice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `cdate` varchar(20) DEFAULT NULL,
  `t_id` int(11) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  `cu_id` int(11) DEFAULT NULL,
  `du_id` int(11) DEFAULT NULL,
  `satisfied` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_b5b7e09qw7xlsxypxhxo7g6fl` (`t_id`),
  KEY `FK_nuafrwyuvnvoywycxj1lpsq2a` (`c_id`),
  KEY `FK_dwxgs53cpho07u516454l1qlo` (`cu_id`),
  KEY `FK_d29b9v4e58f9jyjo4ebgfugvo` (`du_id`),
  CONSTRAINT `FK_b5b7e09qw7xlsxypxhxo7g6fl` FOREIGN KEY (`t_id`) REFERENCES `t_type_dic` (`id`),
  CONSTRAINT `FK_d29b9v4e58f9jyjo4ebgfugvo` FOREIGN KEY (`du_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_dwxgs53cpho07u516454l1qlo` FOREIGN KEY (`cu_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_nuafrwyuvnvoywycxj1lpsq2a` FOREIGN KEY (`c_id`) REFERENCES `t_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_cservice` */

insert  into `t_cservice`(`id`,`content`,`state`,`title`,`cdate`,`t_id`,`c_id`,`cu_id`,`du_id`,`satisfied`) values (1,'内容',2,'标题','2013-12-15 16:23:17',2,3,1,1,0);
insert  into `t_cservice`(`id`,`content`,`state`,`title`,`cdate`,`t_id`,`c_id`,`cu_id`,`du_id`,`satisfied`) values (2,'我啊啊啊啊啊',3,'121221211我1','2013-12-15 16:23:17',1,3,1,1,4);
insert  into `t_cservice`(`id`,`content`,`state`,`title`,`cdate`,`t_id`,`c_id`,`cu_id`,`du_id`,`satisfied`) values (4,'测试服务，并分配1',0,'测试服务12','2013-12-15 16:23:17',1,8,1,1,0);
insert  into `t_cservice`(`id`,`content`,`state`,`title`,`cdate`,`t_id`,`c_id`,`cu_id`,`du_id`,`satisfied`) values (5,'<font color=\"FF0000\">​1212121212</font>',1,'概要','2013-12-15 20:15:17',1,2,1,3,2);

/*Table structure for table `t_customer` */

DROP TABLE IF EXISTS `t_customer`;

CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(100) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `level` int(11) DEFAULT '3',
  `satisfied` int(11) DEFAULT '3',
  `credit` int(11) DEFAULT '10',
  `u_id` int(11) DEFAULT NULL,
  `brand` varchar(40) DEFAULT NULL,
  `company_address` varchar(40) DEFAULT NULL COMMENT '公司地址',
  `operate_brand` int(4) DEFAULT '0' COMMENT '代运营品牌数',
  `status` int(1) DEFAULT '1' COMMENT '1－本地客户 2－合作客户',
  `handler` int(11) DEFAULT NULL COMMENT '所属业务员',
  `cdate` varchar(22) DEFAULT NULL COMMENT '创建时间',
  `is_cooperate` int(1) DEFAULT '0' COMMENT '是否合作过 0:未合作1:已合作',
  `op_time` varchar(22) DEFAULT NULL COMMENT '合作开始时间',
  `is_sign` int(1) DEFAULT '0' COMMENT '合同是否签约 0未签约1已签约',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `audit` int(1) DEFAULT '0' COMMENT '是否审核 0未审核 1已审核 －1驳回',
  `sale_mode` int(3) DEFAULT '-1' COMMENT '销售模式 1直营、2分销、3直营和分销',
  `company_type` int(3) DEFAULT '-1' COMMENT '公司类 1型品牌商、2代运营商',
  `store_platform` varchar(50) DEFAULT '-1' COMMENT '开店平台 1淘宝系、2拍拍系、3京东商城、4当当、5卓越、6１号店、7马可波罗、8慧聪、9其他',
  `brand_awareness` int(3) DEFAULT '-1' COMMENT '品牌知名度 1一线、2二线、3三线',
  `company_awareness` int(3) DEFAULT '-1' COMMENT '公司知名度 1高、2中、3低',
  `customer_source` varchar(50) DEFAULT '-1' COMMENT '客户来源 1电话营销、2会议营销、3主动联系、4客户推荐、5淘拍档、6其他',
  `is_valid` int(1) DEFAULT '1' COMMENT '删除标识 0删除 1正常',
  `audit_time` varchar(20) DEFAULT NULL,
  `free_time` varchar(20) DEFAULT NULL COMMENT '释放时间',
  `customerName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_94fkfhq4hknny6r2xv39l70sn` (`u_id`),
  KEY `FKDACDF349ED6EB35B` (`dept_id`),
  KEY `FKDACDF349BA18E6D6` (`handler`),
  KEY `FK_rn7hnhmn9d251is0xw4rtriym` (`handler`),
  CONSTRAINT `FKDACDF349ED6EB35B` FOREIGN KEY (`dept_id`) REFERENCES `t_dept` (`id`),
  CONSTRAINT `FK_94fkfhq4hknny6r2xv39l70sn` FOREIGN KEY (`u_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_rn7hnhmn9d251is0xw4rtriym` FOREIGN KEY (`handler`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Data for the table `t_customer` */

insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (2,'12','1',2,2,4,1,'aa','aa',0,3,NULL,NULL,0,NULL,0,8,0,0,0,'0',0,0,'0',1,NULL,'',NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (3,'测试信息，不要删除','测试信息，不要删除',5,5,10,1,'fd','fdf',0,3,NULL,NULL,0,NULL,0,9,0,0,0,'0',0,0,'0',1,NULL,'',NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (4,'1测试测试修改1???',NULL,3,3,10,1,NULL,NULL,0,0,NULL,NULL,0,NULL,0,NULL,0,0,0,'0',0,0,'0',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (5,'01212',NULL,3,3,10,1,NULL,NULL,0,0,NULL,NULL,0,NULL,0,NULL,0,0,0,'0',0,0,'0',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (6,'01212',NULL,3,3,10,1,NULL,NULL,0,0,NULL,NULL,0,NULL,0,NULL,0,0,0,'0',0,0,'0',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (7,'测试数据，不要删除',NULL,3,3,10,1,NULL,NULL,0,0,NULL,NULL,0,NULL,0,NULL,0,0,0,'0',0,0,'0',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (8,'张三丰',NULL,3,3,10,1,NULL,NULL,0,0,NULL,NULL,0,NULL,0,NULL,0,0,0,'0',0,0,'0',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (9,'1212','12',2,1,1,1,NULL,NULL,0,0,NULL,NULL,0,NULL,0,NULL,0,0,0,'0',0,0,'0',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (10,'sdads',NULL,3,3,10,NULL,NULL,'safawerewr',1,0,NULL,NULL,0,NULL,0,23,0,0,0,'2',0,0,'2',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (11,'fffffffff',NULL,3,3,10,NULL,NULL,'wertafa',0,0,NULL,NULL,0,NULL,0,8,0,0,0,'2',0,0,'2',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (14,'aaaa',NULL,3,3,10,1,NULL,'aaa',0,0,NULL,'2015-01-20 11:14:33',0,NULL,0,9,0,0,0,'2',0,0,'2',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (15,'bbb',NULL,3,3,10,1,NULL,'bbb',5,0,NULL,'2015-01-20 11:36:15',0,NULL,0,5,0,0,0,'7',0,0,'3',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (16,'555',NULL,3,3,10,1,'555','555',2,0,NULL,'2015-01-20 15:28:31',0,NULL,0,4,0,0,0,'1,6,8',0,0,'2,3,4',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (17,'6666',NULL,3,3,10,1,'6666','6666',1,0,NULL,'2015-01-20 15:52:17',0,NULL,0,4,0,0,0,'1,6,7',0,0,'2,3,4',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (18,'adsf',NULL,3,3,10,1,'asfsd','fsafdsa',0,0,NULL,'2015-01-20 15:57:14',0,NULL,0,3,0,0,0,'6',0,0,'2',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (32,'4545',NULL,3,3,10,1,'45','454',3,0,NULL,'2015-01-27 16:54:13',0,NULL,0,2,0,0,0,'7',0,0,'3,5',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (33,'999',NULL,3,3,10,1,'999','99',3,0,NULL,'2015-02-28 17:12:06',0,NULL,0,7,0,0,0,'3,9',0,0,'2,3,4,5',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (35,'11111',NULL,3,3,10,NULL,'11','111',1,2,NULL,NULL,0,NULL,0,NULL,0,0,0,'2',0,0,'3',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (36,'233',NULL,3,3,10,1,'3','2323',1,2,NULL,'2015-01-30 22:17:19',0,NULL,0,3,0,0,0,'7',0,0,'5',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (37,'999',NULL,3,3,10,1,'999','999',10,1,9,'2015-02-05 10:33:34',0,NULL,0,2,1,2,2,'2',1,1,'3,4',1,'2015-03-05 15:25:46','2015-02-24',NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (38,'跨区域合作',NULL,3,3,10,9,'跨区域合作','跨区域合作',2,1,9,'2015-02-05 14:47:08',0,NULL,0,6,-1,1,1,'2',1,1,'2',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (39,'华东跨区域合作',NULL,3,3,10,11,'华东跨区域合作','华东跨区域合作',1,1,12,'2015-02-06 22:50:31',0,NULL,0,27,1,1,1,'2',2,1,'3',1,'2015-03-05 15:39:15','2015-06-03',NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (40,'华东向华南跨大区合作',NULL,3,3,10,11,'华东向华南跨大区合作','华东向华南跨大区合作',2,1,NULL,'2015-02-06 22:51:28',0,NULL,0,26,0,1,2,'2',2,2,'5',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (41,'华东跨区域02',NULL,3,3,10,11,'华东跨区域02','华东跨区域02',1,2,NULL,'2015-02-06 22:51:56',0,NULL,0,27,0,2,1,'3',3,2,'4',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (42,'华东华南跨大区02',NULL,3,3,10,11,'华东华南跨大区02','华东华南跨大区02',1,1,10,'2015-02-06 22:52:22',0,NULL,0,3,1,2,2,'7',1,1,'5',1,'2015-02-06 23:11:58',NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (43,'test1',NULL,3,3,10,1,'test1','test1',1,0,NULL,'2015-02-10 14:51:29',0,NULL,0,2,1,2,1,'2,7',2,2,'2',1,'2015-02-10 14:52:19',NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (46,'维权01',NULL,3,3,10,11,'维权01','维权01',2,1,11,'2015-03-03 19:13:41',0,NULL,0,26,1,2,1,'2,7,8',2,1,'4',1,'2015-03-03 19:15:28','2015-03-03',NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (47,'维权0102',NULL,3,3,10,11,'维权0102','维权0102',1,1,11,'2015-03-03 19:14:16',0,NULL,0,26,-1,2,2,'2,3,7',2,2,'4,5',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (48,'维权0103',NULL,3,3,10,11,'维权0103','维权0103',3,1,15,'2015-03-03 20:11:55',0,NULL,1,26,1,1,1,'2,7,8',2,2,'4',1,'2015-03-03 20:57:56','',NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (49,'华东大区测试本地合作',NULL,3,3,10,17,'华东大区测试本地合作','1111',3,1,17,'2015-03-10 17:56:49',0,NULL,0,10,0,2,1,'2,3,7',1,1,'4,5',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (50,'华东大区测试本地1',NULL,3,3,10,17,'华东大区测试本地1','华东大区测试本地1',4,1,17,'2015-03-10 17:57:25',0,NULL,0,10,0,1,2,'2,7',1,1,'4,5',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (51,NULL,NULL,3,3,10,17,NULL,NULL,0,1,17,'2015-03-10 18:49:41',0,NULL,0,NULL,0,-1,-1,'2,4,7,8',-1,-1,'4',1,'2015-03-10 19:14:56',NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (52,NULL,NULL,3,3,10,17,NULL,NULL,0,2,NULL,'2015-03-10 18:50:12',0,NULL,0,NULL,0,-1,-1,'2,4,7',-1,-1,'5',0,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (53,'1111',NULL,3,3,10,1,'11','11',0,1,1,'2015-03-10 18:51:11',0,NULL,0,10,0,2,2,'2,7,8',1,2,'4',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (54,'dsfafaf',NULL,3,3,10,17,'1231244','dsafgsfs',7,2,NULL,'2015-03-10 19:19:28',0,NULL,0,NULL,0,2,1,'1',2,1,'5',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (55,NULL,NULL,3,3,10,17,NULL,NULL,0,2,NULL,'2015-03-10 19:54:12',0,NULL,0,NULL,0,-1,-1,'2,7',-1,-1,'4',1,NULL,NULL,NULL);
insert  into `t_customer`(`id`,`customer_name`,`area`,`level`,`satisfied`,`credit`,`u_id`,`brand`,`company_address`,`operate_brand`,`status`,`handler`,`cdate`,`is_cooperate`,`op_time`,`is_sign`,`dept_id`,`audit`,`sale_mode`,`company_type`,`store_platform`,`brand_awareness`,`company_awareness`,`customer_source`,`is_valid`,`audit_time`,`free_time`,`customerName`) values (56,'fawerwerw',NULL,3,3,10,NULL,'1cafwer4','123123',0,1,15,'2015-03-10 20:02:34',0,NULL,1,27,1,2,2,'2,8',2,1,'3,4',1,'2015-03-10 20:40:53','2015-03-01',NULL);

/*Table structure for table `t_delay_apply` */

DROP TABLE IF EXISTS `t_delay_apply`;

CREATE TABLE `t_delay_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `applier_id` int(11) DEFAULT '0' COMMENT '申请人',
  `c_id` int(11) DEFAULT '0' COMMENT '申请延时的顾客ID',
  `delay_days` int(5) DEFAULT '0' COMMENT '申请延时天数',
  `audit` int(1) DEFAULT '0' COMMENT '申请状态. 0-未审批, 1-审批通过, 2-审批不通过',
  `cdate` varchar(22) DEFAULT NULL COMMENT '申请日期',
  `remarks` varchar(100) DEFAULT NULL COMMENT '申请理由',
  PRIMARY KEY (`id`),
  KEY `FK_7r3vxmge15katncndmjl9hbcp` (`applier_id`),
  KEY `FK_c54pm634jq34jyn26mq1jbas` (`c_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_delay_apply` */

insert  into `t_delay_apply`(`id`,`applier_id`,`c_id`,`delay_days`,`audit`,`cdate`,`remarks`) values (1,15,48,20,0,NULL,NULL);
insert  into `t_delay_apply`(`id`,`applier_id`,`c_id`,`delay_days`,`audit`,`cdate`,`remarks`) values (2,15,48,10,4,'2015-03-04 16:45:06',NULL);
insert  into `t_delay_apply`(`id`,`applier_id`,`c_id`,`delay_days`,`audit`,`cdate`,`remarks`) values (3,15,2,23,2,NULL,NULL);

/*Table structure for table `t_dept` */

DROP TABLE IF EXISTS `t_dept`;

CREATE TABLE `t_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `p_id` int(11) DEFAULT NULL,
  `cdate` varchar(22) DEFAULT NULL COMMENT '创建时间',
  `is_valid` int(1) DEFAULT '1' COMMENT '删除标识 0删除 1正常',
  `code` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_xhvm0v2hhqbapbnh4n7vxabs` (`p_id`),
  CONSTRAINT `FK_xhvm0v2hhqbapbnh4n7vxabs` FOREIGN KEY (`p_id`) REFERENCES `t_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `t_dept` */

insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (1,'所有部门',NULL,NULL,1,'1');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (2,'华东大区',1,NULL,1,'101');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (3,'华南大区',1,NULL,1,'102');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (4,'华北大区',1,NULL,1,'103');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (5,'销售部',4,NULL,1,'104');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (6,'福建',3,NULL,1,'10201');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (7,'后勤',4,NULL,1,'105');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (8,'人力',4,NULL,1,'106');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (9,'财务',4,NULL,1,'107');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (10,'上海',2,NULL,1,'10101');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (11,'安徽',2,NULL,1,'10102');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (12,'山东',2,NULL,1,'10103');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (13,'广西',3,NULL,1,'10202');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (14,'上海',3,NULL,1,'10203');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (15,'广州',3,NULL,1,'10204');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (16,'深圳',3,NULL,1,'10205');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (17,'黑盒测试',4,NULL,1,'10301');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (18,'白盒测试',4,NULL,1,'10302');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (21,'江西',2,NULL,1,'10104');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (22,'1211212',4,NULL,1,'10303');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (23,'12',3,NULL,0,'108');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (24,'江苏',2,'2015-02-05 15:55:58',0,'10105');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (25,'浙江',24,'2015-02-05 15:57:26',0,'1010500');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (26,'江苏',2,'2015-02-05 16:13:55',1,'10105');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (27,'浙江',2,'2015-02-05 16:17:20',1,'10106');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (28,'东北',4,'2015-02-05 16:25:22',0,'108');
insert  into `t_dept`(`id`,`name`,`p_id`,`cdate`,`is_valid`,`code`) values (29,'吉林',28,'2015-02-05 16:25:32',0,'10800');

/*Table structure for table `t_dic` */

DROP TABLE IF EXISTS `t_dic`;

CREATE TABLE `t_dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '标签名',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(11) NOT NULL COMMENT '排序（升序）',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `cdate` varchar(22) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '更新者',
  `udate` varchar(22) DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `is_valid` int(1) DEFAULT '1' COMMENT '删除标识 0删除 1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='字典表';

/*Data for the table `t_dic` */

insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (1,'一线','1','brand_awareness','品牌知名度',10,'1','2015-02-05 10:30:08',NULL,NULL,'',1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (2,'二线','2','brand_awareness','品牌知名度',10,'1','2015-02-05 10:30:31',NULL,NULL,'',1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (3,'三线','3','brand_awareness','品牌知名度',10,'1','2015-02-05 10:30:48',NULL,NULL,'',1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (4,'高','1','company_awareness','公司知名度',10,'1','2015-02-05 10:30:48','1','2015-02-10 11:32:26','',1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (5,'中','2','company_awareness','公司知名度',20,'1','2015-02-05 10:30:48','1','2015-02-10 11:40:58','',1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (6,'低','3','company_awareness','公司知名度',30,'1','2015-02-05 10:30:48','1','2015-02-10 11:39:17','',1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (7,'品牌商','1','company_type','公司类型',10,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (8,'代运营商','2','company_type','公司类型',10,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (9,'电话营销','1','customer_source','客户来源',10,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (10,'会议营销','2','customer_source','客户来源',10,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (11,'主动联系','3','customer_source','客户来源',10,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (12,'客户推荐','4','customer_source','客户来源',10,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (13,'淘拍档','5','customer_source','客户来源',10,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (14,'其他','6','customer_source','客户来源',10,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (15,'直营','1','sale_mode','销售模式',0,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (16,'分销','2','sale_mode','销售模式',0,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (17,'直营和分销','3','sale_mode','销售模式',0,'1','2015-02-05 10:30:48',NULL,NULL,NULL,1);
insert  into `t_dic`(`id`,`name`,`value`,`type`,`description`,`sort`,`create_by`,`cdate`,`update_by`,`udate`,`remarks`,`is_valid`) values (19,'释放时间','90','free_time','公海释放时间',10,'1','2015-03-04 20:19:42',NULL,NULL,'',1);

/*Table structure for table `t_lossstep` */

DROP TABLE IF EXISTS `t_lossstep`;

CREATE TABLE `t_lossstep` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cdate` varchar(20) DEFAULT NULL,
  `content` varchar(50) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `o_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sduao4alx33fgnpl63hc2wbjh` (`u_id`),
  KEY `FK_2fkn3kbx1pt0gy33c3ilojw4` (`o_id`),
  CONSTRAINT `FK_2fkn3kbx1pt0gy33c3ilojw4` FOREIGN KEY (`o_id`) REFERENCES `t_outflow` (`id`),
  CONSTRAINT `FK_sduao4alx33fgnpl63hc2wbjh` FOREIGN KEY (`u_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_lossstep` */

insert  into `t_lossstep`(`id`,`cdate`,`content`,`u_id`,`o_id`) values (1,'2013-12-15 14:23:17','12121212-0',2,5);
insert  into `t_lossstep`(`id`,`cdate`,`content`,`u_id`,`o_id`) values (3,'2013-12-15 15:09:38','1212',1,5);
insert  into `t_lossstep`(`id`,`cdate`,`content`,`u_id`,`o_id`) values (4,'2013-12-15 15:10:31','1212',1,5);
insert  into `t_lossstep`(`id`,`cdate`,`content`,`u_id`,`o_id`) values (5,'2013-12-15 15:25:35','1212',1,5);

/*Table structure for table `t_outflow` */

DROP TABLE IF EXISTS `t_outflow`;

CREATE TABLE `t_outflow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cdate` varchar(20) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a3e01ykb6vsg7jqy2e5x7iq9b` (`c_id`),
  CONSTRAINT `FK_a3e01ykb6vsg7jqy2e5x7iq9b` FOREIGN KEY (`c_id`) REFERENCES `t_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `t_outflow` */

insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (4,'2013-12-14 22:39:00',1,3);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (5,'2013-12-14 22:39:00',1,4);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (6,'2013-12-14 22:39:00',1,5);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (8,'2013-12-14 22:39:00',0,7);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (9,'2013-12-14 22:39:00',0,8);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (10,'2013-12-14 22:39:00',0,9);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (11,'2013-12-14 22:39:00',0,8);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (12,'2013-12-14 22:39:00',0,7);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (13,'2013-12-14 22:39:00',2,6);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (14,'2013-12-14 22:39:00',2,5);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (15,'2013-12-14 22:39:00',2,4);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (16,'2013-12-14 22:39:00',2,3);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (17,'2013-12-14 22:39:00',2,2);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (18,'2013-12-14 22:39:00',2,2);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (19,'2013-12-14 22:39:00',1,3);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (20,'2013-12-14 22:39:00',1,4);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (21,'2013-12-14 22:39:00',1,5);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (22,'2013-12-14 22:39:00',2,6);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (23,'2013-12-14 22:39:00',2,7);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (24,'2013-12-14 22:39:00',1,8);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (25,'2013-12-14 22:39:00',2,9);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (26,'2013-12-14 22:39:00',1,9);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (27,'2013-12-14 22:39:00',2,8);
insert  into `t_outflow`(`id`,`cdate`,`state`,`c_id`) values (28,'2013-12-14 22:39:00',1,8);

/*Table structure for table `t_payment_item` */

DROP TABLE IF EXISTS `t_payment_item`;

CREATE TABLE `t_payment_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '款项ID',
  `c_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `agreement_id` int(11) DEFAULT NULL COMMENT '合同ID',
  `agreement_no` varchar(100) DEFAULT NULL COMMENT '合同编号',
  `receive_date` varchar(22) DEFAULT NULL COMMENT '收款日期',
  `receive_amount` int(10) DEFAULT NULL COMMENT '收款金额',
  `pay_mode` int(3) DEFAULT NULL COMMENT '支付方式（1支付宝、2财付通、3转帐、4现金）',
  `receiver` varchar(22) DEFAULT NULL COMMENT '收款人',
  `receive_account` varchar(22) DEFAULT NULL COMMENT '收款帐号',
  `payer` varchar(22) DEFAULT NULL COMMENT '付款人',
  `pay_account` varchar(22) DEFAULT NULL COMMENT '付款帐号',
  `status` int(3) DEFAULT NULL COMMENT '状态',
  `receipt` varchar(22) DEFAULT NULL COMMENT '发票',
  `cdate` varchar(22) DEFAULT NULL COMMENT '创建时间',
  `is_valid` int(1) DEFAULT '1' COMMENT '删除标识 0删除 1正常',
  PRIMARY KEY (`item_id`),
  KEY `FK_mu8edmx4ny2t0srpmmirkbi5p` (`agreement_id`),
  CONSTRAINT `FK_mu8edmx4ny2t0srpmmirkbi5p` FOREIGN KEY (`agreement_id`) REFERENCES `t_agreement` (`agreement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_payment_item` */

insert  into `t_payment_item`(`item_id`,`c_id`,`agreement_id`,`agreement_no`,`receive_date`,`receive_amount`,`pay_mode`,`receiver`,`receive_account`,`payer`,`pay_account`,`status`,`receipt`,`cdate`,`is_valid`) values (1,NULL,10,'','2015-03-11',2,3,'111','1','11','888',0,NULL,'2015-03-02 15:13:43',1);
insert  into `t_payment_item`(`item_id`,`c_id`,`agreement_id`,`agreement_no`,`receive_date`,`receive_amount`,`pay_mode`,`receiver`,`receive_account`,`payer`,`pay_account`,`status`,`receipt`,`cdate`,`is_valid`) values (2,NULL,11,'c06c4f2a-fa92-4737-9fa0-3b51a08516c5','2015-03-04',266,2,'qq','111','vv','4444',0,NULL,'2015-03-03 21:31:30',1);
insert  into `t_payment_item`(`item_id`,`c_id`,`agreement_id`,`agreement_no`,`receive_date`,`receive_amount`,`pay_mode`,`receiver`,`receive_account`,`payer`,`pay_account`,`status`,`receipt`,`cdate`,`is_valid`) values (3,NULL,13,'19a4f786dc9148e1ad2ac6e73d2b3300','2015-03-10',3123,3,'4123','412','3214','1234',0,NULL,'2015-03-10 20:25:09',1);

/*Table structure for table `t_person` */

DROP TABLE IF EXISTS `t_person`;

CREATE TABLE `t_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `post` varchar(10) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `descr` varchar(50) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `wang_wang` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `is_valid` int(1) DEFAULT '1' COMMENT '删除标识 0删除 1正常',
  PRIMARY KEY (`id`),
  KEY `FK_d73j34var2633qp1w4lgp67af` (`c_id`),
  CONSTRAINT `FK_d73j34var2633qp1w4lgp67af` FOREIGN KEY (`c_id`) REFERENCES `t_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `t_person` */

insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (1,'张三',0,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (2,'联系人',0,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (3,'1-',1,'12','12','13112334656','1111',3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (4,'张三',0,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (6,'12',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (7,'12',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (8,'12',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (9,'12',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (10,'12',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (11,'12',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (12,'12',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (15,'2',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (16,'23',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (17,'23',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (18,'23',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (19,'23',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (20,'23',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (21,'2',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (22,'3',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (23,'23',1,NULL,NULL,'13112334656',NULL,3,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (24,'张三',0,NULL,NULL,'张三',NULL,7,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (25,'张三',0,NULL,NULL,'张三',NULL,8,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (26,'1',1,'','','1','',4,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (27,'张无忌',1,'','','101','',8,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (28,'李四',1,'','','1','',8,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (29,'12',1,'12','12','12','12',5,NULL,NULL,NULL,1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (30,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (31,'111',1,'111','11','1','222',33,'11','11','1',0);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (32,'333',1,'333','3','33','666',33,'33','33','3',1);
insert  into `t_person`(`id`,`name`,`sex`,`post`,`phone`,`tel`,`descr`,`c_id`,`qq`,`wang_wang`,`email`,`is_valid`) values (33,'测试1',1,'经理','1234523232','12121','1',49,'121212','212','12121',1);

/*Table structure for table `t_plan` */

DROP TABLE IF EXISTS `t_plan`;

CREATE TABLE `t_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cdate` varchar(22) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mm8563niars247hpofhp1ap0w` (`s_id`),
  KEY `FK_eqtsrx2lennh0g1tyxshatmgv` (`u_id`),
  CONSTRAINT `FK_eqtsrx2lennh0g1tyxshatmgv` FOREIGN KEY (`u_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_mm8563niars247hpofhp1ap0w` FOREIGN KEY (`s_id`) REFERENCES `t_salesopp` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `t_plan` */

insert  into `t_plan`(`id`,`cdate`,`content`,`s_id`,`u_id`) values (1,'2013-12-10 22:27:43','121212',2,1);
insert  into `t_plan`(`id`,`cdate`,`content`,`s_id`,`u_id`) values (2,'2013-12-10 22:29:59','测试开发计划',2,1);
insert  into `t_plan`(`id`,`cdate`,`content`,`s_id`,`u_id`) values (3,'2013-12-10 23:00:18','吃饭12',4,1);
insert  into `t_plan`(`id`,`cdate`,`content`,`s_id`,`u_id`) values (4,'2013-12-10 23:10:44','121212',4,1);
insert  into `t_plan`(`id`,`cdate`,`content`,`s_id`,`u_id`) values (6,'2013-12-10 23:18:30','1212啊',2,1);
insert  into `t_plan`(`id`,`cdate`,`content`,`s_id`,`u_id`) values (8,'2013-12-12 21:09:10','121212121212',2,1);
insert  into `t_plan`(`id`,`cdate`,`content`,`s_id`,`u_id`) values (12,'2013-12-12 21:16:59','00000',2,1);
insert  into `t_plan`(`id`,`cdate`,`content`,`s_id`,`u_id`) values (13,'2013-12-12 21:16:59','1212',4,1);

/*Table structure for table `t_priv` */

DROP TABLE IF EXISTS `t_priv`;

CREATE TABLE `t_priv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `icon` varchar(10) DEFAULT NULL,
  `expanded` tinyint(1) DEFAULT NULL,
  `indx` int(11) DEFAULT NULL,
  `config` varchar(255) DEFAULT NULL,
  `p_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7g969v43v2nnhyq0ortgqdm9w` (`p_id`),
  CONSTRAINT `t_priv_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `t_priv` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `t_priv` */

insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (1,'超级父类',NULL,1,1,'',NULL);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (2,'客户管理',NULL,1,2,'',1);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (3,'客户信息管理',NULL,1,1,'customerlist',2);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (4,'合作管理','',1,3,'',1);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (5,'已提交合作',NULL,1,1,'cooperatesubmitedlist',4);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (6,'审批合作',NULL,1,2,'cooperateunauditlist',4);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (7,'已接受合作',NULL,1,3,'cooperateacceptlist',4);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (8,'系统管理',NULL,1,4,'',1);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (9,'类型管理',NULL,1,1,'typelist',8);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (10,'部门管理',NULL,1,2,'depttree',8);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (11,'人员和权限管理',NULL,1,5,'',1);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (12,'用户管理',NULL,1,1,'userlist',11);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (13,'角色管理',NULL,1,2,'rolemanage',11);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (14,'首页','icon170',1,1,'index',1);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (15,'菜单管理',NULL,1,3,'privtreegrid',8);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (16,'公海管理',NULL,1,2,'publiccustomerlist',2);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (17,'审核客户管理',NULL,1,3,'auditcustomerlist',2);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (18,'审核公海客户申请',NULL,1,4,'applylist',2);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (20,'合同管理',NULL,1,5,'',1);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (21,'合同信息管理',NULL,1,1,'agreementlist',20);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (22,'已提交合同列表',NULL,1,2,'myagreementlist',20);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (23,'公海客户申请',NULL,1,5,'myapplylist',2);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (24,'审核延时申请',NULL,1,6,'delaylist',2);
insert  into `t_priv`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (25,'延时申请列表',NULL,1,7,'mydelaylist',2);

/*Table structure for table `t_priv1111` */

DROP TABLE IF EXISTS `t_priv1111`;

CREATE TABLE `t_priv1111` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `icon` varchar(10) DEFAULT NULL,
  `expanded` tinyint(1) DEFAULT NULL,
  `indx` int(11) DEFAULT NULL,
  `config` varchar(255) DEFAULT '{panelClass:''''}',
  `p_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7g969v43v2nnhyq0ortgqdm9w` (`p_id`),
  CONSTRAINT `FK_7g969v43v2nnhyq0ortgqdm9w` FOREIGN KEY (`p_id`) REFERENCES `t_priv1111` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Data for the table `t_priv1111` */

insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (1,'超级父类',NULL,1,1,'{panelClass:\'\'}',NULL);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (2,'营销管理',NULL,1,8,'{panelClass:\'\'}',1);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (3,'销售机会管理',NULL,1,1,'{panelClass:\'salesopplist\'}',2);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (4,'客户开发计划',NULL,1,2,'{panelClass:\'planlist\'}',2);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (5,'客户管理',NULL,1,2,'{panelClass:\'\'}',1);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (6,'客户信息管理',NULL,1,1,'{panelClass:\'customerlist\'}',5);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (7,'客户流失管理',NULL,1,2,'{panelClass:\'outflowlist\'}',5);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (8,'服务管理',NULL,1,3,'{panelClass:\'\'}',1);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (9,'服务创建',NULL,1,1,'{panelClass:\'addservice\'}',8);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (10,'服务分配',NULL,1,2,'{panelClass:\'servicelist\'}',8);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (11,'服务处理',NULL,1,3,'{panelClass:\'myservicelist\'}',8);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (12,'服务归档',NULL,1,4,'{panelClass:\'servicefilelist\'}',8);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (13,'统计报表',NULL,1,4,'{panelClass:\'\'}',1);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (14,'销售机会分析',NULL,1,1,'{panelClass:\'statistic\'}',13);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (16,'客户服务分析',NULL,1,3,'{panelClass:\'servicestatic\'}',13);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (17,'客户流失分析',NULL,1,4,'{panelClass:\'outflowstatistic\'}',13);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (18,'系统管理',NULL,1,5,'{panelClass:\'\'}',1);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (19,'类型管理',NULL,1,1,'{panelClass:\'typelist\'}',18);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (20,'查询产品信息\r\n',NULL,1,2,'{panelClass:\'\'}',18);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (21,'查询库存\r\n',NULL,1,3,'{panelClass:\'\'}',18);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (22,'人员和权限管理',NULL,1,6,'{panelClass:\'\'}',1);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (23,'用户管理\r\n',NULL,1,1,'{panelClass:\'userlist\'}',22);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (24,'角色管理\r\n',NULL,1,2,'{panelClass:\'rolemanage\'}',22);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (25,'部门管理',NULL,1,1,'{panelClass:\'depttree\'}',18);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (26,'首页','icon170',1,1,'{panelClass:\'index\'}',1);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (27,'合作管理','',1,7,'{panelClass:\'\'}',1);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (31,'已提交合作',NULL,1,1,'{panelClass:\'cooperatesubmitedlist\'}',27);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (32,'审批合作',NULL,1,2,'{panelClass:\'cooperateunauditlist\'}',27);
insert  into `t_priv1111`(`id`,`name`,`icon`,`expanded`,`indx`,`config`,`p_id`) values (33,'已接受合作',NULL,1,3,'{panelClass:\'cooperateacceptlist\'}',27);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `count` int(3) DEFAULT '0' COMMENT '客户人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`count`) values (1,'超级管理员',100);
insert  into `t_role`(`id`,`name`,`count`) values (2,'系统管理员',100);
insert  into `t_role`(`id`,`name`,`count`) values (3,'测试11',10);
insert  into `t_role`(`id`,`name`,`count`) values (4,'大区经理',30);
insert  into `t_role`(`id`,`name`,`count`) values (5,'维权顾问',50);
insert  into `t_role`(`id`,`name`,`count`) values (6,'财务',0);
insert  into `t_role`(`id`,`name`,`count`) values (7,'总监',0);
insert  into `t_role`(`id`,`name`,`count`) values (8,'test',111);

/*Table structure for table `t_role_priv` */

DROP TABLE IF EXISTS `t_role_priv`;

CREATE TABLE `t_role_priv` (
  `role_id` int(11) NOT NULL,
  `priv_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`priv_id`),
  KEY `FK_o6xx0n6hoch1myarnsa2jvle6` (`priv_id`),
  KEY `FK_p7s3ftb1ct2qekmt117c2p9da` (`role_id`),
  CONSTRAINT `FK_o6xx0n6hoch1myarnsa2jvle6` FOREIGN KEY (`priv_id`) REFERENCES `t_priv` (`id`),
  CONSTRAINT `FK_p7s3ftb1ct2qekmt117c2p9da` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_priv` */

insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,1);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,1);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,1);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,2);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,2);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,2);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (7,2);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,3);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,3);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,3);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (7,3);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,4);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,4);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,4);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (7,4);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,5);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,5);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,5);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,6);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,6);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (7,6);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,7);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,7);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,7);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,8);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,9);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,10);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,11);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,12);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,13);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,14);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,14);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,14);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (6,14);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,15);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,16);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,16);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,16);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (7,16);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,17);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,17);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (7,17);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,18);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,18);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (7,18);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,20);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,20);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (6,20);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,21);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,21);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (6,21);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,22);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,23);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (1,24);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (4,24);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (7,24);
insert  into `t_role_priv`(`role_id`,`priv_id`) values (5,25);

/*Table structure for table `t_salesopp` */

DROP TABLE IF EXISTS `t_salesopp`;

CREATE TABLE `t_salesopp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer` varchar(12) DEFAULT NULL,
  `createDate` varchar(20) DEFAULT NULL,
  `descr` varchar(50) DEFAULT NULL,
  `resource` varchar(50) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  `success` int(11) DEFAULT NULL,
  `summery` varchar(120) DEFAULT NULL,
  `person` varchar(12) DEFAULT NULL,
  `createUser_id` int(11) DEFAULT NULL,
  `dealUser_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_31t9jmvwvlf6jb4h2hf17474h` (`createUser_id`),
  KEY `FK_ps4tu5p35hpodjd0fbsnwl3gp` (`dealUser_id`),
  CONSTRAINT `FK_31t9jmvwvlf6jb4h2hf17474h` FOREIGN KEY (`createUser_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_ps4tu5p35hpodjd0fbsnwl3gp` FOREIGN KEY (`dealUser_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `t_salesopp` */

insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (1,'环宇纸业','2013-12-12','描述','1来源','联系人',0,100,'概要','联系人',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (2,'1测试测试修改1···',NULL,'描述','来源','联系人',2,10,'概要','联系人',NULL,1);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (3,'测试数据，不要删除',NULL,'测试数据，不要删除，含有联系人','测试数据，不要删除，含有联系人','张三',2,12,'测试数据，不要删除，含有联系人','张三',NULL,1);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (4,'张三丰','2013-12-06 21:59:27','机会描述，好机会哦','华为1','张三',2,90,'概要说明','张三',1,1);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (7,'1','2013-12-06 22:10:05','1','1','1',2,1,'1','1',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (8,'1','2013-12-06 22:11:11','1','1','1',3,1,'1','1',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (9,'1','2013-12-06 22:11:20','1','1','1',2,1,'1','1',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (11,'12121212','2013-12-06 22:18:03','121212121221','12','12',2,1,'12','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (13,'保险','2013-12-06 22:21:39','好的','电话','181818188181',3,1,'推销保险','张三三',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (14,'11212','2013-12-06 22:22:12','1','1','1',0,1,'1','1',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (15,'12','2013-12-06 22:23:07','121212','1212','12',0,1,'1212','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (16,'12','2013-12-06 22:23:14','12','12','12',0,1,'12','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (17,'12','2013-12-06 22:23:20','sd','额','sd',1,1,'sdws','s',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (18,'we','2013-12-06 22:23:25','we','we','we',0,1,'we','we',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (19,'we','2013-12-06 22:23:31','we','we','we',1,1,'we','wwe',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (20,'1','2013-12-06 22:28:22','1212','211','12',1,1,'121','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (21,'12','2013-12-06 22:28:28','12','12','12',0,1,'12','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (22,'12','2013-12-06 22:28:41','12','12','12',2,1,'12','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (23,'42','2013-12-06 22:28:47','34','34','34',1,1,'343','434',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (24,'12','2013-12-06 22:30:25','12','12','12',2,1,'12','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (25,'1','2013-12-06 22:34:53','1','1','1',1,1,'1','1',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (26,'12','2013-12-06 22:38:09','12','11','12',2,1,'12','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (27,'1','2013-12-06 22:38:32','1212','212','12',2,1,'12','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (28,'23','2013-12-06 22:40:21','23','23','23',3,1,'23','23',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (29,'12','2013-12-06 22:40:36','12','12','12',3,1,'12','12',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (30,'23','2013-12-06 22:41:06','23','23','23',3,1,'23','23',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (31,'q','2013-12-12 21:13:40','q','q','q',0,1,'q','q',1,NULL);
insert  into `t_salesopp`(`id`,`customer`,`createDate`,`descr`,`resource`,`tel`,`state`,`success`,`summery`,`person`,`createUser_id`,`dealUser_id`) values (32,'1','2013-12-12 21:17:31','111','1','1',0,1,'1','1',1,NULL);

/*Table structure for table `t_type_dic` */

DROP TABLE IF EXISTS `t_type_dic`;

CREATE TABLE `t_type_dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `value` int(3) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `is_valid` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_type_dic` */

insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (1,'一般服务',1,1,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (2,'重要服务',1,1,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (3,'紧急服务',1,1,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (4,'贵宾服务',11,1,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (5,'一级',1,2,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (6,'二级',1,2,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (7,'信用度一',1,3,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (8,'111',1,2,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (12,'afwwaee ',1,1,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (13,'afwwaee ',1,1,1);
insert  into `t_type_dic`(`id`,`name`,`value`,`type`,`is_valid`) values (14,'sd aweawe a ',1,2,1);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `realName` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `enable` int(11) NOT NULL DEFAULT '1',
  `d_id` int(11) DEFAULT NULL,
  `is_valid` int(1) DEFAULT '1' COMMENT '删除标识 0删除 1正常',
  PRIMARY KEY (`id`),
  KEY `FK_scwclu6yb897n2kb90d8ghhvq` (`d_id`),
  CONSTRAINT `FK_scwclu6yb897n2kb90d8ghhvq` FOREIGN KEY (`d_id`) REFERENCES `t_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (1,'admin','admin','飞扬','okok0800@126.com','to_cxd@163.com',1,2,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (2,'lisi','lisi','李四','11','111',1,2,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (3,'wangwu','1111','王五','111','1117',1,2,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (4,'1212','121212','1212','1212','1212',2,2,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (5,'12','12','12','12','12',2,7,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (6,'12','12','12','21','12',3,2,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (7,'xiaoming','xiaoming','小明','xiaoming@xm.com','12346',1,5,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (8,'xiaoming2','xiaoming2','xiaoming2','xiaoming2@xm.com','1234454545',1,4,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (9,'test1','test1','ts1','111','2222',1,3,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (10,'dq02','dq02','dq02','111','1111',1,3,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (11,'wq01','wq01','wq01','11','111',1,26,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (12,'wq02','wq02','wq02','222','2222',1,27,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (13,'wq03','wq03','wq03','1111','1111',1,13,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (14,'dq01','dq01','dq01','111','111',1,2,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (15,'wq011','123456','wq011','11','111111111',1,26,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (16,'caiwu','123456','caiwu','123456','11111111',1,2,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (17,'wq0111','111111','wq0111','11111','1111111111',1,10,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (18,'wq0222','111111','wq0222','1111','111111111',1,6,1);
insert  into `t_user`(`id`,`username`,`password`,`realName`,`email`,`tel`,`enable`,`d_id`,`is_valid`) values (19,'wq0112','111111','wq0112','1111','111111111',1,27,1);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_4uvv76e86ms8ru0kk9s01d3s2` (`role_id`),
  KEY `FK_kefwen29p9h9ilvry31mgyc94` (`user_id`),
  CONSTRAINT `FK_4uvv76e86ms8ru0kk9s01d3s2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_kefwen29p9h9ilvry31mgyc94` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`user_id`,`role_id`) values (1,1);
insert  into `t_user_role`(`user_id`,`role_id`) values (2,1);
insert  into `t_user_role`(`user_id`,`role_id`) values (1,2);
insert  into `t_user_role`(`user_id`,`role_id`) values (7,2);
insert  into `t_user_role`(`user_id`,`role_id`) values (8,4);
insert  into `t_user_role`(`user_id`,`role_id`) values (10,4);
insert  into `t_user_role`(`user_id`,`role_id`) values (14,4);
insert  into `t_user_role`(`user_id`,`role_id`) values (9,5);
insert  into `t_user_role`(`user_id`,`role_id`) values (11,5);
insert  into `t_user_role`(`user_id`,`role_id`) values (12,5);
insert  into `t_user_role`(`user_id`,`role_id`) values (13,5);
insert  into `t_user_role`(`user_id`,`role_id`) values (15,5);
insert  into `t_user_role`(`user_id`,`role_id`) values (17,5);
insert  into `t_user_role`(`user_id`,`role_id`) values (18,5);
insert  into `t_user_role`(`user_id`,`role_id`) values (19,5);
insert  into `t_user_role`(`user_id`,`role_id`) values (16,6);

/*!50106 set global event_scheduler = 1*/;

/* Event structure for event `out_time_customer` */

/*!50106 DROP EVENT IF EXISTS `out_time_customer`*/;

DELIMITER $$

/*!50106 CREATE EVENT `out_time_customer` ON SCHEDULE EVERY 1 DAY STARTS '2015-03-01 23:59:59' ON COMPLETION NOT PRESERVE ENABLE DO Begin
 call customer_expired();
End */$$
DELIMITER ;

/* Procedure structure for procedure `customer_expired` */

/*!50003 DROP PROCEDURE IF EXISTS  `customer_expired` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `customer_expired`()
    COMMENT '在合作到期时，把客户扔到公海'
Begin
Declare vid int;
Declare vfree_time VARCHAR(22);
declare done int; 
Declare cur1 cursor for select id,free_time from crm_bf.t_customer where is_cooperate=0 and handler is not null and status=1 and is_sign=0 and is_valid=1 and left(free_time,10) = left(now(),10);
Declare continue handler for sqlstate '02000' set done = 1;
Open cur1;
 repeat  
Fetch cur1 into vid,vfree_time;
IF vfree_time <> '' then 
 if left(vfree_time,10) = left(now(),10) then 
		update crm_bf.t_customer set HANDLER = null , free_time ='' ,status=3 where id = vid;
		update crm_bf.t_delay_apply set audit = 2 where audit=0 and c_id= vid;
		#insert into crm_bf.bbb (out_time) values(vfree_time);
 end if;
end IF;
until done=1 
 end repeat;  

Close cur1;
End */$$
DELIMITER ;

/* Procedure structure for procedure `t_insert_table` */

/*!50003 DROP PROCEDURE IF EXISTS  `t_insert_table` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `t_insert_table`()
begin  
    /** 标记是否出错 */  
    declare t_error int default 0;  
    /** 如果出现sql异常，则将t_error设置为1后继续执行后面的操作 */  
    declare continue handler for sqlexception set t_error=1; -- 出错处理  
    /** 显式的开启事务，它开启后，事务会暂时停止自动提交*/  
    -- start transaction;  
    /** 关闭事务的自动提交 */  
    set autocommit = 0;  
    insert into bbb(out_time) values(CURRENT_DATE());  
    /** 标记被改变,表示事务应该回滚 */  
    if t_error=1 then  
        rollback; -- 事务回滚  
    else  
        commit; -- 事务提交  
    end if;  
    -- rollback;  
    -- commit;  
end */$$
DELIMITER ;

/* Procedure structure for procedure `t_sum` */

/*!50003 DROP PROCEDURE IF EXISTS  `t_sum` */;

DELIMITER $$

/*!50003 CREATE PROCEDURE `t_sum`()
Begin
Declare vnum int;
Declare vflag int;
declare done int; 
Declare cur1 cursor for select num,flag from crm_bf.aaa;
Declare continue handler for sqlstate '02000' set done = 1;
Open cur1;
 repeat  
Fetch cur1 into vnum,vflag;
IF vnum>0 then 
update crm_bf.aaa set flag=1 where num = vnum;
end IF;
until done=1 
 end repeat;  

Close cur1;
End */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
