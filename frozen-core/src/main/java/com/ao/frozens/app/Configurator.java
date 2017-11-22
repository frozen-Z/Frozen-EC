package com.ao.frozens.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * com.ao.frozens.app
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class Configurator {

    private static final HashMap<Object ,Object> FROZEN_CONFIGS = new HashMap<>();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private void initIcons(){
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    private Configurator(){
        initIcons();
        FROZEN_CONFIGS.put(ConfigType.CONFIG_REDAY.name(),false);
    }

    private static class Holder{
        final static Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<Object ,Object> getFrozenConfigs(){
        return FROZEN_CONFIGS;
    }

    public final void configure(){
        FROZEN_CONFIGS.put(ConfigType.CONFIG_REDAY.name(),true);
    }

    public final Configurator withApiHost(String host){
        FROZEN_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) FROZEN_CONFIGS.get(ConfigType.CONFIG_REDAY.name());
        if (!isReady){
            throw new  RuntimeException ("Configuration is not read,call configure");
        }

    }
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        FROZEN_CONFIGS.put(ConfigType.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        FROZEN_CONFIGS.put(ConfigType.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key){
        checkConfiguration();
        return (T)FROZEN_CONFIGS.get(key);
    }
}
