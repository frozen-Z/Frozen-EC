package com.ao.frozens.timer;

import java.util.TimerTask;

/**
 * com.ao.frozens.timer
 * <p>计时器
 * Created by Leo on 2017/11/22.
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener iTimerListener;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.iTimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (iTimerListener != null)
            iTimerListener.onTimer();
    }
}
