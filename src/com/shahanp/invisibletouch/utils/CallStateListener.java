package com.shahanp.invisibletouch.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.TelephonyManager;

import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;

public class CallStateListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.announce("CallStateListener : " + action, Log.Level.INFO);

        if (InvisibleTouchApplication.getInstance().shouldKillApp())
            return;

        if (action.equals("android.intent.action.PHONE_STATE"))
            handleIncomingCall(context, intent);
        else if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL))
            handleOutGoingCalls(context, intent);
    }

    private void handleOutGoingCalls(Context context, Intent intent) {
        String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Contact c = resolveContact(context,number);
        InvisibleTouchApplication.getInstance().getCallManager().startTelephoneInterface(c.getPhone(), c.getName());
    }

    private void handleIncomingCall(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            Log.announce("Call state : " + state, Log.Level.INFO);

            String number = intent
                    .getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Contact c = resolveContact(context, number);

            InvisibleTouchApplication.getInstance().getCallManager().startTelephoneInterface(c.getPhone(), c.getName());
        } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
            InvisibleTouchApplication.getInstance().getCallManager().forceCallEnded();
        }
    }

    private Contact resolveContact(Context context, String number) {

        Contact c = new Contact();
        c.setPhone(number);
        Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        Cursor cur = context.getContentResolver()
                .query(uri, new String[]{PhoneLookup.DISPLAY_NAME},
                        null, null, null);
        if (cur.moveToFirst())
            c.setName(cur.getString(cur.getColumnIndex(PhoneLookup.DISPLAY_NAME)));
        return c;
    }


}
