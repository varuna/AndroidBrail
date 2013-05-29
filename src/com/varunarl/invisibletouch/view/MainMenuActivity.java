package com.varunarl.invisibletouch.view;

import android.content.Intent;

import com.varunarl.invisibletouch.internal.KeyboardActivity;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.Log;

public class MainMenuActivity extends SixPackActivity {

	@Override
	public void onSwipeRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSwipeLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDoubleSwipeRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDoubleSwipeLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSwipeUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDoubleSwipeUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSwipeDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDoubleSwipeDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onVolumeDownKeyShortPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onVolumeDownKeyLongPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onVolumeUpKeyShortPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onVolumeUpKeyLongPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPowerKeyShortPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPowerKeyLongPress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyOne() {
		Intent i = new Intent(this, FavouriteContactsActivity.class);
		startActivity(i);
		super.onKeyOne();

	}

	@Override
	public void onKeyTwo() {
		Intent i = new Intent(this, CallLogActivity.class);
		startActivity(i);
		super.onKeyTwo();
	}

	@Override
	public void onKeyThree() {
		super.onKeyThree();
	}

	@Override
	public void onKeyFour() {
		Intent in = new Intent(this, DialPadActivity.class);
		startActivity(in);
		super.onKeyFour();
	}

	@Override
	public void onKeyFive() {
		Intent i = new Intent(this, ContactsActivity.class);
		startActivity(i);
		super.onKeyFive();
	}

	@Override
	public void onKeySix() {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
		super.onKeySix();
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
        Log.announce("In Main menu",false);

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
