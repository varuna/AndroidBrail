package com.varunarl.invisibletouch.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.TelephonyManager;
import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;

public class CallStateListener extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.announce("CallStateListener : "+action, Log.Level.INFO);

        if (action.equals("android.intent.action.PHONE_STATE"))
            handleIncomingCall(context,intent);
        else if (action.equals(Intent.ACTION_NEW_OUTGOING_CALL))
            handleOutGoingCalls();
    }

    private void handleOutGoingCalls() {
    }

    private void handleIncomingCall(Context context, Intent intent)
    {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.announce("Call state : "+state, Log.Level.INFO);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

            String number = intent
                    .getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            String name = "No name";
            Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
                    Uri.encode(number));
            Cursor c = context.getContentResolver()
                    .query(uri, new String[]{PhoneLookup.DISPLAY_NAME},
                            null, null, null);
            if (c.moveToFirst())
                name = c.getString(c.getColumnIndex(PhoneLookup.DISPLAY_NAME));

            InvisibleTouchApplication.getInstance().setIncomingCallDetected(true);

            CallManager.startTelephoneInterface(context, number, name);
        }
    }


}
