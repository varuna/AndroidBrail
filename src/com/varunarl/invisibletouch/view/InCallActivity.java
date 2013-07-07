package com.varunarl.invisibletouch.view;

import android.content.Intent;
import android.os.RemoteException;
import android.telephony.TelephonyManager;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.CallManager;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.PhoneStateManager;

public class InCallActivity extends SixPackActivity implements PhoneStateManager.INotify {
    public static final String NUMBER = "com.varunarl.call.number";
    private CallStatus mStatus;

    @Override
    protected void init() {
        InvisibleTouchApplication.getInstance().getCallManager().registerPhoneStateListener(this);
        mStatus = new CallStatus();
        if (getIntent().hasExtra(NUMBER))
            mStatus.mPhoneNumber = getIntent().getStringExtra(NUMBER);

        super.init();
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

    @Override
    public void onSwipeRight() {
        try {
            InvisibleTouchApplication.getInstance().getCallManager().answerCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSwipeLeft() {
        try {
            InvisibleTouchApplication.getInstance().getCallManager().endCall();
            finish();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
    public void onCameraKeyShortPress() {

    }

    @Override
    public void onCameraKeyLongPress() {

    }

    @Override
    public void onScreenLongPress() {
        Log.announce("In Call with " + mStatus.mPhoneNumber, true);
    }

    @Override
    public void onKeyOne() {
        InvisibleTouchApplication.getInstance().getCallManager().toggleMicMute();
        super.onKeyOne();
    }

    @Override
    public void onKeyTwo() {
        CallManager cm = InvisibleTouchApplication.getInstance().getCallManager();
        if (cm.isSpeakerOn())
            cm.turnOnSpeaker(false);
        else
            cm.turnOnSpeaker(true);
        super.onKeyTwo();
    }

    @Override
    public void onKeyFive() {
        try {
            InvisibleTouchApplication.getInstance().getCallManager().endCall();
            finish();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onKeyFive();
    }

    @Override
    public void onKeySix() {
        Intent i = new Intent(this, DialPadActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        super.onKeySix();
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        if (state == TelephonyManager.CALL_STATE_IDLE) {
            finish();
            InvisibleTouchApplication.getInstance().getCallManager().unregisterPhoneStateListener();
        }
    }

    private class CallStatus {
        String mPhoneNumber;
        String mName;
        Long mDuration;
    }
}