package com.varunarl.invisibletouch.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import com.varunarl.invisibletouch.utils.CallManager;
import com.varunarl.invisibletouch.utils.ContactManager;
import com.varunarl.invisibletouch.utils.InputManager.TextInputManager;
import com.varunarl.invisibletouch.utils.SettingsManager;

import java.util.HashMap;

public class InvisibleTouchApplication extends Application {

    private static InvisibleTouchApplication instance;
    private TextInputManager mTextInputManager;
    private CallManager mCallManager;
    private ContactManager mContactManager;
    private SettingsManager mSettingsManager;
    private ActivityResults mResultsManager;
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
        mResultsManager = new ActivityResults();
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

    public void speak(String speech) {
        if (mSettingsManager.isTTSReady())
            mSettingsManager.getTTSEngine().speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void speak(String speech, HashMap<String,String> params) {
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

    public Bundle getData() {
        return mResultsManager.getData();
    }

    public void setData(Bundle data) {
        mResultsManager.setData(data);
    }

    public int getResult() {
        return mResultsManager.getResult();
    }

    public void setResult(int result) {
        mResultsManager.setResult(result);
    }

    public void forceQuitApp(BaseActivity context) {
        context.finish();
        _KILL_SIGNAL_ = true;
    }

    public boolean shouldKillApp() {
        return _KILL_SIGNAL_;
    }

    public void killed() {
        _KILL_SIGNAL_ = false;
    }

    public class ActivityResults {
        private int mResult = -1;
        private Bundle mResultsData = null;

        public Bundle getData() {
            return mResultsData;
        }

        public void setData(Bundle data) {
            mResultsData = data;
        }

        public int getResult() {
            return mResult;
        }

        public void setResult(int result) {
            if (result == Activity.RESULT_CANCELED || result == Activity.RESULT_OK) {
                mResult = result;
            }
        }


    }
}
