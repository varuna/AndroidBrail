package com.varunarl.invisibletouch.view;

import android.content.Intent;
import android.view.View;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.ScreenHelper;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.Log;

public class MainMenuActivity extends SixPackActivity {

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

    }

    @Override
    public void onCameraKeyLongPress() {

    }

    @Override
    public void onScreenLongPress() {
        Log.announce(ScreenHelper.MAIN_MENU_SCREEN_HELPER, true);

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
    protected void onResume() {
        super.onResume();
        if (InvisibleTouchApplication.getInstance().shouldKillApp())
            InvisibleTouchApplication.getInstance().killed();
    }

    @Override
    protected void init() {
        super.init();
        setViewText(0,"Favourites",null);
        setViewText(1,"Call log",null);
        setViewText(2,null,null);
        setViewText(3,"Dial pad",null);
        setViewText(4,"Contacts",null);
        setViewText(5,"Settings",null);
    }
}
