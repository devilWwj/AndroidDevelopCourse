package com.devilwwj.ormlite.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.devilwwj.ormlite.db.DBHelper;
import com.devilwwj.ormlite.model.Theme;
import com.j256.ormlite.dao.Dao;

/**
 * 定义数据访问对象，对指定的表进行增删改查操作
 * @author devilwwj
 *
 */
public class ThemeDao {
	
	private Dao<Theme, Integer> themeDao;
	private DBHelper dbHelper;
	
	/**
	 * 构造方法
	 * 获得数据库帮助类实例，通过传入Class对象得到相应的Dao
	 * @param context
	 */
	public ThemeDao(Context context) {
		try {
			dbHelper = DBHelper.getHelper(context);
			themeDao = dbHelper.getDao(Theme.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加一条记录
	 * @param theme
	 */
	public void add(Theme theme) {
		try {
			themeDao.create(theme);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一条记录
	 * @param theme
	 */
	public void delete(Theme theme) {
		try {
			themeDao.delete(theme);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 更新一条记录
	 * @param theme
	 */
	public void update(Theme theme) {
		try {
			themeDao.update(theme);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询一条记录
	 * @param id
	 * @return
	 */
	public Theme queryForId(int id) {
		Theme theme = null;
		try {
			theme = themeDao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theme;
	}
	
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<Theme> queryForAll() {
		List<Theme> themes = new ArrayList<Theme>();
		try {
			themes = themeDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return themes;
	}
	
	
	public void createOrUpdate(Theme theme) {
		try {
			themeDao.createOrUpdate(theme);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
