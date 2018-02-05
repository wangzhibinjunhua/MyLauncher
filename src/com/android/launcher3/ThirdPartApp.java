package com.android.launcher3;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;

import com.android.launcher3.R;
import com.eastaeon.launcher3.activity.ThemeChooseActivity;
import com.eastaeon.launcher3.domain.MyTheme;
import com.mediatek.launcher3.ext.LauncherLog;

public class ThirdPartApp extends AppClassName {

    public ThirdPartApp() {
        super();
    }
	
	public static boolean checkValue(String apk_class_name,String  character ) {
        return null != apk_class_name && !("".equals(apk_class_name)) && apk_class_name.equals(character);
    }
	
	public static boolean isThirdPartApp(String class_name) {
		LauncherLog.e("wenTheme", "class_name = " + class_name);
		if(checkValue(class_name,CONTACTS)){
			return false;
		}else if(checkValue(class_name,CALCULATOR)){
			return false;
		}else if(checkValue(class_name,CALENDAR)){
			return false;
		}else if(checkValue(class_name,PHONE)){
			return false;
		}else if(checkValue(class_name,DESKCLOCK)){
			return false;
		}else if(checkValue(class_name,SOUNDRECORDER)){
			return false;
		}else if(checkValue(class_name,GALLERY)){
			return false;
		}else if(checkValue(class_name,CAMERA)){
			return false;
		}else if(checkValue(class_name,MMS)){
			return false;
		}else if(checkValue(class_name,MUSIC)){
			return false;
		}else if(checkValue(class_name,SETTINGS)){
			return false;
		}else if(checkValue(class_name,VIDEOPLAYER)){
			return false;
		}else if(checkValue(class_name,FILEMANAGER)){
			return false;
		}else if(checkValue(class_name,FMRADIO)){
			return false;
		}else if(checkValue(class_name,USER_MANUAL)){
			return false;
		}else if(checkValue(class_name,QCARE)){
			return false;
		}else if(checkValue(class_name,QPAY)){
			return false;
		}else if(checkValue(class_name,STK)){
			return false;
		}else if(checkValue(class_name,DATATRANSFER)){
			return false;
		}
		return true;
	}
}