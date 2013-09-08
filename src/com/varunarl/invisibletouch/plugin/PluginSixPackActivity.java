package com.varunarl.invisibletouch.plugin;

import com.varunarl.invisibletouch.internal.SixPackActivity;

public abstract class PluginSixPackActivity extends SixPackActivity{
    @Override
    public void onSwipeLeft() {
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
