package com.varunarl.androidbrail;

import android.view.View.OnClickListener;

public interface IGestures extends OnClickListener{

	void onEnterGesture();
	void onBackspaceGesture();
	void onDoubleEnterGesture();
	void onDoubleBackspaceGesture();
	
	void onVolumeDownKeyShortPress();
	void onVolumeDownKeyLongPress();
	void onVolumeUpKeyShortPress();
	void onVolumeUpKeyLongPress();
	void onPowerKeyShortPress();
	void onPowerKeyLongPress();
	
}
