package com.varunarl.invisibletouch.view;

import com.varunarl.invisibletouch.InvisibleTouchApplication;
import com.varunarl.invisibletouch.SixPackActivity;
import com.varunarl.invisibletouch.braille.BrailleCharacter;
import com.varunarl.invisibletouch.utils.InputManager;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;

public class TypingKeyboardActivity extends SixPackActivity {

	private BrailleCharacter mCurrentCharacter;
	private InputManager.TextInputManager mTextInputManager;

	@Override
	protected void init() {
		mCurrentCharacter = new BrailleCharacter();
		mTextInputManager = InvisibleTouchApplication.getInstance()
				.getTextInputManager();
		super.init();
	}

	@Override
	public void onKeyOne() {
		mCurrentCharacter._one_one.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._one_one.getPattern());
		super.onKeyOne();
	}

	@Override
	public void onKeyTwo() {
		mCurrentCharacter._one_two.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._one_two.getPattern());
		super.onKeyTwo();
	}

	@Override
	public void onKeyThree() {
		mCurrentCharacter._one_three.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._one_three.getPattern());
		super.onKeyThree();
	}

	@Override
	public void onKeyFour() {
		mCurrentCharacter._two_one.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._two_one.getPattern());
		super.onKeyFour();
	}

	@Override
	public void onKeyFive() {
		mCurrentCharacter._two_two.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._two_two.getPattern());
		super.onKeyFive();
	}

	@Override
	public void onKeySix() {
		mCurrentCharacter._two_three.swap();
		InvisibleTouchApplication.getInstance().vibrate(
				mCurrentCharacter._two_three.getPattern());
		super.onKeySix();
	}

	@Override
	public void onSwipeRight() {
		Log.announce("onEnterGesture", Level.INFO);
		mTextInputManager.buffer(mCurrentCharacter);
		mCurrentCharacter.reset();
		resetView();
	}

	@Override
	public void onSwipeLeft() {
		Log.announce("onBackSpaceGesture", Level.INFO);
		mTextInputManager.removeLast();
		mCurrentCharacter.reset();
		resetView();
	}

	@Override
	public void onDoubleSwipeRight() {

	}

	@Override
	public void onDoubleSwipeLeft() {
		mTextInputManager.purge();
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
