package com.ao.frozen;

import android.util.Log;

import com.ao.frozens.activitys.ProxyActivity;
import com.ao.frozens.delegates.FrozenDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public FrozenDelegate setRootDelegate() {
        Log.e("--main--", "ExampleActivity 类的 setRootDelegate 方法 调用");
        return new ExampleDelegate();
    }
}
