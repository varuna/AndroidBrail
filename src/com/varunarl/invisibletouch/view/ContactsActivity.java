package com.varunarl.invisibletouch.view;

import android.content.Intent;
import android.view.View;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.Contact;
import com.varunarl.invisibletouch.utils.ContactManager;
import com.varunarl.invisibletouch.utils.FavouriteContacts;
import com.varunarl.invisibletouch.utils.FavouriteExistsException;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.view.sub.ContactModifyActivity;
import com.varunarl.invisibletouch.view.sub.ContactsDetailsActivity;

public class ContactsActivity extends SixPackActivity {

    private Contact mCurrentContact;

    @Override
    public void onSwipeUp() {
        mCurrentContact = InvisibleTouchApplication.getInstance().getContactManager().nextContact();
    }

    @Override
    public void onDoubleSwipeUp() {
        onSwipeUp();
    }

    @Override
    public void onSwipeDown() {
        mCurrentContact = InvisibleTouchApplication.getInstance().getContactManager().previousContact();
    }

    @Override
    public void onDoubleSwipeDown() {
        onSwipeDown();
    }

    @Override
    public void onSwipeRight() {
        InvisibleTouchApplication.getInstance().getCallManager().makeCall(mCurrentContact.getPhone(), this);
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
    protected void init() {
        super.init();

        if (mCurrentContact == null) {
            mCurrentContact = InvisibleTouchApplication.getInstance().getContactManager().getContact();
        }
    }

    @Override
    protected void onAttachView(int id, View view) {

    }

    @Override
    public void onCameraKeyShortPress() {
        //No action specified.

    }

    @Override
    public void onCameraKeyLongPress() {
        //No action specified.

    }

    @Override
    public void onScreenLongPress() {
        Log.announce("In Contacts screen. Current contact " + mCurrentContact.toString(), false);

    }

    @Override
    public void onLongKeyOne() {
        //No action specified.

    }

    @Override
    public void onLongKeyTwo() {
        //No action specified.

    }

    @Override
    public void onLongKeyThree() {
        //No action specified.

    }

    @Override
    public void onLongKeyFour() {
        //No action specified.

    }

    @Override
    public void onLongKeyFive() {
        //No action specified.

    }

    @Override
    public void onLongKeySix() {
        //No action specified.

    }

    @Override
    public void onKeyOne() {
        Intent i = new Intent(this, ContactModifyActivity.class);
        i.setAction(ContactManager.ACTION_UPDATE_CONTACT);
        i.putExtra(ContactManager.INTENT_FLAG_CONTACT, mCurrentContact);
        startActivity(i);
    }

    @Override
    public void onKeyTwo() {
        InvisibleTouchApplication.getInstance().getCallManager().makeCall(mCurrentContact.getPhone(), this);
    }

    @Override
    public void onKeyThree() {
        Intent i = new Intent(this, BooleanActivity.class);
        i.setAction(ContactManager.ACTION_DELETE_CONTACT);
        i.putExtra(ContactManager.INTENT_FLAG_CONTACT, mCurrentContact);
        startActivity(i);
    }

    @Override
    public void onKeyFour() {
        Intent i = new Intent(this, ContactModifyActivity.class);
        i.setAction(ContactManager.ACTION_NEW_CONTACT);
        startActivity(i);
    }

    @Override
    public void onKeyFive() {
        Intent i = new Intent(ContactsActivity.this,
                ContactsDetailsActivity.class);
        i.putExtra(ContactManager.INTENT_FLAG_CONTACT, mCurrentContact);
        startActivity(i);

    }

    @Override
    public void onKeySix() {
        FavouriteContacts fav = FavouriteContacts
                .getInstance(getApplicationContext(), this);
        try {
            fav.addToFavourite(mCurrentContact.getName(), mCurrentContact.getPhone());
        } catch (FavouriteExistsException e) {
            e.printStackTrace();
        }
    }
}
