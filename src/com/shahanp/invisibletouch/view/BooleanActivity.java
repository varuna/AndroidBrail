package com.shahanp.invisibletouch.view;

import android.view.View;
import com.shahanp.invisibletouch.internal.SinglePackActivity;
import com.shahanp.invisibletouch.utils.Log;
import com.shahanp.invisibletouch.internal.ScreenHelper;

public class BooleanActivity extends SinglePackActivity {

    public static final String INTENT_FLAG_MESSAGE = "com.shahanp.invisibletouch.view.booleanactivity.MESSAGE";
    public static final String INTENT_FLAG_MESSAGE_TITLE = "com.shahanp.invisibletouch.view.booleanactivity.MESSAGE.TITLE";
    public static final String INTENT_FLAG_MESSAGE_SUMMARY = "com.shahanp.invisibletouch.view.booleanactivity.MESSAGE.SUMMARY";

    private String mMessage;
    @Override
    protected void init() {
        super.init();
        mMessage = getIntent().getStringExtra(INTENT_FLAG_MESSAGE);
        String title = getIntent().getStringExtra(INTENT_FLAG_MESSAGE_TITLE);
        String summary = getIntent().getStringExtra(INTENT_FLAG_MESSAGE_SUMMARY);
        setViewText(title,summary);
        Log.announce(title+" : "+summary, Log.Level.INFO);
        Log.announce(mMessage,false);
        Log.announce(mMessage,true);
    }

    @Override
    public void onSwipeRight() {
        setResult(RESULT_OK,getIntent());
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
