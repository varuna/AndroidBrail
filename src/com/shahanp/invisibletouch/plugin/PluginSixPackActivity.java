package com.shahanp.invisibletouch.plugin;

import com.shahanp.invisibletouch.internal.SixPackActivity;

public abstract class PluginSixPackActivity extends SixPackActivity{
    @Override
    public void onSwipeLeft() {
        finish();
    }
}
