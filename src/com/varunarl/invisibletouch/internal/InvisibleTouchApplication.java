package com.varunarl.invisibletouch.internal;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import com.varunarl.invisibletouch.utils.CallManager;
import com.varunarl.invisibletouch.utils.InputManager.TextInputManager;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;

public class InvisibleTouchApplication extends Application implements OnInitListener {

    private static InvisibleTouchApplication instance;
    private Vibrator mVibratorService;
    private TextToSpeech mTTS;
    private TextInputManager mTextInputManager;
    private CallManager mCallManager;
    private boolean incomingCallDetected;

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
        incomingCallDetected = false;
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

    public void exit()
    {
        mCallManager.destroy();
    }
}