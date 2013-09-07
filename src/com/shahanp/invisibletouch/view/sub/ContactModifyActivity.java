package com.shahanp.invisibletouch.view.sub;

import android.content.Intent;

import com.shahanp.invisibletouch.braille.Braille;
import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;
import com.shahanp.invisibletouch.internal.KeyboardActivity;
import com.shahanp.invisibletouch.utils.Contact;
import com.shahanp.invisibletouch.utils.ContactManager;
import com.shahanp.invisibletouch.utils.Log;
import com.shahanp.invisibletouch.view.BooleanActivity;


public class ContactModifyActivity extends KeyboardActivity {

    public static final int REQUEST_CONTACT_ADD_NAME = "com.shahanp.invisibletouch.view.MODIFY_CONTACT_NAME".hashCode();
    public static final int REQUEST_CONTACT_MODIFY_NAME = "com.shahanp.invisibletouch.view.MODIFY_CONTACT_NAME".hashCode();
    public static final int REQUEST_CONTACT_ADD_PHONE = "com.shahanp.invisibletouch.view.MODIFY_CONTACT_PHONE".hashCode();
    public static final int REQUEST_CONTACT_MODIFY_PHONE = "com.shahanp.invisibletouch.view.MODIFY_CONTACT_PHONE".hashCode();
    public static final int REQUEST_CONTACT_COMPLETE = "com.shahanp.invisibletouch.view.CONTACT_COMPLETE".hashCode();
    public static final int REQUEST_CONTACT_DELETE = "com.shahanp.invisibletouch.view.CONTACT_DELETE".hashCode();
    private Contact mContact;
    private int mStage;
    private InvisibleTouchApplication app;
    private ContactManager mContactManager;

    @Override
    protected void init() {
        super.init();
        app = InvisibleTouchApplication.getInstance();
        mContactManager = app.getContactManager();
        String action = getIntent().getAction();
        if (action.equals(ContactManager.ACTION_NEW_CONTACT)) {
            mContact = new Contact();
            mStage = Contact.STAGE_NEW_NAME;
        } else if (action.equals(ContactManager.ACTION_UPDATE_CONTACT)) {
            mContact = getIntent().getParcelableExtra(Contact.PARCELABLE_CONTACT);
            mStage = Contact.STAGE_NEW_NAME;
        } else if (action.equals(ContactManager.ACTION_DELETE_CONTACT)) {
            mContact = getIntent().getParcelableExtra(Contact.PARCELABLE_CONTACT);
            Intent i = new Intent(this, BooleanActivity.class);
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE, "Do you want to remove contact, " + mContact.getName() + ", " + mContact.getPhone());
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, "Delete contact");
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_SUMMARY, mContact.getName() + "." + mContact.getPhone());
            startActivityForResult(i,REQUEST_CONTACT_DELETE);
        } else {
            finish();
            return;
        }
        setCharacterVisibility(true);
        setKeyTones(true);
    }

    @Override
    public void onSwipeRight() {
        if (mStage == Contact.STAGE_NEW_NAME || mStage == Contact.STAGE_UPDATE_NAME)
            mTextInputManager.buffer(mCurrentCharacter);
        else if (mStage == Contact.STAGE_NEW_PHONE || mStage == Contact.STAGE_UPDATE_PHONE)
            mTextInputManager.buffer(mCurrentCharacter, Braille.KeyBoard.NUMERIC_KEY_TYPE);
        mCurrentCharacter.reset();
        resetView();
        String textTyped = mTextInputManager.getBufferedText();
        Log.announce(textTyped.charAt(textTyped.length() - 1) + "", true);
    }

    @Override
    public void onDoubleSwipeRight() {
        super.onDoubleSwipeRight();
        String text = mTextInputManager.getText();
        Intent i = new Intent(this, BooleanActivity.class);
        int req = -1;
        if (mStage == Contact.STAGE_NEW_NAME || mStage == Contact.STAGE_UPDATE_NAME) {
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE, "You have typed " + text + " as contact name");
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, text);
            req = REQUEST_CONTACT_ADD_NAME;
        } else if (mStage == Contact.STAGE_NEW_PHONE || mStage == Contact.STAGE_UPDATE_NAME) {
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE, "You have typed " + text + " as contact phone number");
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, mContact.getName());
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_SUMMARY, text);
            req = REQUEST_CONTACT_ADD_PHONE;
        }
        startActivityForResult(i, req);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.announce("Request code : "+ requestCode +" : "+REQUEST_CONTACT_DELETE, Log.Level.INFO);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CONTACT_ADD_NAME) {
                mContact.setName(mTextInputManager.getText());
                mStage = Contact.STAGE_NEW_PHONE;
            } else if (requestCode == REQUEST_CONTACT_ADD_PHONE) {
                mContact.setPhone(mTextInputManager.getText());
                mStage = Contact.STAGE_COMPLETE_CONTACT;
            } else if (requestCode == REQUEST_CONTACT_COMPLETE) {
                mContactManager.addNewContact(mContact);
                finish();
                return;
            } else if (requestCode == REQUEST_CONTACT_DELETE) {
                Log.announce("DELETE contact", Log.Level.INFO);
                mContactManager.deleteContact(mContact.getName(),mContact.getPhone());
                finish();
                return;
            } else if (requestCode == REQUEST_CONTACT_MODIFY_NAME) {
                mStage = Contact.STAGE_UPDATE_NAME;
                mTextInputManager.purge();
                return;
            } else if (requestCode == REQUEST_CONTACT_MODIFY_PHONE) {
                mStage = Contact.STAGE_UPDATE_PHONE;
                mTextInputManager.purge();
                return;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (requestCode == REQUEST_CONTACT_DELETE) {
                finish();
                return;
            } else if (requestCode == REQUEST_CONTACT_MODIFY_NAME && getIntent().getAction().equals(ContactManager.ACTION_UPDATE_CONTACT)) {
                mStage = Contact.STAGE_NEW_PHONE;
                mTextInputManager.purge();
                return;
            }
        }
        mTextInputManager.purge();
        Log.announce("results received " + String.valueOf(resultCode == RESULT_OK) + " " + mStage, Log.Level.INFO);

        if (mStage == Contact.STAGE_COMPLETE_CONTACT) {
            Intent i = new Intent(this, BooleanActivity.class);
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE, "Your contact name is " + mContact.getName() + ", and phone number is " + mContact.getPhone() + ". Is this correct ?");
            startActivityForResult(i, REQUEST_CONTACT_COMPLETE);
        }
    }

    @Override
    public void onSwipeUp() {
        if (getIntent().getAction().equals(ContactManager.ACTION_DELETE_CONTACT)) {
            Intent i = new Intent(this, BooleanActivity.class);
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE, "Do you want to delete the contact ?" + mContact.toString());
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, mContact.getName());
            i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, mContact.getPhone());
            startActivityForResult(i, REQUEST_CONTACT_DELETE);
        } else if (getIntent().getAction().equals(ContactManager.ACTION_UPDATE_CONTACT)) {
            Intent i = new Intent(this, BooleanActivity.class);
            if (mStage == Contact.STAGE_NEW_NAME) {
                i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE, "Do you want to update the contact name ?" + mContact.getName());
                i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, mContact.getName());
                i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, mContact.getPhone());
                startActivityForResult(i, REQUEST_CONTACT_MODIFY_NAME);
            } else if (mStage == Contact.STAGE_NEW_PHONE) {
                i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE, "Do you want to update the contact phone ?" + mContact.getPhone());
                i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, mContact.getName());
                i.putExtra(BooleanActivity.INTENT_FLAG_MESSAGE_TITLE, mContact.getPhone());
                startActivityForResult(i, REQUEST_CONTACT_MODIFY_NAME);
            }

        } else {
            if (mStage == Contact.STAGE_NEW_NAME || mStage == Contact.STAGE_UPDATE_NAME)
                super.onSwipeRight();
            else if (mStage == Contact.STAGE_NEW_PHONE || mStage == Contact.STAGE_UPDATE_PHONE) {
                mTextInputManager.buffer(mCurrentCharacter, Braille.KeyBoard.NUMERIC_KEY_TYPE);
                mCurrentCharacter.reset();
                resetView();
            }
        }
    }

    @Override
    public void onSwipeDown() {
        super.onSwipeDown();
    }

    @Override
    public void onScreenLongPress() {
        if (mContact != null)
            Log.announce(mContact.toString(), false);
    }
}
