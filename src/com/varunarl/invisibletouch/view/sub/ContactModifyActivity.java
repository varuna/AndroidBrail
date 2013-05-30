package com.varunarl.invisibletouch.view.sub;

import com.varunarl.invisibletouch.internal.KeyboardActivity;
import com.varunarl.invisibletouch.utils.ContactManager;

/**
 * Created by Varuna on 5/30/13.
 */
public class ContactModifyActivity extends KeyboardActivity {

    private String mContactName;
    private String mContactPhone;

    @Override
    protected void init() {
        String action = getIntent().getAction();
        if (action.equals(ContactManager.ACTION_NEW_CONTACT))
        {

        }else if (action.equals(ContactManager.NEW_CONTACT_FOR_EXISTING_PHONE))
        {

        }else if (action.equals(ContactManager.EDIT_CONTACT))
        {

        }else{
            finish();
            return;
        }

        super.init();
    }

    @Override
    public void onSwipeRight() {
        super.onSwipeRight();
    }

    @Override
    public void onDoubleSwipeRight() {
        super.onDoubleSwipeRight();
    }

    @Override
    public void onSwipeUp() {
        super.onSwipeUp();
    }

    @Override
    public void onSwipeDown() {
        super.onSwipeDown();
    }
}
