package com.devilwwj.ormlite.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.devilwwj.ormlite.db.DBHelper;
import com.devilwwj.ormlite.model.PackageInfo;
import com.devilwwj.ormlite.model.Photographer;
import com.j256.ormlite.dao.Dao;

/**
 * 定义数据访问对象，对指定的表进行增删改查操作
 * @author devilwwj
 *
 */
public class PackageDao {
	
	private Dao<PackageInfo, Integer> PackageInfoDao;
	private DBHelper dbHelper;
	
	/**
	 * 构造方法
	 * 获得数据库帮助类实例，通过传入Class对象得到相应的Dao
	 * @param context
	 */
	public PackageDao(Context context) {
		try {
			dbHelper = DBHelper.getHelper(context);
			PackageInfoDao = dbHelper.getDao(PackageInfo.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加一条记录
	 * @param PackageInfo
	 */
	public void add(PackageInfo PackageInfo) {
		try {
			PackageInfoDao.create(PackageInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一条记录
	 * @param PackageInfo
	 */
	public void delete(PackageInfo PackageInfo) {
		try {
			PackageInfoDao.delete(PackageInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 更新一条记录
	 * @param PackageInfo
	 */
	public void update(PackageInfo PackageInfo) {
		try {
			PackageInfoDao.update(PackageInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询一条记录
	 * @param id
	 * @return
	 */
	public PackageInfo queryForId(int id) {
		PackageInfo PackageInfo = null;
		try {
			PackageInfo = PackageInfoDao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return PackageInfo;
	}
	
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<PackageInfo> queryForAll() {
		List<PackageInfo> PackageInfos = new ArrayList<PackageInfo>();
		try {
			PackageInfos = PackageInfoDao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return PackageInfos;
	}
	
	/**
	 * 创建或者更新一条记录
	 * @param photographer
	 */
	public void createOrUpdate(PackageInfo packageInfo) {
		try {
			PackageInfoDao.createOrUpdate(packageInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
