package com.ao.frozens.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.ao.frozens.R;
import com.ao.frozens.ui.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * com.ao.frozens.ui
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class FrozenLolder {

    private static final int LOLDER_SIZE_SCALE = 8;
    private static final int LOLDER_OFFSET_SCALE = 10;
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLolding(Context context, String type) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeigth = DimenUtil.getScreenHeight();
        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = deviceWidth / LOLDER_SIZE_SCALE;
            layoutParams.height = deviceHeigth / LOLDER_SIZE_SCALE;
            layoutParams.height = layoutParams.height + deviceHeigth / LOLDER_OFFSET_SCALE;
            layoutParams.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLolding(Context context){
        showLolding(context,DEFAULT_LOADER);

    }
    public static void stopLolding(){

        for (AppCompatDialog dialog : LOADERS){
            if (dialog != null){
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }
}
