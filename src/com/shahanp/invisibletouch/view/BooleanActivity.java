package com.shahanp.invisibletouch.view;

import android.view.View;
import com.shahanp.invisibletouch.internal.SinglePackActivity;
import com.shahanp.invisibletouch.utils.Log;
import com.shahanp.invisibletouch.internal.ScreenHelper;

public class BooleanActivity extends SinglePackActivity {

    public static final String INTENT_FLAG_MESSAGE = "com.shahanp.invisibletouch.view.booleanactivity.MESSAGE";

    private String mMessage;
    @Override
    protected void init() {
        mMessage = getIntent().getStringExtra(INTENT_FLAG_MESSAGE);
        super.init();
        Log.announce(mMessage,false);
        Log.announce(mMessage,true);

    }

    @Override
    public void onSwipeRight() {
        setActivityResult(RESULT_OK);
        finish();
    }

    @Override
    public void onSwipeLeft() {
        setActivityResult(RESULT_CANCELED);
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
        Log.announce(ScreenHelper.BOOLEAN_HELPER, true);
    }

    @Override
    public void onVolumeUpKeyShortPress() {

    }

    @Override
    public void onVolumeUpKeyLongPress() {

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
        Log.announce(mMessage,true);

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
