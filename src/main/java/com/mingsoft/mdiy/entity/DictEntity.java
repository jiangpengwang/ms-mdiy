package com.mingsoft.mdiy.entity;

import com.mingsoft.basic.entity.BaseEntity;

 /**
 * 字典表实体
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：2016-9-8 17:11:19<br/>
 * 历史修订：<br/>
 */
public class DictEntity extends BaseEntity {

	private static final long serialVersionUID = 1473325879430L;
	
	
	/**
	 * 编号
	 */
	private int dictId; 
	/**
	 * 数据值
	 */
	private String dictValue; 
	/**
	 * 标签名
	 */
	private String dictLabel; 
	/**
	 * 类型
	 */
	private String dictType; 
	/**
	 * 描述
	 */
	private String dictDescription; 
	/**
	 * 排序（升序）
	 */
	private Long dictSort; 
	/**
	 * 父级编号
	 */
	private String dictParentId; 
	/**
	 * 创建者
	 */
	private int dictCreateBy; 
	/**
	 * 创建时间
	 */
	private java.util.Date dictCreateDate; 
	/**
	 * 更新者
	 */
	private int dictUpdateBy; 
	/**
	 * 更新时间
	 */
	private java.util.Date dictUpdateDate; 
	/**
	 * 备注信息
	 */
	private String dictRemarks; 
	/**
	 * 删除标记
	 */
	private String dictDel; 
	
	public DictEntity(){}
	public DictEntity(int dictId) {
	this.dictId = dictId;	}
	
	public DictEntity(String dictValue) {
		this.dictValue = dictValue;	
	}
	
	public DictEntity(String dictValue,String dictLabel) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType,String dictDescription) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;		this.dictDescription = dictDescription;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType,String dictDescription,Long dictSort) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;		this.dictDescription = dictDescription;		this.dictSort = dictSort;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType,String dictDescription,Long dictSort,String dictParentId) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;		this.dictDescription = dictDescription;		this.dictSort = dictSort;		this.dictParentId = dictParentId;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType,String dictDescription,Long dictSort,String dictParentId,int dictCreateBy) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;		this.dictDescription = dictDescription;		this.dictSort = dictSort;		this.dictParentId = dictParentId;		this.dictCreateBy = dictCreateBy;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType,String dictDescription,Long dictSort,String dictParentId,int dictCreateBy,java.util.Date dictCreateDate) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;		this.dictDescription = dictDescription;		this.dictSort = dictSort;		this.dictParentId = dictParentId;		this.dictCreateBy = dictCreateBy;		this.dictCreateDate = dictCreateDate;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType,String dictDescription,Long dictSort,String dictParentId,int dictCreateBy,java.util.Date dictCreateDate,int dictUpdateBy) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;		this.dictDescription = dictDescription;		this.dictSort = dictSort;		this.dictParentId = dictParentId;		this.dictCreateBy = dictCreateBy;		this.dictCreateDate = dictCreateDate;		this.dictUpdateBy = dictUpdateBy;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType,String dictDescription,Long dictSort,String dictParentId,int dictCreateBy,java.util.Date dictCreateDate,int dictUpdateBy,java.util.Date dictUpdateDate) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;		this.dictDescription = dictDescription;		this.dictSort = dictSort;		this.dictParentId = dictParentId;		this.dictCreateBy = dictCreateBy;		this.dictCreateDate = dictCreateDate;		this.dictUpdateBy = dictUpdateBy;		this.dictUpdateDate = dictUpdateDate;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType,String dictDescription,Long dictSort,String dictParentId,int dictCreateBy,java.util.Date dictCreateDate,int dictUpdateBy,java.util.Date dictUpdateDate,String dictRemarks) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;		this.dictDescription = dictDescription;		this.dictSort = dictSort;		this.dictParentId = dictParentId;		this.dictCreateBy = dictCreateBy;		this.dictCreateDate = dictCreateDate;		this.dictUpdateBy = dictUpdateBy;		this.dictUpdateDate = dictUpdateDate;		this.dictRemarks = dictRemarks;	
	}
	
	public DictEntity(String dictValue,String dictLabel,String dictType,String dictDescription,Long dictSort,String dictParentId,int dictCreateBy,java.util.Date dictCreateDate,int dictUpdateBy,java.util.Date dictUpdateDate,String dictRemarks,String dictDel) {
		this.dictValue = dictValue;		this.dictLabel = dictLabel;		this.dictType = dictType;		this.dictDescription = dictDescription;		this.dictSort = dictSort;		this.dictParentId = dictParentId;		this.dictCreateBy = dictCreateBy;		this.dictCreateDate = dictCreateDate;		this.dictUpdateBy = dictUpdateBy;		this.dictUpdateDate = dictUpdateDate;		this.dictRemarks = dictRemarks;		this.dictDel = dictDel;	
	}
	
	
	/**
	 * 设置编号
	 */
	public void setDictId(int dictId) {
		this.dictId = dictId;
	}

	/**
	 * 获取编号
	 */
	public int getDictId() {
		return this.dictId;
	}
	
	/**
	 * 设置数据值
	 */
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	/**
	 * 获取数据值
	 */
	public String getDictValue() {
		return this.dictValue;
	}
	
	/**
	 * 设置标签名
	 */
	public void setDictLabel(String dictLabel) {
		this.dictLabel = dictLabel;
	}

	/**
	 * 获取标签名
	 */
	public String getDictLabel() {
		return this.dictLabel;
	}
	
	/**
	 * 设置类型
	 */
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	/**
	 * 获取类型
	 */
	public String getDictType() {
		return this.dictType;
	}
	
	/**
	 * 设置描述
	 */
	public void setDictDescription(String dictDescription) {
		this.dictDescription = dictDescription;
	}

	/**
	 * 获取描述
	 */
	public String getDictDescription() {
		return this.dictDescription;
	}
	
	/**
	 * 设置排序（升序）
	 */
	public void setDictSort(Long dictSort) {
		this.dictSort = dictSort;
	}

	/**
	 * 获取排序（升序）
	 */
	public Long getDictSort() {
		return this.dictSort;
	}
	
	/**
	 * 设置父级编号
	 */
	public void setDictParentId(String dictParentId) {
		this.dictParentId = dictParentId;
	}

	/**
	 * 获取父级编号
	 */
	public String getDictParentId() {
		return this.dictParentId;
	}
	
	/**
	 * 设置创建者
	 */
	public void setDictCreateBy(int dictCreateBy) {
		this.dictCreateBy = dictCreateBy;
	}

	/**
	 * 获取创建者
	 */
	public int getDictCreateBy() {
		return this.dictCreateBy;
	}
	
	/**
	 * 设置创建时间
	 */
	public void setDictCreateDate(java.util.Date dictCreateDate) {
		this.dictCreateDate = dictCreateDate;
	}

	/**
	 * 获取创建时间
	 */
	public java.util.Date getDictCreateDate() {
		return this.dictCreateDate;
	}
	
	/**
	 * 设置更新者
	 */
	public void setDictUpdateBy(int dictUpdateBy) {
		this.dictUpdateBy = dictUpdateBy;
	}

	/**
	 * 获取更新者
	 */
	public int getDictUpdateBy() {
		return this.dictUpdateBy;
	}
	
	/**
	 * 设置更新时间
	 */
	public void setDictUpdateDate(java.util.Date dictUpdateDate) {
		this.dictUpdateDate = dictUpdateDate;
	}

	/**
	 * 获取更新时间
	 */
	public java.util.Date getDictUpdateDate() {
		return this.dictUpdateDate;
	}
	
	/**
	 * 设置备注信息
	 */
	public void setDictRemarks(String dictRemarks) {
		this.dictRemarks = dictRemarks;
	}

	/**
	 * 获取备注信息
	 */
	public String getDictRemarks() {
		return this.dictRemarks;
	}
	
	/**
	 * 设置删除标记
	 */
	public void setDictDel(String dictDel) {
		this.dictDel = dictDel;
	}

	/**
	 * 获取删除标记
	 */
	public String getDictDel() {
		return this.dictDel;
	}
	
}