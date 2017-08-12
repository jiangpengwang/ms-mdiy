package com.mingsoft.mdiy.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingsoft.base.entity.BaseEntity;
import com.mingsoft.base.filter.DateValueFilter;
import com.mingsoft.base.filter.DoubleValueFilter;
import com.mingsoft.mdiy.biz.IDictBiz;
import com.mingsoft.mdiy.entity.DictEntity;

import net.mingsoft.base.util.JSONObject;
import net.mingsoft.basic.bean.EUListBean;
import net.mingsoft.basic.util.BasicUtil;
	
/**
 * 字典表管理控制层
 * @author 上官
 * @version 
 * 版本号：100<br/>
 * 创建日期：2017-7-25 10:39:01<br/>
 * 历史修订：<br/>
 */
@Controller
@RequestMapping("/${managerPath}/mdiy/dict")
public class DictAction extends com.mingsoft.basic.action.BaseAction{
	
	/**
	 * 注入字典表业务层
	 */	
	@Autowired
	private IDictBiz dictBiz;
	
	/**
	 * 返回主界面index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse response,HttpServletRequest request){
		return view ("/basic/dict/index");
	}
	
	/**
	 * 查询字典表列表
	 * @param dict 字典表实体
	 * <i>dict参数包含字段信息参考：</i><br/>
	 * dictId 编号<br/>
	 * dictValue 数据值<br/>
	 * dictLabel 标签名<br/>
	 * dictType 类型<br/>
	 * dictDescription 描述<br/>
	 * dictSort 排序（升序）<br/>
	 * dictParentId 父级编号<br/>
	 * dictCreateBy 创建者<br/>
	 * dictCreateDate 创建时间<br/>
	 * dictUpdateBy 更新者<br/>
	 * dictUpdateDate 更新时间<br/>
	 * dictRemarks 备注信息<br/>
	 * dictDel 删除标记<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>[<br/>
	 * { <br/>
	 * dictId: 编号<br/>
	 * dictValue: 数据值<br/>
	 * dictLabel: 标签名<br/>
	 * dictType: 类型<br/>
	 * dictDescription: 描述<br/>
	 * dictSort: 排序（升序）<br/>
	 * dictParentId: 父级编号<br/>
	 * dictCreateBy: 创建者<br/>
	 * dictCreateDate: 创建时间<br/>
	 * dictUpdateBy: 更新者<br/>
	 * dictUpdateDate: 更新时间<br/>
	 * dictRemarks: 备注信息<br/>
	 * dictDel: 删除标记<br/>
	 * }<br/>
	 * ]</dd><br/>	 
	 */
	@RequestMapping("/list")
	@ResponseBody
	public void list(@ModelAttribute DictEntity dict,HttpServletResponse response, HttpServletRequest request,ModelMap model) {
		BasicUtil.startPage();
		List dictList = dictBiz.query(dict);
		this.outJson(response, net.mingsoft.base.util.JSONArray.toJSONString(new EUListBean(dictList,(int)BasicUtil.endPage(dictList).getTotal()),new DoubleValueFilter(),new DateValueFilter()));
	}
	
	/**
	 * 返回编辑界面dict_form
	 */
	@RequestMapping("/form")
	public String form(@ModelAttribute DictEntity dict,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		if(dict.getDictId() >0){
			BaseEntity dictEntity = dictBiz.getEntity(dict.getDictId());			
			model.addAttribute("dictEntity",dictEntity);
		}
		
		return view ("/basic/dict/form");
	}
	
	/**
	 * 获取字典表
	 * @param dict 字典表实体
	 * <i>dict参数包含字段信息参考：</i><br/>
	 * dictId 编号<br/>
	 * dictValue 数据值<br/>
	 * dictLabel 标签名<br/>
	 * dictType 类型<br/>
	 * dictDescription 描述<br/>
	 * dictSort 排序（升序）<br/>
	 * dictParentId 父级编号<br/>
	 * dictCreateBy 创建者<br/>
	 * dictCreateDate 创建时间<br/>
	 * dictUpdateBy 更新者<br/>
	 * dictUpdateDate 更新时间<br/>
	 * dictRemarks 备注信息<br/>
	 * dictDel 删除标记<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * dictId: 编号<br/>
	 * dictValue: 数据值<br/>
	 * dictLabel: 标签名<br/>
	 * dictType: 类型<br/>
	 * dictDescription: 描述<br/>
	 * dictSort: 排序（升序）<br/>
	 * dictParentId: 父级编号<br/>
	 * dictCreateBy: 创建者<br/>
	 * dictCreateDate: 创建时间<br/>
	 * dictUpdateBy: 更新者<br/>
	 * dictUpdateDate: 更新时间<br/>
	 * dictRemarks: 备注信息<br/>
	 * dictDel: 删除标记<br/>
	 * }</dd><br/>
	 */
	@RequestMapping("/get")
	@ResponseBody
	public void get(@ModelAttribute DictEntity dict,HttpServletResponse response, HttpServletRequest request,ModelMap model){
		if(dict.getDictId()<=0) {
			this.outJson(response, null, false, getResString("err.error", this.getResString("dict.id")));
			return;
		}
		DictEntity _dict = (DictEntity)dictBiz.getEntity(dict.getDictId());
		this.outJson(response, _dict);
	}
	
	/**
	 * 保存字典表实体
	 * @param dict 字典表实体
	 * <i>dict参数包含字段信息参考：</i><br/>
	 * dictId 编号<br/>
	 * dictValue 数据值<br/>
	 * dictLabel 标签名<br/>
	 * dictType 类型<br/>
	 * dictDescription 描述<br/>
	 * dictSort 排序（升序）<br/>
	 * dictParentId 父级编号<br/>
	 * dictCreateBy 创建者<br/>
	 * dictCreateDate 创建时间<br/>
	 * dictUpdateBy 更新者<br/>
	 * dictUpdateDate 更新时间<br/>
	 * dictRemarks 备注信息<br/>
	 * dictDel 删除标记<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * dictId: 编号<br/>
	 * dictValue: 数据值<br/>
	 * dictLabel: 标签名<br/>
	 * dictType: 类型<br/>
	 * dictDescription: 描述<br/>
	 * dictSort: 排序（升序）<br/>
	 * dictParentId: 父级编号<br/>
	 * dictCreateBy: 创建者<br/>
	 * dictCreateDate: 创建时间<br/>
	 * dictUpdateBy: 更新者<br/>
	 * dictUpdateDate: 更新时间<br/>
	 * dictRemarks: 备注信息<br/>
	 * dictDel: 删除标记<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/save")
	@ResponseBody
	public void save(@ModelAttribute DictEntity dict, HttpServletResponse response, HttpServletRequest request) {
		dictBiz.saveEntity(dict);
		this.outJson(response, JSONObject.toJSONString(dict));
	}
	
	/**
	 * @param dict 字典表实体
	 * <i>dict参数包含字段信息参考：</i><br/>
	 * dictId:多个dictId直接用逗号隔开,例如dictId=1,2,3,4
	 * 批量删除字典表
	 *            <dt><span class="strong">返回</span></dt><br/>
	 *            <dd>{code:"错误编码",<br/>
	 *            result:"true｜false",<br/>
	 *            resultMsg:"错误信息"<br/>
	 *            }</dd>
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(@RequestBody List<DictEntity> dicts,HttpServletResponse response, HttpServletRequest request) {
		int[] ids = new int[dicts.size()];
		for(int i = 0;i<dicts.size();i++){
			ids[i] = dicts.get(i).getDictId();
		}
		dictBiz.delete(ids);		
		this.outJson(response, true);
	}
	
	/** 
	 * 更新字典表信息字典表
	 * @param dict 字典表实体
	 * <i>dict参数包含字段信息参考：</i><br/>
	 * dictId 编号<br/>
	 * dictValue 数据值<br/>
	 * dictLabel 标签名<br/>
	 * dictType 类型<br/>
	 * dictDescription 描述<br/>
	 * dictSort 排序（升序）<br/>
	 * dictParentId 父级编号<br/>
	 * dictCreateBy 创建者<br/>
	 * dictCreateDate 创建时间<br/>
	 * dictUpdateBy 更新者<br/>
	 * dictUpdateDate 更新时间<br/>
	 * dictRemarks 备注信息<br/>
	 * dictDel 删除标记<br/>
	 * <dt><span class="strong">返回</span></dt><br/>
	 * <dd>{ <br/>
	 * dictId: 编号<br/>
	 * dictValue: 数据值<br/>
	 * dictLabel: 标签名<br/>
	 * dictType: 类型<br/>
	 * dictDescription: 描述<br/>
	 * dictSort: 排序（升序）<br/>
	 * dictParentId: 父级编号<br/>
	 * dictCreateBy: 创建者<br/>
	 * dictCreateDate: 创建时间<br/>
	 * dictUpdateBy: 更新者<br/>
	 * dictUpdateDate: 更新时间<br/>
	 * dictRemarks: 备注信息<br/>
	 * dictDel: 删除标记<br/>
	 * }</dd><br/>
	 */
	@PostMapping("/update")
	@ResponseBody	 
	public void update(@ModelAttribute DictEntity dict, HttpServletResponse response,
			HttpServletRequest request) {
		dictBiz.updateEntity(dict);
		this.outJson(response, JSONObject.toJSONString(dict));
	}
		
}