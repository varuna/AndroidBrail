package com.varunarl.invisibletouch.view;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.TelephonyManager;
import android.view.View;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.FavouriteContacts;
import com.varunarl.invisibletouch.utils.FavouriteExistsException;
import com.varunarl.invisibletouch.utils.IPhoneState;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;
import com.varunarl.invisibletouch.view.sub.ContactsDetailsActivity;

public class ContactsActivity extends SixPackActivity implements IPhoneState {

	private int CALL_REQUEST_CODE = 1;
	public static String INTENT_FLAG_CONTACT_NAME = "com.varunarl.invisibletouch.contactsactivity.intent_flag_contact_name";
	public static String INTENT_FLAG_CONTACT_TELEPHONE = "com.varunarl.invisibletouch.contactsactivity.intent_flag_contact_telephone";

    public static String ACTION_DELETE_CONTACT = "com.varunarl.invisibletouch.contactsactivity.action_delete_contact";

	private Cursor mContacts;

	private int mLastCallState;
	private String mCurrentContactName;
	private String mCurrentContactPhone;
	private Intent mIntent;

	@Override
	public void onSwipeUp() {
		if (!mContacts.isLast()) {
			mContacts.moveToNext();
			updateCurrentContact();
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
			updateCurrentContact();
		}

	}

	@Override
	public void onDoubleSwipeDown() {
		onSwipeDown();
	}

	@Override
	public void onSwipeRight() {
		if (mLastCallState == TelephonyManager.CALL_STATE_IDLE)
			InvisibleTouchApplication.getInstance().getCallManager().makeCall(mCurrentContactPhone,this);
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
	protected void init() {
		mIntent = getIntent();
		this.mContacts = getContentResolver().query(Phone.CONTENT_URI, null,
				null, null, null);
		this.mContacts.moveToFirst();
		mLastCallState = -1;
		super.init();
		InvisibleTouchApplication.getInstance().getCallManager().registerPhoneStateListener(
				this, mIntent);
		updateCurrentContact();
	}

	@Override
	protected void onAttachView(int id, View view) {

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
		//No action specified.

	}

	@Override
	public void onCameraKeyLongPress() {
        //No action specified.

	}

	@Override
	public void onScreenLongPress() {
        Log.announce("In Contacts screen",false);

	}

	@Override
	public void onLongKeyOne() {
        //No action specified.

	}

	@Override
	public void onLongKeyTwo() {
        //No action specified.

	}

	@Override
	public void onLongKeyThree() {
        //No action specified.

	}

	@Override
	public void onLongKeyFour() {
        //No action specified.

	}

	@Override
	public void onLongKeyFive() {
        //No action specified.

	}

	@Override
	public void onLongKeySix() {
        //No action specified.

	}

	@Override
	public void onKeyOne() {
		// TODO edit contact
	}

	@Override
	public void onKeyTwo() {
		InvisibleTouchApplication.getInstance().getCallManager().makeCall(mCurrentContactPhone,this);
	}

	@Override
	public void onKeyThree() {
        Intent i = new Intent(this,BooleanActivity.class);
        i.setAction(ACTION_DELETE_CONTACT);
        i.putExtra(INTENT_FLAG_CONTACT_NAME,mCurrentContactName);
        i.putExtra(INTENT_FLAG_CONTACT_TELEPHONE,mCurrentContactPhone);
        startActivity(i);
	}

	@Override
	public void onKeyFour() {
		// TODO Add new contact

	}

	@Override
	public void onKeyFive() {
		Intent i = new Intent(ContactsActivity.this,
				ContactsDetailsActivity.class);
		i.putExtra(INTENT_FLAG_CONTACT_NAME, mCurrentContactName);
		i.putExtra(INTENT_FLAG_CONTACT_TELEPHONE, mCurrentContactPhone);
		startActivity(i);

	}

	@Override
	public void onKeySix() {
		FavouriteContacts fav = FavouriteContacts
				.getInstance(getApplicationContext(),this);
		try {
			fav.addToFavourite(mCurrentContactName, mCurrentContactPhone);
		} catch (FavouriteExistsException e) {
			e.printStackTrace();
		}
	}

	private void updateCurrentContact() {
		int nameFieldColumnIndex = mContacts.getColumnIndex(Phone.DISPLAY_NAME);
		int numberFieldColumnIndex = mContacts.getColumnIndex(Phone.NUMBER);
		mCurrentContactName = mContacts.getString(nameFieldColumnIndex);
		mCurrentContactPhone = mContacts.getString(numberFieldColumnIndex);
	}


}
