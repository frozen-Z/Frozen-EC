package com.ao.frozens.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * com.ao.frozens.ui
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOAD_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {

        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOAD_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOAD_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOAD_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {

        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {

            final String defultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
