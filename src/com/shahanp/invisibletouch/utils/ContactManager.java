package com.shahanp.invisibletouch.utils;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;

import java.util.ArrayList;

public class ContactManager {

    public static final String ACTION_NEW_CONTACT = "com.shahanp.invisibletouch.contact.ACTION_NEW_CONTACT";
    public static final String ACTION_DELETE_CONTACT = "com.shahanp.invisibletouch.contact.ACTION_DELETE_CONTACT";
    public static final String ACTION_UPDATE_CONTACT = "com.shahanp.invisibletouch.contact.ACTION_UPDATE_CONTACT";
    public static final String INTENT_FLAG_CONTACT = "com.shahanp.invisibletouch.contactmanager.INTENT_FLAG_CONTACT";
    private Cursor mContactsCursor;
    private Context mContext;
    private FavouriteContacts mFavouriteContacts;

    public ContactManager(Context context) {
        mContext = context;
        mContactsCursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                null, null,  Phone.DISPLAY_NAME + " ASC");
        mContactsCursor.moveToFirst();

        mFavouriteContacts = new FavouriteContacts(mContext);
    }

    public FavouriteContacts getFavouriteContacts() {
        return mFavouriteContacts;
    }

    public Contact moveToFirstContact() {
        if (mContactsCursor != null)
            mContactsCursor.moveToFirst();
        return getContact();
    }

    public Contact nextContact() {
        if (mContactsCursor != null && !mContactsCursor.isLast())
            mContactsCursor.moveToNext();
        return getContact();
    }

    public Contact previousContact() {
        if (mContactsCursor != null && !mContactsCursor.isFirst())
            mContactsCursor.moveToPrevious();
        return getContact();
    }

    public Contact getContact() {
        Contact contact = new Contact();
        if (mContactsCursor != null) {
            int nameFieldColumnIndex = mContactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int numberFieldColumnIndex = mContactsCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            if (nameFieldColumnIndex != -1 && numberFieldColumnIndex != -1) {
                contact.setName(mContactsCursor.getString(nameFieldColumnIndex));
                contact.setPhone(mContactsCursor.getString(numberFieldColumnIndex));
            }
        }
        return contact;
    }

    public boolean addNewContact(Contact contact) {
        if (find(contact).equals(contact))
            return false;
        else {
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
            int rawContactInsertIndex = ops.size();

            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
            ops.add(ContentProviderOperation
                    .newInsert(Data.CONTENT_URI)
                    .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contact.getName())
                    .build());
            ops.add(ContentProviderOperation
                    .newInsert(Data.CONTENT_URI)
                    .withValueBackReference(
                            ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                    .withValue(Phone.NUMBER, contact.getPhone())
                    .withValue(Phone.TYPE, Phone.TYPE_MOBILE).build());
            try {
                mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        }
    }

    public boolean updateContact(Contact contact) {
        if (find(contact).equals(contact))
            return false;
        else {
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
            int rawContactInsertIndex = ops.size();

            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
            ops.add(ContentProviderOperation
                    .newUpdate(Data.CONTENT_URI)
                    .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contact.getName())
                    .build());
            ops.add(ContentProviderOperation
                    .newUpdate(Data.CONTENT_URI)
                    .withValueBackReference(
                            ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                    .withValue(Phone.NUMBER, contact.getPhone())
                    .withValue(Phone.TYPE, Phone.TYPE_MOBILE).build());
            try {
                mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        while (mSearch != null) {
            mSearch = nextContact();
            if (mSearch != null && mSearch.equals(contact)) {
                return mSearch;
            }
        }
        return new Contact();
    }

    /*
        public boolean deleteContact(Contact contact) {
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
            int rawContactInsertIndex = ops.size();

            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
            ops.add(ContentProviderOperation
                    .newDelete(Data.CONTENT_URI)
                    .withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contact.getName())
                    .build());
            ops.add(ContentProviderOperation
                    .newDelete(Data.CONTENT_URI)
                    .withValueBackReference(
                            ContactsContract.Data.RAW_CONTACT_ID, rawContactInsertIndex)
                    .withValue(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                    .withValue(Phone.NUMBER, contact.getPhone())
                    .withValue(Phone.TYPE, Phone.TYPE_MOBILE).build());
            try {
                mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    */
    public boolean deleteContact(String name, String phone) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone));
        Cursor cur = mContext.getContentResolver().query(contactUri, null, null, null, null);
        try {
            if (cur.moveToFirst()) {
                do {
                    if (cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)).equalsIgnoreCase(name)) {
                        String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                        mContext.getContentResolver().delete(uri, null, null);
                        return true;
                    }

                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return false;
    }

}

