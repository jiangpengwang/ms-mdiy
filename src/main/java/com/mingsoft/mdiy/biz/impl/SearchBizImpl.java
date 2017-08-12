package com.mingsoft.mdiy.biz.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.basic.biz.impl.BasicBizImpl;
import com.mingsoft.mdiy.biz.ISearchBiz;
import com.mingsoft.mdiy.dao.ISearchDao;
import com.mingsoft.mdiy.entity.SearchEntity;
import com.mingsoft.util.PageUtil;

/**
 * 
 * 
 * <p>
 * <b>铭飞CMS-铭飞内容管理系统</b>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2014 - 2015
 * </p>
 * 
 * <p>
 * Company:景德镇铭飞科技有限公司
 * </p>
 * 
 * @author 姓名：张敏
 * 
 * @version 300-001-001
 * 
 * <p>
 * 版权所有 铭飞科技
 * </p>
 *  
 * <p>
 * Comments:搜索业务层实现类，继承BasicBizImpl，实现ISearchBiz
 * </p>
 *  
 * <p>
 * Create Date:2014-9-11
 * </p>
 *
 * <p>
 * Modification history:暂无
 * </p>
 */
@Service("searchBiz")
public class SearchBizImpl extends BasicBizImpl implements ISearchBiz{

	/**
	 * 搜索持久化层
	 */
	@Autowired
	private ISearchDao searchDao;
	
	/**
	 * 获取searchDao
	 */
	@Override
	protected IBaseDao getDao() {
		return searchDao;
	}
	
}
