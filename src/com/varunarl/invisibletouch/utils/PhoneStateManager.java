package com.varunarl.invisibletouch.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.view.OutGoingCallActivity;

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
        Toast.makeText(mContext,"state : "+state,Toast.LENGTH_LONG).show();
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                if (mPhoneState.getPhoneState() == TelephonyManager.CALL_STATE_OFFHOOK) {
                    mContext.startActivity(mIntent);
                }
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                if (InvisibleTouchApplication.getInstance().getCallManager().isOutGoingCall()){
                    Intent i = new Intent(mContext,OutGoingCallActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    i.putExtra(OutGoingCallActivity.NUMBER,incomingNumber);

                    PendingIntent pi = PendingIntent.getActivity(mContext,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager am = (AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
                    am.set(AlarmManager.RTC_WAKEUP,300,pi);
                }

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
