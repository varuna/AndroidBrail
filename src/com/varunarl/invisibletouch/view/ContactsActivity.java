package com.varunarl.invisibletouch.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varunarl.invisibletouch.InvisibleTouchApplication;
import com.varunarl.invisibletouch.SinglePackActivity;
import com.varunarl.invisibletouch.utils.IPhoneState;

public class ContactsActivity extends SinglePackActivity implements IPhoneState {

	private int NAME_VIEW_ID = 1000;
	private int NUMBER_VIEW_ID = 1001;
	private int CALL_REQUEST_CODE = 1;

	private Cursor mContacts;
	private LinearLayout mRootView;

	private int mLastCallState;
	private String mCurrentContactName;
	private String mCurrentContactPhone;
	private Intent mIntent;

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
		if (mLastCallState == TelephonyManager.CALL_STATE_IDLE)
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
		mIntent = getIntent();
		this.mContacts = getContentResolver().query(Phone.CONTENT_URI, null,
				null, null, null);
		this.mContacts.moveToFirst();
		mLastCallState = -1;
		super.init();
		InvisibleTouchApplication.getInstance().registerPhoneStateListener(this, mIntent);
	}

	@Override
	protected void onAttachView(int id, View view) {
		TextView name;
		TextView phone;
		if (!mContacts.isAfterLast() || !mContacts.isBeforeFirst()) {
			int nameFieldColumnIndex = mContacts
					.getColumnIndex(Phone.DISPLAY_NAME);
			int numberFieldColumnIndex = mContacts.getColumnIndex(Phone.NUMBER);
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
				mCurrentContactName = mContacts.getString(nameFieldColumnIndex);
				mCurrentContactPhone = mContacts
						.getString(numberFieldColumnIndex);
				name.setText(mCurrentContactName);
				phone.setText(mCurrentContactPhone);

				mRootView.addView(name);
				mRootView.addView(phone);
			} else {
				name = (TextView) mRootView.findViewById(NAME_VIEW_ID);
				phone = (TextView) mRootView.findViewById(NUMBER_VIEW_ID);
				mCurrentContactName = mContacts.getString(nameFieldColumnIndex);
				mCurrentContactPhone = mContacts
						.getString(numberFieldColumnIndex);
				name.setText(mCurrentContactName);
				phone.setText(mCurrentContactPhone);
			}
		} else {
			if (id != 0)
				view.setBackgroundColor(Color.GRAY);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CALL_REQUEST_CODE) {
			Log.i("Contacts", "Hit");
		}
	}

	@Override
	public void setPhoneState(int state) {
		this.mLastCallState = state;
	}

	@Override
	public int getPhoneState() {
		return mLastCallState;
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

}