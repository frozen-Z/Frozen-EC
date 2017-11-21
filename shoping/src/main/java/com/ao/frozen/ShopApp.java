package com.ao.frozen;

import android.app.Application;

import com.ao.frozens.app.Frozen;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * com.ao.frozen
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class ShopApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Frozen.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .configure();
    }
}
