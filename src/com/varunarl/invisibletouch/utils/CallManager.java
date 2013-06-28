package com.varunarl.invisibletouch.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.varunarl.invisibletouch.internal.BaseActivity;
import com.varunarl.invisibletouch.view.IncomingCallActivity;
import com.varunarl.invisibletouch.view.OutGoingCallActivity;

import java.lang.reflect.Method;

/**
 * Created by vlekamwasam on 5/27/13.
 */
public class CallManager {

    public static final String FLAG_RINGING_CALLER_NUMBER = "com.varunarl.invisibletouch.utils.CallStateListener.RINGING_NUMBER";
    public static final String FLAG_RINGING_CALLER_NAME = "com.varunarl.invisibletouch.utils.CallStateListener.RINGING_NAME";

    private Context mContext;
    private com.android.internal.telephony.ITelephony mTelephonyService;
    private TelephonyManager mTelephonyManager;
    private boolean mOutGoingCall;

    public CallManager(Context context) {
        mContext = context;
        if (mTelephonyService == null) {
            mTelephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            try {
                Log.announce("Get getting Telephony Service...", Log.Level.INFO);
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

        IntentFilter intentFilterPhoneState = new IntentFilter("android.intent.action.PHONE_STATE");
        IntentFilter intentFilterOutGoing = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);

        mContext.registerReceiver(new CallStateListener(), intentFilterPhoneState, "android.permission.READ_PHONE_STATE", null);
        mContext.registerReceiver(new CallStateListener(),intentFilterOutGoing, "android.permission.PROCESS_OUTGOING_CALLS",null);

        mOutGoingCall = false;
    }

    public void makeCall(String phoneNumber, BaseActivity mActivity) {

        Toast.makeText(mContext, "Launching the OutGoing Screen", Toast.LENGTH_LONG).show();
        Intent i = new Intent(mContext, OutGoingCallActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra(OutGoingCallActivity.NUMBER, phoneNumber);

        PendingIntent pi = PendingIntent.getActivity(mContext, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 300, pi);

        Intent mCallIntent = new Intent(Intent.ACTION_CALL);
        mCallIntent.setData(Uri.parse("tel:" + phoneNumber));
        mCallIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mActivity.startActivity(mCallIntent);
    }

    public void endCall() throws RemoteException {
        mTelephonyService.endCall();
    }

    public void answerCall() throws RemoteException{
        Intent answer = new Intent(Intent.ACTION_MEDIA_BUTTON);
        answer.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(
                KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
        mContext.sendOrderedBroadcast(answer, null);
    }

    public void registerPhoneStateListener(IPhoneState phoneState, Intent intent) {
        mTelephonyManager.listen(new PhoneStateManager(intent, phoneState,
                mContext.getApplicationContext()), PhoneStateListener.LISTEN_CALL_STATE);
    }

    public void removePhoneStateListener() {
        mTelephonyManager.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
    }

    public static void startTelephoneInterface(Context context,String number, String name)
    {
        Log.announce("Starting UI : "+number,false);
        Intent i = new Intent(context, IncomingCallActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra(FLAG_RINGING_CALLER_NUMBER, number);
        i.putExtra(FLAG_RINGING_CALLER_NAME, name);

        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+1000,pi);
    }

    public void destroy() {
        removePhoneStateListener();
        mContext.unregisterReceiver(new CallStateListener());
    }


}
