package com.mingsoft.mdiy.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.basic.entity.ManagerEntity;
import com.mingsoft.mdiy.biz.IContentModelBiz;
import com.mingsoft.mdiy.biz.IContentModelFieldBiz;
import com.mingsoft.basic.constant.e.SessionConstEnum;
import com.mingsoft.mdiy.entity.ContentModelEntity;
import com.mingsoft.util.StringUtil;

import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
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
 * Comments:内容模型控制层，继承BasicAction
 * </p>
 *  
 * <p>
 * Create Date:2014-9-12
 * </p>
 *
 * <p>
 * Modification history:
 * </p>
 */
@Controller
@RequestMapping("/${managerPath}/mdiy/contentModel/")
public class ContentModelAction extends BaseAction{
	
	/**
	 * 自定义表前缀
	 */
	private static final String TABLE_NAME_PREFIX = "mdiy_";
	/**
	 * 表名分隔符
	 */
	private static final String TABLE_NAME_SPLIT= "_";
	/**
	 * 表单管理业务层
	 */
	@Autowired
	private IContentModelBiz contentModelBiz;
	
	/**
	 * 注入字段业务层
	 */
	@Autowired
	private IContentModelFieldBiz fieldBiz;
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/mdiy/content_model/index");
	}
	/**
	 * 返回编辑界面contentModel_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute ContentModelEntity contentModel,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(contentModel.getCmId() > 0){
			BaseEntity contentModelEntity = contentModelBiz.getEntity(contentModel.getCmId());			
			model.addAttribute("contentModelEntity",contentModelEntity);
		}
		
		return view ("/mdiy/content_model/form");
	}
	/**
	 * 查询自定义模型表列表
	 * @param contentModel 自定义模型表实体
	 * <i>contentModel参数包含字段信息参考：</i><br/>
	 * cmId 自增长id<br/>
	 * cmTipsname 表单提示文字<br/>
	 * cmTablename 表单名称<br/>
	 * cmManagerid 表单管理员ID<br/>
	 * cmModelId 模块编号<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * cmId: 自增长id<br/>
	 * cmTipsname: 表单提示文字<br/>
	 * cmTablename: 表单名称<br/>
	 * cmManagerid: 表单管理员ID<br/>
	 * cmModelId: 模块编号<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute ContentModelEntity contentModel,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		// 获取管理实体
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConstEnum.MANAGER_SESSION);
		contentModel.setCmManagerId(managerSession.getManagerId());
		List contentModelList = contentModelBiz.query(contentModel);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(contentModelList,(int)BasicUtil.endPage(contentModelList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	/**
	 * 获取自定义模型表
	 * @param contentModel 自定义模型表实体
	 * <i>contentModel参数包含字段信息参考：</i><br/>
	 * cmId 自增长id<br/>
	 * cmTipsname 表单提示文字<br/>
	 * cmTablename 表单名称<br/>
	 * cmManagerid 表单管理员ID<br/>
	 * cmModelId 模块编号<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * cmId: 自增长id<br/>
	 * cmTipsname: 表单提示文字<br/>
	 * cmTablename: 表单名称<br/>
	 * cmManagerid: 表单管理员ID<br/>
	 * cmModelId: 模块编号<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute ContentModelEntity contentModel,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(contentModel.getCmId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("cm.id")));
			return;
		}
		ContentModelEntity _contentModel = (ContentModelEntity)contentModelBiz.getEntity(contentModel.getCmId());
		this.outJson(response, JSONObject.toJSON(_contentModel));
	}
	/**
	 * @param contentModel 自定义模型表实体
	 * <i>contentModel参数包含字段信息参考：</i><br/>
	 * cmId:多个cmId直接用逗号隔开,例如cmId=1,2,3,4
	 * 批量删除自定义模型表
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<ContentModelEntity> contentModels,HttpServletResponse response, HttpServletRequest request) {
		for(int i = 0;i<contentModels.size();i++){
			if(contentModels.size()>0){
				if(contentModels.get(i) != null){
					ContentModelEntity cme =  (ContentModelEntity)contentModelBiz.getEntity(contentModels.get(i).getCmId());
					if(cme!=null){
						contentModelBiz.dropTable(cme.getCmTableName());
					}
					contentModelBiz.deleteEntity(contentModels.get(i).getCmId());
				}
			}
		}
		this.outJson(response, true);
	}
	/**
	 * 保存内容模型实体
	 * @param contentModel
	 * @param response 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute ContentModelEntity contentModel,HttpServletRequest request, HttpServletResponse response){
		// 保存前判断数据是否合法
		if(!StringUtil.checkLength(contentModel.getCmTipsName(), 1,30)){
			this.outJson(response, null, false,getResString("err.length",this.getResString("content.model.tips.name"),"1","30"));
			return;
		}
		if (!StringUtil.checkLength(contentModel.getCmTableName(), 1,20)) {
			this.outJson(response, null, false,getResString("err.length",this.getResString("content.model.table.name"),"1","20"));
			return;
		}
		
		// 获取当前管理员实体
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConstEnum.MANAGER_SESSION);
		//获取当前管理员Id
		int managerId = managerSession.getManagerId();
		if (contentModelBiz.getContentModelByTableName(TABLE_NAME_PREFIX+contentModel.getCmTableName()+TABLE_NAME_SPLIT+managerId)!=null) {
			this.outJson(response, null, false,getResString("err.exist",this.getResString("content.model")));
			return;
		}
		
		
		contentModel.setCmManagerId(managerId);
		// 新增表名为"cms_"+用户填写的表名+"_"+站点id
		contentModel.setCmTableName(TABLE_NAME_PREFIX+contentModel.getCmTableName()+TABLE_NAME_SPLIT+managerId);
		// 新增表
		contentModelBiz.createTable(contentModel.getCmTableName(),null);
		contentModelBiz.saveEntity(contentModel);
		int cmId= contentModelBiz.getContentModelByTableName(contentModel.getCmTableName()).getCmId();
		
		this.outJson(response, null, true,String.valueOf(cmId));
	}
	
	/**
	 * 更新内容模型实体
	 * @param contentModel 内容模型实体
	 * @param request
	 * @param response
	 */
	@RequestMapping("/update")
	@ResponseBody
	public void update(@ModelAttribute ContentModelEntity contentModel,HttpServletRequest request, HttpServletResponse response){
		// 保存前判断数据是否合法
		if(!StringUtil.checkLength(contentModel.getCmTipsName(), 1,30)){
			this.outJson(response, null, false,getResString("err.length",this.getResString("content.model.tips.name"),"1","30"));
			return;
		}
		// 获取当前管理员实体
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConstEnum.MANAGER_SESSION);
		//获取当前管理员Id
		int managerId = managerSession.getManagerId();
		contentModel.setCmManagerId(managerId);
		contentModelBiz.updateEntity(contentModel);
		this.outJson(response, null, true,null);
	}
	
	/**
	 * 判断自定义模型表名是否重复
	 * @param cmTableName 表明
	 * @param request
	 * @return true:存在重复,false:不存在重复
	 */
	@RequestMapping("/{cmTableName}/checkcmTableNameExist")
	@ResponseBody
	public boolean checkcmTableNameExist(@PathVariable String cmTableName, HttpServletRequest request) {
		// 获取当前管理员实体
		ManagerEntity managerSession = (ManagerEntity) getSession(request, SessionConstEnum.MANAGER_SESSION);
		//获取当前管理员Id
		int managerId = managerSession.getManagerId();
		cmTableName =TABLE_NAME_PREFIX+cmTableName+TABLE_NAME_SPLIT+managerId;
		// 判断表名是否重复
		if(contentModelBiz.getContentModelByTableName(cmTableName)!=null){
			return true;
		}else{
			return false;
		}
		
	}
}
