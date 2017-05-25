package com.mingsoft.mdiy.biz.impl;

import org.springframework.stereotype.Service;

import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.mdiy.biz.IPageBiz;

/**
 * 模块模版业务接口实现类
 * @author 王天培QQ:78750478
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2012-03-15<br/>
 * 历史修订：<br/>
 */
@Service("pageBiz")
public class IPageBizImpl extends BaseBizImpl implements IPageBiz{

	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
