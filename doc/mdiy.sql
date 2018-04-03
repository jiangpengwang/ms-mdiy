ALTER TABLE `mdiy_page` ADD COLUMN `create_date` datetime NULL AFTER `page_key`;
ALTER TABLE `mdiy_page` MODIFY COLUMN `page_app_id` int(11) NOT NULL COMMENT '应用id' AFTER `page_id`;
ALTER TABLE `mdiy_content_model` MODIFY COLUMN `create_by` int(11) NULL AFTER `cm_app_id`;
ALTER TABLE `mdiy_content_model` MODIFY COLUMN `update_by` int(11) NULL AFTER `creaet_date`;
ALTER TABLE `model` ADD COLUMN `model_parent_ids` varchar(300) NULL COMMENT '父级编号集合，从小到大排序' AFTER `model_ismenu`;
update model  set model_modelid = null where model_modelid=0;
ALTER TABLE `model` ADD FOREIGN KEY (`model_modelid`) REFERENCES `model` (`model_id`) ON DELETE CASCADE ON UPDATE NO ACTION;