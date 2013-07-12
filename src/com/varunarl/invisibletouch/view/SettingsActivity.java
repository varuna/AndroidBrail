package com.varunarl.invisibletouch.view;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.SettingsManager;

public class SettingsActivity extends SixPackActivity {

    private SettingsManager mSettingsManager;
    private boolean FLAG_EDIT_TTS_VOLUME = false;
    private boolean FLAG_EDIT_TTS_SPEED = false;

    @Override
    protected void init() {
        super.init();
        mSettingsManager = InvisibleTouchApplication.getInstance().getSettingsManager();
    }

    @Override
    public void onLongKeyOne() {

    }

    @Override
    public void onLongKeyTwo() {

    }

    @Override
    public void onLongKeyThree() {

    }

    @Override
    public void onLongKeyFour() {

    }

    @Override
    public void onLongKeyFive() {

    }

    @Override
    public void onLongKeySix() {

    }

    @Override
    public void onSwipeRight() {

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
        SettingsManager settingsManager = InvisibleTouchApplication.getInstance().getSettingsManager();
        if (FLAG_EDIT_TTS_VOLUME)
            settingsManager.getSettings().setTTSVolume(settingsManager.getSettings().getTTSVolume() - 1);
        else if (FLAG_EDIT_TTS_SPEED)
            settingsManager.getSettings().setTTSSpeed(settingsManager.getSettings().getTTSSpeed() - 0.2f);

    }

    @Override
    public void onVolumeDownKeyLongPress() {

    }

    @Override
    public void onVolumeUpKeyShortPress() {
        SettingsManager settingsManager = InvisibleTouchApplication.getInstance().getSettingsManager();
        if (FLAG_EDIT_TTS_VOLUME)
            settingsManager.getSettings().setTTSVolume(settingsManager.getSettings().getTTSVolume() + 1);
        else if (FLAG_EDIT_TTS_SPEED)
            settingsManager.getSettings().setTTSSpeed(settingsManager.getSettings().getTTSSpeed() + 0.2f);

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
        Log.announce("In Settings", true);
    }

    @Override
    public void onKeyOne() {
        super.onKeyOne();
        FLAG_EDIT_TTS_VOLUME = true;
        FLAG_EDIT_TTS_SPEED = false;
    }

    @Override
    public void onKeyTwo() {
        super.onKeyTwo();
        FLAG_EDIT_TTS_VOLUME = false;
        FLAG_EDIT_TTS_SPEED = true;
    }

    @Override
    public void onKeyThree() {
        super.onKeyThree();
    }

    @Override
    public void onKeyFour() {
        super.onKeyFour();
    }

    @Override
    public void onKeyFive() {
        super.onKeyFive();
    }

    @Override
    public void onKeySix() {
        super.onKeySix();
        InvisibleTouchApplication.getInstance().getSettingsManager().restorFacotryDefaults();
    }
}
