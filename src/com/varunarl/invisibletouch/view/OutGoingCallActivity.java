package com.varunarl.invisibletouch.view;

import android.os.Bundle;
import android.os.RemoteException;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.SixPackActivity;

/**
 * Created by Varuna on 6/18/13.
 */
public class OutGoingCallActivity extends SixPackActivity {
    public static final String NUMBER = "com.varunarl.outgoingcall.number";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }

    @Override
    public void onSwipeLeft() {
        try {
            InvisibleTouchApplication.getInstance().getCallManager().endCall();
            finish();
        } catch (RemoteException e) {
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
}