package com.varunarl.invisibletouch.utils;

import android.telephony.PhoneStateListener;

public class PhoneStateManager extends PhoneStateListener {

    private INotify mCallBack;

    public PhoneStateManager(INotify callBack) {
        super();
        mCallBack = callBack;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        mCallBack.onCallStateChanged(state, incomingNumber);
    }

    public interface INotify {
        void onCallStateChanged(int state, String incomingNumber);
    }
}
