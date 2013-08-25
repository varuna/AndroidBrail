package com.shahanp.invisibletouch.internal;

import com.shahanp.invisibletouch.braille.BrailleCharacter;
import com.shahanp.invisibletouch.utils.InputManager;
import com.shahanp.invisibletouch.utils.Log;
import com.shahanp.invisibletouch.utils.Log.Level;

public class KeyboardActivity extends SixPackActivity {

    protected BrailleCharacter mCurrentCharacter;
    protected InputManager.TextInputManager mTextInputManager;

    @Override
    protected void init() {
        mCurrentCharacter = new BrailleCharacter();
        mTextInputManager = InvisibleTouchApplication.getInstance()
                .getTextInputManager();
        setCharacterVisibility(true);
        setKeyTones(true);
        setVibrations(true);
        super.init();
    }

    @Override
    public void onKeyOne() {
        mCurrentCharacter._one_one.swap();
        super.onKeyOne();
    }

    @Override
    public void onKeyTwo() {
        mCurrentCharacter._one_two.swap();
        super.onKeyTwo();
    }

    @Override
    public void onKeyThree() {
        mCurrentCharacter._one_three.swap();
        super.onKeyThree();
    }

    @Override
    public void onKeyFour() {
        mCurrentCharacter._two_one.swap();
        super.onKeyFour();
    }

    @Override
    public void onKeyFive() {
        mCurrentCharacter._two_two.swap();
        super.onKeyFive();
    }

    @Override
    public void onKeySix() {
        mCurrentCharacter._two_three.swap();
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
        Log.announce("Back space.",true);
    }

    @Override
    public void onDoubleSwipeRight() {
        mTextInputManager.processBuffer();
    }

    @Override

    public void onDoubleSwipeLeft() {
        mTextInputManager.purge();
        mCurrentCharacter.reset();
        finish();
    }

    @Override
    public void onVolumeDownKeyShortPress() {

    }

    @Override
    public void onVolumeDownKeyLongPress() {

    }

    @Override
    public void onVolumeUpKeyShortPress() {

    }

    @Override
    public void onVolumeUpKeyLongPress() {

    }

    @Override
    public void onSwipeUp() {

    }

    @Override
    public void onDoubleSwipeUp() {

    }

    @Override
    public void onSwipeDown() {

    }

    @Override
    public void onDoubleSwipeDown() {

    }

    @Override
    public void onCameraKeyShortPress() {

    }

    @Override
    public void onCameraKeyLongPress() {

    }

    @Override
    public void onScreenLongPress() {

    }

    @Override
    public void onLongKeyOne() {

    }

    @Override
    public void onLongKeyTwo() {

    }

    @Override
    public void onLongKeyThree() {

    }

    @Override
    public void onLongKeyFour() {

    }

    @Override
    public void onLongKeyFive() {

    }

    @Override
    public void onLongKeySix() {

    }

}
