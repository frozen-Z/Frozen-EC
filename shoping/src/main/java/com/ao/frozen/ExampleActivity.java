package com.ao.frozen;

import com.ao.frozenec.launcher.LauncherDelegate;
import com.ao.frozens.activitys.ProxyActivity;
import com.ao.frozens.delegates.FrozenDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public FrozenDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
