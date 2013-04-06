package com.varunarl.invisibletouch;

import android.view.View;

public abstract class SinglePackActivity extends BaseActivity {

	@Override
	protected void init() {
		setContentView(R.layout.screen_single_pack);
		View _one_one = findViewById(R.id.item_one_one);
		_one_one.setClickable(true);
		_one_one.setOnClickListener(this);
		onAttachView(R.id.item_one_one, _one_one);
	}
	
	@Override
	public void onKeyTwo() {
		// Disable keyboard function
	}

	@Override
	public void onKeyThree() {
		// Disable keyboard function
	}

	@Override
	public void onKeyFour() {
		// Disable keyboard function
	}

	@Override
	public void onKeyFive() {
		// Disable keyboard function
	}

	@Override
	public void onKeySix() {
		// Disable keyboard function
	}

}
