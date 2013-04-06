package com.varunarl.invisibletouch;

import android.view.View;

public abstract class SixPackActivity extends BaseActivity {

	@Override
	protected void init() {
		setContentView(R.layout.screen_six_pack);
		View _one_one = findViewById(R.id.item_one_one);
		View _one_two = findViewById(R.id.item_one_two);
		View _one_three = findViewById(R.id.item_one_three);
		View _two_one = findViewById(R.id.item_two_one);
		View _two_two = findViewById(R.id.item_two_two);
		View _two_three = findViewById(R.id.item_two_three);

		_one_one.setClickable(true);
		_one_two.setClickable(true);
		_one_three.setClickable(true);
		_two_one.setClickable(true);
		_two_two.setClickable(true);
		_two_three.setClickable(true);

		_one_one.setOnClickListener(this);
		_one_two.setOnClickListener(this);
		_one_three.setOnClickListener(this);
		_two_one.setOnClickListener(this);
		_two_two.setOnClickListener(this);
		_two_three.setOnClickListener(this);
		
		onAttachView(R.id.item_one_one, _one_one);
		onAttachView(R.id.item_one_two, _one_two);
		onAttachView(R.id.item_one_three, _one_three);
		onAttachView(R.id.item_two_one, _two_one);
		onAttachView(R.id.item_two_two, _two_two);
		onAttachView(R.id.item_two_three, _two_three);
	}
	
	@Override
	protected void onAttachView(int id, View view) {
		
	}

}
