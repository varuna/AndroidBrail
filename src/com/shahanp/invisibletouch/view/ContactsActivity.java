package com.shahanp.invisibletouch.view;

import android.content.Intent;
import android.view.View;

import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;
import com.shahanp.invisibletouch.internal.ScreenHelper;
import com.shahanp.invisibletouch.internal.SixPackActivity;
import com.shahanp.invisibletouch.utils.Contact;
import com.shahanp.invisibletouch.utils.ContactManager;
import com.shahanp.invisibletouch.utils.FavouriteContacts;
import com.shahanp.invisibletouch.utils.FavouriteExistsException;
import com.shahanp.invisibletouch.utils.Log;
import com.shahanp.invisibletouch.view.sub.ContactModifyActivity;
import com.shahanp.invisibletouch.view.sub.ContactsDetailsActivity;

public class ContactsActivity extends SixPackActivity {

    private Contact mCurrentContact;

    @Override
    public void onSwipeUp() {
        mCurrentContact = InvisibleTouchApplication.getInstance().getContactManager().nextContact();
        setViewData();
        Log.announce(ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact), true);
    }

    @Override
    public void onDoubleSwipeUp() {
        onSwipeUp();
    }

    @Override
    public void onSwipeDown() {
        mCurrentContact = InvisibleTouchApplication.getInstance().getContactManager().previousContact();
        setViewData();
        Log.announce(ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact), true);
    }

    @Override
    public void onDoubleSwipeDown() {
        onSwipeDown();
    }

    @Override
    public void onSwipeRight() {
        Log.announce(ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact) + " Dialing",true);
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
        Log.announce(ScreenHelper.CONTACTS_SCREEN_HELPER, true);
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
        setViewData();
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE, true);
        Log.announce(ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact), true);
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
        Log.announce(ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact), false);

    }

    @Override
    public void onLongKeyOne() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE+ "Update Contact.", true);

    }

    @Override
    public void onLongKeyTwo() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE+ "Call" + ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact) , true);

    }

    @Override
    public void onLongKeyThree() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE+ "Delete Contact.", true);

    }

    @Override
    public void onLongKeyFour() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE+ "New Contact.", true);

    }

    @Override
    public void onLongKeyFive() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE+ "Show Details.", true);

    }

    @Override
    public void onLongKeySix() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE+ "Add to favourite.", true);

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

    private void setViewData(){
        setViewText(0,"Update contact",mCurrentContact.getName());
        setViewText(1,"Call contact",mCurrentContact.getPhone());
        setViewText(2,"Delete contact",null);
        setViewText(3,"New contact",null);
        setViewText(4,"Show details",null);
        setViewText(5,"Add to favourite",null);
    }
}
