package com.ao.frozen;

import com.ao.frozenec.launcher.LauncherScrollDelegate;
import com.ao.frozens.activitys.ProxyActivity;
import com.ao.frozens.delegates.FrozenDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public FrozenDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }
}
