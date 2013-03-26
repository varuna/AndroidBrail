package com.varunarl.androidbrail.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.varunarl.androidbrail.BaseActivity;
import com.varunarl.androidbrail.BrailApplication;
import com.varunarl.androidbrail.IAndroidBrail;
import com.varunarl.androidbrail.R;
import com.varunarl.androidbrail.brail.BrailCharacter;

public class TypingKeyboardActivity extends BaseActivity implements
		IAndroidBrail {

	private final String TAG = "MainActivity";
	private int mLastMotionEvent = -1;
	private float mXTranslation = 0;

	private BrailCharacter mCurrentCharacter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mCurrentCharacter = new BrailCharacter();
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

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int action = event.getAction();
		Log.i(TAG, "" + event.getAction());
		if (mLastMotionEvent == -1)
			mLastMotionEvent = action;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mXTranslation = event.getX();
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			if (mLastMotionEvent == MotionEvent.ACTION_MOVE) {
				Log.i(TAG, "Swipe detected");
				if (detectGesture(mXTranslation, event.getX()) == GESTURE_ENTER) {
					Log.i(TAG, "Enter detected");
					onEnterGesture();
				} else {
					Log.i(TAG, "Backspace detected");
					onBackspaceGesture();
				}
				return true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (event.getHistorySize() > 3)
				mLastMotionEvent = MotionEvent.ACTION_MOVE;
			else
				mLastMotionEvent = -1;
			break;

		default:
			break;
		}
		mLastMotionEvent = action;
		return super.dispatchTouchEvent(event);
	}

	@Override
	public void onEnterGesture() {
		if (mLastGesture < GESTURE_ENTER) {
			mLastGesture = GESTURE_ENTER;
			buffer(mCurrentCharacter);
			mCurrentCharacter.reset();
		} else {
			if (mLastGesture == GESTURE_ENTER) {
				mLastGesture = GESTURE_DOUBLE_ENTER;
				buffer('\n');
			} else {
				mLastGesture = GESTURE_TRIPLE_ENTER;

			}
		}
	}

	@Override
	public void onBackspaceGesture() {
		mLastGesture = GESTURE_BACKSPACE;
		removeLast();
		mCurrentCharacter.reset();
	}

	@Override
	public void onClick(View v) {
		int _id = v.getId();

		switch (_id) {
		case R.id.item_one_one:
			mCurrentCharacter._one_one.swap();
			BrailApplication.getInstance().vibrate(
					mCurrentCharacter._one_one.getPattern());
			break;
		case R.id.item_one_two:
			mCurrentCharacter._one_two.swap();
			BrailApplication.getInstance().vibrate(
					mCurrentCharacter._one_two.getPattern());
			break;
		case R.id.item_one_three:
			mCurrentCharacter._one_three.swap();
			BrailApplication.getInstance().vibrate(
					mCurrentCharacter._one_three.getPattern());
			break;
		case R.id.item_two_one:
			mCurrentCharacter._two_one.swap();
			BrailApplication.getInstance().vibrate(
					mCurrentCharacter._two_one.getPattern());
			break;
		case R.id.item_two_two:
			mCurrentCharacter._two_two.swap();
			BrailApplication.getInstance().vibrate(
					mCurrentCharacter._two_two.getPattern());
			break;
		case R.id.item_two_three:
			mCurrentCharacter._two_three.swap();
			BrailApplication.getInstance().vibrate(
					mCurrentCharacter._two_three.getPattern());
			break;

		default:
			break;
		}
		Log.i(TAG, mCurrentCharacter.toString());
	}

	@Override
	public void onBackPressed() {
	}

}
