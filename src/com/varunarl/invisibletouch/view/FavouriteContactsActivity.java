package com.varunarl.invisibletouch.view;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varunarl.invisibletouch.R;
import com.varunarl.invisibletouch.SixPackActivity;
import com.varunarl.invisibletouch.utils.FavouriteContacts;

public class FavouriteContactsActivity extends SixPackActivity {

	private FavouriteContacts mFavourites;

	@Override
	public void onSwipeRight() {
	}

	@Override
	public void onSwipeLeft() {
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
		Log.i("Fav", "init");
	}

	@Override
	protected void onAttachView(int id, View view) {
		switch (id) {
		case R.id.item_one_one:
			setFavouriteView(view, mFavourites.get(0).first, mFavourites.get(0).second);
		case R.id.item_one_two:
			setFavouriteView(view, mFavourites.get(1).first, mFavourites.get(1).second);
		case R.id.item_one_three:
			setFavouriteView(view, mFavourites.get(2).first, mFavourites.get(2).second);
		case R.id.item_two_one:
			setFavouriteView(view, mFavourites.get(3).first, mFavourites.get(3).second);
		case R.id.item_two_two:
			setFavouriteView(view, mFavourites.get(4).first, mFavourites.get(4).second);
		case R.id.item_two_three:
			setFavouriteView(view, mFavourites.get(5).first, mFavourites.get(5).second);
			break;

		default:
			break;
		}
	}

	private void setFavouriteView(View v, String name, int telephone) {
		LinearLayout top = (LinearLayout)v;
		top.removeAllViews();
		top.setOrientation(LinearLayout.HORIZONTAL);
		
		if (name == "" || telephone == 0)
			return;

		TextView _name = new TextView(this);
		TextView _telephone = new TextView(this);

		_name.setText(name);
		_telephone.setText(telephone + "");

		top.setGravity(Gravity.CENTER);
		top.setWeightSum(1);
		top.addView(_name);
		top.addView(_telephone);

	}

}
