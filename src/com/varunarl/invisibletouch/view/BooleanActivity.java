package com.varunarl.invisibletouch.view;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import com.varunarl.invisibletouch.internal.SinglePackActivity;

public class BooleanActivity extends SinglePackActivity {

    private final int ACTION_DELETE_CONTACT = 0;
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
        if (action.equals(ContactsActivity.ACTION_DELETE_CONTACT))
            mActionToPerform = ACTION_DELETE_CONTACT;
    }

    private void proceed() {
        switch (mActionToPerform) {
            case ACTION_DELETE_CONTACT:
                deleteContact();
                break;
            default:
                break;
        }
    }



    private boolean deleteContact() {
        String name = getIntent().getStringExtra(ContactsActivity.INTENT_FLAG_CONTACT_NAME);
        String phone = getIntent().getStringExtra(ContactsActivity.INTENT_FLAG_CONTACT_TELEPHONE);

        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(phone));
        Cursor cur = getContentResolver().query(contactUri, null, null, null,
                null);
        try {
            if (cur != null && cur.moveToFirst()) {
                do {
                    if (cur.getString(
                            cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME))
                            .equalsIgnoreCase(name)) {
                        String lookupKey = cur
                                .getString(cur
                                        .getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                        Uri uri = Uri.withAppendedPath(
                                ContactsContract.Contacts.CONTENT_LOOKUP_URI,
                                lookupKey);
                        getContentResolver().delete(uri, null, null);
                        return true;
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
