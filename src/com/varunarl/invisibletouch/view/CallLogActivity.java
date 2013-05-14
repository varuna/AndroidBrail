package com.varunarl.invisibletouch.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CallLog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varunarl.invisibletouch.SixPackActivity;

public class CallLogActivity extends SixPackActivity {

	private String TAG = "CallLogActivity";
	private int NAME_VIEW_ID = 1000;
	private int NUMBER_VIEW_ID = 1001;
	private int TYPE_VIEW_ID = 1002;
	private int DURATION_VIEW_ID = 1003;
	private int DATE_VIEW_ID = 1004;

	private int CALL_REQUEST_CODE = 1;

	private Cursor mContacts;
	private LinearLayout mRootView;

	private String mCurrentContactName;
	private String mCurrentContactPhone;
	private String mCurrentContactType;
	private String mCurrentContactDuration;
	private String mCurrentContactDate;

	@Override
	public void onSwipeUp() {
		if (!mContacts.isLast()) {
			mContacts.moveToNext();
			onAttachView(0, mRootView);
		}
	}

	@Override
	public void onDoubleSwipeUp() {
		onSwipeUp();
	}

	@Override
	public void onSwipeDown() {
		if (!mContacts.isFirst()) {
			mContacts.moveToPrevious();
			onAttachView(0, mRootView);
		}

	}

	@Override
	public void onDoubleSwipeDown() {
		onSwipeDown();
	}

	@Override
	public void onSwipeRight() {
			startActivityForResult(
					new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
							+ mCurrentContactPhone)), CALL_REQUEST_CODE);
	}

	@Override
	public void onSwipeLeft() {
		finish();
	}

	@Override
	public void onDoubleSwipeRight() {
		onSwipeRight();
	}

	@Override
	public void onDoubleSwipeLeft() {
		onSwipeLeft();
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
	public void onKeyOne() {

	}

	@Override
	protected void init() {
		this.mContacts = getContentResolver().query(CallLog.Calls.CONTENT_URI,
				null, null, null, null);
		this.mContacts.moveToFirst();
		super.init();
	}

	@Override
	protected void onAttachView(int id, View view) {
		TextView name;
		TextView phone;
		TextView type;
		TextView duration;
		TextView date;
		if (!mContacts.isAfterLast() || !mContacts.isBeforeFirst()) {
			int nameFieldColumnIndex = mContacts
					.getColumnIndex(CallLog.Calls.CACHED_NAME);
			int numberFieldColumnIndex = mContacts
					.getColumnIndex(CallLog.Calls.NUMBER);
			int typeFieldColumnIndex = mContacts
					.getColumnIndex(CallLog.Calls.TYPE);
			int durationFieldColumnIndex = mContacts
					.getColumnIndex(CallLog.Calls.DURATION);
			int dateFieldColumnIndex = mContacts
					.getColumnIndex(CallLog.Calls.DATE);

			if (mRootView == null) {
				mRootView = (LinearLayout) view.getParent();
				mRootView.removeAllViews();
				mRootView.setOrientation(LinearLayout.VERTICAL);
				mRootView.setGravity(Gravity.CENTER);
				mRootView.setBackgroundColor(Color.BLACK);
				name = new TextView(this);
				phone = new TextView(this);
				type = new TextView(this);
				duration = new TextView(this);
				date = new TextView(this);

				name.setGravity(Gravity.CENTER);
				phone.setGravity(Gravity.CENTER);
				type.setGravity(Gravity.CENTER);
				duration.setGravity(Gravity.CENTER);
				date.setGravity(Gravity.CENTER);

				name.setTextColor(Color.GRAY);
				phone.setTextColor(Color.GRAY);
				type.setTextColor(Color.GRAY);
				duration.setTextColor(Color.GRAY);

				name.setId(NAME_VIEW_ID);
				phone.setId(NUMBER_VIEW_ID);
				type.setId(TYPE_VIEW_ID);
				duration.setId(DURATION_VIEW_ID);
				date.setId(DATE_VIEW_ID);

				mCurrentContactName = mContacts.getString(nameFieldColumnIndex);
				mCurrentContactPhone = mContacts
						.getString(numberFieldColumnIndex);
				mCurrentContactType = mContacts.getString(typeFieldColumnIndex);
				mCurrentContactDuration = mContacts
						.getString(durationFieldColumnIndex);
				mCurrentContactDate = mContacts.getString(dateFieldColumnIndex);

				name.setText(mCurrentContactName);
				phone.setText(mCurrentContactPhone);
				type.setText(mCurrentContactType);
				duration.setText(mCurrentContactDuration);
				date.setText(mCurrentContactDate);

				mRootView.addView(name);
				mRootView.addView(phone);
				mRootView.addView(type);
				mRootView.addView(duration);
				mRootView.addView(date);

			} else {
				name = (TextView) mRootView.findViewById(NAME_VIEW_ID);
				phone = (TextView) mRootView.findViewById(NUMBER_VIEW_ID);
				type = (TextView) mRootView.findViewById(TYPE_VIEW_ID);
				duration = (TextView) mRootView.findViewById(DURATION_VIEW_ID);
				date = (TextView) mRootView.findViewById(DATE_VIEW_ID);

				mCurrentContactName = mContacts.getString(nameFieldColumnIndex);
				mCurrentContactPhone = mContacts
						.getString(numberFieldColumnIndex);
				mCurrentContactType = mContacts.getString(typeFieldColumnIndex);
				mCurrentContactDuration = mContacts
						.getString(durationFieldColumnIndex);
				mCurrentContactDate = mContacts.getString(dateFieldColumnIndex);

				name.setText(mCurrentContactName);
				phone.setText(mCurrentContactPhone);
				type.setText(mCurrentContactType);
				duration.setText(mCurrentContactDuration);
				date.setText(mCurrentContactDate);
			}
		} else {
			if (id != 0)
				view.setBackgroundColor(Color.GRAY);
		}
		Log.i(TAG, "Current contact\n" + mCurrentContactName + "\n"
				+ mCurrentContactPhone + "\n" + mCurrentContactType + "\n"
				+ mCurrentContactDuration);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CALL_REQUEST_CODE) {
			Log.i("Contacts", "Hit");
		}
	}

	@Override
	public void onCameraKeyShortPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCameraKeyLongPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScreenLongPress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLongKeyOne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLongKeyTwo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLongKeyThree() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLongKeyFour() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLongKeyFive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLongKeySix() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyTwo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyThree() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyFour() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyFive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeySix() {
		// TODO Auto-generated method stub
		
	}

}
