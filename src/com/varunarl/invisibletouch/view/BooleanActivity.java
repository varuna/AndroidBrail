package com.varunarl.invisibletouch.view;

import android.view.View;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.SinglePackActivity;
import com.varunarl.invisibletouch.utils.Contact;
import com.varunarl.invisibletouch.utils.ContactManager;

public class BooleanActivity extends SinglePackActivity {

    private final int ACTION_ADD_CONTACT = 0;
    private final int ACTION_UPDATE_CONTACT = 1;
    private final int ACTION_DELETE_CONTACT = 2;
    private int mActionToPerform = -1;

    @Override
    protected void init() {
        booleanAction();
        super.init();
    }

    @Override
    public void onSwipeRight() {
        proceed();
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
    public void onPowerKeyShortPress() {

    }

    @Override
    public void onPowerKeyLongPress() {

    }

    @Override
    public void onKeyOne() {

    }

    @Override
    protected void onAttachView(int id, View view) {

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

    private void booleanAction() {
        String action = getIntent().getAction();
        if (action.equals(ContactManager.ACTION_DELETE_CONTACT))
            mActionToPerform = ACTION_DELETE_CONTACT;
        else if (action.equals(ContactManager.ACTION_NEW_CONTACT))
            mActionToPerform = ACTION_ADD_CONTACT;
        else if (action.equals(ContactManager.ACTION_UPDATE_CONTACT))
            mActionToPerform = ACTION_UPDATE_CONTACT;
        else
            finish();
    }

    private void proceed() {
        InvisibleTouchApplication app = InvisibleTouchApplication.getInstance();
        Contact c = getIntent().getParcelableExtra(Contact.PARCELABLE_CONTACT);
        switch (mActionToPerform) {
            case ACTION_ADD_CONTACT:
                app.getContactManager().addNewContact(c);
                break;
            case ACTION_UPDATE_CONTACT:
                app.getContactManager().updateContact(c);
                break;
            case ACTION_DELETE_CONTACT:
                app.getContactManager().deleteContact(c);
                break;

            default:
                break;
        }
    }
}
