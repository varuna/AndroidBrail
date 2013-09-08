package com.varunarl.invisibletouch.utils;

import android.util.Pair;

public class FavouriteExistsException extends Exception {

	private Pair<String,String> mFavContact;
	
	public FavouriteExistsException(Pair<String,String> contact)
	{
		super();
		this.mFavContact = contact;
	}
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Specified Favourite for " +mFavContact.first+" Already Exists.";
	}

	@Override
	public void printStackTrace() {
		System.out.print(getMessage());
	}
	
	

}
