package com.varunarl.invisibletouch;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.varunarl.invisibletouch.utils.IPhoneState;
import com.varunarl.invisibletouch.utils.PhoneStateManager;
import com.varunarl.invisibletouch.utils.TextInputManager;

public class InvisibleTouchApplication extends Application implements
		OnInitListener {

	private final String TAG = "BrailApplication";
	private Vibrator mVibratorService;
	private TextToSpeech mTTS;
	private TelephonyManager mTelephonyManager;
	private TextInputManager mTextInputManager;

	private static InvisibleTouchApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		mTextInputManager = new TextInputManager(this);
		mVibratorService = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		if (mVibratorService != null)
			Log.i(TAG, "Vibrator service ready");
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
			mTTS = new TextToSpeech(getApplicationContext(), this);

	}

	public TextInputManager getTextInputManager() {
		return mTextInputManager;
	}

	public static InvisibleTouchApplication getInstance() {
		return instance;
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			Log.i(TAG, "TextToSpeech service is ready.");
		} else
			Log.i(TAG, "TextToSpeech service is broken.");
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

	public void registerPhoneStateListener(IPhoneState phoneState, Intent intent) {
		mTelephonyManager.listen(new PhoneStateManager(intent, phoneState,
				getApplicationContext()), PhoneStateListener.LISTEN_CALL_STATE);
	}

	public void unregisterPhoneStateListener() {
		mTelephonyManager.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
	}

}
