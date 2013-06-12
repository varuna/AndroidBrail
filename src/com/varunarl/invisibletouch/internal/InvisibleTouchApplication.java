package com.varunarl.invisibletouch.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import com.varunarl.invisibletouch.utils.CallManager;
import com.varunarl.invisibletouch.utils.ContactManager;
import com.varunarl.invisibletouch.utils.InputManager.TextInputManager;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;

public class InvisibleTouchApplication extends Application implements OnInitListener {

    private static InvisibleTouchApplication instance;
    private Vibrator mVibratorService;
    private TextToSpeech mTTS;
    private TextInputManager mTextInputManager;
    private CallManager mCallManager;
    private ContactManager mContactManager;
    private boolean incomingCallDetected;
    private ActivityResults mResultsManager;

    public static InvisibleTouchApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mTextInputManager = new TextInputManager(this);
        mVibratorService = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mCallManager = new CallManager(this);
        mContactManager = new ContactManager(this);
        incomingCallDetected = false;
        mResultsManager = new ActivityResults();
        if (mVibratorService != null)
            Log.announce("Vibrator service ready", Level.INFO);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            mTTS = new TextToSpeech(getApplicationContext(), this);
            Log.announce("TextToSpeech service is ready.", Level.INFO);
        }

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

    public boolean isIncomingCallDetected() {
        return incomingCallDetected;
    }

    public void setIncomingCallDetected(boolean incomingCallDetected) {
        this.incomingCallDetected = incomingCallDetected;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Log.announce("TextToSpeech service is ready.", Level.INFO);
        } else
            Log.announce("TextToSpeech service is broken.", Level.INFO);
    }

    public void speak(String speech) {
        mTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void vibrate(long[] pattern) {
        if (mVibratorService != null) {
            try {
                mVibratorService.vibrate(pattern, -1);
            } catch (Exception e) {
                // On some devices vibrator would crash. Think its to do with
                // rooting of the device.
            }
        }
    }

    public void exit() {
        mCallManager.destroy();
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
