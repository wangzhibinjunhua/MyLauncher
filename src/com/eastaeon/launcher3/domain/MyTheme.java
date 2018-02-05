
package com.eastaeon.launcher3.domain;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class MyTheme {

    private String theme_name = null;
    private Drawable theme_icon = null;

    public MyTheme(String theme_name, Drawable theme_icon) {
        super();
        this.theme_name = theme_name;
        this.theme_icon = theme_icon;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    public Drawable getTheme_icon() {
        return theme_icon;
    }

    public void setTheme_icon(Drawable theme_icon) {
        this.theme_icon = theme_icon;
    }

}
