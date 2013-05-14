package com.varunarl.invisibletouch.view;

import android.os.Bundle;
import android.view.MotionEvent;

import com.varunarl.invisibletouch.InvisibleTouchApplication;
import com.varunarl.invisibletouch.SixPackActivity;
import com.varunarl.invisibletouch.brail.BrailCharacter;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;
import com.varunarl.invisibletouch.utils.TextInputManager;

public class TypingKeyboardActivity extends SixPackActivity {

	private BrailCharacter mCurrentCharacter;
	private TextInputManager mInputManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCurrentCharacter = new BrailCharacter();
		mInputManager = InvisibleTouchApplication.getInstance()
				.getTextInputManager();
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
	public void onSwipeRight() {
		Log.announce("onEnterGesture", Level.INFO);
		mInputManager.buffer(mCurrentCharacter);
		mCurrentCharacter.reset();
	}

	@Override
	public void onSwipeLeft() {
		Log.announce("onBackSpaceGesture", Level.INFO);
		mInputManager.removeLast();
		mCurrentCharacter.reset();
	}

	@Override
	public void onDoubleSwipeRight() {

	}

	@Override
	public void onDoubleSwipeLeft() {
		mInputManager.purge();
		mCurrentCharacter.reset();
		finish();
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

	@Override
	public void onCameraKeyShortPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCameraKeyLongPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScreenLongPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongKeyOne() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongKeyTwo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongKeyThree() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongKeyFour() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongKeyFive() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongKeySix() {
		// TODO Auto-generated method stub

	}

}
