package com.varunarl.androidbrail.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.varunarl.androidbrail.BaseActivity;
import com.varunarl.androidbrail.BrailApplication;
import com.varunarl.androidbrail.brail.BrailCharacter;

public class TypingKeyboardActivity extends BaseActivity {

	private final String TAG = "MainActivity";
	private BrailCharacter mCurrentCharacter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCurrentCharacter = new BrailCharacter();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public void onBackPressed() {
	}

	@Override
	public void onKeyOne() {
		mCurrentCharacter._one_one.swap();
		BrailApplication.getInstance().vibrate(
				mCurrentCharacter._one_one.getPattern());
	}

	@Override
	public void onKeyTwo() {
		mCurrentCharacter._one_two.swap();
		BrailApplication.getInstance().vibrate(
				mCurrentCharacter._one_two.getPattern());
	}

	@Override
	public void onKeyThree() {
		mCurrentCharacter._one_three.swap();
		BrailApplication.getInstance().vibrate(
				mCurrentCharacter._one_three.getPattern());
	}

	@Override
	public void onKeyFour() {
		mCurrentCharacter._two_one.swap();
		BrailApplication.getInstance().vibrate(
				mCurrentCharacter._two_one.getPattern());
	}

	@Override
	public void onKeyFive() {
		mCurrentCharacter._two_two.swap();
		BrailApplication.getInstance().vibrate(
				mCurrentCharacter._two_two.getPattern());
	}

	@Override
	public void onKeySix() {
		mCurrentCharacter._two_three.swap();
		BrailApplication.getInstance().vibrate(
				mCurrentCharacter._two_three.getPattern());
	}

	@Override
	public void onEnterGesture() {
		Log.i(TAG, "onEnterGesture");
		buffer(mCurrentCharacter);
		mCurrentCharacter.reset();
	}

	@Override
	public void onBackspaceGesture() {
		Log.i(TAG, "onBackSpaceGesture");
		removeLast();
		mCurrentCharacter.reset();
	}

	@Override
	public void onDoubleEnterGesture() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDoubleBackspaceGesture() {
		// TODO Auto-generated method stub

	}

}
