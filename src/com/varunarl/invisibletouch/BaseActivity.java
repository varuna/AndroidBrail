package com.varunarl.invisibletouch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.varunarl.invisibletouch.brail.Brail;
import com.varunarl.invisibletouch.brail.Brail.KeyBoard;
import com.varunarl.invisibletouch.brail.BrailCharacter;
import com.varunarl.invisibletouch.view.MainMenuActivity;

public abstract class BaseActivity extends Activity implements IGestures,
		IBrailKeyboard {

	private final String TAG = "BaseActivity";

	private static final int MOVE_DETECTION_THRESHOLD = 20; // in pixels
	private static final int LONGTIME_DETECTION_THRESHOLD = 200; // in millis

	protected final static int GESTURE_TAP = 0;
	protected final static int GESTURE_LONGTAP = 1;
	protected final static int GESTURE_SWIPE = 2;

	protected final static int SWIPE_LEFT = 10;
	protected final static int SWIPE_RIGHT = 11;
	protected final static int SWIPE_UP = 12;
	protected final static int SWIPE_DOWN = 13;

	protected String mText;
	protected int mLastGesture = -1;
	private List<Motion> mGestureHistory;

	private StringBuffer mBuffer;
	private int mCurrentBufferType;
	private KeyBoard mKeyBoard;

	private boolean mStoppedFromNewScreen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCurrentBufferType = Brail.KeyBoard.LOWER_KEY_TYPE;
		mKeyBoard = new KeyBoard();
		mText = "";
		mBuffer = new StringBuffer();
		mGestureHistory = new ArrayList<Motion>();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		init();
	}

	protected void buffer(BrailCharacter c) {
		if (mKeyBoard.isControlCharacter(c)) {
			int oldType = mCurrentBufferType;
			mCurrentBufferType = mKeyBoard.getControlType(mKeyBoard.get(c,
					mCurrentBufferType));
			if (oldType == Brail.KeyBoard.UPPER_KEY_TYPE
					&& mCurrentBufferType == Brail.KeyBoard.UPPER_KEY_TYPE)
				mCurrentBufferType = Brail.KeyBoard.LOWER_KEY_TYPE;
			processBuffer();
		} else {
			mBuffer.append(mKeyBoard.get(c, mCurrentBufferType));
			Log.i(TAG, mBuffer.toString());
		}
	}

	protected void buffer(Character c) {
		processBuffer();
		mBuffer.append(c);
	}

	protected void removeLast() {
		if (mBuffer.length() > 0)
			mBuffer.deleteCharAt(mBuffer.length() - 1);
		else if (mText.length() > 1)
			mText = mText.substring(0, mText.length() - 2);
		else if (mText.length() == 1)
			mText = "";

	}

	private void processBuffer() {
		String bufferValue = mBuffer.toString();
		mText += bufferValue;
		mBuffer.delete(0, mBuffer.length());
		Toast.makeText(this, mText, Toast.LENGTH_LONG).show();
		Log.i(TAG, "Current String : " + mText);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		detectGesture(ev);
		int gesture = getGestureDirection();
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
		} else if (gesture == SWIPE_DOWN) {
			if (mLastGesture == SWIPE_DOWN)
				onDoubleSwipeDown();
			else {
				mLastGesture = SWIPE_DOWN;
				onSwipeDown();
			}
		} else if (gesture == GESTURE_TAP)
			mLastGesture = GESTURE_TAP;
		else if (gesture == GESTURE_LONGTAP) {
			mLastGesture = GESTURE_LONGTAP;
			onScreenLongPress();
		}

		return super.dispatchTouchEvent(ev);
	}

	private void detectGesture(MotionEvent e) {
		Motion m = new Motion();
		m._action = e.getAction();
		m._x = e.getX();
		m._y = e.getY();
		m._time = e.getEventTime();

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

	private int getGestureDirection() {
		if (getGestures() == GESTURE_SWIPE) {
			float oldx = mGestureHistory.get(0)._x;
			float oldy = mGestureHistory.get(0)._y;

			float newx = mGestureHistory.get(mGestureHistory.size() - 1)._x;
			float newy = mGestureHistory.get(mGestureHistory.size() - 1)._y;

			float xTranslation = oldx - newx;
			float yTranslation = oldy - newy;

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
		Log.i(TAG, "" + mLastGesture);
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

	@Override
	protected void onStop() {
		if (!mStoppedFromNewScreen) {
			Log.w(TAG,
					"Whoa.. we are losing screen. Install Alarm to start Invisible touch");
			AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			Intent i = new Intent(this, MainMenuActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			am.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance()
					.getTimeInMillis() + 10, PendingIntent.getActivity(this,
					0, i, PendingIntent.FLAG_CANCEL_CURRENT));
		}
		super.onStop();
	}

	private void onHomeKeyPressed() {
		Log.i(TAG, "Home pressed");
	}

	@Override
	public void onBackPressed() {
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
