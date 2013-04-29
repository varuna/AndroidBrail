package com.varunarl.invisibletouch.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Pair;

public class FavouriteContacts {
	
	private static final String FAVOURITE_CONTACTS = "FAVOURITE_CONTACTS_INVISIBLE_TOUCH";
	
	private static FavouriteContacts instance;
	
	private Pair<String, Integer> favouriteOne;
	private Pair<String, Integer> favouriteTwo;
	private Pair<String, Integer> favouriteThree;
	private Pair<String, Integer> favouriteFour;
	private Pair<String, Integer> favouriteFive;
	private Pair<String, Integer> favouriteSix;
	
	private static Context appContext;

	private FavouriteContacts(Context context) {
		favouriteOne = new Pair<String, Integer>("", 0);
		favouriteTwo = new Pair<String, Integer>("", 0);
		favouriteThree = new Pair<String, Integer>("", 0);
		favouriteFour = new Pair<String, Integer>("", 0);
		favouriteFive = new Pair<String, Integer>("", 0);
		favouriteSix = new Pair<String, Integer>("", 0);
		
		appContext = context;
	}
	
	public static FavouriteContacts getInstance(Context ctx)
	{
		if (instance == null)
			return getCurrentFavouriteContacts(ctx);
		else
			return instance;
	}

	public void set(int index, Pair<String, Integer> favourite) {
		switch (index) {
		case 1:
			favouriteOne = favourite;
			break;
		case 2:
			favouriteTwo = favourite;
			break;
		case 3:
			favouriteThree = favourite;
			break;
		case 4:
			favouriteFour = favourite;
			break;
		case 5:
			favouriteFive = favourite;
			break;
		case 6:
			favouriteSix = favourite;
			break;

		default:
			break;
		}
	}

	public Pair<String, Integer> get(int index) {
		switch (index) {
		case 1:
			return favouriteOne;
		case 2:
			return favouriteTwo;
		case 3:
			return favouriteThree;
		case 4:
			return favouriteFour;
		case 5:
			return favouriteFive;
		case 6:
			return favouriteSix;
		default:
			return null;
		} 
	}

	private static FavouriteContacts getCurrentFavouriteContacts(Context ctx) {
		instance = new FavouriteContacts(ctx);
		SharedPreferences favouritePreference = ctx.getSharedPreferences(
				FAVOURITE_CONTACTS, Context.MODE_PRIVATE);
		for (String key : favouritePreference.getAll().keySet()) {
			int index = Integer.parseInt("" + key.charAt(key.length() - 1));
			String name = key.substring(0, key.length() - 2);
			int telephone = favouritePreference.getInt(key, 0);
			if (telephone != 0)
				instance.set(index, new Pair<String, Integer>(name,
						telephone));
		}
		return instance;
	}

	public void addToFavourite(int index, String name, int telephone) {
		Pair<String, Integer> curFav = instance.get(index);
		instance.set(index, new Pair<String, Integer>(name, telephone));
		SharedPreferences favouritePreference = appContext.getSharedPreferences(
				FAVOURITE_CONTACTS, Context.MODE_PRIVATE);
		SharedPreferences.Editor favEditor = favouritePreference.edit();
		favEditor.putInt(name+index, telephone);
		favEditor.remove(curFav.first+index);
		favEditor.apply();
	}
	
	public void callFavourite(int index)
	{
		int tele = get(index).second;
		if (tele > 0)
		appContext.startActivity(
				new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
						+ tele)));
	}
}