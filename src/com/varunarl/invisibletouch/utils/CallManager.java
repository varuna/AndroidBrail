package com.varunarl.invisibletouch.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;

import java.lang.reflect.Method;

/**
 * Created by vlekamwasam on 5/27/13.
 */
public class CallManager {
    private Context mContext;
    private com.android.internal.telephony.ITelephony mTelephonyService;
    private TelephonyManager mTelephonyManager;

    public CallManager(Context context) {
        mContext = context;

        if (mTelephonyService == null) {
            mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                Log.announce("Get getTeleService...", Log.Level.INFO);
                Class c = Class.forName(mTelephonyManager.getClass().getName());
                Method m = c.getDeclaredMethod("getITelephony");
                m.setAccessible(true);
                mTelephonyService = (com.android.internal.telephony.ITelephony) m.invoke(mTelephonyManager);
            } catch (Exception e) {
                e.printStackTrace();
                Log.announce("FATAL ERROR: could not connect to telephony subsystem",
                        Log.Level.ERROR);
            }
        }
    }

    public void makeCall(String phoneNumber) {
        Intent mCallIntent = new Intent(Intent.ACTION_CALL);
        mCallIntent.setData(Uri.parse("tel:" + phoneNumber));
        mCallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(mCallIntent);
    }

    public void endCall() throws Exception {
        mTelephonyService.endCall();
    }

    public void answerCall()
    {
        Intent answer = new Intent(Intent.ACTION_MEDIA_BUTTON);
        answer.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(
                KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
        mContext.sendOrderedBroadcast(answer, null);
    }

    public void registerPhoneStateListener(IPhoneState phoneState, Intent intent) {
        mTelephonyManager.listen(new PhoneStateManager(intent, phoneState,
                mContext.getApplicationContext()), PhoneStateListener.LISTEN_CALL_STATE);
    }

    public void unregisterPhoneStateListener() {
        mTelephonyManager.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
    }


}
