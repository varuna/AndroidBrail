package com.varunarl.invisibletouch;

import android.view.View;

public abstract class SixPackActivity extends BaseActivity {

	private boolean ONE, TWO, THREE, FOUR, FIVE, SIX;
	private View _one_one, _one_two, _one_three, _two_one, _two_two,
			_two_three;

	@Override
	protected void init() {
		setContentView(R.layout.screen_six_pack);
		_one_one = findViewById(R.id.item_one_one);
		_one_two = findViewById(R.id.item_one_two);
		_one_three = findViewById(R.id.item_one_three);
		_two_one = findViewById(R.id.item_two_one);
		_two_two = findViewById(R.id.item_two_two);
		_two_three = findViewById(R.id.item_two_three);

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

	@Override
	public void onKeyOne() {
		toggleColor(0);
	}

	@Override
	public void onKeyTwo() {
		toggleColor(1);
	}

	@Override
	public void onKeyThree() {
		toggleColor(2);
	}

	@Override
	public void onKeyFour() {
		toggleColor(3);
	}

	@Override
	public void onKeyFive() {
		toggleColor(4);
	}

	@Override
	public void onKeySix() {
		toggleColor(5);
	}

	public void resetView() {
		_one_one.setBackgroundColor(getResources().getColor(
				android.R.color.black));
		_one_two.setBackgroundColor(getResources().getColor(
				android.R.color.black));
		_one_three.setBackgroundColor(getResources().getColor(
				android.R.color.black));
		_two_one.setBackgroundColor(getResources().getColor(
				android.R.color.black));
		_two_two.setBackgroundColor(getResources().getColor(
				android.R.color.black));
		_two_three.setBackgroundColor(getResources().getColor(
				android.R.color.black));
		ONE = false;
		TWO = false;
		THREE = false;
		FOUR = false;
		FIVE = false;
		SIX = false;
	}

	private void toggleColor(int index)

	{
		switch (index) {
		case 0:
			if (ONE) {
				_one_one.setBackgroundColor(getResources().getColor(
						android.R.color.black));
				ONE = false;
			} else {
				_one_one.setBackgroundColor(getResources().getColor(
						android.R.color.darker_gray));
				ONE = true;
			}
			break;
		case 1:
			if (TWO) {
				_one_two.setBackgroundColor(getResources().getColor(
						android.R.color.black));
				TWO = false;
			} else {
				_one_two.setBackgroundColor(getResources().getColor(
						android.R.color.darker_gray));
				TWO = true;
			}
			break;
		case 2:
			if (THREE) {
				_one_three.setBackgroundColor(getResources().getColor(
						android.R.color.black));
				THREE = false;
			} else {
				_one_three.setBackgroundColor(getResources().getColor(
						android.R.color.darker_gray));
				THREE = true;
			}
			break;
		case 3:
			if (FOUR) {
				_two_one.setBackgroundColor(getResources().getColor(
						android.R.color.black));
				FOUR = false;
			} else {
				_two_one.setBackgroundColor(getResources().getColor(
						android.R.color.darker_gray));
			}
			break;
		case 4:
			if (FIVE) {
				_two_two.setBackgroundColor(getResources().getColor(
						android.R.color.black));
				FIVE = false;
			} else {
				_two_two.setBackgroundColor(getResources().getColor(
						android.R.color.darker_gray));
				FIVE = true;
			}
			break;
		case 5:
			if (SIX) {
				_two_three.setBackgroundColor(getResources().getColor(
						android.R.color.black));
				SIX = false;
			} else {
				_two_three.setBackgroundColor(getResources().getColor(
						android.R.color.darker_gray));
				SIX = true;
			}
			break;

		default:
			break;
		}
	}

}
