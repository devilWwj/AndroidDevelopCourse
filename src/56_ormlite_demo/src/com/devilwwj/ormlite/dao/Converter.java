package com.devilwwj.ormlite.dao;

import java.sql.SQLException;
import java.util.concurrent.Callable;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.devilwwj.ormlite.model.Img;
import com.devilwwj.ormlite.model.PackageInfo;
import com.devilwwj.ormlite.model.Photographer;
import com.devilwwj.ormlite.model.Theme;
import com.j256.ormlite.dao.Dao;

public class Converter {
	
	/**
	 * 转化json对象为数据库对象
	 * @param context
	 * @param theme
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static Theme ConvertTheme(Context context, final JSONObject theme) throws SQLException, Exception {
		JSONObject photographerObj = theme.getJSONObject("photographer");
		JSONObject packageObj = theme.getJSONObject("package");
		
		ThemeDao themeDao = new ThemeDao(context);
		
		PhotographDao photographDao = new PhotographDao(context);
		// 根据id查询摄影师
		Photographer mPhotographer = photographDao.queryForId(theme.optInt("photographerId"));
		if (mPhotographer == null)
			mPhotographer = new Photographer();

		mPhotographer.id = theme.optString("photographerId");
		mPhotographer.name = photographerObj.optString("nickname");
		mPhotographer.serviceArea = photographerObj.optString("serviceArea");
		mPhotographer.avatar = photographerObj.optString("avatar");

		// 这里创建或更新摄影师
		photographDao.createOrUpdate(mPhotographer);
		
		PackageDao packageDao = new PackageDao(context);
		
		// 根据id查询套餐
		PackageInfo mPackage = packageDao.queryForId(packageObj.optInt("id"));
		if (mPackage == null) 
			mPackage = new PackageInfo();
		
		mPackage.id = packageObj.optInt("id");
		mPackage.name = packageObj.optString("title");
		mPackage.cost = packageObj.optInt("cost");
		mPackage.detail = packageObj.optString("detail");
		
		// 这里创建或更新套餐
		packageDao.createOrUpdate(mPackage);

		// 根据id查询作品
		Theme mThemeTmp = themeDao.queryForId(
				theme.optInt("id"));
		if (mThemeTmp == null)
			mThemeTmp = new Theme(); 
		
		final Theme mTheme = mThemeTmp;
		

		mTheme.id = theme.optString("id");
		mTheme.title = theme.optString("title");
		// mTheme.coverId = theme.optString("place");
		// mTheme.coverUrl = theme.optString("coverUrl");
		mTheme.photographerId = theme.optString("photographerId");
		mTheme.detail = theme.optString("detail");
		// mTheme.cost = theme.optDouble("cost");
		// mTheme.recordTime = theme.optString("recordTime");
		mTheme.favStatus = theme.optInt("isFav");
		mTheme.photoCount = theme.optInt("photoCount");
		mTheme.tags = theme.optString("tags");
		mTheme.packageId = theme.optString("packageId");
		
		// 同步更新
		mTheme.photographer = mPhotographer;
		mTheme.mPackage = mPackage;

		// 创建或更新主题
		themeDao.createOrUpdate(mTheme);
		
		final ImgDao mDao = new ImgDao(context);
		Dao<Img, Integer> imgDao = mDao.getImgDao();
		

		// 执行批处理操作
		imgDao.callBatchTasks(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				JSONArray imgs = theme.getJSONArray("photos");
				for (int i = 0; i < imgs.length(); i++) {
					JSONObject jsonObject = imgs.getJSONObject(i);
					Img mImg = mDao.queryForId(jsonObject.optInt("id"));
					if (mImg == null)
						mImg = new Img();

					mImg.id = jsonObject.optString("id");
					mImg.isCover = jsonObject.optInt("isCover");
					mImg.imgUrl = jsonObject.optString("url");
					mImg.theme = mTheme;

					mDao.createOrUpdate(mImg);
				}
				return null;
			}
		});
		return mTheme;
	}
}
