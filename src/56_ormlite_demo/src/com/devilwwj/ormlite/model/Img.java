package com.devilwwj.ormlite.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 图片
 * 
 * @author infzm
 * 
 */
@DatabaseTable
public class Img {
	@DatabaseField(id = true)
	public String id; // 主题id
	@DatabaseField
	public int isCover;
	@DatabaseField
	public String themeId;
	@DatabaseField
	public String photographer;
	@DatabaseField
	public String imgUrl;
	@DatabaseField
	public boolean isCheck; // 是否被选中
	@DatabaseField(foreign = true, foreignAutoRefresh = true)
	public Theme theme;
	
	
	@Override
	public String toString() {
		return "Img [id=" + id + ", isCover=" + isCover + ", themeId="
				+ themeId + ", photographer=" + photographer + ", imgUrl="
				+ imgUrl + "]";
	}
	

	
	
	
}
