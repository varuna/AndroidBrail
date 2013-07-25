package com.varunarl.invisibletouch.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.varunarl.invisibletouch.R;
import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;

public class SoundManager {

    private AudioManager mAudioManager;
    private Context mContext;
    private KeyTones mTones;

    public SoundManager(InvisibleTouchApplication context) {
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mContext = context;
        mTones = new KeyTones();
    }

    public KeyTones getKeyTones() {
        return mTones;
    }

    public class KeyTones {

        private int KEY_ONE_SOUND = R.raw.key_one;
        private int KEY_TWO_SOUND = R.raw.key_two;
        private int KEY_THREE_SOUND = R.raw.key_three;
        private int KEY_FOUR_SOUND = R.raw.key_four;
        private int KEY_FIVE_SOUND = R.raw.key_five;
        private int KEY_SIX_SOUND = R.raw.key_six;
        private int DESELECT_SOUND = R.raw.deselect_tone;

        private void play(int sound) {
            playAsync(new int[]{sound});
        }

        private void playAsync(int[] sounds) {
            AsyncMediaPlayer amp = new AsyncMediaPlayer(sounds);
            amp.start();
        }

        public void one(boolean selected) {
            if (!selected)
                play(KEY_ONE_SOUND);
            else
                playAsync(new int[]{DESELECT_SOUND, KEY_ONE_SOUND});
        }

        public void two(boolean selected) {
            if (!selected)
                play(KEY_TWO_SOUND);
            else
                playAsync(new int[]{DESELECT_SOUND, KEY_TWO_SOUND});
        }

        public void three(boolean selected) {
            if (!selected)
                play(KEY_THREE_SOUND);
            else
                playAsync(new int[]{DESELECT_SOUND, KEY_THREE_SOUND});
        }

        public void four(boolean selected) {
            if (!selected)
                play(KEY_FOUR_SOUND);
            else
                playAsync(new int[]{DESELECT_SOUND, KEY_FOUR_SOUND});
        }

        public void five(boolean selected) {
            if (!selected)
                play(KEY_FIVE_SOUND);
            else
                playAsync(new int[]{DESELECT_SOUND, KEY_FIVE_SOUND});
        }

        public void six(boolean selected) {
            if (!selected)
                play(KEY_SIX_SOUND);
            else
                playAsync(new int[]{DESELECT_SOUND, KEY_SIX_SOUND});
        }
    }

    class AsyncMediaPlayer implements MediaPlayer.OnCompletionListener {

        private int[] mSounds;
        private int currentId = 0;

        public AsyncMediaPlayer(int[] sounds) {
            mSounds = sounds;

        }

        public void start() {
            if (currentId < mSounds.length) {
                MediaPlayer mp = MediaPlayer.create(mContext, mSounds[currentId]);
                mp.setOnCompletionListener(this);
                mp.setVolume(0.2f,0.2f);
                mp.start();
            }
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            currentId++;
            start();
        }
    }
}
