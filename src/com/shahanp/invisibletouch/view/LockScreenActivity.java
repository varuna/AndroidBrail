package com.shahanp.invisibletouch.view;

import android.view.View;
import android.view.WindowManager;

import com.shahanp.invisibletouch.internal.ScreenHelper;
import com.shahanp.invisibletouch.internal.SinglePackActivity;
import com.shahanp.invisibletouch.utils.Log;

public class LockScreenActivity extends SinglePackActivity {

    // Double Swipe unlock the screen

    @Override
    protected void onAttachView(int id, View view) {

    }

    @Override
    public void onKeyOne() {

    }

    @Override
    public void onLongKeyOne() {
        Log.announce(ScreenHelper.LOCK_SCREEN_HELPER, true);
    }

    @Override
    public void onSwipeRight() {

    }

    @Override
    public void onSwipeLeft() {

    }

    @Override
    public void onDoubleSwipeRight() {
        finish();
        Log.announce("Unlocked.", true);
    }

    @Override
    public void onDoubleSwipeLeft() {
        finish();
        Log.announce("Unlocked.", true);
    }

    @Override
    public void onSwipeUp() {

    }

    @Override
    public void onDoubleSwipeUp() {
        finish();
        Log.announce("Unlocked.", true);
    }

    @Override
    public void onSwipeDown() {

    }

    @Override
    public void onDoubleSwipeDown() {
        finish();
        Log.announce("Unlocked.", true);
    }

    @Override
    public void onVolumeDownKeyShortPress() {

    }

    @Override
    public void onVolumeDownKeyLongPress() {
        Log.announce(ScreenHelper.LOCK_SCREEN_HELPER, true);
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
        Log.announce(ScreenHelper.LOCK_ACTIVATE, true);
    }




}
