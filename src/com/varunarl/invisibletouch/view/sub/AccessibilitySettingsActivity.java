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
            settingsManager.getSettings().setTTSPitch(settingsManager.getSettings().getTTSPitch() + 0.1f);

    }

    @Override
    public void onKeyOne() {
        FLAG_EDIT_TTS_VOLUME = true;
        FLAG_EDIT_TTS_SPEED = false;
        FLAG_EDIT_TTS_PITCH = false;
    }

    @Override
    public void onLongKeyOne() {
        super.onLongKeyOne();
        Log.announce(ScreenHelper.SETTINGS_TEXT_TO_SPEECH_VOLUME, true);
    }

    @Override
    public void onKeyTwo() {
        super.onKeyTwo();
        FLAG_EDIT_TTS_VOLUME = false;
        FLAG_EDIT_TTS_SPEED = true;
        FLAG_EDIT_TTS_PITCH = false;
    }

    @Override
    public void onLongKeyTwo() {
        super.onLongKeyTwo();
        Log.announce(ScreenHelper.SETTINGS_TEXT_TO_SPEECH_SPEED, true);
    }

    @Override
    public void onKeyThree() {
        super.onKeyThree();
        FLAG_EDIT_TTS_VOLUME = false;
        FLAG_EDIT_TTS_SPEED = false;
        FLAG_EDIT_TTS_PITCH = true;
    }

    @Override
    public void onLongKeyThree() {
        super.onLongKeyThree();
        Log.announce(ScreenHelper.SETTINGS_TEXT_TO_SPEECH_PITCH, true);
    }

    @Override
    public void onSwipeLeft() {
        finish();
    }

    @Override
    protected void init() {
        super.init();
        setViewText(0, "Volume", "Increase or decrease TTS volume");
        setViewText(1, "Speed", "Increase or decrease TTS speed");
        setViewText(2, "Pitch", "Increase or decrease TTS pitch");
        setViewText(3, null, null);
        setViewText(4, null, null);
        setViewText(5, null, null);
        Log.announce(ScreenHelper.ACCESSIBILITY_ACTIVATE, true);
    }

    @Override
    public void onVolumeDownKeyLongPress() {
        Log.announce(ScreenHelper.ACCESSIBILITY_SCREEN_HELPER, true);
        super.onVolumeDownKeyLongPress();
    }

    @Override
    public void onVolumeUpKeyLongPress() {
        //DATE AND TIME
        super.onVolumeUpKeyLongPress();
    }
}
