package com.ao.frozens.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * com.ao.frozens.app
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public class Configurator {

    private static final HashMap<String ,Object> FROZEN_CONFIGS = new HashMap<>();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private void initIcons(){
        if (ICONS.size() > 0 ){
            Iconify.IconifyInitializer iconifyInitializer = Iconify.with(ICONS.get(0));
            for (int i = 1 ; i < ICONS.size() ;i ++){
                iconifyInitializer.with(ICONS.get(i));
            }
        }
    }

    private Configurator(){
        initIcons();
        FROZEN_CONFIGS.put(ConfigTyoe.CONFIG_REDAY.name(),false);
    }

    private static class Holder{
        final static Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String ,Object> getFrozenConfigs(){
        return FROZEN_CONFIGS;
    }

    public final void configure(){
        FROZEN_CONFIGS.put(ConfigTyoe.CONFIG_REDAY.name(),true);
    }

    public final Configurator withApiHost(String host){
        FROZEN_CONFIGS.put(ConfigTyoe.API_HOST.name(),host);
        return this;
    }

    private void checkConfiguration(){
        final boolean isRead = (boolean) FROZEN_CONFIGS.get(ConfigTyoe.CONFIG_REDAY.name());
        if (!isRead){
            throw new  RuntimeException ("Configuration is not read,call configure");
        }

    }
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigTyoe> key){
        checkConfiguration();
        return (T)FROZEN_CONFIGS.get(key.name());
    }
}
