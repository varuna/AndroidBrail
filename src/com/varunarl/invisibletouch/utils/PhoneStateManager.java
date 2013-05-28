package com.varunarl.invisibletouch.utils;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneStateManager extends PhoneStateListener {

    private Intent mIntent;
    private IPhoneState mPhoneState;
    private Context mContext;

    public PhoneStateManager(Intent intent, IPhoneState phoneState, Context context) {
        super();
        this.mIntent = intent;
        this.mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mPhoneState = phoneState;
        this.mContext = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                if (mPhoneState.getPhoneState() == TelephonyManager.CALL_STATE_OFFHOOK) {
                    mContext.startActivity(mIntent);
                }
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                break;
            default:
                break;
        }
        mPhoneState.setPhoneState(state);
    }

    public IPhoneState getCurrentPhoneState() {
        return mPhoneState;
    }
}
