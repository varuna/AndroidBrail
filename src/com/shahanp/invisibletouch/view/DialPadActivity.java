package com.shahanp.invisibletouch.view;

import android.content.Intent;

import com.shahanp.invisibletouch.braille.Braille;
import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;
import com.shahanp.invisibletouch.internal.KeyboardActivity;
import com.shahanp.invisibletouch.internal.ScreenHelper;
import com.shahanp.invisibletouch.utils.CallManager;
import com.shahanp.invisibletouch.utils.InputManager;
import com.shahanp.invisibletouch.utils.Log;
import com.shahanp.invisibletouch.view.sub.DialPadMenuActivity;


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
        setKeyTones(InvisibleTouchApplication.getInstance().getSettingsManager().getSettings().getKeypadTonesEnabled());
        Log.announce(ScreenHelper.DIAL_PAD_ACTIVATE, true);
    }

    @Override
    public void onScreenLongPress() {
        Log.announce(ScreenHelper.DIAL_PAD_SCREEN_HELPER, true);
    }

    @Override
    public void onSwipeUp() {
        mPhoneNumber = mTextInputManager.getText();
        Intent menuIntent = new Intent(this, DialPadMenuActivity.class);
        startActivity(menuIntent);
    }

    @Override
    public void onSwipeRight() {
        mTextInputManager.forceBuffer(mCurrentCharacter, Braille.KeyBoard.NUMERIC_KEY_TYPE, new Character[]{'#','*'});
        mCurrentCharacter.reset();
        Log.announce(mTextInputManager.getBufferedText(), false);
        Log.announce(mTextInputManager.getBufferedText(), true);
        resetView();
    }

    @Override
    public void onDoubleSwipeRight() {
        super.onDoubleSwipeRight();
        mPhoneNumber = mTextInputManager.getText();
        if (mPhoneNumber.equals("01010001")) {
            mStoppedFromNewScreen = true;
            Log.announce("Invisible Touch Exit",true);
            InvisibleTouchApplication.getInstance().forceQuitApp(this);

        } else {
            Log.announce(mPhoneNumber + " Dialing",true);
            mCallManager.makeCall(mPhoneNumber, this);
        }
        mTextInputManager.purge();
        mCurrentCharacter.reset();
    }

    @Override
    public void onVolumeDownKeyLongPress() {
        Log.announce(ScreenHelper.DIAL_PAD_SCREEN_HELPER, true);
    }

    @Override
    public void onVolumeUpKeyLongPress() {

    }

}

