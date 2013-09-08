package com.varunarl.invisibletouch.internal;

import android.app.Application;
import android.speech.tts.TextToSpeech;

import com.varunarl.invisibletouch.utils.CallManager;
import com.varunarl.invisibletouch.utils.ContactManager;
import com.varunarl.invisibletouch.utils.InputManager.TextInputManager;
import com.varunarl.invisibletouch.utils.SettingsManager;
import com.varunarl.invisibletouch.utils.SoundManager;

import java.util.HashMap;

public class InvisibleTouchApplication extends Application {

    private static InvisibleTouchApplication instance;
    private TextInputManager mTextInputManager;
    private CallManager mCallManager;
    private ContactManager mContactManager;
    private SettingsManager mSettingsManager;
    private SoundManager mSoundsManager;
    private PluginManager mPluginManager;
    private boolean _KILL_SIGNAL_ = false;

    public static InvisibleTouchApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mTextInputManager = new TextInputManager(this);
        mSettingsManager = new SettingsManager(this);
        mCallManager = new CallManager(this);
        mContactManager = new ContactManager(this);
        mSoundsManager = new SoundManager(this);
        mPluginManager = new PluginManager(this);
    }

    public TextInputManager getTextInputManager() {
        return mTextInputManager;
    }

    public ContactManager getContactManager() {
        return mContactManager;
    }

    public CallManager getCallManager() {
        return mCallManager;
    }

    public SettingsManager getSettingsManager() {
        return mSettingsManager;
    }

    public SoundManager getSoundsManager() {
        return mSoundsManager;
    }

    public PluginManager getPluginManager() {
        return mPluginManager;
    }

    public void speak(String speech) {
        if (mSettingsManager.isTTSReady())
            mSettingsManager.getTTSEngine().speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void speak(String speech, HashMap<String, String> params) {
        if (mSettingsManager.isTTSReady())
            mSettingsManager.getTTSEngine().speak(speech, TextToSpeech.QUEUE_FLUSH, params);
    }

    public void vibrate(long[] pattern) {
        if (mSettingsManager.isVibratorReady()) {
            try {
                mSettingsManager.getVibratorService().vibrate(pattern, -1);
            } catch (Exception e) {
                mSettingsManager.getSettings().setVibrationEnabled(false);
            }
        }
    }

    public void forceQuitApp(BaseActivity context) {
        _KILL_SIGNAL_ = true;
        context.quit();
        mSettingsManager.resetDeviceConfigurations();
    }

    public boolean shouldKillApp() {
        return _KILL_SIGNAL_;
    }

    public void killed() {
        _KILL_SIGNAL_ = false;
    }

}
