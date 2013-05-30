package com.varunarl.invisibletouch.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Varuna on 5/30/13.
 */
public class Contact implements Parcelable {
    public static final String PARCELABLE_CONTACT = "_CONTACT_";
    private static final String PARCELABLE_NAME_KEY = "_CONTACT_NAME_";
    private static final String PARCELABLE_PHONE_KEY = "_CONTACT_PHONE_";
    private String name;
    private String phone;

    public Contact() {
        this.name = "";
        this.phone = "";
    }

    public Contact(Parcel in)
    {
        Map<String,String> m = in.readHashMap(Contact.class.getClassLoader());
        if (m != null)
        {
            setName(m.get(PARCELABLE_NAME_KEY));
            setPhone(m.get(PARCELABLE_PHONE_KEY));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Map<String, String> p = new HashMap<String, String>(2);
        p.put(PARCELABLE_NAME_KEY, getName());
        p.put(PARCELABLE_PHONE_KEY, getPhone());
        parcel.writeMap(p);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>(){
        @Override
        public Contact createFromParcel(Parcel parcel) {
            return new Contact(parcel);
        }

        @Override
        public Contact[] newArray(int i) {
            return new Contact[i];
        }
    };


    @Override
    public boolean equals(Object o) {
        if (o instanceof Contact) {
            Contact obj = (Contact) o;
            return this.getPhone().equals(obj.getPhone()) && this.getName().equals(obj.getName());
        }
        return false;

    }
}
