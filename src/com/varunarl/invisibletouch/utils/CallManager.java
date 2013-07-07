package com.varunarl.invisibletouch.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.Uri;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;

import com.varunarl.invisibletouch.internal.BaseActivity;
import com.varunarl.invisibletouch.view.InCallActivity;

import java.lang.reflect.Method;

public class CallManager {

    public static final String FLAG_RINGING_CALLER_NUMBER = "com.varunarl.invisibletouch.utils.CallStateListener.RINGING_NUMBER";
    public static final String FLAG_RINGING_CALLER_NAME = "com.varunarl.invisibletouch.utils.CallStateListener.RINGING_NAME";
    private Context mContext;
    private com.android.internal.telephony.ITelephony mTelephonyService;
    private AudioManager mAudioManager;
    private TelephonyManager mTelephonyManager;

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

        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

        IntentFilter intentFilterPhoneState = new IntentFilter("android.intent.action.PHONE_STATE");
        IntentFilter intentFilterOutGoing = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);

        mContext.registerReceiver(new CallStateListener(), intentFilterPhoneState, "android.permission.READ_PHONE_STATE", null);
        mContext.registerReceiver(new CallStateListener(), intentFilterOutGoing, "android.permission.PROCESS_OUTGOING_CALLS", null);
    }

    public static void startTelephoneInterface(Context context, String number, String name) {
        Log.announce("Starting UI : " + number, false);
        Intent i = new Intent(context, InCallActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra(InCallActivity.NUMBER, number);
        i.putExtra(FLAG_RINGING_CALLER_NAME, name);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000, pi);
    }

    public void makeCall(String phoneNumber, BaseActivity mActivity) {
        Intent i = new Intent(mContext, InCallActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        i.putExtra(InCallActivity.NUMBER, phoneNumber);

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
        unregisterPhoneStateListener();
        if (isSpeakerOn())
            turnOnSpeaker(false);
    }

    public void turnOnSpeaker(boolean turnOn) {
        mAudioManager.setMode(AudioManager.MODE_IN_CALL);
        mAudioManager.setSpeakerphoneOn(turnOn);
    }

    public void toggleMicMute() {
        if (mAudioManager.isMicrophoneMute())
            mAudioManager.setMicrophoneMute(false);
        else
            mAudioManager.setMicrophoneMute(true);
    }

    public boolean isSpeakerOn() {
        AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.isSpeakerphoneOn();
    }

    public void answerCall() throws RemoteException {
        Intent answer = new Intent(Intent.ACTION_MEDIA_BUTTON);
        answer.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(
                KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
        mContext.sendOrderedBroadcast(answer, null);
    }

    public void destroy() {
        mContext.unregisterReceiver(new CallStateListener());
    }

    public void registerPhoneStateListener(PhoneStateManager.INotify callBack) {
        mTelephonyManager.listen(new PhoneStateManager(callBack), PhoneStateListener.LISTEN_CALL_STATE);
    }

    public void unregisterPhoneStateListener() {
        mTelephonyManager.listen(null, PhoneStateListener.LISTEN_CALL_STATE);
    }

}
