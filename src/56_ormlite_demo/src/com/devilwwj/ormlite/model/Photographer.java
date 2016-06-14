package com.devilwwj.ormlite.model;

import java.io.Serializable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 摄影师
 * 
 * @author infzm
 * 
 */
@DatabaseTable
public class Photographer implements Serializable {

	@DatabaseField(id = true, canBeNull = false)
	public String id;
	@DatabaseField(defaultValue = "")
	public String name; // 昵称
	@DatabaseField
	public String avatar; // 头像
	@DatabaseField
	public String serviceArea; // 服务范围
	@DatabaseField
	public String areaCode; // 定位代号
	@DatabaseField
	public String description; // 个人介绍
	@DatabaseField
	public String experiense; // 专业经历
	@DatabaseField
	public String detail; // 拍摄环境/花絮
	@DatabaseField
	public int star; // 星级
	@DatabaseField
	public int packageCount; // 套餐数
	@DatabaseField
	public int themeCount; // 作品数
	@DatabaseField
	public int orderCount; // 订单数
	@DatabaseField
	public int commentCount; // 评论数
	
	
	// 一对多关联映射
	@ForeignCollectionField(eager = true)
	public ForeignCollection<Theme> themes;
	
	@ForeignCollectionField(eager = true) 
	public ForeignCollection<PackageInfo> packages;
//	
//	@ForeignCollectionField(eager = true)
//	public ForeignCollection<Comment> comments;
//	
	


	
	
}
