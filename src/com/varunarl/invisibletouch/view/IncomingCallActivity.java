package com.varunarl.invisibletouch.view;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.SinglePackActivity;
import com.varunarl.invisibletouch.utils.IPhoneState;
import com.varunarl.invisibletouch.utils.IncommingCallListener;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;

import java.lang.reflect.Method;

public class IncomingCallActivity extends SinglePackActivity implements IPhoneState{

    private String mName = "";
    private String mNumber = "";
    private LinearLayout mRootView;
    private int NAME_VIEW_ID = 1000;
    private int NUMBER_VIEW_ID = 1001;
    private int mPhoneState;

    @Override
    protected void init() {
        mName = getIntent().getStringExtra(
                IncommingCallListener.FLAG_RINGING_CALLER_NAME);
        mNumber = getIntent().getStringExtra(
                IncommingCallListener.FLAG_RINGING_CALLER_NUMBER);

        super.init();
        InvisibleTouchApplication.getInstance().getCallManager().registerPhoneStateListener(this,getIntent());
        Log.announce("Current Phone State : "+mPhoneState,false);
    }

    @Override
    public void onSwipeRight() {
        InvisibleTouchApplication.getInstance().getCallManager().answerCall();
        Log.announce("Current Phone State : "+mPhoneState,false);
    }

    @Override
    public void onSwipeLeft() {
        try {
            InvisibleTouchApplication.getInstance().getCallManager().endCall();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
        Log.announce("Current Phone State : "+mPhoneState,false);
        InvisibleTouchApplication.getInstance().getCallManager().unregisterPhoneStateListener();
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
    public void onCameraKeyShortPress() {
    }

    @Override
    public void onCameraKeyLongPress() {
    }

    @Override
    public void onScreenLongPress() {
    }

    @Override
    public void onKeyOne() {
    }

    @Override
    public void onLongKeyOne() {
    }

    @Override
    protected void onAttachView(int id, View view) {
        TextView name;
        TextView phone;
        if (mRootView == null) {
            mRootView = (LinearLayout) view.getParent();
            mRootView.removeAllViews();
            mRootView.setOrientation(LinearLayout.VERTICAL);
            mRootView.setGravity(Gravity.CENTER);
            mRootView.setBackgroundColor(Color.BLACK);
            name = new TextView(this);
            phone = new TextView(this);
            name.setGravity(Gravity.CENTER);
            phone.setGravity(Gravity.CENTER);
            name.setTextColor(Color.GRAY);
            phone.setTextColor(Color.GRAY);
            name.setId(NAME_VIEW_ID);
            phone.setId(NUMBER_VIEW_ID);
            name.setText(mName);
            phone.setText(mNumber);

            mRootView.addView(name);
            mRootView.addView(phone);
        } else {
            name = (TextView) mRootView.findViewById(NAME_VIEW_ID);
            phone = (TextView) mRootView.findViewById(NUMBER_VIEW_ID);
            name.setText(mName);
            phone.setText(mNumber);
        }
    }

    @Override
    public void setPhoneState(int state) {
        mPhoneState = state;
    }

    @Override
    public int getPhoneState() {
        return mPhoneState;
    }
}
