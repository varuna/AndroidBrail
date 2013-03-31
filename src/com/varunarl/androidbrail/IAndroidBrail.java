package com.varunarl.androidbrail;

import android.view.View.OnClickListener;

public interface IAndroidBrail extends OnClickListener{

	void onEnterGesture();
	void onBackspaceGesture();
	void onDoubleEnterGesture();
	void onDoubleBackspaceGesture();
}
