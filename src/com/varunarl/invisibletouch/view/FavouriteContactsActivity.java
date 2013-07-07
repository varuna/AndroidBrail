package com.varunarl.invisibletouch.view;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varunarl.invisibletouch.R;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.FavouriteContacts;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;

public class FavouriteContactsActivity extends SixPackActivity {

    private FavouriteContacts mFavourites;

    @Override
    public void onSwipeRight() {
    }

    @Override
    public void onSwipeLeft() {
        Log.announce("Left swipe", Level.INFO);
        finish();
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
    public void onCameraKeyShortPress() {
    }

    @Override
    public void onCameraKeyLongPress() {
    }

    @Override
    public void onScreenLongPress() {
        Log.announce("In Favourite screen", false);
    }

    @Override
    public void onKeyOne() {
        mFavourites.callFavourite(0);
        super.onKeyOne();
    }

    @Override
    public void onKeyTwo() {
        mFavourites.callFavourite(1);
        super.onKeyTwo();
    }

    @Override
    public void onKeyThree() {
        mFavourites.callFavourite(2);
        super.onKeyThree();
    }

    @Override
    public void onKeyFour() {
        mFavourites.callFavourite(3);
        super.onKeyFour();
    }

    @Override
    public void onKeyFive() {
        mFavourites.callFavourite(4);
        super.onKeyFive();
    }

    @Override
    public void onKeySix() {
        mFavourites.callFavourite(5);
        super.onKeySix();
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
        mFavourites = FavouriteContacts.getInstance(getApplicationContext(), this);
        super.init();
    }

    @Override
    protected void onAttachView(int id, View view) {
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
}
