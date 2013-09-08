package com.varunarl.invisibletouch.view;

import android.content.Intent;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.ScreenHelper;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.FavouriteContacts;
import com.varunarl.invisibletouch.utils.Log;

public class FavouriteContactsActivity extends SixPackActivity {

    private final int REQUEST_REMOVE_FAVOURITE_CONTACT = 12469;
    private final String CONTACT_INDEX = "com.varunarl.invisibletouch.view.favouritecontactsactivity.CONTACT_INDEX";
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
//        Log.announce(ScreenHelper.FAVOURITE_SCREEN_HELPER, true);
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
        requestRemove(0, mFavourites.get(0).second);
    }

    @Override
    public void onLongKeyTwo() {
        requestRemove(1, mFavourites.get(1).second);
    }

    @Override
    public void onLongKeyThree() {
        requestRemove(2, mFavourites.get(2).second);
    }

    @Override
    public void onLongKeyFour() {
        requestRemove(3, mFavourites.get(3).second);
    }

    @Override
    public void onLongKeyFive() {
        requestRemove(4, mFavourites.get(4).second);
    }

    @Override
    public void onLongKeySix() {
        requestRemove(5, mFavourites.get(5).second);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_REMOVE_FAVOURITE_CONTACT && resultCode == RESULT_OK) {
            mFavourites.removeFromFavourites(data.getIntExtra(CONTACT_INDEX, 0));
            setViewText(data.getIntExtra(CONTACT_INDEX, 0), "Empty", "");
        }
    }

    private void requestRemove(int index, String phone) {
        Intent intent = new Intent(this, BooleanActivity.class);
        intent.putExtra(CONTACT_INDEX, index);
        intent.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE, "Do you want to remove " + phone + ", from favourite contacts ?");
        intent.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, "Remove Favourite");
        intent.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_SUMMARY, "Remove " + phone + " ?");
        startActivityForResult(intent, REQUEST_REMOVE_FAVOURITE_CONTACT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setViewText(0, mFavourites.get(0).first.isEmpty() ? "Empty" : mFavourites.get(0).first, mFavourites.get(0).second.isEmpty() ? "" : mFavourites.get(0).second);
        setViewText(1, mFavourites.get(1).first.isEmpty() ? "Empty" : mFavourites.get(1).first, mFavourites.get(1).second.isEmpty() ? "" : mFavourites.get(1).second);
        setViewText(2, mFavourites.get(2).first.isEmpty() ? "Empty" : mFavourites.get(2).first, mFavourites.get(2).second.isEmpty() ? "" : mFavourites.get(2).second);
        setViewText(3, mFavourites.get(3).first.isEmpty() ? "Empty" : mFavourites.get(3).first, mFavourites.get(3).second.isEmpty() ? "" : mFavourites.get(3).second);
        setViewText(4, mFavourites.get(4).first.isEmpty() ? "Empty" : mFavourites.get(4).first, mFavourites.get(4).second.isEmpty() ? "" : mFavourites.get(4).second);
        setViewText(5, mFavourites.get(5).first.isEmpty() ? "Empty" : mFavourites.get(5).first, mFavourites.get(5).second.isEmpty() ? "" : mFavourites.get(5).second);


    }

    @Override
    protected void init() {
        mFavourites = InvisibleTouchApplication.getInstance().getContactManager().getFavouriteContacts();
        super.init();

        setViewText(0, mFavourites.get(0).first.isEmpty() ? "Empty" : mFavourites.get(0).first, mFavourites.get(0).second.isEmpty() ? "" : mFavourites.get(0).second);
        setViewText(1, mFavourites.get(1).first.isEmpty() ? "Empty" : mFavourites.get(1).first, mFavourites.get(1).second.isEmpty() ? "" : mFavourites.get(1).second);
        setViewText(2, mFavourites.get(2).first.isEmpty() ? "Empty" : mFavourites.get(2).first, mFavourites.get(2).second.isEmpty() ? "" : mFavourites.get(2).second);
        setViewText(3, mFavourites.get(3).first.isEmpty() ? "Empty" : mFavourites.get(3).first, mFavourites.get(3).second.isEmpty() ? "" : mFavourites.get(3).second);
        setViewText(4, mFavourites.get(4).first.isEmpty() ? "Empty" : mFavourites.get(4).first, mFavourites.get(4).second.isEmpty() ? "" : mFavourites.get(4).second);
        setViewText(5, mFavourites.get(5).first.isEmpty() ? "Empty" : mFavourites.get(5).first, mFavourites.get(5).second.isEmpty() ? "" : mFavourites.get(5).second);
        Log.announce(ScreenHelper.FAVOURITE_ACTIVATE, true);
    }
}
