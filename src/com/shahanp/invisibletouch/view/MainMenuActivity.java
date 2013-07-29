package com.shahanp.invisibletouch.view;

import android.content.Intent;
import android.view.View;

import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;
import com.shahanp.invisibletouch.internal.ScreenHelper;
import com.shahanp.invisibletouch.internal.SixPackActivity;
import com.shahanp.invisibletouch.utils.Log;

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
            Log.announce(ScreenHelper.MAIN_MENU_SCREEN_HELPER, true);
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
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
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

    }

    @Override
    public void onLongKeyOne() {
        Log.announce(ScreenHelper.MAIN_MENU_ACTIVATE + "Favourite Contacts", true);
    }

    @Override
    public void onLongKeyTwo() {
        Log.announce(ScreenHelper.MAIN_MENU_ACTIVATE + "Call log", true);
    }

    @Override
    public void onLongKeyThree() {
        Log.announce(ScreenHelper.MAIN_MENU_ACTIVATE + "Settings", true);
    }

    @Override
    public void onLongKeyFour() {
        Log.announce(ScreenHelper.MAIN_MENU_ACTIVATE + "Dial pad", true);
    }

    @Override
    public void onLongKeyFive() {
        Log.announce(ScreenHelper.MAIN_MENU_ACTIVATE + "Contacts", true);
    }

    @Override
    public void onLongKeySix() {
        Log.announce(ScreenHelper.MAIN_MENU_ACTIVATE + "Alarm", true);
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
        setViewText(0,"Favourite Contacts",null);
        setViewText(1,"Call log",null);
        setViewText(2,"Settings",null);
        setViewText(3,"Dial Pad",null);
        setViewText(4,"Contacts",null);
        setViewText(5,"Alarm",null);
        Log.announce(ScreenHelper.MAIN_MENU_ACTIVATE, true);
    }
}
