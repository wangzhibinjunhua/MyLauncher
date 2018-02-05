/*
* Copyright (C) 2014 MediaTek Inc.
* Modification based on code covered by the mentioned copyright
* and/or permission notice(s).
*/
/*
 * Copyright (C) 2011 The Android Open Source Project
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

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.mediatek.launcher3.ext.LauncherLog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
public class Hotseat extends FrameLayout {
    private static final String TAG = "Hotseat";

    private CellLayout mContent;

    private Launcher mLauncher;

    private int mAllAppsButtonRank;

    private boolean mTransposeLayoutWithOrientation;
    private boolean mIsLandscape;

    public Hotseat(Context context) {
        this(context, null);
    }

    public Hotseat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Hotseat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Resources r = context.getResources();
        mTransposeLayoutWithOrientation = 
                r.getBoolean(R.bool.hotseat_transpose_layout_with_orientation);
        mIsLandscape = context.getResources().getConfiguration().orientation ==
            Configuration.ORIENTATION_LANDSCAPE;
    }
	private int mCount = 0;	
	private AnimatorSet mEditorAnimator;
	private ArrayList<Animator> iconAnimators;
	private static final float DEGREE_0 = 2.0f;
	private static final float DEGREE_1 = -2.0f;
	private static final int ANIMATION_DURATION = 500;	
	private void setShakeAnimation(final View v) {
		float rotate = 0;  
		int c = mCount++ % 2;
		if (c == 0) {  
            rotate = DEGREE_0;  
        } else if (c == 1) {  
            rotate = DEGREE_1;  
        }		  
		
		if(v instanceof BubbleTextView){
			final BubbleTextView btv = (BubbleTextView) v;
			btv.setLayerType(View.LAYER_TYPE_HARDWARE, null);
			btv.setDeleteButtonVisibility(false);
			if (btv.getTag() instanceof ShortcutInfo) {
				ShortcutInfo shortcut = (ShortcutInfo) btv.getTag();		
				if (shortcut.intent != null) {
					if(!Utilities.isSystemApp(mLauncher, shortcut.intent)){
						btv.setDeleteButtonVisibility(true);
					}
				}
			}
		
			ObjectAnimator iconRatation = ObjectAnimator.ofFloat(btv,"rotation", 0f, rotate, 0f, -rotate, 0f); 
			iconRatation.setDuration(ANIMATION_DURATION);
			iconRatation.setRepeatCount(ObjectAnimator.INFINITE);
			iconRatation.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					btv.setLayerType(View.LAYER_TYPE_NONE, null);
					btv.setDeleteButtonVisibility(false);
				}
			});
			iconAnimators.add(iconRatation);
		} else if(v instanceof FolderIcon) {
			final FolderIcon fi = (FolderIcon) v;
			fi.setLayerType(View.LAYER_TYPE_HARDWARE, null);
			ObjectAnimator folderRatation = ObjectAnimator.ofFloat(fi,"rotation", 0f, rotate, 0f, -rotate, 0f); 
			folderRatation.setDuration(ANIMATION_DURATION);
			folderRatation.setRepeatCount(ObjectAnimator.INFINITE);
			folderRatation.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					fi.setLayerType(View.LAYER_TYPE_NONE, null);
				}
			});
			iconAnimators.add(folderRatation);			
		}
	} 

    void setEditorChangeStateAnimation(boolean enable) {


        // Initialize animation arrays for the first time if necessary
        //initAnimationArrays();
			
		if(enable) {
			
			if(iconAnimators == null) {
				iconAnimators = new ArrayList<Animator>();
			} else {
				iconAnimators.clear();
			}	

			if(mEditorAnimator == null) {
				mEditorAnimator = LauncherAnimUtils.createAnimatorSet();
			}
			

			CellLayout cl = mContent;
			for(int i = 0; i < cl.getShortcutsAndWidgets().getChildCount(); i++) {
				View v = cl.getShortcutsAndWidgets().getChildAt(i);
				setShakeAnimation(v);
			}


			if (mEditorAnimator != null) {
				mEditorAnimator.playTogether(iconAnimators);
				mEditorAnimator.start();
			}			
		} else {
			if (mEditorAnimator != null && mEditorAnimator.isRunning()) {
				mEditorAnimator.end();
				mEditorAnimator = null;
			}
		}
		
		
    }

    public void exitEditorMode() {
        setEditorChangeStateAnimation(false);
    }
	public void enterEditorMode() {
        setEditorChangeStateAnimation(true);
    }
    public void setup(Launcher launcher) {
        mLauncher = launcher;
    }

    CellLayout getLayout() {
        return mContent;
    }

    /**
     * Registers the specified listener on the cell layout of the hotseat.
     */
    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        mContent.setOnLongClickListener(l);
    }
  
    private boolean hasVerticalHotseat() {
        return (mIsLandscape && mTransposeLayoutWithOrientation);
    }

    /* Get the orientation invariant order of the item in the hotseat for persistence. */
    int getOrderInHotseat(int x, int y) {
        return hasVerticalHotseat() ? (mContent.getCountY() - y - 1) : x;
    }
    /* Get the orientation specific coordinates given an invariant order in the hotseat. */
    int getCellXFromOrder(int rank) {
        return hasVerticalHotseat() ? 0 : rank;
    }
    int getCellYFromOrder(int rank) {
        return hasVerticalHotseat() ? (mContent.getCountY() - (rank + 1)) : 0;
    }
    public boolean isAllAppsButtonRank(int rank) {
        if (LauncherAppState.isDisableAllApps()) {
            return false;
        } else {
            return rank == mAllAppsButtonRank;
        }
    }

    /** This returns the coordinates of an app in a given cell, relative to the DragLayer */
    Rect getCellCoordinates(int cellX, int cellY) {
        Rect coords = new Rect();
        mContent.cellToRect(cellX, cellY, 1, 1, coords);
        int[] hotseatInParent = new int[2];
        Utilities.getDescendantCoordRelativeToParent(this, mLauncher.getDragLayer(),
                hotseatInParent, false);
        coords.offset(hotseatInParent[0], hotseatInParent[1]);

        // Center the icon
        int cWidth = mContent.getShortcutsAndWidgets().getCellContentWidth();
        int cHeight = mContent.getShortcutsAndWidgets().getCellContentHeight();
        int cellPaddingX = (int) Math.max(0, ((coords.width() - cWidth) / 2f));
        int cellPaddingY = (int) Math.max(0, ((coords.height() - cHeight) / 2f));
        coords.offset(cellPaddingX, cellPaddingY);

        return coords;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LauncherAppState app = LauncherAppState.getInstance();
        DeviceProfile grid = app.getDynamicGrid().getDeviceProfile();

        mAllAppsButtonRank = grid.hotseatAllAppsRank;
        mContent = (CellLayout) findViewById(R.id.layout);
        if (grid.isLandscape && !grid.isLargeTablet()) {
            mContent.setGridSize((int) grid.numHotseatIcons,1);//wbin modify
        } else {
            mContent.setGridSize((int) grid.numHotseatIcons, 1);
        }
        mContent.setIsHotseat(true);

        Log.i(TAG, "onFinishInflate,(int) grid.numHotseatIcons: " + (int) grid.numHotseatIcons);

        resetLayout();
    }

    void resetLayout() {
        mContent.removeAllViewsInLayout();

        if (!LauncherAppState.isDisableAllApps()) {
            // Add the Apps button
            Context context = getContext();

            LayoutInflater inflater = LayoutInflater.from(context);
            TextView allAppsButton = (TextView)
                    inflater.inflate(R.layout.all_apps_button, mContent, false);
            Drawable d = context.getResources().getDrawable(R.drawable.all_apps_button_icon);

            Utilities.resizeIconDrawable(d);
            allAppsButton.setCompoundDrawables(null, d, null, null);

            allAppsButton.setContentDescription(context.getString(R.string.all_apps_button_label));
            allAppsButton.setOnKeyListener(new HotseatIconKeyEventListener());
            if (mLauncher != null) {
                allAppsButton.setOnTouchListener(mLauncher.getHapticFeedbackTouchListener());
                mLauncher.setAllAppsButton(allAppsButton);
                allAppsButton.setOnClickListener(mLauncher);
                allAppsButton.setOnFocusChangeListener(mLauncher.mFocusHandler);
            }

            // Note: We do this to ensure that the hotseat is always laid out in the orientation of
            // the hotseat in order regardless of which orientation they were added
            int x = getCellXFromOrder(mAllAppsButtonRank);
            int y = getCellYFromOrder(mAllAppsButtonRank);
            CellLayout.LayoutParams lp = new CellLayout.LayoutParams(x,y,1,1);
            lp.canReorder = false;
            mContent.addViewToCellLayout(allAppsButton, -1, allAppsButton.getId(), lp, true);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // We don't want any clicks to go through to the hotseat unless the workspace is in
        // the normal state.
        if (mLauncher.getWorkspace().workspaceInModalState()) {
            return true;
        }
        return false;
    }

    void addAllAppsFolder(IconCache iconCache,
            ArrayList<AppInfo> allApps, ArrayList<ComponentName> onWorkspace,
            Launcher launcher, Workspace workspace) {
        if (LauncherAppState.isDisableAllApps()) {
            FolderInfo fi = new FolderInfo();

            fi.cellX = getCellXFromOrder(mAllAppsButtonRank);
            fi.cellY = getCellYFromOrder(mAllAppsButtonRank);
            fi.spanX = 1;
            fi.spanY = 1;
            fi.container = LauncherSettings.Favorites.CONTAINER_HOTSEAT;
            fi.screenId = mAllAppsButtonRank;
            fi.itemType = LauncherSettings.Favorites.ITEM_TYPE_FOLDER;
            fi.title = "More Apps";
            LauncherModel.addItemToDatabase(launcher, fi, fi.container, fi.screenId, fi.cellX,
                    fi.cellY, false);
            FolderIcon folder = FolderIcon.fromXml(R.layout.folder_icon, launcher,
                    getLayout(), fi, iconCache);
            workspace.addInScreen(folder, fi.container, fi.screenId, fi.cellX, fi.cellY,
                    fi.spanX, fi.spanY);

            for (AppInfo info: allApps) {
                ComponentName cn = info.intent.getComponent();
                if (!onWorkspace.contains(cn)) {
                    Log.d(TAG, "Adding to 'more apps': " + info.intent);
                    ShortcutInfo si = info.makeShortcut();
                    fi.add(si);
                }
            }
        }
    }

    void addAppsToAllAppsFolder(ArrayList<AppInfo> apps) {
        if (LauncherAppState.isDisableAllApps()) {
            View v = mContent.getChildAt(getCellXFromOrder(mAllAppsButtonRank), getCellYFromOrder(mAllAppsButtonRank));
            FolderIcon fi = null;

            if (v instanceof FolderIcon) {
                fi = (FolderIcon) v;
            } else {
                return;
            }

            FolderInfo info = fi.getFolderInfo();
            for (AppInfo a: apps) {
                ShortcutInfo si = a.makeShortcut();
                info.add(si);
            }
        }
    }
}
