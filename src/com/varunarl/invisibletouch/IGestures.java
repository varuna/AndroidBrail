package com.varunarl.invisibletouch;

import android.view.View.OnClickListener;

public interface IGestures extends OnClickListener{

	void onSwipeRight();
	void onSwipeLeft();
	void onDoubleSwipeRight();
	void onDoubleSwipeLeft();
	
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
	void onCameraKeyShortPress();
	void onCameraKeyLongPress();
	
	void onScreenLongPress();
	
}
