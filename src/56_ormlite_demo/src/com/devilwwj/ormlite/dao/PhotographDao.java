package com.devilwwj.ormlite.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.devilwwj.ormlite.db.DBHelper;
import com.devilwwj.ormlite.model.Photographer;
import com.devilwwj.ormlite.model.Theme;
import com.j256.ormlite.dao.Dao;

/**
 * 定义数据访问对象，对指定的表进行增删改查操作
 * @author devilwwj
 *
 */
public class PhotographDao {
	
	private Dao<Photographer, Integer> photographerDao;
	private DBHelper dbHelper;
	
	/**
	 * 构造方法
	 * 获得数据库帮助类实例，通过传入Class对象得到相应的Dao
	 * @param context
	 */
	public PhotographDao(Context context) {
		try {
			dbHelper = DBHelper.getHelper(context);
			photographerDao = dbHelper.getDao(Photographer.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加一条记录
	 * @param theme
	 */
	public void add(Photographer photographer) {
		try {
			photographerDao.create(photographer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一条记录
	 * @param theme
	 */
	public void delete(Photographer photographer) {
		try {
			photographerDao.delete(photographer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 更新一条记录
	 * @param theme
	 */
	public void update(Photographer photographer) {
		try {
			photographerDao.update(photographer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询一条记录
	 * @param id
	 * @return
	 */
	public Photographer queryForId(int id) {
		Photographer photographer = null;
		try {
			photographer = photographerDao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photographer;
	}
	
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<Photographer> queryForAll() {
		List<Photographer> photographers = new ArrayList<Photographer>();
		try {
			photographers = photographerDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photographers;
	}

	
	/**
	 * 创建或者更新一条记录
	 * @param photographer
	 */
	public void createOrUpdate(Photographer photographer) {
		try {
			photographerDao.createOrUpdate(photographer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
