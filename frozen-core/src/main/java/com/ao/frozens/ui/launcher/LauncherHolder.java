package com.ao.frozens.ui.launcher;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * com.ao.frozens.ui.launcher
 * <p>
 * <p>
 * Created by Leo on 2017/11/22.
 */

public class LauncherHolder implements Holder<Integer> {

    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        mImageView = new ImageView(context);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
