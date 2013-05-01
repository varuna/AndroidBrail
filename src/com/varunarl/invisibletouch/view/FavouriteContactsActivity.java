package com.varunarl.invisibletouch.view;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varunarl.invisibletouch.InvisibleTouchApplication;
import com.varunarl.invisibletouch.R;
import com.varunarl.invisibletouch.SixPackActivity;
import com.varunarl.invisibletouch.utils.FavouriteContacts;
import com.varunarl.invisibletouch.utils.IPhoneState;

public class FavouriteContactsActivity extends SixPackActivity implements
		IPhoneState {

	private FavouriteContacts mFavourites;
	private int mLastCallState;

	@Override
	public void onSwipeRight() {
	}

	@Override
	public void onSwipeLeft() {
		this.finish();
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
		mFavourites.callFavourite(0);
	}

	@Override
	public void onKeyTwo() {
		mFavourites.callFavourite(1);
	}

	@Override
	public void onKeyThree() {
		mFavourites.callFavourite(2);
	}

	@Override
	public void onKeyFour() {
		mFavourites.callFavourite(3);
	}

	@Override
	public void onKeyFive() {
		mFavourites.callFavourite(4);
	}

	@Override
	public void onKeySix() {
		mFavourites.callFavourite(5);
	}

	@Override
	public void onLongKeyOne() {
	}

	@Override
	public void onLongKeyTwo() {
	}

	@Override
	public void onLongKeyThree() {
	}

	@Override
	public void onLongKeyFour() {
	}

	@Override
	public void onLongKeyFive() {
	}

	@Override
	public void onLongKeySix() {
	}

	@Override
	protected void init() {
		mFavourites = FavouriteContacts.getInstance(getApplicationContext());
		super.init();
		InvisibleTouchApplication.getInstance().registerPhoneStateListener(
				this, getIntent());
	}

	@Override
	protected void onAttachView(int id, View view) {
		Log.i("View attaches", id + "");
		int idx;
		switch (id) {
		case R.id.item_one_one:
			idx = 0;
			break;
		case R.id.item_one_two:
			idx = 2;
			break;
		case R.id.item_one_three:
			idx = 4;
			break;
		case R.id.item_two_one:
			idx = 1;
			break;
		case R.id.item_two_two:
			idx = 3;
			break;
		case R.id.item_two_three:
			idx = 5;
			break;

		default:
			idx = 5;
			break;
		}
		setFavouriteView(view, mFavourites.get(idx).first,
				mFavourites.get(idx).second);

	}

	private void setFavouriteView(View v, String name, String telephone) {
		LinearLayout top = (LinearLayout) v;
		top.removeAllViews();
		top.setOrientation(LinearLayout.VERTICAL);
		TextView _name = new TextView(this);
		top.setGravity(Gravity.CENTER);
		_name.setGravity(Gravity.CENTER);

		if (name.equals("") || telephone.equals("")) {
			_name.setText("Empty");
			top.addView(_name);
			return;
		}

		TextView _telephone = new TextView(this);

		_name.setText(name);
		_telephone.setText(telephone);

		_telephone.setGravity(Gravity.CENTER);
		top.addView(_name);
		top.addView(_telephone);

	}

	@Override
	public void setPhoneState(int state) {
		mLastCallState = state;
	}

	@Override
	public int getPhoneState() {
		return mLastCallState;
	}

}
