
package com.eastaeon.launcher3.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eastaeon.launcher3.domain.MyTheme;

import com.android.launcher3.R;

import java.io.IOException;
import java.util.ArrayList;

import com.android.launcher3.IconCache;
import com.android.launcher3.Launcher;

/**
 * 
 * @author zhangle
 * @since 2013/07/27
 * 主题选择界面
 *
 */
public class ThemeChooseActivity extends Activity {
    private static final String TAG = "ThemeChooseActivity";
    public static final String THEME_TYPE = "theme_type";
    public static final String THEME_UPDATE = "theme_need_update";
    public static final String THEMES_DATA = "themes";
    public static final int THEMES_DATA_SYSTEM = 0;
	public static final int THEMES_TYPE_0 = 0;
    public static final int THEMES_TYPE_1 = 1;
    public static final int THEMES_TYPE_2 = 2;
    public static final int THEMES_TYPE_3 = 3;
    public static final int THEMES_TYPE_4 = 4;
    public static final int THEMES_DATA_DEFAULT = 4;
    public static final int THEMES_COUNT = 5;
    private ListView lv;
    private GridView gv;
    private ArrayList<MyTheme> themes = new ArrayList<MyTheme>();
    private MyTheme mTheme;

    private int[] icons = {
            R.drawable.theme1, //INEW
			R.drawable.theme2, //default
			R.drawable.theme3,
			R.drawable.theme4,
			R.drawable.theme5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //Log.d("zhangle","oncreate");
        setContentView(R.layout.choose_theme);
        initdata(false,true);
        
        gv = (GridView)findViewById(R.id.gv_all);
        gv.setAdapter(new MainGridViewAdapter(getApplicationContext()));
        
        lv = (ListView) findViewById(R.id.lv_themes);
        /*lv.setAdapter(new ThemeListAdapter(getApplicationContext(), mTheme));
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                applyTheme(position);
            }
            
        });*/
        
        gv.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                applyTheme(arg2);
                
            }});
    }

    
    protected void applyTheme(int position) {
        Context ctx =getApplicationContext();       
       SharedPreferences sp = ctx.getSharedPreferences(THEMES_DATA, MODE_WORLD_WRITEABLE);
      //存入数据
       int type = sp.getInt(THEME_TYPE, THEMES_DATA_DEFAULT);
       Log.d("zhangle","applyTheme: type=" + type + " -- position=" + position );
       if(position == type){
           Toast.makeText(ctx, ctx.getResources().getText(R.string.choose_again), 0).show();
       }else{
           Editor editor = sp.edit();
           editor.putInt(THEME_TYPE, position);
           editor.putInt(THEME_UPDATE, 1);
           editor.commit();
           selectWallpaper(position);
           //System.exit(0);
           //new SetThemeTask(this.getApplicationContext()).execute(position);
           
       }
	   
       startActivity(new Intent(getApplicationContext(), Launcher.class)/*.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)*/);
    }
    
    private int [] wallpapers = {
            R.drawable.wallpapers1,
			R.drawable.wallpapers2,
			R.drawable.wallpapers3,
			R.drawable.wallpapers4,
			R.drawable.wallpapers5,
    };
    
    private void selectWallpaper(int type) {
       try {
	   
            WallpaperManager wpm = (WallpaperManager) this.getSystemService(Context.WALLPAPER_SERVICE);
            wpm.setResource(wallpapers[type]);
        } catch (IOException e) {
            Log.e(TAG, "Failed to set wallpaper: " + e);
        }
    }

    private void initdata(boolean lv, boolean gv) {
        if(lv){ //listview
            String[] array_icon = getResources().getStringArray(R.array.theme_icon_lv);
            String[] array_name = getResources().getStringArray(R.array.theme_names_lv);
            for (int i = 0; i <= THEMES_COUNT - 1; i++) {
                themes.add(new MyTheme(array_name[i], getResources().getDrawable(icons[i])));
            }
        }else if (gv){ //gridview
            String[] array_icon = getResources().getStringArray(R.array.theme_icon_gv);
            String[] array_name = getResources().getStringArray(R.array.theme_names_gv);
            for (int i = 0; i <= THEMES_COUNT - 1; i++) {
                themes.add(new MyTheme(array_name[i], getResources().getDrawable(icons[i])));
            }
        }
        
    }

    final class ThemeListAdapter extends BaseAdapter {

        private Context mContext;

        public ThemeListAdapter(Context applicationContext, MyTheme theme) {
            mContext = applicationContext;
        }

        @Override
        public int getCount() {
            return themes.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return themes.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater li = LayoutInflater.from(mContext);
            View view = li.inflate(R.layout.theme_item, null);
            ImageView iv = (ImageView) view.findViewById(R.id.theme_icon);
            TextView tv = (TextView) view.findViewById(R.id.theme_name);
            iv.setBackground(themes.get(position).getTheme_icon());
            tv.setText(themes.get(position).getTheme_name());

            return view;
        }
    }
    
 // 完成gridview 数据到界面的适配 
    final class MainGridViewAdapter extends BaseAdapter {
        private static final String TAG = "MainGridViewAdapter";

        private Context context;
        LayoutInflater infalter;

        public MainGridViewAdapter(Context context) {
            this.context = context;
            // 方法1 通过系统的service 获取到 试图填充器
            // infalter = (LayoutInflater)
            // context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 方法2 通过layoutinflater的静态方法获取到 视图填充器
            infalter = LayoutInflater.from(context);
        }

        // 返回gridview里面有多少个条目
        public int getCount() {
            return themes.size(); 
        }

        // 返回某个position对应的条目
        public Object getItem(int position) {
            return themes.get(position);
        }

        // 返回某个position对应的id
        public long getItemId(int position) {
            return position;
        }

        // 返回某个位置对应的视图
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i(TAG, "GETVIEW " + position);
            // 把一个布局文件转换成视图
            View view = infalter.inflate(R.layout.meunitem, null);
            ImageView iv = (ImageView) view.findViewById(R.id.main_gv_iv);
            TextView tv = (TextView) view.findViewById(R.id.main_gv_tv);
            // 设置每一个item的名字和图标
            iv.setBackground(themes.get(position).getTheme_icon());
            tv.setText(themes.get(position).getTheme_name());
            /*if (position == 1) {
                // 设置每一个item的名字和图标
                iv.setImageResource(icons[position]);
                tv.setText(names[position]);
                tv.setSingleLine(true);
                tv.setEllipsize(TruncateAt.END);
                tv.setSingleLine(true);
                tv.setMarqueeRepeatLimit(-1);
                // tv.setFocusable(true);
                // tv.setFocusableInTouchMode(true);
            }*/
            return view;
        }
    }
    
    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }
     
}
