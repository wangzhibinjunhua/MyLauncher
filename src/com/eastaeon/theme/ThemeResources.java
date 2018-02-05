package com.eastaeon.theme;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class ThemeResources {
	private static final String TAG = "ThemeResources";
	private Context mContext;
	
	public ThemeResources(Context context) {
		super();
		mContext = context;
	}	

	
	private int getThemeResourcesId(String name, String type){	
			return mContext.getResources().getIdentifier(name, type, mContext.getPackageName());
	}
	
	protected Drawable getThemeIcon(String name){
		Drawable themeDrawable = null;
		int themeResourcesId = getThemeResourcesId(name, "drawable");
		if(themeResourcesId == 0){
			return themeDrawable;
		}
		themeDrawable = mContext.getResources().getDrawable(themeResourcesId);

		 
		return themeDrawable;
	}
	
}
