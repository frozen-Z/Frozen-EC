package com.ao.frozens.app;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;

/**
 * com.ao.frozens.app
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public final class Frozen {


    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static HashMap<Object, Object> getConfigurations(){
       return Configurator.getInstance().getFrozenConfigs();
    }

    public static Application getApplication(){
        return (Application) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
}
