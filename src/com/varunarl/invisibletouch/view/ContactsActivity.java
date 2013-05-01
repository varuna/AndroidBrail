package com.varunarl.invisibletouch.view;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import com.varunarl.invisibletouch.InvisibleTouchApplication;
import com.varunarl.invisibletouch.SixPackActivity;
import com.varunarl.invisibletouch.utils.FavouriteContacts;
import com.varunarl.invisibletouch.utils.FavouriteExistsException;
import com.varunarl.invisibletouch.utils.IPhoneState;
import com.varunarl.invisibletouch.view.sub.ContactsDetailsActivity;

public class ContactsActivity extends SixPackActivity implements IPhoneState {

	private int CALL_REQUEST_CODE = 1;
	public static String INTENT_FLAG_CONTACT_NAME = "com.varunarl.invisibletouch.contactsactivity.intent_flag_contact_name";
	public static String INTENT_FLAG_CONTACT_TELEPHONE = "com.varunarl.invisibletouch.contactsactivity.intent_flag_contact_telephone";

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
	protected void init() {
		mIntent = getIntent();
		this.mContacts = getContentResolver().query(Phone.CONTENT_URI, null,
				null, null, null);
		this.mContacts.moveToFirst();
		mLastCallState = -1;
		super.init();
		InvisibleTouchApplication.getInstance().registerPhoneStateListener(
				this, mIntent);
		updateCurrentContact();
	}

	@Override
	protected void onAttachView(int id, View view) {

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

	@Override
	public void onKeyOne() {
		// TODO edit contact
	}

	@Override
	public void onKeyTwo() {
		// TODO search contact

	}

	@Override
	public void onKeyThree() {
		deleteContact(mCurrentContactPhone, mCurrentContactName);
	}

	@Override
	public void onKeyFour() {
		// TODO Add new contact
		
	}

	@Override
	public void onKeyFive() {
		Log.i("Contacts", "keyFour");
		Intent i = new Intent(ContactsActivity.this,
				ContactsDetailsActivity.class);
		i.putExtra(INTENT_FLAG_CONTACT_NAME, mCurrentContactName);
		i.putExtra(INTENT_FLAG_CONTACT_TELEPHONE, mCurrentContactPhone);
		startActivity(i);

	}

	@Override
	public void onKeySix() {
		FavouriteContacts fav = FavouriteContacts
				.getInstance(getApplicationContext());
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
	
	private boolean deleteContact(String phone, String name) {
	    Uri contactUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone));
	    Cursor cur = getContentResolver().query(contactUri, null, null, null, null);
	    try {
	        if (cur.moveToFirst()) {
	            do {
	                if (cur.getString(cur.getColumnIndex(PhoneLookup.DISPLAY_NAME)).equalsIgnoreCase(name)) {
	                    String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
	                    Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
	                    getContentResolver().delete(uri, null, null);
	                    return true;
	                }

	            } while (cur.moveToNext());
	        }

	    } catch (Exception e) {
	        System.out.println(e.getStackTrace());
	    }
	    return false;
	}

}
