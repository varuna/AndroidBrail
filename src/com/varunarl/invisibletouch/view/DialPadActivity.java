package com.varunarl.invisibletouch.view;

import android.content.Intent;
import com.varunarl.invisibletouch.braille.Braille;
import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.KeyboardActivity;
import com.varunarl.invisibletouch.utils.InputManager;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.view.sub.DialPadMenuActivity;

/**
 * Created by vlekamwasam on 5/21/13.
 */
public class DialPadActivity extends KeyboardActivity {
    private String mPhoneNumber;
    private InputManager.TextInputManager mTextInputManager;

    @Override
    public void onSwipeUp() {
        mTextInputManager = InvisibleTouchApplication.getInstance().getTextInputManager();
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
}
