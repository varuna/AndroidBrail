package com.varunarl.invisibletouch.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.view.IncomingCallActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.TelephonyManager;

public class IncommingCallListener extends BroadcastReceiver {

	public static final String FLAG_RINGING_CALLER_NUMBER = "com.varunarl.invisibletouch.utils.IncommingCallListener.RINGING_NUMBER";
	public static final String FLAG_RINGING_CALLER_NAME = "com.varunarl.invisibletouch.utils.IncommingCallListener.RINGING_NAME";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (!intent.getAction().equals("android.intent.action.PHONE_STATE"))
			return;
		String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

		if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

			String number = intent
					.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			String name = "No name";
			Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
					Uri.encode(number));
			Cursor c = context.getContentResolver()
					.query(uri, new String[] { PhoneLookup.DISPLAY_NAME },
							null, null, null);
			if (c.moveToFirst())
				name = c.getString(c.getColumnIndex(PhoneLookup.DISPLAY_NAME));

            InvisibleTouchApplication.getInstance().setIncomingCallDetected(true);
			Intent i = new Intent(context, IncomingCallActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.putExtra(FLAG_RINGING_CALLER_NUMBER, number);
			i.putExtra(FLAG_RINGING_CALLER_NAME, name);
//			context.startActivity(i);

            AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
            am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+200,pi);
		}

	}
}
