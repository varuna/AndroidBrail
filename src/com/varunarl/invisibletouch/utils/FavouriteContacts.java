package com.varunarl.invisibletouch.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import com.varunarl.invisibletouch.internal.BaseActivity;
import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;

public class FavouriteContacts {

    private static final String FAVOURITE_CONTACTS = "com.varunarl.invisibletouch.utils.FavouriteContacts.FAVOURITE_CONTACTS";
    private Context appContext;
    private Pair<String, String> favouriteOne;
    private Pair<String, String> favouriteTwo;
    private Pair<String, String> favouriteThree;
    private Pair<String, String> favouriteFour;
    private Pair<String, String> favouriteFive;
    private Pair<String, String> favouriteSix;


    public FavouriteContacts(Context context) {
        favouriteOne = new Pair<String, String>("", "");
        favouriteTwo = new Pair<String, String>("", "");
        favouriteThree = new Pair<String, String>("", "");
        favouriteFour = new Pair<String, String>("", "");
        favouriteFive = new Pair<String, String>("", "");
        favouriteSix = new Pair<String, String>("", "");
        appContext = context;
        init();

    }

    private void init() {
        SharedPreferences favouritePreference = appContext.getSharedPreferences(
                FAVOURITE_CONTACTS, Context.MODE_MULTI_PROCESS);
        for (String key : favouritePreference.getAll().keySet()) {
            int index = Integer.parseInt("" + key.charAt(key.length() - 1));
            String name = key.substring(0, key.length() - 1);
            String telephone = favouritePreference.getString(key, "");
            if (!telephone.equals(""))
                set(index, new Pair<String, String>(name, telephone));
        }

    }

    private void set(int index, Pair<String, String> favourite) {
        switch (index) {
            case 0:
                favouriteOne = favourite;
                break;
            case 1:
                favouriteTwo = favourite;
                break;
            case 2:
                favouriteThree = favourite;
                break;
            case 3:
                favouriteFour = favourite;
                break;
            case 4:
                favouriteFive = favourite;
                break;
            case 5:
                favouriteSix = favourite;
                break;

            default:
                break;
        }
    }

    public Pair<String, String> get(int index) {
        switch (index) {
            case 0:
                return favouriteOne;
            case 1:
                return favouriteTwo;
            case 2:
                return favouriteThree;
            case 3:
                return favouriteFour;
            case 4:
                return favouriteFive;
            case 5:
                return favouriteSix;
            default:
                return null;
        }
    }

    private void addToFavourite(int index, String name, String telephone) {
        Pair<String, String> curFav = get(index);
        set(index, new Pair<String, String>(name, telephone));
        SharedPreferences favouritePreference = appContext
                .getSharedPreferences(FAVOURITE_CONTACTS, Context.MODE_PRIVATE);
        SharedPreferences.Editor favEditor = favouritePreference.edit();
        favEditor.putString(name + index, telephone);
        favEditor.remove(curFav.first + index);
        favEditor.apply();
    }

    public void addToFavourite(String name, String telephone) throws FavouriteExistsException {

        for (int j = 0; j < 6; j++)
            if (get(j).first.equals(name) && get(j).second.equals(telephone))
                throw new FavouriteExistsException(get(j));

        int i = 0;
        for (; i < 6; i++) {
            if (get(i).second.equals(""))
                break;
        }
        if (i == 6)
            i = 5;
        addToFavourite(i, name, telephone);
    }

    public void callFavourite(int index, BaseActivity activity) {
        String tele = get(index).second;
        if (!tele.equals("")) {
            InvisibleTouchApplication.getInstance().getCallManager().makeCall(tele, activity);
        }
    }

    public void removeFromFavourites(int index) {
        addToFavourite(index, "", "");
    }

}