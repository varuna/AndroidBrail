package com.varunarl.invisibletouch.utils;

import android.content.Context;
import android.widget.Toast;

import com.varunarl.invisibletouch.braille.Braille;
import com.varunarl.invisibletouch.braille.Braille.KeyBoard;
import com.varunarl.invisibletouch.braille.BrailleCharacter;
import com.varunarl.invisibletouch.utils.Log.Level;

public class InputManager {
    public static class TextInputManager {
        private String mText;
        private int mCurrentBufferType;
        private KeyBoard mKeyBoard;
        private StringBuffer mBuffer;
        private Context mContext;

        public TextInputManager(Context context) {
            mContext = context;
            mCurrentBufferType = Braille.KeyBoard.LOWER_KEY_TYPE;
            mKeyBoard = new KeyBoard();
            mText = "";
            mBuffer = new StringBuffer();
        }

        public void buffer(BrailleCharacter c) {
            if (mKeyBoard.isControlCharacter(c)) {
                int oldType = mCurrentBufferType;
                mCurrentBufferType = mKeyBoard.getControlType(mKeyBoard.get(c,
                        mCurrentBufferType));
                if (oldType == Braille.KeyBoard.UPPER_KEY_TYPE
                        && mCurrentBufferType == Braille.KeyBoard.UPPER_KEY_TYPE)
                    mCurrentBufferType = Braille.KeyBoard.LOWER_KEY_TYPE;

            } else if (!mKeyBoard.isErrorCharacter(c)) {
                Character asciic = mKeyBoard.get(c, mCurrentBufferType);
                if (!asciic.equals('~'))
                    mBuffer.append(asciic);
                Log.announce(mBuffer.toString(), Level.INFO);
            } else
                Log.announce("Error character. skipping", Level.WARNING);
        }

        public void buffer(BrailleCharacter c, int type) {
            if (!mKeyBoard.isControlCharacter(c)) {
                Character ascii = mKeyBoard.get(c, type);
                if (!ascii.equals('~')) {
                    mBuffer.append(ascii);
                }
            }
        }

        public void forceBuffer(BrailleCharacter character, int type, Character[] chars) {
            Character c = mKeyBoard.get(character, type);
            for (Character x : chars)
                if (x.equals(c)){
                    mBuffer.append(c);
                    return;
                }else{
                    buffer(character,type);
                    return;
                }
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
            mCurrentBufferType = Braille.KeyBoard.LOWER_KEY_TYPE;
        }

        public void processBuffer() {
            String bufferValue = mBuffer.toString();
            mText += bufferValue;
            mBuffer.delete(0, mBuffer.length());
            Toast.makeText(mContext, mText, Toast.LENGTH_LONG).show();
            Log.announce("Current String : " + mText, Level.INFO);
        }

        public String getText() {
            return mText;
        }

        public String getBufferedText() {
            return mBuffer.toString();
        }
    }
}
