package com.varunarl.invisibletouch.view;

import java.lang.reflect.Method;

import android.content.Intent;
import android.graphics.Color;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.internal.telephony.ITelephony;
import com.varunarl.invisibletouch.SinglePackActivity;
import com.varunarl.invisibletouch.utils.IncommingCallListener;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;

public class IncommingCallActivity extends SinglePackActivity {

	private com.android.internal.telephony.ITelephony telephonyService;
	private String mName = "";
	private String mNumber = "";
	private LinearLayout mRootView;
	private int NAME_VIEW_ID = 1000;
	private int NUMBER_VIEW_ID = 1001;

	@Override
	protected void init() {
		initializeTelephonyService();
		mName = getIntent().getStringExtra(
				IncommingCallListener.FLAG_RINGING_CALLER_NAME);
		mNumber = getIntent().getStringExtra(
				IncommingCallListener.FLAG_RINGING_CALLER_NUMBER);

		super.init();
	}

	@Override
	public void onSwipeRight() {
		Intent answer = new Intent(Intent.ACTION_MEDIA_BUTTON);
		answer.putExtra(Intent.EXTRA_KEY_EVENT, new KeyEvent(
				KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK));
		sendOrderedBroadcast(answer, null);
	}

	@Override
	public void onSwipeLeft() {
		try {
			telephonyService.endCall();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		finish();
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

	@Override
	public void onKeyOne() {
	}

	@Override
	public void onLongKeyOne() {
	}

	@Override
	protected void onAttachView(int id, View view) {
		TextView name;
		TextView phone;
		if (mRootView == null) {
			mRootView = (LinearLayout) view.getParent();
			mRootView.removeAllViews();
			mRootView.setOrientation(LinearLayout.VERTICAL);
			mRootView.setGravity(Gravity.CENTER);
			mRootView.setBackgroundColor(Color.BLACK);
			name = new TextView(this);
			phone = new TextView(this);
			name.setGravity(Gravity.CENTER);
			phone.setGravity(Gravity.CENTER);
			name.setTextColor(Color.GRAY);
			phone.setTextColor(Color.GRAY);
			name.setId(NAME_VIEW_ID);
			phone.setId(NUMBER_VIEW_ID);
			name.setText(mName);
			phone.setText(mNumber);

			mRootView.addView(name);
			mRootView.addView(phone);
		} else {
			name = (TextView) mRootView.findViewById(NAME_VIEW_ID);
			phone = (TextView) mRootView.findViewById(NUMBER_VIEW_ID);
			name.setText(mName);
			phone.setText(mNumber);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeTelephonyService() {
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		try {
			Log.announce("Get getTeleService...", Level.INFO);
			Class c = Class.forName(tm.getClass().getName());
			Method m = c.getDeclaredMethod("getITelephony");
			m.setAccessible(true);
			telephonyService = (ITelephony) m.invoke(tm);
		} catch (Exception e) {
			e.printStackTrace();
			Log.announce(
					"FATAL ERROR: could not connect to telephony subsystem",
					Level.ERROR);
			Log.announce("Exception object: " + e, Level.ERROR);
		}
	}

}
