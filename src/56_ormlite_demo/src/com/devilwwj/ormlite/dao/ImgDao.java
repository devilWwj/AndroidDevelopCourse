package com.devilwwj.ormlite.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.devilwwj.ormlite.db.DBHelper;
import com.devilwwj.ormlite.model.Img;
import com.j256.ormlite.dao.Dao;

/**
 * 定义数据访问对象，对指定的表进行增删改查操作
 * @author devilwwj
 *
 */
public class ImgDao {
	
	private Dao<Img, Integer> imgDao;
	private DBHelper dbHelper;
	
	/**
	 * 构造方法
	 * 获得数据库帮助类实例，通过传入Class对象得到相应的Dao
	 * @param context
	 */
	public ImgDao(Context context) {
		try {
			dbHelper = DBHelper.getHelper(context);
			imgDao = dbHelper.getDao(Img.class);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Dao getImgDao() {
		return imgDao;
	}
	
	/**
	 * 添加一条记录
	 * @param Img
	 */
	public void add(Img img) {
		try {
			imgDao.create(img);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一条记录
	 * @param Img
	 */
	public void delete(Img Img) {
		try {
			imgDao.delete(Img);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 更新一条记录
	 * @param Img
	 */
	public void update(Img img) {
		try {
			imgDao.update(img);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询一条记录
	 * @param id
	 * @return
	 */
	public Img queryForId(int id) {
		Img Img = null;
		try {
			Img = imgDao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Img;
	}
	
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<Img> queryForAll() {
		List<Img> Imgs = new ArrayList<Img>();
		try {
			Imgs = imgDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Imgs;
	}
	
	/**
	 * 创建或更新一条字段
	 * @param mImg
	 */
	public void createOrUpdate(Img mImg) {
		try {
			imgDao.createOrUpdate(mImg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
