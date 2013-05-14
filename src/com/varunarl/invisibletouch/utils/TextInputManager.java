package com.varunarl.invisibletouch.utils;

import android.content.Context;
import android.widget.Toast;

import com.varunarl.invisibletouch.brail.Brail;
import com.varunarl.invisibletouch.brail.Brail.KeyBoard;
import com.varunarl.invisibletouch.brail.BrailCharacter;
import com.varunarl.invisibletouch.utils.Log.Level;

public class TextInputManager {
	private String mText;
	private int mCurrentBufferType;
	private KeyBoard mKeyBoard;
	private StringBuffer mBuffer;
	private Context mContext;

	public TextInputManager(Context context) {
		mContext = context;
		mCurrentBufferType = Brail.KeyBoard.LOWER_KEY_TYPE;
		mKeyBoard = new KeyBoard();
		mText = "";
		mBuffer = new StringBuffer();
	}

	public void buffer(BrailCharacter c) {
		if (mKeyBoard.isControlCharacter(c)) {
			int oldType = mCurrentBufferType;
			mCurrentBufferType = mKeyBoard.getControlType(mKeyBoard.get(c,
					mCurrentBufferType));
			if (oldType == Brail.KeyBoard.UPPER_KEY_TYPE
					&& mCurrentBufferType == Brail.KeyBoard.UPPER_KEY_TYPE)
				mCurrentBufferType = Brail.KeyBoard.LOWER_KEY_TYPE;
			processBuffer();
		} else if (!mKeyBoard.isErrorCharacter(c)) {
			Character asciic = mKeyBoard.get(c, mCurrentBufferType);
			if (!asciic.equals('~'))
				mBuffer.append(asciic);
			Log.announce(mBuffer.toString(), Level.INFO);
		} else
			Log.announce("Error character. skipping", Level.WARNING);
	}

	public void buffer(Character c) {
		processBuffer();
		mBuffer.append(c);
	}

	public void removeLast() {
		if (mBuffer.length() > 0)
			mBuffer.deleteCharAt(mBuffer.length() - 1);
		else if (mText.length() > 1)
			mText = mText.substring(0, mText.length() - 2);
		else if (mText.length() == 1)
			mText = "";

	}

	public void purge() {
		mBuffer.delete(0, mBuffer.length());
		mText = "";
		mCurrentBufferType = Brail.KeyBoard.LOWER_KEY_TYPE;
	}

	private void processBuffer() {
		String bufferValue = mBuffer.toString();
		mText += bufferValue;
		mBuffer.delete(0, mBuffer.length());
		Toast.makeText(mContext, mText, Toast.LENGTH_LONG).show();
		Log.announce("Current String : " + mText, Level.INFO);
	}
}
