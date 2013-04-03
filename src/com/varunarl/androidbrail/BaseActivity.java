package com.varunarl.androidbrail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.varunarl.androidbrail.brail.Brail;
import com.varunarl.androidbrail.brail.Brail.KeyBoard;
import com.varunarl.androidbrail.brail.BrailCharacter;

public abstract class BaseActivity extends Activity implements IGestures,
		IBrailKeyboard {

	private final String TAG = "BaseActivity";

	protected final static int GESTURE_TAP = 0;
	protected final static int GESTURE_SWIPE = 1;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCurrentBufferType = Brail.KeyBoard.LOWER_KEY_TYPE;
		mKeyBoard = new KeyBoard();
		mText = "";
		mBuffer = new StringBuffer();
		mGestureHistory = new ArrayList<Motion>();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		View _one_one = findViewById(R.id.item_one_one);
		View _one_two = findViewById(R.id.item_one_two);
		View _one_three = findViewById(R.id.item_one_three);
		View _two_one = findViewById(R.id.item_two_one);
		View _two_two = findViewById(R.id.item_two_two);
		View _two_three = findViewById(R.id.item_two_three);

		_one_one.setClickable(true);
		_one_two.setClickable(true);
		_one_three.setClickable(true);
		_two_one.setClickable(true);
		_two_two.setClickable(true);
		_two_three.setClickable(true);

		_one_one.setOnClickListener(this);
		_one_two.setOnClickListener(this);
		_one_three.setOnClickListener(this);
		_two_one.setOnClickListener(this);
		_two_two.setOnClickListener(this);
		_two_three.setOnClickListener(this);

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
				onDoubleBackspaceGesture();
			else {
				mLastGesture = SWIPE_LEFT;
				onBackspaceGesture();
			}
			return true;
		} else if (gesture == SWIPE_RIGHT) {
			if (mLastGesture == SWIPE_RIGHT)
				onDoubleEnterGesture();
			else {
				mLastGesture = SWIPE_RIGHT;
				onEnterGesture();
			}
			return true;
		} else if (gesture == SWIPE_UP) {
			if (mLastGesture == SWIPE_UP)
				onDoubleBackspaceGesture();
			else {
				mLastGesture = SWIPE_UP;
				onBackspaceGesture();
			}
		} else if (gesture == SWIPE_DOWN) {
			if (mLastGesture == SWIPE_DOWN)
				onDoubleEnterGesture();
			else {
				mLastGesture = SWIPE_DOWN;
				onEnterGesture();
			}
		}

		return super.dispatchTouchEvent(ev);
	}

	private void detectGesture(MotionEvent e) {
		Motion m = new Motion();
		m._action = e.getAction();
		m._x = e.getX();
		m._y = e.getY();
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
				if (mGestureHistorySize == 2 || mGestureHistorySize == 3)
					return GESTURE_TAP;
				if (mGestureHistorySize > 3)
					return GESTURE_SWIPE;
			}
		return -1;
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
				return SWIPE_DOWN;
			else
				return SWIPE_UP;
		}
		return getGestures();
	}

	@Override
	public void onClick(View v) {
		int _id = v.getId();
		switch (_id) {
		case R.id.item_one_one:
			onKeyOne();
			break;
		case R.id.item_one_two:
			onKeyTwo();
			break;
		case R.id.item_one_three:
			onKeyThree();
			break;
		case R.id.item_two_one:
			onKeyFour();
			break;
		case R.id.item_two_two:
			onKeyFive();
			break;
		case R.id.item_two_three:
			onKeySix();
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

			default:
				break;
			}
		}
		return super.onKeyLongPress(keyCode, event);
	}

	private class Motion {
		int _action;
		float _x;
		float _y;
	}

}
