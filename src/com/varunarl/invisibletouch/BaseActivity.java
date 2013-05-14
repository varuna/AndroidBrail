package com.varunarl.invisibletouch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;
import com.varunarl.invisibletouch.view.MainMenuActivity;

public abstract class BaseActivity extends Activity implements IGestures,
		IBrailKeyboard {

	protected static String TAG = "BaseActivity";

	private static final int MOVE_DETECTION_THRESHOLD = 100; // in pixels
	private static final int LONGTIME_DETECTION_THRESHOLD = 200; // in millis

	protected final static int GESTURE_TAP = 0;
	protected final static int GESTURE_LONGTAP = 1;
	protected final static int GESTURE_SWIPE = 2;

	protected final static int SWIPE_LEFT = 10;
	protected final static int SWIPE_RIGHT = 11;
	protected final static int SWIPE_UP = 12;
	protected final static int SWIPE_DOWN = 13;

	protected int mLastGesture = -1;
	private List<Motion> mGestureHistory;

	private boolean mStoppedFromNewScreen = false;
	private boolean isFinishing = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGestureHistory = new ArrayList<Motion>();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		init();
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		detectGesture(ev);
		int gesture = getSwipeDirection();
		if (mLastGesture == -1)
			mLastGesture = gesture;

		if (gesture == SWIPE_LEFT) {
			if (mLastGesture == SWIPE_LEFT)
				onDoubleSwipeLeft();
			else {
				mLastGesture = SWIPE_LEFT;
				onSwipeLeft();
			}
			return true;
		} else if (gesture == SWIPE_RIGHT) {
			if (mLastGesture == SWIPE_RIGHT)
				onDoubleSwipeRight();
			else {
				mLastGesture = SWIPE_RIGHT;
				onSwipeRight();
			}
			return true;
		} else if (gesture == SWIPE_UP) {
			if (mLastGesture == SWIPE_UP)
				onDoubleSwipeUp();
			else {
				mLastGesture = SWIPE_UP;
				onSwipeUp();
			}
			return true;
		} else if (gesture == SWIPE_DOWN) {
			if (mLastGesture == SWIPE_DOWN)
				onDoubleSwipeDown();
			else {
				mLastGesture = SWIPE_DOWN;
				onSwipeDown();
			}
			return true;
		} else if (gesture == GESTURE_TAP)
			mLastGesture = GESTURE_TAP;
		else if (gesture == GESTURE_LONGTAP) {
			mLastGesture = GESTURE_LONGTAP;
			onScreenLongPress();
			return true;
		}

		return super.dispatchTouchEvent(ev);
	}

	private void detectGesture(MotionEvent e) {
		Motion m = new Motion();
		m._action = e.getAction();
		m._x = e.getX();
		m._y = e.getY();
		m._time = e.getEventTime();
		Log.announce( "Some one is touching me : on x :" + m._x + " y : " + m._y,Level.INFO);
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			mGestureHistory.clear();
			mGestureHistory.add(m);
		} else
			mGestureHistory.add(m);

	}

	private int getGestures() {
		int mGestureHistorySize = mGestureHistory.size();
		if (mGestureHistorySize > 0)
			if (mGestureHistory.get(mGestureHistorySize - 1)._action == MotionEvent.ACTION_UP) {
				if (didMove())
					return GESTURE_SWIPE;
				else if (isLongTimeTakenForEvent())
					return GESTURE_LONGTAP;
				else
					return GESTURE_TAP;
			}
		return -1;
	}

	private boolean didMove() {
		if (mGestureHistory.size() > 0) {
			float sX = mGestureHistory.get(0)._x;
			float sY = mGestureHistory.get(0)._y;

			float fX = mGestureHistory.get(mGestureHistory.size() - 1)._x;
			float fY = mGestureHistory.get(mGestureHistory.size() - 1)._y;

			if ((Math.abs(sX - fX) > MOVE_DETECTION_THRESHOLD)
					|| (Math.abs(sY - fY) > MOVE_DETECTION_THRESHOLD))
				return true;
		}
		return false;
	}

	private boolean isLongTimeTakenForEvent() {
		if (mGestureHistory.size() > 0) {
			float sTime = mGestureHistory.get(0)._time;
			float fTime = mGestureHistory.get(mGestureHistory.size() - 1)._time;

			if (Math.abs(sTime - fTime) > LONGTIME_DETECTION_THRESHOLD)
				return true;
		}
		return false;
	}

	private int getSwipeDirection() {
		if (getGestures() == GESTURE_SWIPE) {
			float oldx = mGestureHistory.get(0)._x;
			float oldy = mGestureHistory.get(0)._y;

			float newx = mGestureHistory.get(mGestureHistory.size() - 1)._x;
			float newy = mGestureHistory.get(mGestureHistory.size() - 1)._y;

			float xTranslation = oldx - newx;
			float yTranslation = oldy - newy;
			Log.announce("Gesture Swipe X : " + xTranslation + " Y : "
					+ yTranslation,Level.INFO);
			if (Math.abs(xTranslation) > Math.abs(yTranslation)) {
				if (xTranslation > 0)
					return SWIPE_LEFT;
				else
					return SWIPE_RIGHT;
			} else if (yTranslation > 0)
				return SWIPE_UP;
			else
				return SWIPE_DOWN;
		}
		return getGestures();
	}

	@Override
	public void onClick(View v) {
		int _id = v.getId();
		Log.announce(""+ mLastGesture,Level.INFO);
		switch (_id) {
		case R.id.item_one_one:
			if (mLastGesture == GESTURE_TAP)
				onKeyOne();
			else if (mLastGesture == GESTURE_LONGTAP)
				onLongKeyOne();
			break;
		case R.id.item_one_two:
			if (mLastGesture == GESTURE_TAP)
				onKeyTwo();
			else if (mLastGesture == GESTURE_LONGTAP)
				onLongKeyTwo();
			break;
		case R.id.item_one_three:
			if (mLastGesture == GESTURE_TAP)
				onKeyThree();
			else if (mLastGesture == GESTURE_LONGTAP)
				onLongKeyThree();
			break;
		case R.id.item_two_one:
			if (mLastGesture == GESTURE_TAP)
				onKeyFour();
			else if (mLastGesture == GESTURE_LONGTAP)
				onLongKeyFour();
			break;
		case R.id.item_two_two:
			if (mLastGesture == GESTURE_TAP)
				onKeyFive();
			else if (mLastGesture == GESTURE_LONGTAP)
				onLongKeyFive();
			break;
		case R.id.item_two_three:
			if (mLastGesture == GESTURE_TAP)
				onKeySix();
			else if (mLastGesture == GESTURE_LONGTAP)
				onLongKeySix();
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (!event.isTracking()) {
			event.startTracking();
			switch (keyCode) {
			case KeyEvent.KEYCODE_VOLUME_DOWN:
				onVolumeDownKeyShortPress();
				return true;
			case KeyEvent.KEYCODE_VOLUME_UP:
				onVolumeUpKeyShortPress();
				return true;
			case KeyEvent.KEYCODE_POWER:
				onPowerKeyShortPress();
				return true;
			case KeyEvent.KEYCODE_CAMERA:
				onCameraKeyShortPress();
				return true;

			default:
				break;
			}
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
		if (event.isTracking()) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_VOLUME_DOWN:
				onVolumeDownKeyLongPress();
				return true;
			case KeyEvent.KEYCODE_VOLUME_UP:
				onVolumeUpKeyLongPress();
				return true;
			case KeyEvent.KEYCODE_POWER:
				onPowerKeyLongPress();
				return true;
			case KeyEvent.KEYCODE_CAMERA:
				onCameraKeyLongPress();
				return true;
			case KeyEvent.KEYCODE_HOME:
				onHomeKeyPressed();
				return true;
			default:
				break;
			}
		}
		return super.onKeyLongPress(keyCode, event);
	}

	@Override
	public void startActivity(Intent intent) {
		mStoppedFromNewScreen = true;
		super.startActivity(intent);
	}

	@SuppressLint("InlinedApi")
	@Override
	protected void onStop() {
		if (!mStoppedFromNewScreen && !isFinishing) {
			Log.announce("Whoa.. we are losing screen. Install Alarm to start Invisible touch",Level.WARNING);
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			Intent i = new Intent(this, MainMenuActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			am.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance()
					.getTimeInMillis() + 10, PendingIntent.getActivity(this, 0,
					i, PendingIntent.FLAG_CANCEL_CURRENT));
		}
		super.onStop();
	}

	private void onHomeKeyPressed() {
		Log.announce("Home pressed",Level.INFO);
	}

	@Override
	public void onBackPressed() {
	}
	
	@Override
	public void finish() {
		isFinishing = true;
		super.finish();
	}

	private class Motion {
		int _action;
		float _x;
		float _y;
		float _time;
	}

	protected abstract void init();

	protected abstract void onAttachView(int id, View view);
}
