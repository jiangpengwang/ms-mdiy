package com.mingsoft.mdiy.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.mdiy.dao.IContentModelDao;
import com.mingsoft.mdiy.dao.IContentModelFieldDao;
import com.mingsoft.mdiy.entity.ContentModelEntity;
import com.mingsoft.mdiy.biz.IContentModelBiz;
import com.mingsoft.util.PageUtil;
@Service("contentModelBiz")
public class ContentModelBizImpl extends BaseBizImpl implements IContentModelBiz{
	
	/**
	 * 内容模型管理业务层
	 */
	private IContentModelDao contentModelDao;
	
	/**
	 * 注入模型字段持久层接口
	 */
	private IContentModelFieldDao fieldDao;
	
	/**
	 * 获取内容模型业务层
	 * @return 内容模型业务层
	 */
	public IContentModelDao getContentModelDao() {
		return contentModelDao;
	}

	/**
	 * 设置内容模型业务层
	 * @param contentModelDao 内容模型业务层
	 */
	@Autowired
	public void setContentModelDao(IContentModelDao contentModelDao) {
		this.contentModelDao = contentModelDao;
	}

	
	/**
	 * 根据内容模型的表名查找实体
	 */
	@Override
	public ContentModelEntity getContentModelByTableName(String cmTableName) {
		// TODO Auto-generated method stub
		return (ContentModelEntity) contentModelDao.getContentModelByTableName(cmTableName);
	}
	
	/**
	 * 获取内容模型的dao
	 */
	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return contentModelDao;
	}
	
	/**
	 * 根据管理员id查找内容模型实体
	 * @param cmManagerId
	 * @return
	 */
	@Override
	public List<BaseEntity> queryByManagerId(int cmManagerId) {
		// TODO Auto-generated method stub
		return contentModelDao.queryByManagerId(cmManagerId);
	}
	
}
