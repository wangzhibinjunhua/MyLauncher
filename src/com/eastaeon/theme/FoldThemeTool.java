/**
 * 
 */
package com.eastaeon.theme;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.android.launcher3.*;


/**
 * @author panhongyu
 *
 */
public class FoldThemeTool extends AppClassName {
	public static final String TAG = "ThemeTool";
	    
	public static final String FOLDER_ICON = "portal_ring_inner_holo";
	public static final String BACKGROUND_ICON = "theme_background_icon";
	
	public static final int MAX_BACKGOUND_ICON_NUM = 5;
    
    private ThemeResources mThemeResources= null;
    private Context mContext;
    
    
    public FoldThemeTool(Context context) {
        super();
        this.mContext = context;
		mThemeResources = new ThemeResources(mContext);
    }

    
    /**
     * 
     *@authod panhongyu
     * @param apk_class_name
     * @param character
     * @return
     */
    public boolean checkValue(String apk_class_name,String  character ) {
        return null != apk_class_name && !("".equals(apk_class_name)) && apk_class_name.equals(character);
    }
    
	
    /**
     * @author panhongyu
     * @param info
     */
    public  ShortcutInfo changeShortcutForTheme(ShortcutInfo info,Context mContext) {
                
        final String class_name = info.getClassName();
        Log.d("panhongyu","changeShortcutForTheme class_name=" + class_name);
        if(null == class_name || "".equals(class_name) ){
            return info;
        }
		if(checkValue(class_name,CONTACTS)){
			setShortIcon(info, mThemeResources.getThemeIcon( CONTACTS.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,BROWSER)){
			setShortIcon(info, mThemeResources.getThemeIcon( BROWSER.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,CALCULATOR)){
			setShortIcon(info, mThemeResources.getThemeIcon( CALCULATOR.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,CALENDAR)){
			 Log.d("liminchek","changeShortcutForTheme class_name=" + class_name);
			setShortIcon(info, mThemeResources.getThemeIcon( CALENDAR.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,PHONE)){
			setShortIcon(info, mThemeResources.getThemeIcon( PHONE.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,DESKCLOCK)){
			setShortIcon(info, mThemeResources.getThemeIcon( DESKCLOCK.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,EMAIL)){
			setShortIcon(info, mThemeResources.getThemeIcon( EMAIL.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,GALLERY)){
			setShortIcon(info, mThemeResources.getThemeIcon( GALLERY.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,CAMERA)){
			setShortIcon(info, mThemeResources.getThemeIcon( CAMERA.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,MMS)){
			setShortIcon(info, mThemeResources.getThemeIcon( MMS.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,MUSIC)){
			setShortIcon(info, mThemeResources.getThemeIcon( MUSIC.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,SETTINGS)){
			setShortIcon(info, mThemeResources.getThemeIcon( SETTINGS.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,SOUNDRECORDER)){
			setShortIcon(info, mThemeResources.getThemeIcon( SOUNDRECORDER.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,VIDEOPLAYER)){
			setShortIcon(info, mThemeResources.getThemeIcon( VIDEOPLAYER.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,BACKUPRESTORE)){
			setShortIcon(info, mThemeResources.getThemeIcon( BACKUPRESTORE.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,FILEMANAGER)){
			setShortIcon(info, mThemeResources.getThemeIcon( FILEMANAGER.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,FMRADIO)){
			setShortIcon(info, mThemeResources.getThemeIcon( FMRADIO.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,NOTEBOOK)){
			setShortIcon(info, mThemeResources.getThemeIcon( NOTEBOOK.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,STK)){
			setShortIcon(info, mThemeResources.getThemeIcon( STK.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,DOWNLOADS)){
			setShortIcon(info, mThemeResources.getThemeIcon( DOWNLOADS.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,THEMECHOOSE)){
			setShortIcon(info, mThemeResources.getThemeIcon( THEMECHOOSE.replace(".", "_").toLowerCase()));
			
		}else if(checkValue(class_name,IFLY)){
			setShortIcon(info, mThemeResources.getThemeIcon( IFLY.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,QQPIM)){
			setShortIcon(info, mThemeResources.getThemeIcon( QQPIM.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,ANDROIDMARKET)){
			setShortIcon(info, mThemeResources.getThemeIcon( ANDROIDMARKET.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,TAOBAO)){
			setShortIcon(info, mThemeResources.getThemeIcon( TAOBAO.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,TMALL)){
			setShortIcon(info, mThemeResources.getThemeIcon( TMALL.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,JD)){
			setShortIcon(info, mThemeResources.getThemeIcon( JD.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,WEIXIN)){
			setShortIcon(info, mThemeResources.getThemeIcon( WEIXIN.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,TENCENTNEWS)){
			setShortIcon(info, mThemeResources.getThemeIcon( TENCENTNEWS.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,MEITUXIUXIU)){
			setShortIcon(info, mThemeResources.getThemeIcon( MEITUXIUXIU.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,MEIYANCAMERA)){
			setShortIcon(info, mThemeResources.getThemeIcon( MEIYANCAMERA.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,IREADER)){
			setShortIcon(info, mThemeResources.getThemeIcon( IREADER.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,CTRIP)){
			setShortIcon(info, mThemeResources.getThemeIcon( CTRIP.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,BAIDUSEARCHBOX)){
			setShortIcon(info, mThemeResources.getThemeIcon( BAIDUSEARCHBOX.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,BAIDUBROWSER)){
			setShortIcon(info, mThemeResources.getThemeIcon( BAIDUBROWSER.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,SOHUNEWS)){
			setShortIcon(info, mThemeResources.getThemeIcon( SOHUNEWS.replace(".", "_").toLowerCase()));
		}else if(checkValue(class_name,QQ)){
			setShortIcon(info, mThemeResources.getThemeIcon( QQ.replace(".", "_").toLowerCase()));
		}
        
        return info;
    }  
    
    /**
     * 
     *@authod panhongyu
     * @param info
     * @param drawable
     */
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
     *@authod panhongyu
     * @param info
     * @param mContext
     * @return
     */
    public  Drawable getAppDrawable(ActivityInfo info) {
    	Drawable drawable =null;
        String class_name = info.name;
        Log.d("panhongyu","getAppDrawableId class_name: " + class_name);
		if(checkValue(class_name,CONTACTS)){
			drawable = mThemeResources.getThemeIcon( CONTACTS.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,BROWSER)){
			drawable = mThemeResources.getThemeIcon( BROWSER.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,CALCULATOR)){
			drawable = mThemeResources.getThemeIcon( CALCULATOR.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,CALENDAR)){
			drawable = mThemeResources.getThemeIcon( CALENDAR.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,PHONE)){
			drawable = mThemeResources.getThemeIcon( PHONE.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,DESKCLOCK)){
			drawable = mThemeResources.getThemeIcon( DESKCLOCK.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,EMAIL)){
			drawable = mThemeResources.getThemeIcon( EMAIL.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,GALLERY)){
			drawable = mThemeResources.getThemeIcon( GALLERY.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,CAMERA)){
			drawable = mThemeResources.getThemeIcon( CAMERA.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,MMS)){
			drawable = mThemeResources.getThemeIcon( MMS.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,MUSIC)){
			drawable = mThemeResources.getThemeIcon( MUSIC.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,SETTINGS)){
			drawable = mThemeResources.getThemeIcon( SETTINGS.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,SOUNDRECORDER)){
			drawable = mThemeResources.getThemeIcon( SOUNDRECORDER.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,VIDEOPLAYER)){
			drawable = mThemeResources.getThemeIcon( VIDEOPLAYER.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,BACKUPRESTORE)){
			drawable = mThemeResources.getThemeIcon( BACKUPRESTORE.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,FILEMANAGER)){
			drawable = mThemeResources.getThemeIcon( FILEMANAGER.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,FMRADIO)){
			drawable = mThemeResources.getThemeIcon( FMRADIO.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,NOTEBOOK)){
			drawable = mThemeResources.getThemeIcon( NOTEBOOK.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,STK)){
			drawable = mThemeResources.getThemeIcon( STK.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,DOWNLOADS)){
			drawable = mThemeResources.getThemeIcon( DOWNLOADS.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,THEMECHOOSE)){
			drawable = mThemeResources.getThemeIcon( THEMECHOOSE.replace(".", "_").toLowerCase());
			
		}else if(checkValue(class_name,IFLY)){
			drawable = mThemeResources.getThemeIcon( IFLY.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,QQPIM)){
			drawable = mThemeResources.getThemeIcon( QQPIM.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,ANDROIDMARKET)){
			drawable = mThemeResources.getThemeIcon( ANDROIDMARKET.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,TAOBAO)){
			drawable = mThemeResources.getThemeIcon( TAOBAO.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,TMALL)){
			drawable = mThemeResources.getThemeIcon( TMALL.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,JD)){
			drawable = mThemeResources.getThemeIcon( JD.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,WEIXIN)){
			drawable = mThemeResources.getThemeIcon( WEIXIN.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,TENCENTNEWS)){
			drawable = mThemeResources.getThemeIcon( TENCENTNEWS.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,MEITUXIUXIU)){
			drawable = mThemeResources.getThemeIcon( MEITUXIUXIU.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,MEIYANCAMERA)){
			drawable = mThemeResources.getThemeIcon( MEIYANCAMERA.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,IREADER)){
			drawable = mThemeResources.getThemeIcon( IREADER.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,CTRIP)){
			drawable = mThemeResources.getThemeIcon( CTRIP.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,BAIDUSEARCHBOX)){
			drawable = mThemeResources.getThemeIcon( BAIDUSEARCHBOX.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,BAIDUBROWSER)){
			drawable = mThemeResources.getThemeIcon( BAIDUBROWSER.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,SOHUNEWS)){
			drawable = mThemeResources.getThemeIcon( SOHUNEWS.replace(".", "_").toLowerCase());
		}else if(checkValue(class_name,QQ)){
			drawable = mThemeResources.getThemeIcon( QQ.replace(".", "_").toLowerCase());
		}
				
        return drawable;
    }
	
	
    /**
     * 
     *@authod panhongyu
     * @return Drawable
     */
	public Drawable getFolderDrawable() {
		Drawable drawable = null;
		drawable = mThemeResources.getThemeIcon(FOLDER_ICON);
		return drawable;
	} 
	
	public List<Drawable> getThemeBackgroundIcons() {
		List<Drawable> drawable = new ArrayList<Drawable>();
		drawable.clear();
		for(int i = 1; i <= MAX_BACKGOUND_ICON_NUM; i++) {
			Drawable backgroundDrawable = mThemeResources.getThemeIcon(BACKGROUND_ICON+"_"+i);
			if(backgroundDrawable != null) {
				drawable.add(backgroundDrawable);
			}
		}			
		return drawable;
	}		

}
