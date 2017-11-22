package com.ao.frozens.utils.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ao.frozens.app.Frozen;

/**
 * com.ao.frozens.ui.util
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Frozen.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Frozen.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
