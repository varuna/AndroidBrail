package com.varunarl.invisibletouch.view.sub;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.ScreenHelper;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.SettingsManager;
import com.varunarl.invisibletouch.view.SettingsActivity;


public class AccessibilitySettingsActivity extends SettingsActivity {

    protected boolean FLAG_EDIT_TTS_VOLUME = false;
    protected boolean FLAG_EDIT_TTS_SPEED = false;
    protected boolean FLAG_EDIT_TTS_PITCH = false;

    @Override
    public void onVolumeDownKeyShortPress() {
        SettingsManager settingsManager = InvisibleTouchApplication.getInstance().getSettingsManager();
        if (FLAG_EDIT_TTS_VOLUME)
            settingsManager.getSettings().setTTSVolume(settingsManager.getSettings().getTTSVolume() - 1);
        else if (FLAG_EDIT_TTS_SPEED)
            settingsManager.getSettings().setTTSSpeed(settingsManager.getSettings().getTTSSpeed() - 0.2f);
        else if (FLAG_EDIT_TTS_PITCH)
            settingsManager.getSettings().setTTSPitch(settingsManager.getSettings().getTTSPitch() - 0.1f);

    }

    @Override
    public void onVolumeUpKeyShortPress() {
        SettingsManager settingsManager = InvisibleTouchApplication.getInstance().getSettingsManager();
        if (FLAG_EDIT_TTS_VOLUME)
            settingsManager.getSettings().setTTSVolume(settingsManager.getSettings().getTTSVolume() + 1);
        else if (FLAG_EDIT_TTS_SPEED)
            settingsManager.getSettings().setTTSSpeed(settingsManager.getSettings().getTTSSpeed() + 0.2f);
        else if (FLAG_EDIT_TTS_PITCH)
            settingsManager.getSettings().setTTSPitch(settingsManager.getSettings().getTTSPitch() +0.1f);

    }

    @Override
    public void onKeyOne() {
        super.onKeyOne();
        mSettingsManager.getSettings().setTTSEnabled(!mSettingsManager.getSettings().getTTSEnabled());
    }

    @Override
    public void onLongKeyOne() {
        super.onLongKeyOne();
        Log.announce(ScreenHelper.SETTINGS_TEXT_TO_SPEECH_TOGGLE,true);
    }

    @Override
    public void onKeyTwo() {
        super.onKeyTwo();
        FLAG_EDIT_TTS_VOLUME = true;
        FLAG_EDIT_TTS_SPEED = false;
        FLAG_EDIT_TTS_PITCH = false;
    }

    @Override
    public void onLongKeyTwo() {
        super.onLongKeyTwo();
        Log.announce(ScreenHelper.SETTINGS_TEXT_TO_SPEECH_VOLUME,true);
    }

    @Override
    public void onKeyThree() {
        super.onKeyThree();
        FLAG_EDIT_TTS_VOLUME = false;
        FLAG_EDIT_TTS_SPEED = true;
        FLAG_EDIT_TTS_PITCH = false;
    }

    @Override
    public void onLongKeyThree() {
        super.onLongKeyThree();
        Log.announce(ScreenHelper.SETTINGS_TEXT_TO_SPEECH_SPEED,true);
    }

    @Override
    public void onKeyFour() {
        super.onKeyFour();
        FLAG_EDIT_TTS_VOLUME = false;
        FLAG_EDIT_TTS_SPEED = false;
        FLAG_EDIT_TTS_PITCH = true;
    }

    @Override
    public void onLongKeyFour() {
        super.onLongKeyFour();
        Log.announce(ScreenHelper.SETTINGS_TEXT_TO_SPEECH_PITCH,true);
    }

    @Override
    public void onSwipeLeft() {
        finish();
    }

    @Override
    public void onLongKeyFive() {
        Log.announce(ScreenHelper.ACCESSIBILITY_SETTINGS_SCREEN_HELPER,true);
    }

    @Override
    public void onLongKeySix() {
        Log.announce(ScreenHelper.ACCESSIBILITY_SETTINGS_SCREEN_HELPER,true);
    }

    @Override
    public void onScreenLongPress() {
    }
}
