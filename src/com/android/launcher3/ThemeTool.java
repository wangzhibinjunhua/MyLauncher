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

/**
 * theme tool class 
 * @author zhangle
 *
 */
public class ThemeTool extends AppClassName {

    
    private SharedPreferences sp = null;

    public ThemeTool() {
        super();
    }

    
    /**
     * 
     *@authod zhangle
     * @param apk_class_name
     * @param character
     * @return
     */
    public boolean checkValue(String apk_class_name,String  character ) {
        return null != apk_class_name && !("".equals(apk_class_name)) && apk_class_name.equals(character);
    }
    
    /**
     * @author zhangle
     * @param info
     */
    public  ShortcutInfo changeShortcutForTheme(ShortcutInfo info,Context mContext) {
        sp = mContext.getSharedPreferences(ThemeChooseActivity.THEMES_DATA, 2 );
        final int type = ThemeChooseActivity.THEMES_DATA_DEFAULT;   //sp.getInt(ThemeChooseActivity.THEME_TYPE, ThemeChooseActivity.THEMES_DATA_DEFAULT);
        
        final String class_name = info.getClassName();
        Log.d("zhangle","changeShortcutForTheme class_name=" + class_name);
        if(null == class_name || "".equals(class_name) ){
            return info;
        }
        
        switch (type) {
		
            case ThemeChooseActivity.THEMES_TYPE_1:
                if(checkValue(class_name,CONTACTS)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_contacts_1);
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_browser_1);
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_calculator_1);
                    break;
                }else if(checkValue(class_name,CALENDAR)){
				 Log.d("liminchek","changeShortcutForTheme class_name=" + class_name);
				    Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_launcher_calendar_1);
				    Bitmap bitmap = Utilities.createCalendarIconBitmap(drawable, mContext);
				    Drawable newdrawable =new BitmapDrawable(bitmap);
                    setShortIcon(info, newdrawable);
                    break;
                }else if(checkValue(class_name,PHONE)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_phone_1);
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_alarmclock_1);
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_email_1);
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_record_1);
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_gallery_1);
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_camera_1);
                    break;
                }else if(checkValue(class_name,MMS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_smsmms_1);
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_music_1);
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_settings_1);
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_videoeditor_1);
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_download_1);
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_filemanager_1);
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_fm_1);
                    break;
                }
            case ThemeChooseActivity.THEMES_TYPE_2:
                if(checkValue(class_name,CONTACTS)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_contacts_2);
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_browser_2);
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_calculator_2);
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                    	 Log.d("liminchek","changeShortcutForTheme class_name=" + class_name);
				    Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_launcher_calendar_2);
				    Bitmap bitmap = Utilities.createCalendarIconBitmap(drawable, mContext);
				    Drawable newdrawable =new BitmapDrawable(bitmap);
                    setShortIcon(info, newdrawable);
                    
                    break;
                }else if(checkValue(class_name,PHONE)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_phone_2);
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_alarmclock_2);
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_email_2);
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_record_2);
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_gallery_2);
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_camera_2);
                    break;
                }else if(checkValue(class_name,MMS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_smsmms_2);
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_music_2);
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_settings_2);
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_videoeditor_2);
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_download_2);
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_filemanager_2);
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_fm_2);
                    break;
                }
            case ThemeChooseActivity.THEMES_TYPE_3:
                if(checkValue(class_name,CONTACTS)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_contacts_3);
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_browser_3);
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_calculator_3);
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                   Log.d("liminchek","changeShortcutForTheme class_name=" + class_name);
				    Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_launcher_calendar_3);
				    Bitmap bitmap = Utilities.createCalendarIconBitmap(drawable, mContext);
				    Drawable newdrawable =new BitmapDrawable(bitmap);
                    setShortIcon(info, newdrawable);
                 
                    break;
                }else if(checkValue(class_name,PHONE)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_phone_3);
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_alarmclock_3);
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_email_3);
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_record_3);
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_gallery_3);
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_camera_3);
                    break;
                }else if(checkValue(class_name,MMS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_smsmms_3);
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_music_3);
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_settings_3);
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_videoeditor_3);
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_download_3);
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_filemanager_3);
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_fm_3);
                    break;
                }
            case ThemeChooseActivity.THEMES_TYPE_4:
                /*if(checkValue(class_name,CONTACTS)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_contacts_0);
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_browser_0);
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_calculator_0);
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                   	Log.d("liminchek","changeShortcutForTheme class_name=" + class_name);
				    Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_launcher_calendar_0);
				    Bitmap bitmap = Utilities.createCalendarIconBitmap(drawable, mContext);
				    Drawable newdrawable =new BitmapDrawable(bitmap);
                    setShortIcon(info, newdrawable);
                    break;
                }else if(checkValue(class_name,PHONE)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_phone_0);
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    setShortIcon(info, mContext,R.drawable.ic_launcher_alarmclock_0);
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_email_0);
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_record_0);
                    break;
                }else if(checkValue(class_name,EMAIL2)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_gmail_0);
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_gallery_0);
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_camera_0);
                    break;
                }else if(checkValue(class_name,MMS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_smsmms_0);
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_music_0);
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_settings_0);
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_videoeditor_0);
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_download_0);
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_filemanager_0);
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_fm_0);
                    break;
                }else if(checkValue(class_name,BLMANAGER)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_ble_0);
                    break;
                }else if(checkValue(class_name,DEVELOPMENT)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_dev_0);
                    break;
                }else if(checkValue(class_name,HOTKNOT)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_hotknot_0);
                    break;
                }else if(checkValue(class_name,MEDIATEK)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_mediatek_0);
                    break;
                }else if(checkValue(class_name,SEARCH)){
                    setShortIcon(info, mContext, R.drawable.ic_launcher_search_0);
                    break;
                }*/
            case ThemeChooseActivity.THEMES_DATA_SYSTEM:
			
			 /*if(checkValue(class_name,CALENDAR)){
				 Log.d("liminchek","changeShortcutForTheme class_name=" + class_name);
				    Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_launcher_calendar_1);
				    Bitmap bitmap = Utilities.createCalendarIconBitmap(drawable, mContext);
				    Drawable newdrawable =new BitmapDrawable(bitmap);
                    setShortIcon(info, newdrawable);
                    break;
                }*/
             

            default:
                break;
        }
        
        return info;
    }
    /**
     *@authod zhangle
     * @param info
     * @param resId
     * @return
     */
    public ActivityInfo setAppIcon(ActivityInfo  info,int resId) {
        info.icon = resId;
        // Log.d("zhangle","setAppIcon --- info.name:" + info.name + ",info.icon:" + info.icon);
        return info;
    }
    
    
    /**
     * 
     *@authod zhangle
     * @param info
     * @param mContext
     * @param resId
     */
    public void setShortIcon(ShortcutInfo info, Context mContext,int resId) {
        // Log.d("zhangle","setShortIcon --- info.getClassName():"+info.getClassName());
        info.setIcon(BitmapFactory.decodeResource(mContext.getResources(),resId));
        /* BitmapFactory.Options factory=new BitmapFactory.Options();  
        factory.inJustDecodeBounds = true; 
        //factory.inSampleSize= 96;
        //factory.inDensity = 96;
        factory.outHeight = 48;
        factory.outWidth = 48;
        info.setIcon(BitmapFactory.decodeResource(mContext.getResources(), resId, factory)); */
    }
    
	public void setShortIcon(ShortcutInfo info, Drawable drawable) {
//        //info.setIcon(BitmapFactory.decodeResource(mContext.getResources(),resId));
//        BitmapFactory.Options factory=new BitmapFactory.Options();  
//        factory.inJustDecodeBounds = true; 
//        //factory.inSampleSize= 96;
//        //factory.inDensity = 96;
//        factory.outHeight = 48;
//        factory.outWidth = 48;
//        info.setIcon(BitmapFactory.decodeResource(mContext.getResources(), resId, factory));
		if(drawable != null) {
			BitmapDrawable bd = (BitmapDrawable) drawable;
			info.setIcon(bd.getBitmap());
		} else {
			info = null;
		}
    }

    /**
     * 
     *@authod zhangle
     * @param info
     * @param mContext
     * @return
     */
    /*public  ActivityInfo changeThemeIconByActivityInfo(ActivityInfo info,Context mContext) {
        sp = mContext.getSharedPreferences(ThemeChooseActivity.THEMES_DATA, 2 );
        int type = sp.getInt(ThemeChooseActivity.THEME_TYPE, ThemeChooseActivity.THEMES_DATA_DEFAULT);
        
        String class_name = info.name;
        Log.d("zhangle","changeThemeIconByActivityInfo class_name: " + class_name);
        switch (type) {
            case ThemeChooseActivity.THEMES_TYPE_1:
				if(checkValue(class_name,CONTACTS)){
                    setAppIcon(info,R.drawable.ic_launcher_contacts_1);
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    setAppIcon(info,R.drawable.ic_launcher_browser_1);
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    setAppIcon(info,R.drawable.ic_launcher_calculator_1);
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                    setAppIcon(info,R.drawable.ic_launcher_calendar_1);
                    break;
                }else if(checkValue(class_name,PHONE)){
                    setAppIcon(info,R.drawable.ic_launcher_phone_1);
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    setAppIcon(info,R.drawable.ic_launcher_alarmclock_1);
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    setAppIcon(info, R.drawable.ic_launcher_record_1);
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    setAppIcon(info, R.drawable.ic_launcher_email_1);
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    setAppIcon(info, R.drawable.ic_launcher_gallery_1);
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    setAppIcon(info, R.drawable.ic_launcher_camera_1);
                    break;
                }else if(checkValue(class_name,MMS)){
                    setAppIcon(info, R.drawable.ic_launcher_smsmms_1);
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    setAppIcon(info, R.drawable.ic_launcher_music_1);
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    setAppIcon(info, R.drawable.ic_launcher_settings_1);
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    setAppIcon(info, R.drawable.ic_launcher_videoeditor_1);
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    setAppIcon(info, R.drawable.ic_launcher_download_1);
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    setAppIcon(info, R.drawable.ic_launcher_filemanager_1);
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    setAppIcon(info, R.drawable.ic_launcher_fm_1);
                    break;
                }
            case ThemeChooseActivity.THEMES_TYPE_2:
				if(checkValue(class_name,CONTACTS)){
                    setAppIcon(info,R.drawable.ic_launcher_contacts_2);
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    setAppIcon(info,R.drawable.ic_launcher_browser_2);
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    setAppIcon(info,R.drawable.ic_launcher_calculator_2);
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                    setAppIcon(info,R.drawable.ic_launcher_calendar_2);
                    break;
                }else if(checkValue(class_name,PHONE)){
                    setAppIcon(info,R.drawable.ic_launcher_phone_2);
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    setAppIcon(info,R.drawable.ic_launcher_alarmclock_2);
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    setAppIcon(info, R.drawable.ic_launcher_record_2);
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    setAppIcon(info, R.drawable.ic_launcher_email_2);
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    setAppIcon(info, R.drawable.ic_launcher_gallery_2);
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    setAppIcon(info, R.drawable.ic_launcher_camera_2);
                    break;
                }else if(checkValue(class_name,MMS)){
                    setAppIcon(info, R.drawable.ic_launcher_smsmms_2);
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    setAppIcon(info, R.drawable.ic_launcher_music_2);
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    setAppIcon(info, R.drawable.ic_launcher_settings_2);
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    setAppIcon(info, R.drawable.ic_launcher_videoeditor_2);
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    setAppIcon(info, R.drawable.ic_launcher_download_2);
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    setAppIcon(info, R.drawable.ic_launcher_filemanager_2);
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    setAppIcon(info, R.drawable.ic_launcher_fm_2);
                    break;
                }
            case ThemeChooseActivity.THEMES_TYPE_3:
				if(checkValue(class_name,CONTACTS)){
                    setAppIcon(info,R.drawable.ic_launcher_contacts_3);
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    setAppIcon(info,R.drawable.ic_launcher_browser_3);
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    setAppIcon(info,R.drawable.ic_launcher_calculator_3);
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                    setAppIcon(info,R.drawable.ic_launcher_calendar_3);
                    break;
                }else if(checkValue(class_name,PHONE)){
                    setAppIcon(info,R.drawable.ic_launcher_phone_3);
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    setAppIcon(info,R.drawable.ic_launcher_alarmclock_3);
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    setAppIcon(info, R.drawable.ic_launcher_record_3);
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    setAppIcon(info, R.drawable.ic_launcher_email_3);
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    setAppIcon(info, R.drawable.ic_launcher_gallery_3);
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    setAppIcon(info, R.drawable.ic_launcher_camera_3);
                    break;
                }else if(checkValue(class_name,MMS)){
                    setAppIcon(info, R.drawable.ic_launcher_smsmms_3);
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    setAppIcon(info, R.drawable.ic_launcher_music_3);
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    setAppIcon(info, R.drawable.ic_launcher_settings_3);
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    setAppIcon(info, R.drawable.ic_launcher_videoeditor_3);
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    setAppIcon(info, R.drawable.ic_launcher_download_3);
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    setAppIcon(info, R.drawable.ic_launcher_filemanager_3);
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    setAppIcon(info, R.drawable.ic_launcher_fm_3);
                    break;
                }
            case ThemeChooseActivity.THEMES_TYPE_4:
				if(checkValue(class_name,CONTACTS)){
                    setAppIcon(info,R.drawable.ic_launcher_contacts_0);
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    setAppIcon(info,R.drawable.ic_launcher_browser_0);
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    setAppIcon(info,R.drawable.ic_launcher_calculator_0);
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                    setAppIcon(info,R.drawable.ic_launcher_calendar_0);
                    break;
                }else if(checkValue(class_name,PHONE)){
                    setAppIcon(info,R.drawable.ic_launcher_phone_0);
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    setAppIcon(info,R.drawable.ic_launcher_alarmclock_0);
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    setAppIcon(info, R.drawable.ic_launcher_record_0);
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    setAppIcon(info, R.drawable.ic_launcher_email_0);
                    break;
                }else if(checkValue(class_name,EMAIL2)){
                    setAppIcon(info, R.drawable.ic_launcher_gmail_0);
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    setAppIcon(info, R.drawable.ic_launcher_gallery_0);
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    setAppIcon(info, R.drawable.ic_launcher_camera_0);
                    break;
                }else if(checkValue(class_name,MMS)){
                    setAppIcon(info, R.drawable.ic_launcher_smsmms_0);
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    setAppIcon(info, R.drawable.ic_launcher_music_0);
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    setAppIcon(info, R.drawable.ic_launcher_settings_0);
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    setAppIcon(info, R.drawable.ic_launcher_videoeditor_0);
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    setAppIcon(info, R.drawable.ic_launcher_download_0);
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    setAppIcon(info, R.drawable.ic_launcher_filemanager_0);
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    setAppIcon(info, R.drawable.ic_launcher_fm_0);
                    break;
                }else if(checkValue(class_name,BLMANAGER)){
                    setAppIcon(info, R.drawable.ic_launcher_ble_0);
                    break;
                }else if(checkValue(class_name,DEVELOPMENT)){
                    setAppIcon(info, R.drawable.ic_launcher_dev_0);
                    break;
                }else if(checkValue(class_name,HOTKNOT)){
                    setAppIcon(info, R.drawable.ic_launcher_hotknot_0);
                    break;
                }else if(checkValue(class_name,MEDIATEK)){
                    setAppIcon(info, R.drawable.ic_launcher_mediatek_0);
                    break;
                }else if(checkValue(class_name,SEARCH)){
                    setAppIcon(info, R.drawable.ic_launcher_search_0);
                    break;
                }
            case ThemeChooseActivity.THEMES_DATA_SYSTEM:
                break;

            default:
                break;
        }
        
        return info;
    }*/
   
    

    /**
     * 
     *@authod zhangle
     * @param info
     * @param mContext
     * @return
     */
    public  int getAppDrawableId(ActivityInfo info,Context mContext) {
        sp = mContext.getSharedPreferences(ThemeChooseActivity.THEMES_DATA, 2 );
        int type = ThemeChooseActivity.THEMES_DATA_DEFAULT; //sp.getInt(ThemeChooseActivity.THEME_TYPE, ThemeChooseActivity.THEMES_DATA_DEFAULT);
        int id = 0;
        String class_name = info.name;
       Log.d("zhangle","getAppDrawableId class_name: " + class_name);
        switch (type) {
            case ThemeChooseActivity.THEMES_TYPE_1:
				if(checkValue(class_name,CONTACTS)){
                    id = R.drawable.ic_launcher_contacts_1;
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    id = R.drawable.ic_launcher_browser_1;
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    id = R.drawable.ic_launcher_calculator_1;
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                    id = R.drawable.ic_launcher_calendar_1;
                    break;
                }else if(checkValue(class_name,PHONE)){
                    id = R.drawable.ic_launcher_phone_1;
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    id = R.drawable.ic_launcher_alarmclock_1;
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    id =  R.drawable.ic_launcher_record_1;
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    id =  R.drawable.ic_launcher_email_1;
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    id =  R.drawable.ic_launcher_gallery_1;
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    id =  R.drawable.ic_launcher_camera_1;
                    break;
                }else if(checkValue(class_name,MMS)){
                    id =  R.drawable.ic_launcher_smsmms_1;
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    id =  R.drawable.ic_launcher_music_1;
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    id =  R.drawable.ic_launcher_settings_1;
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    id =  R.drawable.ic_launcher_videoeditor_1;
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    id =  R.drawable.ic_launcher_download_1;
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    id =  R.drawable.ic_launcher_filemanager_1;
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    id =  R.drawable.ic_launcher_fm_1;
                    break;
                }
            case ThemeChooseActivity.THEMES_TYPE_2:
				if(checkValue(class_name,CONTACTS)){
                    id = R.drawable.ic_launcher_contacts_2;
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    id = R.drawable.ic_launcher_browser_2;
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    id = R.drawable.ic_launcher_calculator_2;
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                    id = R.drawable.ic_launcher_calendar_2;
                    break;
                }else if(checkValue(class_name,PHONE)){
                    id = R.drawable.ic_launcher_phone_2;
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    id = R.drawable.ic_launcher_alarmclock_2;
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    id =  R.drawable.ic_launcher_record_2;
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    id =  R.drawable.ic_launcher_email_2;
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    id =  R.drawable.ic_launcher_gallery_2;
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    id =  R.drawable.ic_launcher_camera_2;
                    break;
                }else if(checkValue(class_name,MMS)){
                    id =  R.drawable.ic_launcher_smsmms_2;
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    id =  R.drawable.ic_launcher_music_2;
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    id =  R.drawable.ic_launcher_settings_2;
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    id =  R.drawable.ic_launcher_videoeditor_2;
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    id =  R.drawable.ic_launcher_download_2;
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    id =  R.drawable.ic_launcher_filemanager_2;
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    id =  R.drawable.ic_launcher_fm_2;
                    break;
                }
            case ThemeChooseActivity.THEMES_TYPE_3:
				if(checkValue(class_name,CONTACTS)){
                    id = R.drawable.ic_launcher_contacts_3;
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    id = R.drawable.ic_launcher_browser_3;
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    id = R.drawable.ic_launcher_calculator_3;
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                    id = R.drawable.ic_launcher_calendar_3;
                    break;
                }else if(checkValue(class_name,PHONE)){
                    id = R.drawable.ic_launcher_phone_3;
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    id = R.drawable.ic_launcher_alarmclock_3;
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    id =  R.drawable.ic_launcher_record_3;
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    id =  R.drawable.ic_launcher_email_3;
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    id =  R.drawable.ic_launcher_gallery_3;
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    id =  R.drawable.ic_launcher_camera_3;
                    break;
                }else if(checkValue(class_name,MMS)){
                    id =  R.drawable.ic_launcher_smsmms_3;
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    id =  R.drawable.ic_launcher_music_3;
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    id =  R.drawable.ic_launcher_settings_3;
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    id =  R.drawable.ic_launcher_videoeditor_3;
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    id =  R.drawable.ic_launcher_download_3;
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    id =  R.drawable.ic_launcher_filemanager_3;
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    id =  R.drawable.ic_launcher_fm_3;
                    break;
                }
            case ThemeChooseActivity.THEMES_TYPE_4:
				/*if(checkValue(class_name,CONTACTS)){
                    id = R.drawable.ic_launcher_contacts_0;
                    break;
                }else if(checkValue(class_name,BROWSER)){
                    id = R.drawable.ic_launcher_browser_0;
                    break;
                }else if(checkValue(class_name,CALCULATOR)){
                    id = R.drawable.ic_launcher_calculator_0;
                    break;
                }else if(checkValue(class_name,CALENDAR)){
                    id = R.drawable.ic_launcher_calendar_0;
                    break;
                }else if(checkValue(class_name,PHONE)){
                    id = R.drawable.ic_launcher_phone_0;
                    break;
                }else if(checkValue(class_name,DESKCLOCK)){
                    id = R.drawable.ic_launcher_alarmclock_0;
                    break;
                }else if(checkValue(class_name,SOUNDRECORDER)){
                    id =  R.drawable.ic_launcher_record_0;
                    break;
                }else if(checkValue(class_name,EMAIL)){
                    id =  R.drawable.ic_launcher_email_0;
                    break;
                }else if(checkValue(class_name,EMAIL2)){
                    id =  R.drawable.ic_launcher_gmail_0;
                    break;
                }else if(checkValue(class_name,GALLERY)){
                    id =  R.drawable.ic_launcher_gallery_0;
                    break;
                }else if(checkValue(class_name,CAMERA)){
                    id =  R.drawable.ic_launcher_camera_0;
                    break;
                }else if(checkValue(class_name,MMS)){
                    id =  R.drawable.ic_launcher_smsmms_0;
                    break;
                }else if(checkValue(class_name,MUSIC)){
                    id =  R.drawable.ic_launcher_music_0;
                    break;
                }else if(checkValue(class_name,SETTINGS)){
                    id =  R.drawable.ic_launcher_settings_0;
                    break;
                }else if(checkValue(class_name,VIDEOPLAYER)){
                    id =  R.drawable.ic_launcher_videoeditor_0;
                    break;
                }else if(checkValue(class_name,DOWNLOADS)){
                    id =  R.drawable.ic_launcher_download_0;
                    break;
                }else if(checkValue(class_name,FILEMANAGER)){
                    id =  R.drawable.ic_launcher_filemanager_0;
                    break;
                }else if(checkValue(class_name,FMRADIO)){
                    id =  R.drawable.ic_launcher_fm_0;
                    break;
                }else if(checkValue(class_name,BLMANAGER)){
                    id =  R.drawable.ic_launcher_ble_0;
                    break;
                }else if(checkValue(class_name,DEVELOPMENT)){
                    id =  R.drawable.ic_launcher_dev_0;
                    break;
                }else if(checkValue(class_name,HOTKNOT)){
                    id =  R.drawable.ic_launcher_hotknot_0;
                    break;
                }else if(checkValue(class_name,MEDIATEK)){
                    id =  R.drawable.ic_launcher_mediatek_0;
                    break;
                }else if(checkValue(class_name,SEARCH)){
                    id =  R.drawable.ic_launcher_search_0;
                    break;
                }*/
            case ThemeChooseActivity.THEMES_DATA_SYSTEM:
                id = 0;
                break;

            default:
                break;
        }
        Log.d("zhangle","getAppDrawableId id=" + id);
        return id;
    }
    
}
