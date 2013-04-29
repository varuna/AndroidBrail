package com.varunarl.invisibletouch.view;

import android.content.SharedPreferences;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varunarl.invisibletouch.R;
import com.varunarl.invisibletouch.SixPackActivity;

public class FavouriteContactsActivity extends SixPackActivity {

	private static final String FAVOURITE_CONTACTS = "FAVOURITE_CONTACTS_INVISIBLE_TOUCH";
	
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
	}

	@Override
	public void onKeyTwo() {
	}

	@Override
	public void onKeyThree() {
	}

	@Override
	public void onKeyFour() {
	}

	@Override
	public void onKeyFive() {
	}

	@Override
	public void onKeySix() {
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
		mFavourites = getCurrentFavouriteContacts();
		super.init();
	}

	@Override
	protected void onAttachView(int id, View view) {
		switch (id) {
		case R.id.item_one_one:
		case R.id.item_one_two:
		case R.id.item_one_three:
		case R.id.item_two_one:
		case R.id.item_two_two:
		case R.id.item_two_three:
			break;

		default:
			break;
		}
	}
	
	private void setFavouriteView(View v, String name, int telephone )
	{
		if (name == "" || telephone == 0)
			return;
		
		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
		LinearLayout top = new LinearLayout(this);
		top.setOrientation(LinearLayout.HORIZONTAL);
		TextView _name = new TextView(this);
		TextView _telephone = new TextView(this);
		
		_name.setText(name);
		_telephone.setText(telephone+"");
		
		top.setGravity(Gravity.CENTER);
		top.setWeightSum(1);
		top.addView(_name);
		top.addView(_telephone);
		
		
	}

	private FavouriteContacts getCurrentFavouriteContacts() {
		SharedPreferences favouritePreference = getSharedPreferences(
				FAVOURITE_CONTACTS, MODE_PRIVATE);
		FavouriteContacts contacts = new FavouriteContacts();
		for (String key : favouritePreference.getAll().keySet()) {
			int index = Integer.parseInt(""+key.charAt(key.length() -1));
			String name = key.substring(0, key.length() - 2);
			int telephone = favouritePreference.getInt(key, 0);
			if (telephone != 0)
				contacts.set(index, new Pair<String, Integer>(name, telephone));
		}
		return contacts;
	}

	private void addToFavourite(int index,Pair<String, Integer> favourite) {
		
	}

	private class FavouriteContacts {
		private Pair<String, Integer> favouriteOne;
		private Pair<String, Integer> favouriteTwo;
		private Pair<String, Integer> favouriteThree;
		private Pair<String, Integer> favouriteFour;
		private Pair<String, Integer> favouriteFive;
		private Pair<String, Integer> favouriteSix;

		public FavouriteContacts() {
			this.favouriteOne = new Pair<String, Integer>("",0);
			this.favouriteTwo = new Pair<String, Integer>("",0);
			this.favouriteThree = new Pair<String, Integer>("",0);
			this.favouriteFour = new Pair<String, Integer>("",0);
			this.favouriteFive = new Pair<String, Integer>("",0);
			this.favouriteSix = new Pair<String, Integer>("",0);
		}

		public void set(int index, Pair<String, Integer> favourite) {
			switch (index) {
			case 1:
				favouriteOne = favourite;
				break;
			case 2:
				favouriteTwo = favourite;
				break;
			case 3:
				favouriteThree = favourite;
				break;
			case 4:
				favouriteFour = favourite;
				break;
			case 5:
				favouriteFive = favourite;
				break;
			case 6:
				favouriteSix = favourite;
				break;

			default:
				break;
			}
		}

		public Pair<String, Integer> get(int index) {
			switch (index) {
			case 1:
				return favouriteOne;
			case 2:
				return favouriteTwo;
			case 3:
				return favouriteThree;
			case 4:
				return favouriteFour;
			case 5:
				return favouriteFive;
			case 6:
				return favouriteSix;
			default:
				return null;
			}
		}
	}
}
