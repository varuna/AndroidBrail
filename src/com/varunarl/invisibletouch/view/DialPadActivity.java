package com.varunarl.invisibletouch.view;

import android.content.Intent;
import com.varunarl.invisibletouch.braille.Braille;
import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.KeyboardActivity;
import com.varunarl.invisibletouch.utils.CallManager;
import com.varunarl.invisibletouch.utils.InputManager;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.view.sub.DialPadMenuActivity;

/**
 * Created by vlekamwasam on 5/21/13.
 */
public class DialPadActivity extends KeyboardActivity {
    private String mPhoneNumber;
    private InputManager.TextInputManager mTextInputManager;
    private CallManager mCallManager;

    @Override
    protected void init() {
        super.init();
        InvisibleTouchApplication app = InvisibleTouchApplication.getInstance();
        mTextInputManager = app.getTextInputManager();
        mCallManager = app.getCallManager();
        setCharacterVisibility(true);
    }

    @Override
    public void onScreenLongPress() {
        Log.announce("In Dial pad screen. Current Number " + mTextInputManager.getText(), false);
    }

    @Override
    public void onSwipeUp() {
        mPhoneNumber = mTextInputManager.getText();
        Intent menuIntent = new Intent(this, DialPadMenuActivity.class);
        startActivity(menuIntent);
    }

    @Override
    public void onSwipeRight() {
        Log.announce("onEnterGesture", Log.Level.INFO);
        mTextInputManager.buffer(mCurrentCharacter, Braille.KeyBoard.NUMERIC_KEY_TYPE);
        mCurrentCharacter.reset();
        resetView();
    }

    @Override
    public void onDoubleSwipeRight() {
        super.onDoubleSwipeRight();
        mPhoneNumber = mTextInputManager.getText();
       /* Log.announce(mPhoneNumber, false);
        int count = 0;
        boolean isFirstCharacterZero = false;
        for (Character c : mPhoneNumber.toCharArray()) {
            if (!Character.isDigit(c))
                break;
            if (count == 0)
                if (c.equals('0'))
                    isFirstCharacterZero = true;
            count++;
        }
        if (count == 10 || (!isFirstCharacterZero && count == 9)) {
            if (count == 10) {
                mCallManager.makeCall(mPhoneNumber, this);
            }
            if (count == 9) {
                mPhoneNumber = "0" + mPhoneNumber;*/
                mCallManager.makeCall(mPhoneNumber, this);
         /*   }
        }*/
    }
}
