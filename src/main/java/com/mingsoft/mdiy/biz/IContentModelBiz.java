package com.mingsoft.mdiy.biz;

import java.util.List;


import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.mdiy.entity.ContentModelEntity;
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
 * @author 姓名 史爱华
 * 
 * @version 300-001-001
 * 
 *          <p>
 *          版权所有 铭飞科技
 *          </p>
 * 
 *          <p>
 *          Comments:表单内容管理业务处理层 || 继承IBasicBiz业务处理层
 *          </p>
 * 
 *          <p>
 *          Create Date:2014-7-14
 *          </p>
 * 
 *          <p>
 *          Modification history:
 *          </p>
 */
public interface IContentModelBiz extends IBaseBiz{
	
	/**
	 * 根据内容模型的表名查找实体
	 * @param cmTableName 表名
	 * @return 内容模型实体
	 */
	public ContentModelEntity getContentModelByTableName(String cmTableName);
	
	
}
