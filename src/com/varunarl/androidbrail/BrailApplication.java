package com.varunarl.androidbrail;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;

public class BrailApplication extends Application implements OnInitListener {

	private final String TAG = "BrailApplication";
	private Vibrator mVibratorService;
	private TextToSpeech mTTS;

	private static BrailApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		mVibratorService = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		if (mVibratorService != null)
			Log.i(TAG, "Vibrator service ready");
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN)
			mTTS = new TextToSpeech(getApplicationContext(), this);
	}

	public static BrailApplication getInstance() {
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

}
