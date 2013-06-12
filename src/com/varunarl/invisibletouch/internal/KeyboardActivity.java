package com.varunarl.invisibletouch.internal;

import com.varunarl.invisibletouch.braille.BrailleCharacter;
import com.varunarl.invisibletouch.utils.InputManager;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;

public class KeyboardActivity extends SixPackActivity {

    protected BrailleCharacter mCurrentCharacter;
    protected InputManager.TextInputManager mTextInputManager;

    @Override
    protected void init() {
        mCurrentCharacter = new BrailleCharacter();
        mTextInputManager = InvisibleTouchApplication.getInstance()
                .getTextInputManager();
        setCharacterVisibility(true);
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
