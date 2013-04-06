package com.varunarl.invisibletouch.view;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.varunarl.invisibletouch.InvisibleTouchApplication;
import com.varunarl.invisibletouch.SixPackActivity;
import com.varunarl.invisibletouch.brail.BrailCharacter;

public class TypingKeyboardActivity extends SixPackActivity {

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
	public void onKeyOne() {
		mCurrentCharacter._one_one.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._one_one.getPattern());
	}

	@Override
	public void onKeyTwo() {
		mCurrentCharacter._one_two.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._one_two.getPattern());
	}

	@Override
	public void onKeyThree() {
		mCurrentCharacter._one_three.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._one_three.getPattern());
	}

	@Override
	public void onKeyFour() {
		mCurrentCharacter._two_one.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._two_one.getPattern());
	}

	@Override
	public void onKeyFive() {
		mCurrentCharacter._two_two.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._two_two.getPattern());
	}

	@Override
	public void onKeySix() {
		mCurrentCharacter._two_three.swap();
		InvisibleTouchApplication.getInstance().vibrate(
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

	@Override
	public void onVolumeDownKeyShortPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVolumeDownKeyLongPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVolumeUpKeyShortPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onVolumeUpKeyLongPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPowerKeyShortPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPowerKeyLongPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSwipeUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoubleSwipeUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSwipeDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDoubleSwipeDown() {
		// TODO Auto-generated method stub
		
	}

}
