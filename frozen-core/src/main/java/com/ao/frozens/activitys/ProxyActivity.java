package com.ao.frozens.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.ao.frozens.R;
import com.ao.frozens.delegates.FrozenDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * com.ao.frozens.activitys
 * <p>
 * <p>
 * Created by Leo on 2017/11/21.
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract FrozenDelegate setRootDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.deletage_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.deletage_container,setRootDelegate());
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        System.gc();
        System.runFinalization();
    }
}
