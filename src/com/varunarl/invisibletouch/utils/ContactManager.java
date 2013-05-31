package com.varunarl.invisibletouch.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;

/**
 * Created by Varuna on 5/30/13.
 */
public class ContactManager {

    public static final String ACTION_NEW_CONTACT = "com.varunarl.invisibletouch.contact.ACTION_NEW_CONTACT";
    public static final String ACTION_DELETE_CONTACT = "com.varunarl.invisibletouch.contact.ACTION_DELETE_CONTACT";

    public static final String ACTION_UPDATE_CONTACT = "com.varunarl.invisibletouch.contact.ACTION_UPDATE_CONTACT";

    public static final String INTENT_FLAG_CONTACT = "com.varunarl.invisibletouch.contactmanager.INTENT_FLAG_CONTACT";

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
            ContentValues values = new ContentValues();
            values.put(Data.RAW_CONTACT_ID, contact.hashCode());
            values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
            values.put(Phone.DISPLAY_NAME,contact.getName());
            values.put(Phone.NUMBER, contact.getPhone());
            values.put(Phone.TYPE, Phone.TYPE_CUSTOM);
            mContext.getContentResolver().insert(Data.CONTENT_URI, values);

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

