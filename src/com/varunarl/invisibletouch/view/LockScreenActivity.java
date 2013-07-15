package com.varunarl.invisibletouch.view;

import android.view.View;
import android.view.WindowManager;

import com.varunarl.invisibletouch.internal.ScreenHelper;
import com.varunarl.invisibletouch.internal.SinglePackActivity;
import com.varunarl.invisibletouch.utils.Log;

public class LockScreenActivity extends SinglePackActivity {

    @Override
    protected void onAttachView(int id, View view) {

    }

    @Override
    public void onKeyOne() {

    }

    @Override
    public void onLongKeyOne() {

    }

    @Override
    public void onSwipeRight() {
        finish();
    }

    @Override
    public void onSwipeLeft() {

    }

    @Override
    public void onDoubleSwipeRight() {

    }

    @Override
    public void onDoubleSwipeLeft() {

    }

    @Override
    public void onSwipeUp() {

    }

    @Override
    public void onDoubleSwipeUp() {

    }

    @Override
    public void onSwipeDown() {

    }

    @Override
    public void onDoubleSwipeDown() {

    }

    @Override
    public void onVolumeDownKeyShortPress() {

    }

    @Override
    public void onVolumeDownKeyLongPress() {

    }

    @Override
    public void onVolumeUpKeyShortPress() {

    }

    @Override
    public void onVolumeUpKeyLongPress() {

    }

    @Override
    public void onCameraKeyShortPress() {

    }

    @Override
    public void onCameraKeyLongPress() {

    }

    @Override
    public void onScreenLongPress() {
        Log.announce(ScreenHelper.LOCK_SCREEN_HELPER,true);
    }

    @Override
    protected void init() {
        super.init();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }
}
