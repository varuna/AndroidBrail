package com.varunarl.invisibletouch;

import android.view.View.OnClickListener;

public interface IGestures extends OnClickListener{

	void onEnterGesture();
	void onBackspaceGesture();
	void onDoubleEnterGesture();
	void onDoubleBackspaceGesture();
	
	void onSwipeUp();
	void onDoubleSwipeUp();
	void onSwipeDown();
	void onDoubleSwipeDown();
	
	void onVolumeDownKeyShortPress();
	void onVolumeDownKeyLongPress();
	void onVolumeUpKeyShortPress();
	void onVolumeUpKeyLongPress();
	void onPowerKeyShortPress();
	void onPowerKeyLongPress();
	
}
