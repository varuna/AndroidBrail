package com.varunarl.invisibletouch.view;

import android.content.Intent;
import android.view.View;

import com.varunarl.invisibletouch.internal.BaseActivity;
import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.ScreenHelper;
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
    private Contact mPreviousContact;

    @Override
    public void onSwipeUp() {
        mPreviousContact = mCurrentContact;
        mCurrentContact = InvisibleTouchApplication.getInstance().getContactManager().nextContact();
        if (!mPreviousContact.equals(mCurrentContact)) {
            setViewData();
            Log.announce(ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact), true);
        }
    }

    @Override
    public void onDoubleSwipeUp() {
        onSwipeUp();
    }

    @Override
    public void onSwipeDown() {
        mPreviousContact = mCurrentContact;
        mCurrentContact = InvisibleTouchApplication.getInstance().getContactManager().previousContact();
        if (!mPreviousContact.equals(mCurrentContact)) {
            setViewData();
            Log.announce(ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact), true);
        }
    }

    @Override
    public void onDoubleSwipeDown() {
        onSwipeDown();
    }

    @Override
    public void onSwipeRight() {
        Log.announce(ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact) + " Dialing", true);
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
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE + ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact), true);

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
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE + "Update Contact.", true);

    }

    @Override
    public void onLongKeyTwo() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE + "Call" + ScreenHelper.getContactDetailsActivityScreenHelper(mCurrentContact), true);

    }

    @Override
    public void onLongKeyThree() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE + "Delete Contact.", true);

    }

    @Override
    public void onLongKeyFour() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE + "New Contact.", true);

    }

    @Override
    public void onLongKeyFive() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE + "Show Details.", true);

    }

    @Override
    public void onLongKeySix() {
        Log.announce(ScreenHelper.CONTACTS_ACTIVATE + "Add to favourite.", true);

    }

    @Override
    public void onKeyOne() {
        Intent i = new Intent(this, ContactModifyActivity.class);
        i.setAction(ContactManager.ACTION_UPDATE_CONTACT);
        i.putExtra(ContactManager.INTENT_FLAG_CONTACT, mCurrentContact);
        i.putExtra(BaseActivity.INTENT_FLAG_LAST_SCREEN_NAME,ScreenHelper.CONTACTS_ACTIVATE);
        startActivity(i);
    }

    @Override
    public void onKeyTwo() {
        InvisibleTouchApplication.getInstance().getCallManager().makeCall(mCurrentContact.getPhone(), this);
    }

    @Override
    public void onKeyThree() {
        Intent i = new Intent(ContactsActivity.this, BooleanActivity.class);
        i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE, "Do you want to remove contact, " + mCurrentContact.getName() + ", " + mCurrentContact.getPhone());
        i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, "Delete contact");
        i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_SUMMARY, mCurrentContact.getName() + "." + mCurrentContact.getPhone());
        startActivityForResult(i,ContactModifyActivity.REQUEST_CONTACT_DELETE);
    }

    @Override
    public void onKeyFour() {
        Intent i = new Intent(this, ContactModifyActivity.class);
        i.setAction(ContactManager.ACTION_NEW_CONTACT);
        i.putExtra(BaseActivity.INTENT_FLAG_LAST_SCREEN_NAME,ScreenHelper.CONTACTS_ACTIVATE);
        startActivity(i);
    }

    @Override
    public void onKeyFive() {
        Intent i = new Intent(ContactsActivity.this,
                ContactsDetailsActivity.class);
        i.putExtra(ContactManager.INTENT_FLAG_CONTACT, mCurrentContact);
        i.putExtra(BaseActivity.INTENT_FLAG_LAST_SCREEN_NAME,ScreenHelper.CONTACTS_ACTIVATE);
        startActivity(i);

    }

    @Override
    public void onKeySix() {
        FavouriteContacts fav = InvisibleTouchApplication.getInstance().getContactManager().getFavouriteContacts();
        try {
            fav.addToFavourite(mCurrentContact.getName(), mCurrentContact.getPhone());
            Log.announce("Added to favourite. ", true);
        } catch (FavouriteExistsException e) {
            e.printStackTrace();
            Log.announce("Already a favourite Contact. ", true);
        }
    }

    private void setViewData() {
        setViewText(0, "Update contact", mCurrentContact.getName());
        setViewText(1, "Call contact", mCurrentContact.getPhone());
        setViewText(2, "Delete contact", null);
        setViewText(3, "New contact", null);
        setViewText(4, "Show details", null);
        setViewText(5, "Add to favourite", null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ContactModifyActivity.REQUEST_CONTACT_DELETE) {
            Log.announce("DELETE contact", Log.Level.INFO);
            InvisibleTouchApplication.getInstance().getContactManager().deleteContact(mCurrentContact.getName(), mCurrentContact.getPhone());
        }
    }
}
