package com.shahanp.invisibletouch.view;

import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;
import com.shahanp.invisibletouch.internal.ScreenHelper;
import com.shahanp.invisibletouch.internal.SixPackActivity;
import com.shahanp.invisibletouch.utils.FavouriteContacts;
import com.shahanp.invisibletouch.utils.Log;

public class FavouriteContactsActivity extends SixPackActivity {

    private FavouriteContacts mFavourites;

    @Override
    public void onSwipeRight() {
    }

    @Override
    public void onSwipeLeft() {
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
        Log.announce(ScreenHelper.FAVOURITE_SCREEN_HELPER, true);
    }

    @Override
    public void onKeyOne() {
        mFavourites.callFavourite(0, this);
        super.onKeyOne();
    }

    @Override
    public void onKeyTwo() {
        mFavourites.callFavourite(1, this);
        super.onKeyTwo();
    }

    @Override
    public void onKeyThree() {
        mFavourites.callFavourite(2, this);
        super.onKeyThree();
    }

    @Override
    public void onKeyFour() {
        mFavourites.callFavourite(3, this);
        super.onKeyFour();
    }

    @Override
    public void onKeyFive() {
        mFavourites.callFavourite(4, this);
        super.onKeyFive();
    }

    @Override
    public void onKeySix() {
        mFavourites.callFavourite(5, this);
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
        mFavourites = InvisibleTouchApplication.getInstance().getContactManager().getFavouriteContacts();
        super.init();

        setViewText(0, mFavourites.get(0).first.isEmpty() ? "Empty" : mFavourites.get(0).first, mFavourites.get(0).second.isEmpty() ? "" : mFavourites.get(0).second);
        setViewText(3, mFavourites.get(1).first.isEmpty() ? "Empty" : mFavourites.get(1).first, mFavourites.get(1).second.isEmpty() ? "" : mFavourites.get(1).second);
        setViewText(1, mFavourites.get(2).first.isEmpty() ? "Empty" : mFavourites.get(2).first, mFavourites.get(2).second.isEmpty() ? "" : mFavourites.get(2).second);
        setViewText(4, mFavourites.get(3).first.isEmpty() ? "Empty" : mFavourites.get(3).first, mFavourites.get(3).second.isEmpty() ? "" : mFavourites.get(3).second);
        setViewText(2, mFavourites.get(4).first.isEmpty() ? "Empty" : mFavourites.get(4).first, mFavourites.get(4).second.isEmpty() ? "" : mFavourites.get(4).second);
        setViewText(5, mFavourites.get(5).first.isEmpty() ? "Empty" : mFavourites.get(5).first, mFavourites.get(5).second.isEmpty() ? "" : mFavourites.get(5).second);
        Log.announce(ScreenHelper.FAVOURITE_ACTIVATE, true);
    }
}
