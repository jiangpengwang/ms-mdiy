package com.mingsoft.mdiy.dao;

import java.util.List;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.entity.DictEntity;

/**
 * 字典表持久层
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：2016-9-8 17:11:19<br/>
 * 历史修订：<br/>
 */
public interface IDictDao extends IBaseDao {
	/**
	 * 查询
	 * @param dict 字典表
	 * @return
	 */
	List query(DictEntity dict);
}