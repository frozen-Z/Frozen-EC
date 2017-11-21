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
        getConfigurations().put(ConfigTyoe.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations(){
       return Configurator.getInstance().getFrozenConfigs();
    }

    public static Application getApplication(){
        return (Application) getConfigurations().get(ConfigTyoe.APPLICATION_CONTEXT.name());
    }
}
