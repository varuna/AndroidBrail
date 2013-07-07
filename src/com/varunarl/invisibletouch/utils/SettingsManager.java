package com.varunarl.invisibletouch.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by vlekamwasam on 5/28/13.
 */
public class SettingsManager implements TextToSpeech.OnInitListener {

    private Context mContext;
    private TextToSpeech mTTS;
    private Vibrator mVibrator;
    private boolean _TTS_SERVICE_READY_ = false;
    private boolean _VIBRATOR_SERVICE_READY_ = false;

    public SettingsManager(Context context) {
        this.mContext = context;
        if (!isTTSReady())
            requestTTSService();
        if (!isVibratorReady())
            requestVibratorService();
    }

    public boolean isTTSReady() {
        return this._TTS_SERVICE_READY_;
    }

    public boolean isVibratorReady() {
        return this._VIBRATOR_SERVICE_READY_;
    }

    public void activateVibratorService(boolean flag) {
        if (flag)
            if (!isVibratorReady())
                requestVibratorService();
        this._VIBRATOR_SERVICE_READY_ = flag;
    }

    public void activateTextToSpeechEngine(boolean flag) {
        if (flag)
            if (!isTTSReady())
                requestTTSService();
        this._TTS_SERVICE_READY_ = flag;
    }

    public void requestVibratorService() {
        if (!this._VIBRATOR_SERVICE_READY_) {
            if (mVibrator == null)
                mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
            if (mVibrator != null) {
                this._VIBRATOR_SERVICE_READY_ = true;
                Log.announce("Vibrator service ready", Log.Level.INFO);
            }
        }
    }

    public void requestTTSService() {
        if (!this._TTS_SERVICE_READY_ || mTTS == null) {
            mTTS = new TextToSpeech(mContext, this);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = mTTS.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Intent installIntent = new Intent();
                installIntent.setAction(
                        TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(installIntent);
                Log.announce("TextToSpeech service is broken.", Log.Level.INFO);
            } else {
                this._TTS_SERVICE_READY_ = true;
                Log.announce("TextToSpeech service is ready.", Log.Level.INFO);
            }
        } else
            Log.announce("TextToSpeech service is broken.", Log.Level.INFO);
    }

    public TextToSpeech getTTSEngine() {
        if (mTTS != null) {
            if (isTTSReady())
                return mTTS;
            else {
                mTTS = null;
                this._TTS_SERVICE_READY_ = false;
                return null;
            }
        } else {
            if (isTTSReady())
                this._TTS_SERVICE_READY_ = false;
            return null;
        }
    }

    public Vibrator getVibratorService() {
        if (mVibrator != null) {
            if (isVibratorReady())
                return mVibrator;
            else {
                mVibrator = null;
                this._VIBRATOR_SERVICE_READY_ = false;
                return null;
            }
        } else {
            if (isVibratorReady())
                this._VIBRATOR_SERVICE_READY_ = false;
            return null;
        }
    }
}
