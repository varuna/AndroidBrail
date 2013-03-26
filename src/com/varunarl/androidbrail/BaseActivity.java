package com.varunarl.androidbrail;

import com.varunarl.androidbrail.brail.Brail;
import com.varunarl.androidbrail.brail.BrailCharacter;
import com.varunarl.androidbrail.brail.Brail.KeyBoard;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class BaseActivity extends Activity {

	private final String TAG = "BaseActivity";

	protected final static int GESTURE_ENTER = 2;
	protected final static int GESTURE_DOUBLE_ENTER = 3;
	protected final static int GESTURE_TRIPLE_ENTER = 4;
	protected final static int GESTURE_BACKSPACE = 1;
	protected final static int GESTURE_DOUBLE_BACKSPACE = 0;
	protected final static int GESTURE_TAP = 5;

	protected String mText;
	protected int mLastGesture = -1;
	
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
	}

	protected void buffer(BrailCharacter c) {
		if (mKeyBoard.isControlCharacter(c)) {
			int oldType = mCurrentBufferType;
			mCurrentBufferType = mKeyBoard.get(c, mCurrentBufferType);
			if (oldType == Brail.KeyBoard.UPPER_KEY_TYPE
					&& mCurrentBufferType == Brail.KeyBoard.UPPER_KEY_TYPE)
				mCurrentBufferType = Brail.KeyBoard.LOWER_KEY_TYPE;
			processBuffer();
		}
		mBuffer.append(mKeyBoard.get(c, mCurrentBufferType));
		Log.i(TAG, "Current Buffer : " + mBuffer.toString());
	}

	protected void buffer(Character c) {
		processBuffer();
		mBuffer.append(c);
		Log.i(TAG, "Current Buffer : " + mBuffer.toString());
	}

	protected void removeLast() {
		if (mBuffer.length() > 0)
			mBuffer.deleteCharAt(mBuffer.length() - 1);

	}

	private void processBuffer() {
		String bufferValue = mBuffer.toString();
		mText += bufferValue;
		mBuffer.delete(0, mBuffer.length());
		Log.i(TAG, "Current String : " + mText);
	}

	protected int detectGesture(float oldX, float newX) {
		if (mLastGesture == MotionEvent.ACTION_MOVE)
			if (oldX < newX)
				return GESTURE_ENTER;
			else if (oldX > newX)
				return GESTURE_BACKSPACE;
			else
				return GESTURE_TAP;
		else
			return GESTURE_TAP;

	}
}
