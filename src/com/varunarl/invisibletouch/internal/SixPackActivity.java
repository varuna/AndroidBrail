package com.varunarl.invisibletouch.internal;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.varunarl.invisibletouch.R;
import com.varunarl.invisibletouch.braille.BrailleCharacter;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.view.LockScreenActivity;

public abstract class SixPackActivity extends BaseActivity {

    private final BrailleCharacter mTemplateCharacter = new BrailleCharacter();
    protected boolean mIsColorOnKeyboard;
    private boolean ONE, TWO, THREE, FOUR, FIVE, SIX;
    private View _one_one, _one_two, _one_three, _two_one, _two_two,
            _two_three;
    private TextView _one_one_title, _one_two_title, _one_three_title, _two_one_title, _two_two_title, _two_three_title;
    private TextView _one_one_summary, _one_two_summary, _one_three_summary, _two_one_summary, _two_two_summary, _two_three_summary;
    private boolean mIsVibrationsOn;

    @Override
    protected void init() {
        setContentView(R.layout.screen_six_pack);
        _one_one = findViewById(R.id.item_one_one);
        _one_two = findViewById(R.id.item_one_two);
        _one_three = findViewById(R.id.item_one_three);
        _two_one = findViewById(R.id.item_two_one);
        _two_two = findViewById(R.id.item_two_two);
        _two_three = findViewById(R.id.item_two_three);

        _one_one_title = (TextView) findViewById(R.id.item_one_one_title);
        _one_two_title = (TextView) findViewById(R.id.item_one_two_title);
        _one_three_title = (TextView) findViewById(R.id.item_one_three_title);
        _two_one_title = (TextView) findViewById(R.id.item_two_one_title);
        _two_two_title = (TextView) findViewById(R.id.item_two_two_title);
        _two_three_title = (TextView) findViewById(R.id.item_two_three_title);

        _one_one_summary = (TextView) findViewById(R.id.item_one_one_summary);
        _one_two_summary = (TextView) findViewById(R.id.item_one_two_summary);
        _one_three_summary = (TextView) findViewById(R.id.item_one_three_summary);
        _two_one_summary = (TextView) findViewById(R.id.item_two_one_summary);
        _two_two_summary = (TextView) findViewById(R.id.item_two_two_summary);
        _two_three_summary = (TextView) findViewById(R.id.item_two_three_summary);

        _one_one_title.setVisibility(View.INVISIBLE);
        _one_two_title.setVisibility(View.INVISIBLE);
        _one_three_title.setVisibility(View.INVISIBLE);
        _two_one_title.setVisibility(View.INVISIBLE);
        _two_two_title.setVisibility(View.INVISIBLE);
        _two_three_title.setVisibility(View.INVISIBLE);

        _one_one_summary.setVisibility(View.INVISIBLE);
        _one_two_summary.setVisibility(View.INVISIBLE);
        _one_three_summary.setVisibility(View.INVISIBLE);
        _two_one_summary.setVisibility(View.INVISIBLE);
        _two_two_summary.setVisibility(View.INVISIBLE);
        _two_three_summary.setVisibility(View.INVISIBLE);

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

        //Setting default Six pack settings
        mIsColorOnKeyboard = false; //By default the keyboard colors will not appear
        mIsVibrationsOn = false; //By default the vibrations will not occur
    }

    @Override
    protected void onAttachView(int id, View view) {
        //Hidden implementation. Childs do not override unless required.
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsVibrationsOn = InvisibleTouchApplication.getInstance().getSettingsManager().getSettings().getVibrationEnabled();
    }

    @Override
    public void onKeyOne() {
        if (mIsColorOnKeyboard)
            toggleColor(0);
        if (mIsVibrationsOn)
            InvisibleTouchApplication.getInstance().vibrate(
                    mTemplateCharacter._one_one.getPattern());
    }

    @Override
    public void onKeyTwo() {
        if (mIsColorOnKeyboard)
            toggleColor(1);
        if (mIsVibrationsOn)
            InvisibleTouchApplication.getInstance().vibrate(
                    mTemplateCharacter._one_two.getPattern());
    }

    @Override
    public void onKeyThree() {
        if (mIsColorOnKeyboard)
            toggleColor(2);
        if (mIsVibrationsOn)
            InvisibleTouchApplication.getInstance().vibrate(
                    mTemplateCharacter._one_three.getPattern());
    }

    @Override
    public void onKeyFour() {
        if (mIsColorOnKeyboard)
            toggleColor(3);
        if (mIsVibrationsOn)
            InvisibleTouchApplication.getInstance().vibrate(
                    mTemplateCharacter._two_one.getPattern());
    }

    @Override
    public void onKeyFive() {
        if (mIsColorOnKeyboard)
            toggleColor(4);
        if (mIsVibrationsOn)
            InvisibleTouchApplication.getInstance().vibrate(
                    mTemplateCharacter._two_two.getPattern());
    }

    @Override
    public void onKeySix() {
        if (mIsColorOnKeyboard)
            toggleColor(5);
        if (mIsVibrationsOn)
            InvisibleTouchApplication.getInstance().vibrate(
                    mTemplateCharacter._two_three.getPattern());

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

    public void setCharacterVisibility(boolean isVisible) {
        mIsColorOnKeyboard = isVisible;
    }

    public void setVibrations(boolean turnOn) {
        mIsVibrationsOn = turnOn;
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
                    FOUR = true;
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

    @Override
    public void onPowerKeyShortPress() {
        Log.announce("Power short press", Log.Level.INFO);
        startActivity(new Intent(this, LockScreenActivity.class));
    }

    @Override
    public void onPowerKeyLongPress() {
        Log.announce("Power Long press", Log.Level.INFO);
    }

    public void setViewText(int index, String title, String summary) {
        if (title != null && !title.equals(""))
            switch (index) {
                case 0:
                    _one_one_title.setText(title);
                    _one_one_title.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    _one_two_title.setText(title);
                    _one_two_title.setVisibility(View.VISIBLE);

                    break;
                case 2:
                    _one_three_title.setText(title);
                    _one_three_title.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    _two_one_title.setText(title);
                    _two_one_title.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    _two_two_title.setText(title);
                    _two_two_title.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    _two_three_title.setText(title);
                    _two_three_title.setVisibility(View.VISIBLE);
                    break;
            }
        if (summary != null && !summary.equals(""))
            switch (index) {
                case 0:
                    _one_one_summary.setText(summary);
                    _one_one_summary.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    _one_two_summary.setText(summary);
                    _one_two_summary.setVisibility(View.VISIBLE);

                    break;
                case 2:
                    _one_three_summary.setText(summary);
                    _one_three_summary.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    _two_one_summary.setText(summary);
                    _two_one_summary.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    _two_two_summary.setText(summary);
                    _two_two_summary.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    _two_three_summary.setText(summary);
                    _two_three_summary.setVisibility(View.VISIBLE);
                    break;
            }
    }
}
