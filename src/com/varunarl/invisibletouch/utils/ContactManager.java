package com.varunarl.invisibletouch.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Varuna on 5/30/13.
 */
public class ContactManager {

    public static final String ACTION_NEW_CONTACT = "com.varunarl.invisibletouch.contact.ACTION_NEW_CONTACT";
    public static final String ACTION_UPDATE_CONTACT = "com.varunarl.invisibletouch.contact.ACTION_UPDATE_CONTACT";
    public static final String NEW_CONTACT_FOR_EXISTING_PHONE = "com.varunarl.invisibletouch.contact.NEW_CONTACT_FOR_EXISTING_PHONE";
    public static final String EDIT_CONTACT = "com.varunarl.invisibletouch.contact.EDIT_CONTACT";
    public static String ACTION_DELETE_CONTACT = "com.varunarl.invisibletouch.contactsactivity.ACTION_DELETE_CONTACT";
    public static String INTENT_FLAG_CONTACT_NAME = "com.varunarl.invisibletouch.contactsactivity.INTENT_FLAG_CONTACT_NAME";
    public static String INTENT_FLAG_CONTACT_TELEPHONE = "com.varunarl.invisibletouch.contactsactivity.INTENT_FLAG_CONTACT_TELEPHONE";
    private Cursor mContactsCursor;
    private Context mContext;

    public ContactManager(Context context) {
        mContext = context;
        mContactsCursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                null, null, null);
        mContactsCursor.moveToFirst();
    }

    public Contact moveToFirstContact() {
        if (mContactsCursor != null)
            mContactsCursor.moveToFirst();
        return getContact();
    }

    public Contact moveToLastContact() {
        if (mContactsCursor != null)
            mContactsCursor.moveToLast();
        return getContact();
    }

    public Contact nextContact() {
        if (mContactsCursor != null)
            mContactsCursor.moveToNext();
        return getContact();
    }

    public Contact previousContact() {
        if (mContactsCursor != null)
            mContactsCursor.moveToPrevious();
        return getContact();
    }

    public boolean isListOver() {
        return mContactsCursor.isAfterLast();
    }

    public Contact getContact() {
        Contact contact = new Contact();
        if (mContactsCursor != null) {
            int nameFieldColumnIndex = mContactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberFieldColumnIndex = mContactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            contact.setName(mContactsCursor.getString(nameFieldColumnIndex));
            contact.setPhone(mContactsCursor.getString(numberFieldColumnIndex));
        }
        return contact;
    }

    public boolean addNewContact(Contact contact) {
        if (find(contact).equals(contact))
            return false;
        else {
            //TODO add logic
            return true;
        }
    }

    public boolean updateContact(Contact contact) {
        if (find(contact).equals(contact))
            return false;
        else {
            //TODO: edit logic
            return true;
        }

    }

    public Contact find(Contact contact) {
        Contact current = getContact();
        Contact result = _find(contact);
        _find(current);
        return result;
    }

    private Contact _find(Contact contact) {
        Contact mSearch = moveToFirstContact();
        if (mSearch.equals(contact))
            return mSearch;
        while (!isListOver()) {
            mSearch = nextContact();
            if (mSearch.equals(contact))
                return mSearch;
        }
        return new Contact();
    }

    public boolean deleteContact(Contact contact) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(contact.getPhone()));
        Cursor cur = mContext.getContentResolver().query(contactUri, null, null, null,
                null);
        try {
            if (cur != null && cur.moveToFirst()) {
                do {
                    if (cur.getString(
                            cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME))
                            .equalsIgnoreCase(contact.getName())) {
                        String lookupKey = cur
                                .getString(cur
                                        .getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                        Uri uri = Uri.withAppendedPath(
                                ContactsContract.Contacts.CONTENT_LOOKUP_URI,
                                lookupKey);
                        mContext.getContentResolver().delete(uri, null, null);
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

