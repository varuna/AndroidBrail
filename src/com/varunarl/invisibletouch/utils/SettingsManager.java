package com.varunarl.invisibletouch.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;

import java.util.Locale;


public class SettingsManager implements TextToSpeech.OnInitListener {

    public static final String PREFERENCE_USER = "com.varunarl.invisibletouch.USER_PREFERENCE";
    public static final String PREF_KEY_TTS_ENABLE = "com.varunarl.invisibletouch.USER_PREFERENCE.ENABLE_TTS";
    public static final String PREF_KEY_TTS_VOLUME = "com.varunarl.invisibletouch.USER_PREFERENCE.TTS_VOLUME";
    public static final String PREF_KEY_TTS_SPEED = "com.varunarl.invisibletouch.USER_PREFERENCE.TTS_SPEED";
    public static final String PREF_KEY_TTS_PITCH = "com.varunarl.invisibletouch.USER_PREFERENCE.TTS_PITCH";
    public static final String PREF_KEY_KEYTONES_ENABLE = "com.varunarl.invisibletouch.USER_PREFERENCE.ENABLE_KEYTONES";
    public static final String PREF_KEY_VIBRATION_ENABLE = "com.varunarl.invisibletouch.USER_PREFERENCE.ENABLE_VIBRATION";
    public static final String PREF_KEY_RECOVER_SYSTEM = "com.varunarl.invisibletouch.USER_PREFERENCE.RECOVER_SYSTEM";
    public static final String PREF_KEY_RINGING_VOLUME = "com.varunarl.invisibletouch.USER_PREFERENCE.RINGING_VOLUME";
    private static final String PREFERENCE_INTERNAL = "com.varunarl.invisibletouch.INTERNAL_PREFERENCE";
    private static final String PREFERENCE_INTERNAL_PRECONFIG = "com.varunarl.invisibletouch.INTERNAL_PRECONFIG_PREFERENCE";
    private static final String PREFERENCE_INTERNAL_PRECONFIG_INIT = "com.varunarl.invisibletouch.INTERNAL_PRECONFIG_INITIALIZED_PREFERENCE";
    private static final String PREF_KEY_INTERNAL_RECOVER_SYSTEM = "com.varunarl.invisibletouch.INTERNAL_PREFERENCE.ENABLE_TTS";
    private Context mContext;
    private TextToSpeech mTTS;
    private Vibrator mVibrator;
    private boolean _TTS_SERVICE_READY_ = false;
    private boolean _VIBRATOR_SERVICE_READY_ = false;


    public SettingsManager(Context context) {
        this.mContext = context;

        PreConfig config = new PreConfig();
        config.rememberConfig();

        UserSettings settings = getSettings();
        settings.setVibrationEnabled(settings.getVibrationEnabled());
        settings.setSystemRecovery(settings.getSystemRecovery());
        settings.setTTSVolume(settings.getTTSVolume());
        settings.setRingingVolume(settings.getRingingVolume());
        settings.setTTSEnabled(settings.getTTSEnabled());
        settings.setKeypadTonesEnable(settings.getKeypadTonesEnabled());
        if (isTTSReady()) {
            settings.setTTSPitch(settings.getTTSPitch());
            settings.setTTSSpeed(settings.getTTSSpeed());
        }
    }

    private static void writeToPreference(String preferenceId, String preference, Object value) {
        Context ctx = InvisibleTouchApplication.getInstance();
        SharedPreferences pref = ctx.getSharedPreferences(preferenceId, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = pref.edit();
        if (value instanceof Boolean)
            prefEdit.putBoolean(preference, (Boolean) value);
        else if (value instanceof String)
            prefEdit.putString(preference, (String) value);
        else if (value instanceof Integer)
            prefEdit.putInt(preference, (Integer) value);
        else if (value instanceof Float)
            prefEdit.putFloat(preference, (Float) value);
        else if (value instanceof Long)
            prefEdit.putLong(preference, (Long) value);
        prefEdit.apply();
        prefEdit.commit();
    }

    private static Object readFromPreference(String preferenceId, String preference) {
        Context ctx = InvisibleTouchApplication.getInstance();
        SharedPreferences pref = ctx.getSharedPreferences(preferenceId, Context.MODE_PRIVATE);
        return pref.getAll().get(preference);
    }

    private static SharedPreferences readFromPreference(String preferenceId) {
        Context ctx = InvisibleTouchApplication.getInstance();
        return ctx.getSharedPreferences(preferenceId, Context.MODE_PRIVATE);
    }

    public void restoreFactoryDefaults() {
        writeToPreference(PREFERENCE_INTERNAL, PREF_KEY_INTERNAL_RECOVER_SYSTEM, true);
        writeToPreference(PREFERENCE_USER, PREF_KEY_RECOVER_SYSTEM, true);
        writeToPreference(PREFERENCE_USER, PREF_KEY_TTS_ENABLE, false);
        writeToPreference(PREFERENCE_USER, PREF_KEY_VIBRATION_ENABLE, true);
        writeToPreference(PREFERENCE_USER, PREF_KEY_KEYTONES_ENABLE, true);
        AudioManager am = (AudioManager) InvisibleTouchApplication.getInstance().getSystemService(Context.AUDIO_SERVICE);
        getSettings().setTTSVolume(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        getSettings().setRingingVolume(am.getStreamMaxVolume(AudioManager.STREAM_RING));
        if (isTTSReady()) {
            getSettings().setTTSSpeed(1.0f);
            getSettings().setTTSPitch(1.0f);
        }
    }

    public boolean isTTSReady() {
        return this._TTS_SERVICE_READY_;
    }

    public boolean isVibratorReady() {
        return this._VIBRATOR_SERVICE_READY_;
    }

    private void activateVibratorService(boolean flag) {
        if (flag)
            if (!isVibratorReady())
                requestVibratorService();
        this._VIBRATOR_SERVICE_READY_ = flag;
    }

    private void activateTextToSpeechEngine(boolean flag) {
        if (flag)
            if (!isTTSReady())
                requestTTSService();
        this._TTS_SERVICE_READY_ = flag;
    }

    private void requestVibratorService() {
        if (!this._VIBRATOR_SERVICE_READY_) {
            if (mVibrator == null)
                mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
            if (mVibrator != null) {
                this._VIBRATOR_SERVICE_READY_ = true;
                Log.announce("Vibrator service ready", Log.Level.INFO);
            }
        }
    }

    private void requestTTSService() {
        if (!this._TTS_SERVICE_READY_ || mTTS == null) {
            mTTS = new TextToSpeech(mContext, this);
        }
    }

    public void resetDeviceConfigurations() {
        PreConfig config = new PreConfig();
        config.resetPreConfig();
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

    public UserSettings getSettings() {
        return new UserSettings();
    }

    public class UserSettings {
        private SharedPreferences mSharedPreference;

        public UserSettings() {
            mSharedPreference = readFromPreference(PREFERENCE_USER);
        }

        public boolean getTTSEnabled() {
            return mSharedPreference.getBoolean(PREF_KEY_TTS_ENABLE, true);
        }

        public void setTTSEnabled(Boolean value) {
            activateTextToSpeechEngine(value);
            writeToPreference(PREFERENCE_USER, PREF_KEY_TTS_ENABLE, value);
        }

        public int getTTSVolume() {
            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            return mSharedPreference.getInt(PREF_KEY_TTS_VOLUME, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        }

        public void setTTSVolume(Integer value) {
            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            am.setStreamVolume(AudioManager.STREAM_MUSIC, value, 0);
            writeToPreference(PREFERENCE_USER, PREF_KEY_TTS_VOLUME, value);
        }

        public Float getTTSSpeed() {
            return mSharedPreference.getFloat(PREF_KEY_TTS_SPEED, 1);
        }

        public void setTTSSpeed(Float value) {
            getTTSEngine().setSpeechRate(value);
            writeToPreference(PREFERENCE_USER, PREF_KEY_TTS_SPEED, value);
        }

        public boolean getVibrationEnabled() {
            return mSharedPreference.getBoolean(PREF_KEY_VIBRATION_ENABLE, true);
        }

        public void setVibrationEnabled(Boolean value) {
            activateVibratorService(value);
            writeToPreference(PREFERENCE_USER, PREF_KEY_VIBRATION_ENABLE, value);
        }

        public boolean getSystemRecovery() {
            return mSharedPreference.getBoolean(PREF_KEY_RECOVER_SYSTEM, true);
        }

        public void setSystemRecovery(Boolean value) {
            writeToPreference(PREFERENCE_USER, PREF_KEY_RECOVER_SYSTEM, value);
        }

        public Float getTTSPitch() {
            return mSharedPreference.getFloat(PREF_KEY_TTS_PITCH, 1.0f);
        }

        public void setTTSPitch(Float value) {
            getTTSEngine().setPitch(value);
            writeToPreference(PREFERENCE_USER, PREF_KEY_TTS_PITCH, value);
        }

        public boolean getKeypadTonesEnabled() {
            return mSharedPreference.getBoolean(PREF_KEY_KEYTONES_ENABLE, true);
        }

        public void setKeypadTonesEnable(Boolean value) {
            writeToPreference(PREFERENCE_USER, PREF_KEY_KEYTONES_ENABLE, value);
        }

        public int getRingingVolume() {
            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            return mSharedPreference.getInt(PREF_KEY_RINGING_VOLUME, am.getStreamMaxVolume(AudioManager.STREAM_RING));
        }

        public void setRingingVolume(Integer value) {
            AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            am.setStreamVolume(AudioManager.STREAM_RING, value, 0);
            writeToPreference(PREFERENCE_USER, PREF_KEY_RINGING_VOLUME, value);
        }

    }

    class PreConfig {
        void rememberConfig() {
            Boolean isInit = (Boolean) readFromPreference(PREFERENCE_INTERNAL_PRECONFIG_INIT, PREFERENCE_INTERNAL_PRECONFIG_INIT);
            if (isInit == null || !isInit) {
                AudioManager am = (AudioManager) InvisibleTouchApplication.getInstance().getSystemService(Context.AUDIO_SERVICE);
                writeToPreference(PREFERENCE_INTERNAL_PRECONFIG, PREF_KEY_RINGING_VOLUME, am.getStreamVolume(AudioManager.STREAM_RING));
                writeToPreference(PREFERENCE_INTERNAL_PRECONFIG, PREF_KEY_TTS_VOLUME, am.getStreamVolume(AudioManager.STREAM_MUSIC));
                writeToPreference(PREFERENCE_INTERNAL_PRECONFIG_INIT, PREFERENCE_INTERNAL_PRECONFIG_INIT, true);
                Toast.makeText(InvisibleTouchApplication.getInstance(), "Remembered", Toast.LENGTH_SHORT).show();
            }
        }

        void resetPreConfig() {
            Boolean isInit = (Boolean) readFromPreference(PREFERENCE_INTERNAL_PRECONFIG_INIT, PREFERENCE_INTERNAL_PRECONFIG_INIT);
            if (isInit != null && isInit) {
                AudioManager am = (AudioManager) InvisibleTouchApplication.getInstance().getSystemService(Context.AUDIO_SERVICE);
                Integer ringVolume = (Integer) readFromPreference(PREFERENCE_INTERNAL_PRECONFIG, PREF_KEY_RINGING_VOLUME);
                Integer ttsVolume = (Integer) readFromPreference(PREFERENCE_INTERNAL_PRECONFIG, PREF_KEY_TTS_VOLUME);

                am.setStreamVolume(AudioManager.STREAM_RING, ringVolume, 0);
                am.setStreamVolume(AudioManager.STREAM_MUSIC, ttsVolume, 0);
            }
        }
    }
}
