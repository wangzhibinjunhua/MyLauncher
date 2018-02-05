/*
* Copyright (C) 2014 MediaTek Inc.
* Modification based on code covered by the mentioned copyright
* and/or permission notice(s).
*/
/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher3;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;
import com.mediatek.launcher3.ext.LauncherLog;
import com.eastaeon.launcher3.activity.ThemeChooseActivity;

import java.util.List;
import java.util.Random;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import com.eastaeon.theme.FoldThemeTool;

import java.util.ArrayList;

import java.util.Calendar;
import android.graphics.Typeface;
import android.graphics.Paint.Align;

/**
 * Various utilities shared amongst the Launcher's classes.
 */
public final class Utilities {
    private static final String TAG = "Launcher.Utilities";

    private static int sIconWidth = -1;
    private static int sIconHeight = -1;
    public static int sIconTextureWidth = -1;
    public static int sIconTextureHeight = -1;

    private static final Rect sOldBounds = new Rect();
    private static final Canvas sCanvas = new Canvas();

    static {
        sCanvas.setDrawFilter(new PaintFlagsDrawFilter(Paint.DITHER_FLAG,
                Paint.FILTER_BITMAP_FLAG));
    }
    static int sColors[] = { 0xffff0000, 0xff00ff00, 0xff0000ff };
    static int sColorIndex = 0;

    static int[] sLoc0 = new int[2];
    static int[] sLoc1 = new int[2];

    // To turn on these properties, type
    // adb shell setprop log.tag.PROPERTY_NAME [VERBOSE | SUPPRESS]
    static final String FORCE_ENABLE_ROTATION_PROPERTY = "launcher_force_rotate";
    public static boolean sForceEnableRotation = isPropertyEnabled(FORCE_ENABLE_ROTATION_PROPERTY);

    /**
     * Returns a FastBitmapDrawable with the icon, accurately sized.
     */
    public static FastBitmapDrawable createIconDrawable(Bitmap icon) {
        FastBitmapDrawable d = new FastBitmapDrawable(icon);
        d.setFilterBitmap(true);
        resizeIconDrawable(d);
        return d;
    }

    /**
     * Resizes an icon drawable to the correct icon size.
     */
    static void resizeIconDrawable(Drawable icon) {
        icon.setBounds(0, 0, sIconTextureWidth, sIconTextureHeight);
    }

    private static boolean isPropertyEnabled(String propertyName) {
        return Log.isLoggable(propertyName, Log.VERBOSE);
    }

    public static boolean isRotationEnabled(Context c) {
        boolean enableRotation = sForceEnableRotation ||
                c.getResources().getBoolean(R.bool.allow_rotation);
		//return true;
        return enableRotation;
    }

    /**
     * Indicates if the device is running LMP or higher.
     */
    public static boolean isLmpOrAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.L;//21
    }

    /**
     * Returns a bitmap suitable for the all apps view. Used to convert pre-ICS
     * icon bitmaps that are stored in the database (which were 74x74 pixels at hdpi size)
     * to the proper size (48dp)
     */
    static Bitmap createIconBitmap(Bitmap icon, Context context) {
        int textureWidth = sIconTextureWidth;
        int textureHeight = sIconTextureHeight;
        int sourceWidth = icon.getWidth();
        int sourceHeight = icon.getHeight();
        if (sourceWidth > textureWidth && sourceHeight > textureHeight) {
            // Icon is bigger than it should be; clip it (solves the GB->ICS migration case)
            return Bitmap.createBitmap(icon,
                    (sourceWidth - textureWidth) / 2,
                    (sourceHeight - textureHeight) / 2,
                    textureWidth, textureHeight);
        } else if (sourceWidth == textureWidth && sourceHeight == textureHeight) {
            // Icon is the right size, no need to change it
            return icon;
        } else {
            // Icon is too small, render to a larger bitmap
            final Resources resources = context.getResources();
            return createIconBitmap(new BitmapDrawable(resources, icon), context);
        }
    }

    /**
     * Returns a bitmap suitable for the all apps view.
     */
    public static Bitmap createIconBitmap(Drawable icon, Context context) {
        synchronized (sCanvas) { // we share the statics :-(
            if (sIconWidth == -1) {
                initStatics(context);
            }

            int width = sIconWidth;
            int height = sIconHeight;

            if (icon instanceof PaintDrawable) {
                PaintDrawable painter = (PaintDrawable) icon;
                painter.setIntrinsicWidth(width);
                painter.setIntrinsicHeight(height);
            } else if (icon instanceof BitmapDrawable) {
                // Ensure the bitmap has a density.
                BitmapDrawable bitmapDrawable = (BitmapDrawable) icon;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap.getDensity() == Bitmap.DENSITY_NONE) {
                    bitmapDrawable.setTargetDensity(context.getResources().getDisplayMetrics());
                }
            }
            int sourceWidth = icon.getIntrinsicWidth();
            int sourceHeight = icon.getIntrinsicHeight();
            if (sourceWidth > 0 && sourceHeight > 0) {
                // Scale the icon proportionally to the icon dimensions
                final float ratio = (float) sourceWidth / sourceHeight;
                if (sourceWidth > sourceHeight) {
                    height = (int) (width / ratio);
                } else if (sourceHeight > sourceWidth) {
                    width = (int) (height * ratio);
                }
            }

            // no intrinsic size --> use default size
            int textureWidth = sIconTextureWidth;
            int textureHeight = sIconTextureHeight;

            final Bitmap bitmap = Bitmap.createBitmap(textureWidth, textureHeight,
                    Bitmap.Config.ARGB_8888);
            final Canvas canvas = sCanvas;
            canvas.setBitmap(bitmap);

            final int left = (textureWidth-width) / 2;
            final int top = (textureHeight-height) / 2;

            @SuppressWarnings("all") // suppress dead code warning
            final boolean debug = false;
            if (debug) {
                // draw a big box for the icon for debugging
                canvas.drawColor(sColors[sColorIndex]);
                if (++sColorIndex >= sColors.length) sColorIndex = 0;
                Paint debugPaint = new Paint();
                debugPaint.setColor(0xffcccc00);
                canvas.drawRect(left, top, left+width, top+height, debugPaint);
            }

            //zhangle add start 
            //Log.d("zhangle","createIconBitmap(Drawable icon, Context context)");
            SharedPreferences mSharedPrefs = context.getSharedPreferences(ThemeChooseActivity.THEMES_DATA, 2 );
            int type = mSharedPrefs.getInt(ThemeChooseActivity.THEME_TYPE, ThemeChooseActivity.THEMES_DATA_DEFAULT);
            
            /**
             * @author zhangle
             * 
             */
                icon.setBounds(left, top, left+width, top+height); //default code
            //zhangle add end
            
            /**
             * @author zhangle 
             * 
             */
            /*if(type == ThemeChooseActivity.THEMES_DATA_SYSTEM){
                addThemeBackground(context, canvas,R.drawable.default_icon_bakcground);
            }*/ /*else if (type == ThemeChooseActivity.THEMES_DATA_ZHANGLE){
                addThemeBackground(context, canvas,R.drawable.default_icon_bakcground);
            }else if (type == ThemeChooseActivity.THEMES_DATA_SIMPLE){
                addThemeBackground(context, canvas,R.drawable.default_icon_bakcground);
            } */
           //zhangle add end
            
            //sOldBounds.set(icon.getBounds());
            //icon.setBounds(left, top, left+width, top+height);
            icon.draw(canvas);
            icon.setBounds(sOldBounds);
            List<Drawable> bgIcons = new FoldThemeTool(context).getThemeBackgroundIcons();

			if (null != bgIcons && bgIcons.size() != 0) {
				addThemeBackground(canvas,
						bgIcons.get(new Random().nextInt(bgIcons.size())));
			} else {
			
			
			}
			
            canvas.setBitmap(null);

            return bitmap;
        }
    }



	public static Bitmap createIconBitmapForThirdPartApp(Drawable icon, Context context, boolean isThirdPartApp){
        synchronized (sCanvas) { // we share the statics :-(
            if (sIconWidth == -1) {
                initStatics(context);
            }

            int width = sIconWidth;
            int height = sIconHeight;

            if (icon instanceof PaintDrawable) {
                PaintDrawable painter = (PaintDrawable) icon;
                painter.setIntrinsicWidth(width);
                painter.setIntrinsicHeight(height);
            } else if (icon instanceof BitmapDrawable) {
                // Ensure the bitmap has a density.
                BitmapDrawable bitmapDrawable = (BitmapDrawable) icon;
                Bitmap bitmap = bitmapDrawable.getBitmap();
                if (bitmap.getDensity() == Bitmap.DENSITY_NONE) {
                    bitmapDrawable.setTargetDensity(context.getResources().getDisplayMetrics());
                }
            }
            int sourceWidth = icon.getIntrinsicWidth();
            int sourceHeight = icon.getIntrinsicHeight();
            if (sourceWidth > 0 && sourceHeight > 0) {
                // Scale the icon proportionally to the icon dimensions
                final float ratio = (float) sourceWidth / sourceHeight;
                if (sourceWidth > sourceHeight) {
                    height = (int) (width / ratio);
                } else if (sourceHeight > sourceWidth) {
                    width = (int) (height * ratio);
                }
            }

            // no intrinsic size --> use default size
            int textureWidth = sIconTextureWidth;
            int textureHeight = sIconTextureHeight;

            final Bitmap bitmap = Bitmap.createBitmap(textureWidth, textureHeight,
                    Bitmap.Config.ARGB_8888);
            final Canvas canvas = sCanvas;
            canvas.setBitmap(bitmap);

            final int left = (textureWidth-width) / 2;
            final int top = (textureHeight-height) / 2;

            @SuppressWarnings("all") // suppress dead code warning
            final boolean debug = false;
            if (debug) {
                // draw a big box for the icon for debugging
                canvas.drawColor(sColors[sColorIndex]);
                if (++sColorIndex >= sColors.length) sColorIndex = 0;
                Paint debugPaint = new Paint();
                debugPaint.setColor(0xffcccc00);
                canvas.drawRect(left, top, left+width, top+height, debugPaint);
            }

            //zhangle add start 
            //Log.d("zhangle","createIconBitmap(Drawable icon, Context context)");
            SharedPreferences mSharedPrefs = context.getSharedPreferences(ThemeChooseActivity.THEMES_DATA, 2 );
            int type = mSharedPrefs.getInt(ThemeChooseActivity.THEME_TYPE, ThemeChooseActivity.THEMES_DATA_DEFAULT);
            
            /**
             * @author zhangle
             * 
             */
                icon.setBounds(left, top, left+width, top+height); //default code
            //zhangle add end
            
            /**
             * @author zhangle 
             *
             */
            if(type == ThemeChooseActivity.THEMES_DATA_SYSTEM){
				if(isThirdPartApp)
                	addThemeBackground(context, canvas,R.drawable.default_icon_bakcground);
            }/*else if (type == ThemeChooseActivity.THEMES_DATA_ZHANGLE){
                addThemeBackground(context, canvas,R.drawable.default_icon_bakcground);
            }else if (type == ThemeChooseActivity.THEMES_DATA_SIMPLE){
                addThemeBackground(context, canvas,R.drawable.default_icon_bakcground);
            } */
           //zhangle add end
            
            //sOldBounds.set(icon.getBounds());
            //icon.setBounds(left, top, left+width, top+height);
            icon.draw(canvas);
            icon.setBounds(sOldBounds);
            List<Drawable> bgIcons = new FoldThemeTool(context).getThemeBackgroundIcons();

			if (null != bgIcons && bgIcons.size() != 0) {
				addThemeBackground(canvas,
						bgIcons.get(new Random().nextInt(bgIcons.size())));
			} else {
			
			
			}
			
            canvas.setBitmap(null);

            return bitmap;
        }
    }
	
		public static void addThemeBackground(final Canvas canvas, Drawable drawable) {
		if (true) {
			BitmapDrawable bd = (BitmapDrawable) drawable;
			Bitmap maskBitmap = bd.getBitmap();
			if (maskBitmap.getWidth() != sIconWidth || maskBitmap.getHeight() != sIconHeight) {
				Matrix matrix = new Matrix();
				matrix.postScale((float) sIconWidth / maskBitmap.getWidth(),
						(float) sIconHeight / maskBitmap.getHeight());
				maskBitmap = Bitmap.createBitmap(maskBitmap, 0, 0, maskBitmap.getWidth(), maskBitmap.getHeight(), matrix, true);
			} 			
			int maskWidth = maskBitmap.getWidth();
			int maskHeight = maskBitmap.getHeight();
			Log.d("createIconBitmap", "maskWidth = " + maskWidth + ", maskHeight = " + maskHeight);
			PaintFlagsDrawFilter pdf = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			paint.setStyle(Paint.Style.STROKE);
			paint.setAntiAlias(true);
			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
			
			int mask_left = (sIconTextureWidth-maskWidth)/2;
			int mask_top  = (sIconTextureHeight-maskHeight)/2;
			canvas.setDrawFilter(pdf);
			canvas.drawBitmap(maskBitmap, mask_left, mask_top, paint);	
			

		   if(true){
				paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
				canvas.drawBitmap(maskBitmap, mask_left, mask_top, paint); 
           }
			Log.d("Launcher.Utilities", "" + maskBitmap.getHeight());
			paint.setXfermode(null);
		}
	}
    
	
	

	/**
     * 
     *@authod zhangle
     * @param context
     * @param canvas
     */
    public static void addThemeBackground(Context context, final Canvas canvas,int resId) {
        if (true) 
        {
            Bitmap backBitmap = BitmapFactory.decodeResource(context.getResources(),
                    resId);
            int backWidth = backBitmap.getWidth();
            int backHeight = backBitmap.getHeight();
            if(backWidth != sIconWidth || backHeight != sIconHeight)
            {
                Matrix matrix = new Matrix();
                matrix.postScale((float)sIconWidth/backWidth, (float)sIconHeight/backHeight);
                canvas.drawBitmap(Bitmap.createBitmap(backBitmap, 0, 0, backWidth, backHeight, matrix, true),
                                .0f, 0.0f, null);
            }else{
                canvas.drawBitmap(backBitmap, 0.0f, 0.0f, null);
            }
            Log.d("Launcher.Utilities", "" + backBitmap.getHeight());
        }
    }

    /**
     * 
     * @authod zhangle
     * @param bitmap
     * @param roundPx
     * @return
     */
      public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPx){    
          Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);    
          Canvas canvas = new Canvas(output);    
           
          final int color = 0xff424242;    
          final Paint paint = new Paint();    
          final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());    
          final RectF rectF = new RectF(rect);    
           
          paint.setAntiAlias(true);    
          canvas.drawARGB(0, 0, 0, 0);    
          paint.setColor(color);    
          canvas.drawRoundRect(rectF, roundPx, roundPx, paint);    
          paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));    
          canvas.drawBitmap(bitmap, rect, rect, paint);    
               
          return output;    
    }

    //added by panmengze for calender icon start
    static Bitmap createCalendarIconBitmap(Drawable icon, Context context){
            Bitmap bitmapIcon = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher_calendar);
            Bitmap calendarIcon = createIconBitmap(new BitmapDrawable(bitmapIcon),context);
            String dayString  = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            String weekDay  = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
            String dayOfWeek = null;
			 Log.d("liminlaun","changeShortcutForTheme class_name=" + weekDay);
            if ("1".equals(weekDay)){
                    dayOfWeek = context.getString(R.string.dayofweek_Sun);
            } else if ("2".equals(weekDay)){
                    dayOfWeek = context.getString(R.string.dayofweek_Mon);
            } else if ("3".equals(weekDay)){
                    dayOfWeek = context.getString(R.string.dayofweek_Tues);
            } else if ("4".equals(weekDay)){
                    dayOfWeek = context.getString(R.string.dayofweek_Wed);
            } else if ("5".equals(weekDay)){
                    dayOfWeek = context.getString(R.string.dayofweek_Thur);
            } else if ("6".equals(weekDay)){
                    dayOfWeek = context.getString(R.string.dayofweek_Fri);
            } else if ("7".equals(weekDay)){
                    dayOfWeek = context.getString(R.string.dayofweek_Sat);
            }

            synchronized (sCanvas) {
                    final Canvas canvas = sCanvas;
                    canvas.setBitmap(calendarIcon);

                    final float mDensity = context.getResources().getDisplayMetrics().density;

                    Paint mDatePaint = new Paint();
                    mDatePaint.setTypeface(Typeface.DEFAULT);
                    mDatePaint.setTextSize((int)28F * mDensity);
                    mDatePaint.setTextAlign(Align.CENTER);
                                       mDatePaint.setShadowLayer(5,2,2,0x88404040);
                    mDatePaint.setColor(0xff333333);
                    mDatePaint.setAntiAlias(true);

                    Rect rect = new Rect();
                    mDatePaint.getTextBounds(dayString,0,1,rect);
                    Log.e(TAG, "panmengze :" + dayOfWeek.length());
                    int hoffset = 20;
                    int width1 = rect.right - rect.left;
                    int height1 = rect.bottom - rect.top;
                    int width2 = calendarIcon.getWidth();
                    int height2 = calendarIcon.getHeight() + hoffset;

                    canvas.drawText(dayString,(width2 - width1)/2 - rect.left + 12,(height2 - height1)/2 - rect.top + 4, mDatePaint);

                    Paint mDatePaint1 = new Paint();
                    mDatePaint1.setTypeface(Typeface.DEFAULT);
                    mDatePaint1.setTextSize((int)12F * mDensity);
                    mDatePaint1.setTextAlign(Align.CENTER);
                                       mDatePaint1.setShadowLayer(5,2,2,0x88404040);
                    mDatePaint1.setColor(0xffffffff);
                    mDatePaint1.setAntiAlias(true);
                    mDatePaint1.getTextBounds(dayOfWeek,0,1,rect);

                    canvas.drawText(dayOfWeek,(width2 - width1)/2 - rect.left + 10,(height2 - height1)/2 + rect.top - 2,mDatePaint1);

                    canvas.setBitmap(null);
                    return calendarIcon;
            }
    }
    //added by panmengze for calender icon end

    /**
     * Returns a Bitmap representing the thumbnail of the specified Bitmap.
     *
     * @param bitmap The bitmap to get a thumbnail of.
     * @param context The application's context.
     *
     * @return A thumbnail for the specified bitmap or the bitmap itself if the
     *         thumbnail could not be created.
     */
    static Bitmap resampleIconBitmap(Bitmap bitmap, Context context) {
        synchronized (sCanvas) { // we share the statics :-(
            if (sIconWidth == -1) {
                initStatics(context);
            }

            if (bitmap.getWidth() == sIconWidth && bitmap.getHeight() == sIconHeight) {
                return bitmap;
            } else {
                final Resources resources = context.getResources();
                return createIconBitmap(new BitmapDrawable(resources, bitmap), context);
            }
        }
    }

    /**
     * Given a coordinate relative to the descendant, find the coordinate in a parent view's
     * coordinates.
     *
     * @param descendant The descendant to which the passed coordinate is relative.
     * @param root The root view to make the coordinates relative to.
     * @param coord The coordinate that we want mapped.
     * @param includeRootScroll Whether or not to account for the scroll of the descendant:
     *          sometimes this is relevant as in a child's coordinates within the descendant.
     * @return The factor by which this descendant is scaled relative to this DragLayer. Caution
     *         this scale factor is assumed to be equal in X and Y, and so if at any point this
     *         assumption fails, we will need to return a pair of scale factors.
     */
    public static float getDescendantCoordRelativeToParent(View descendant, View root,
                                                           int[] coord, boolean includeRootScroll) {
        ArrayList<View> ancestorChain = new ArrayList<View>();

        float[] pt = {coord[0], coord[1]};

        View v = descendant;
        while(v != root && v != null) {
            ancestorChain.add(v);
            v = (View) v.getParent();
        }
        ancestorChain.add(root);

        float scale = 1.0f;
        int count = ancestorChain.size();
        for (int i = 0; i < count; i++) {
            View v0 = ancestorChain.get(i);
            // For TextViews, scroll has a meaning which relates to the text position
            // which is very strange... ignore the scroll.
            if (v0 != descendant || includeRootScroll) {
                pt[0] -= v0.getScrollX();
                pt[1] -= v0.getScrollY();
            }

            v0.getMatrix().mapPoints(pt);
            pt[0] += v0.getLeft();
            pt[1] += v0.getTop();
            scale *= v0.getScaleX();
        }

        coord[0] = (int) Math.round(pt[0]);
        coord[1] = (int) Math.round(pt[1]);
        return scale;
    }

    /**
     * Inverse of {@link #getDescendantCoordRelativeToSelf(View, int[])}.
     */
    public static float mapCoordInSelfToDescendent(View descendant, View root,
                                                   int[] coord) {
        ArrayList<View> ancestorChain = new ArrayList<View>();

        float[] pt = {coord[0], coord[1]};

        View v = descendant;
        while(v != root) {
            ancestorChain.add(v);
            v = (View) v.getParent();
        }
        ancestorChain.add(root);

        float scale = 1.0f;
        Matrix inverse = new Matrix();
        int count = ancestorChain.size();
        for (int i = count - 1; i >= 0; i--) {
            View ancestor = ancestorChain.get(i);
            View next = i > 0 ? ancestorChain.get(i-1) : null;

            pt[0] += ancestor.getScrollX();
            pt[1] += ancestor.getScrollY();

            if (next != null) {
                pt[0] -= next.getLeft();
                pt[1] -= next.getTop();
                next.getMatrix().invert(inverse);
                inverse.mapPoints(pt);
                scale *= next.getScaleX();
            }
        }

        coord[0] = (int) Math.round(pt[0]);
        coord[1] = (int) Math.round(pt[1]);
        return scale;
    }

    /**
     * Utility method to determine whether the given point, in local coordinates,
     * is inside the view, where the area of the view is expanded by the slop factor.
     * This method is called while processing touch-move events to determine if the event
     * is still within the view.
     */
    public static boolean pointInView(View v, float localX, float localY, float slop) {
        return localX >= -slop && localY >= -slop && localX < (v.getWidth() + slop) &&
                localY < (v.getHeight() + slop);
    }

    /// M: Change to public for smart book feature.
    public static void initStatics(Context context) {
        final Resources resources = context.getResources();
        sIconWidth = sIconHeight = (int) resources.getDimension(R.dimen.app_icon_size);
        sIconTextureWidth = sIconTextureHeight = sIconWidth;
    }

    public static void setIconSize(int widthPx) {
        sIconWidth = sIconHeight = widthPx;
        sIconTextureWidth = sIconTextureHeight = widthPx;
    }

    public static void scaleRect(Rect r, float scale) {
        if (scale != 1.0f) {
            r.left = (int) (r.left * scale + 0.5f);
            r.top = (int) (r.top * scale + 0.5f);
            r.right = (int) (r.right * scale + 0.5f);
            r.bottom = (int) (r.bottom * scale + 0.5f);
        }
    }

    public static int[] getCenterDeltaInScreenSpace(View v0, View v1, int[] delta) {
        v0.getLocationInWindow(sLoc0);
        v1.getLocationInWindow(sLoc1);

        sLoc0[0] += (v0.getMeasuredWidth() * v0.getScaleX()) / 2;
        sLoc0[1] += (v0.getMeasuredHeight() * v0.getScaleY()) / 2;
        sLoc1[0] += (v1.getMeasuredWidth() * v1.getScaleX()) / 2;
        sLoc1[1] += (v1.getMeasuredHeight() * v1.getScaleY()) / 2;

        if (delta == null) {
            delta = new int[2];
        }

        delta[0] = sLoc1[0] - sLoc0[0];
        delta[1] = sLoc1[1] - sLoc0[1];

        return delta;
    }

    public static void scaleRectAboutCenter(Rect r, float scale) {
        int cx = r.centerX();
        int cy = r.centerY();
        r.offset(-cx, -cy);
        Utilities.scaleRect(r, scale);
        r.offset(cx, cy);
    }

    public static void startActivityForResultSafely(
            Activity activity, Intent intent, int requestCode) {
        try {
            activity.startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(activity, R.string.activity_not_found, Toast.LENGTH_SHORT).show();
        } catch (SecurityException e) {
            Toast.makeText(activity, R.string.activity_not_found, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Launcher does not have the permission to launch " + intent +
                    ". Make sure to create a MAIN intent-filter for the corresponding activity " +
                    "or use the exported attribute for this activity.", e);
        }
    }

    static boolean isSystemApp(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        ComponentName cn = intent.getComponent();
        String packageName = null;
        if (cn == null) {
            ResolveInfo info = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if ((info != null) && (info.activityInfo != null)) {
                packageName = info.activityInfo.packageName;
            }
        } else {
            packageName = cn.getPackageName();
        }
        if (packageName != null) {
            try {
                PackageInfo info = pm.getPackageInfo(packageName, 0);
                return (info != null) && (info.applicationInfo != null) &&
                        ((info.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
            } catch (NameNotFoundException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This picks a dominant color, looking for high-saturation, high-value, repeated hues.
     * @param bitmap The bitmap to scan
     * @param samples The approximate max number of samples to use.
     */
    static int findDominantColorByHue(Bitmap bitmap, int samples) {
        final int height = bitmap.getHeight();
        final int width = bitmap.getWidth();
        int sampleStride = (int) Math.sqrt((height * width) / samples);
        if (sampleStride < 1) {
            sampleStride = 1;
        }

        // This is an out-param, for getting the hsv values for an rgb
        float[] hsv = new float[3];

        // First get the best hue, by creating a histogram over 360 hue buckets,
        // where each pixel contributes a score weighted by saturation, value, and alpha.
        float[] hueScoreHistogram = new float[360];
        float highScore = -1;
        int bestHue = -1;

        for (int y = 0; y < height; y += sampleStride) {
            for (int x = 0; x < width; x += sampleStride) {
                int argb = bitmap.getPixel(x, y);
                int alpha = 0xFF & (argb >> 24);
                if (alpha < 0x80) {
                    // Drop mostly-transparent pixels.
                    continue;
                }
                // Remove the alpha channel.
                int rgb = argb | 0xFF000000;
                Color.colorToHSV(rgb, hsv);
                // Bucket colors by the 360 integer hues.
                int hue = (int) hsv[0];
                if (hue < 0 || hue >= hueScoreHistogram.length) {
                    // Defensively avoid array bounds violations.
                    continue;
                }
                float score = hsv[1] * hsv[2];
                hueScoreHistogram[hue] += score;
                if (hueScoreHistogram[hue] > highScore) {
                    highScore = hueScoreHistogram[hue];
                    bestHue = hue;
                }
            }
        }

        SparseArray<Float> rgbScores = new SparseArray<Float>();
        int bestColor = 0xff000000;
        highScore = -1;
        // Go back over the RGB colors that match the winning hue,
        // creating a histogram of weighted s*v scores, for up to 100*100 [s,v] buckets.
        // The highest-scoring RGB color wins.
        for (int y = 0; y < height; y += sampleStride) {
            for (int x = 0; x < width; x += sampleStride) {
                int rgb = bitmap.getPixel(x, y) | 0xff000000;
                Color.colorToHSV(rgb, hsv);
                int hue = (int) hsv[0];
                if (hue == bestHue) {
                    float s = hsv[1];
                    float v = hsv[2];
                    int bucket = (int) (s * 100) + (int) (v * 10000);
                    // Score by cumulative saturation * value.
                    float score = s * v;
                    Float oldTotal = rgbScores.get(bucket);
                    float newTotal = oldTotal == null ? score : oldTotal + score;
                    rgbScores.put(bucket, newTotal);
                    if (newTotal > highScore) {
                        highScore = newTotal;
                        // All the colors in the winning bucket are very similar. Last in wins.
                        bestColor = rgb;
                    }
                }
            }
        }
        return bestColor;
    }

    /*
     * Finds a system apk which had a broadcast receiver listening to a particular action.
     * @param action intent action used to find the apk
     * @return a pair of apk package name and the resources.
     */
    static Pair<String, Resources> findSystemApk(String action, PackageManager pm) {
        final Intent intent = new Intent(action);
        for (ResolveInfo info : pm.queryBroadcastReceivers(intent, 0)) {
            if (info.activityInfo != null &&
                    (info.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                final String packageName = info.activityInfo.packageName;
                try {
                    final Resources res = pm.getResourcesForApplication(packageName);
                    return Pair.create(packageName, res);
                } catch (NameNotFoundException e) {
                    Log.w(TAG, "Failed to find resources for " + packageName);
                }
            }
        }
        return null;
    }

    /**
     * M: Check whether the given component name is enabled.
     *
     * @param context
     * @param cmpName
     * @return true if the component is in default or enable state, and the application is also in default or enable state,
     *         false if in disable or disable user state.
     */
    static boolean isComponentEnabled(final Context context, final ComponentName cmpName) {
        final String pkgName = cmpName.getPackageName();
        final PackageManager pm = context.getPackageManager();
        // Check whether the package has been uninstalled or the component already removed.
        ActivityInfo aInfo = null;
        try {
            aInfo = pm.getActivityInfo(cmpName, 0);
        } catch (NameNotFoundException e) {
            LauncherLog.w(TAG, "isComponentEnabled NameNotFoundException: pkgName = " + pkgName);
        }

        if (aInfo == null) {
            LauncherLog.d(TAG, "isComponentEnabled return false because component " + cmpName + " has been uninstalled!");
            return false;
        }

        final int pkgEnableState = pm.getApplicationEnabledSetting(pkgName);
        if (LauncherLog.DEBUG) {
            LauncherLog.d(TAG, "isComponentEnabled: cmpName = " + cmpName + ",pkgEnableState = " + pkgEnableState);
        }
        if (pkgEnableState == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT
                || pkgEnableState == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            final int cmpEnableState = pm.getComponentEnabledSetting(cmpName);
            if (LauncherLog.DEBUG) {
                LauncherLog.d(TAG, "isComponentEnabled: cmpEnableState = " + cmpEnableState);
            }
            if (cmpEnableState == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT
                    || cmpEnableState == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
                return true;
            }
        }

        return false;
    }

    /**
     * M: The app is system app or not.
     *
     * @param info
     * @return
     */
    public static boolean isSystemApp(AppInfo info) {
        if (info == null) {
            return false;
        }
        return (info.flags & AppInfo.DOWNLOADED_FLAG) == 0;
    }
}
