package com.varunarl.invisibletouch.view;

import android.view.View;
import com.varunarl.invisibletouch.internal.SinglePackActivity;
import com.varunarl.invisibletouch.utils.Log;

public class BooleanActivity extends SinglePackActivity {

    public static final String INTENT_FLAG_MESSAGE = "com.varunarl.invisibletouch.view.booleanactivity.MESSAGE";

    private String mMessage;
    @Override
    protected void init() {
        mMessage = getIntent().getStringExtra(INTENT_FLAG_MESSAGE);
        super.init();
    }

    @Override
    public void onSwipeRight() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onSwipeLeft() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onDoubleSwipeRight() {

    }

    @Override
    public void onDoubleSwipeLeft() {

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
    public void onPowerKeyShortPress() {

    }

    @Override
    public void onPowerKeyLongPress() {

    }

    @Override
    public void onKeyOne() {

    }

    @Override
    protected void onAttachView(int id, View view) {

    }

    @Override
    public void onCameraKeyShortPress() {

    }

    @Override
    public void onCameraKeyLongPress() {

    }

    @Override
    public void onScreenLongPress() {
        Log.announce(mMessage,false);

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
