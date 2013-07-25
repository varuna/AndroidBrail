package com.varunarl.invisibletouch.view;

import android.content.Intent;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.ScreenHelper;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.SettingsManager;
import com.varunarl.invisibletouch.view.sub.AccessibilitySettingsActivity;

public class SettingsActivity extends SixPackActivity {

    protected SettingsManager mSettingsManager;


    @Override
    protected void init() {
        super.init();
        mSettingsManager = InvisibleTouchApplication.getInstance().getSettingsManager();
        setViewText(0,"KeyTones","enable/disable key tones");
        setViewText(1,"Vibrations","enable/disable vibrations");
        setViewText(2,"Recovery","enable/disable system recovery");
        setViewText(3,"TTS speed","Increase/decrease TTS speed");
        setViewText(3,"TTS rate","Increase/decrease TTS rate");
        setViewText(3,"TTS pitch","Increase/decrease TTS pitch");
    }

    @Override
    public void onLongKeyOne() {
        Log.announce(ScreenHelper.SETTINGS_ACCESSIBILITY,true);
    }

    @Override
    public void onLongKeyTwo() {
        Log.announce(ScreenHelper.SETTINGS_VIBRATION_TOGGLE,true);
    }

    @Override
    public void onLongKeyThree() {
        Log.announce(ScreenHelper.SETTINGS_RESET_FACTORY_DEFAULTS,true);
    }

    @Override
    public void onLongKeyFour() {
        Log.announce(ScreenHelper.SETTINGS_SYSTEM_RECOVERY,true);
    }

    @Override
    public void onLongKeyFive() {

    }

    @Override
    public void onLongKeySix() {
        Log.announce(ScreenHelper.SETTINGS_MORE,true);
    }

    @Override
    public void onSwipeRight() {

    }

    @Override
    public void onSwipeLeft() {
        finish();
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
        Log.announce(ScreenHelper.SETTINGS_SCREEN_HELPER, true);
    }

    @Override
    public void onKeyOne() {
        super.onKeyOne();
        Intent accessibilityIntent = new Intent(this, AccessibilitySettingsActivity.class);
        startActivity(accessibilityIntent);
    }

    @Override
    public void onKeyTwo() {
        super.onKeyTwo();
        mSettingsManager.getSettings().setVibrationEnabled(!mSettingsManager.getSettings().getVibrationEnabled());
    }

    @Override
    public void onKeyThree() {
        super.onKeyThree();
        InvisibleTouchApplication.getInstance().getSettingsManager().restoreFactoryDefaults();
    }

    @Override
    public void onKeyFour() {
        super.onKeyFour();
        mSettingsManager.getSettings().setSystemRecovery(!mSettingsManager.getSettings().getSystemRecovery());
    }

    @Override
    public void onKeyFive() {
        super.onKeyFive();
        //free slot
    }

    @Override
    public void onKeySix() {
        super.onKeySix();
        //Reserved for more settings.
    }
}
