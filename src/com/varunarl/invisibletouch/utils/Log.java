package com.varunarl.invisibletouch.utils;

import android.widget.Toast;

import com.varunarl.invisibletouch.InvisibleTouchApplication;

public class Log {
	public static void announce(String message,Level l) {
		switch (l) {
		case INFO:
			android.util.Log.i("InvisibleTouch", message);
			break;
		case VERBOSE:
			android.util.Log.v("InvisibleTouch", message);
			break;
		case WARNING:
			android.util.Log.w("InvisibleTouch", message);
			break;
		case ERROR:
			android.util.Log.e("InvisibleTouch", message);
			break;
		case DEBUG:
			android.util.Log.d("InvisibleTouch", message);
			break;

		default:
			break;
		}
	}

	public static void announce(String message, boolean isLoud) {
		if (isLoud)
			InvisibleTouchApplication.getInstance().speak(message);
		else
			Toast.makeText(InvisibleTouchApplication.getInstance(), message,
					Toast.LENGTH_SHORT).show();
	}

	public enum Level {
		INFO, VERBOSE, WARNING, ERROR, DEBUG;
	}
}
