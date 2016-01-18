package com.example.weatherapp.utils;


import android.os.Handler;

import com.google.common.eventbus.EventBus;

public class Background {
    private final Handler mUIHolder;
    private final EventBus mEventBus;

    public Background(EventBus bus){
        mUIHolder = new Handler();
        mEventBus = bus;
    }

    public void postEventOnUIThread(final Object event){
        mUIHolder.post(new Runnable() {
            @Override
            public void run() {
                mEventBus.post(event);
            }
        });
    }
}
