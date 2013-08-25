package com.shahanp.invisibletouch.view;

import android.content.Intent;
import android.media.AudioManager;
import android.os.RemoteException;
import android.speech.tts.TextToSpeech;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;
import com.shahanp.invisibletouch.internal.ScreenHelper;
import com.shahanp.invisibletouch.internal.SixPackActivity;
import com.shahanp.invisibletouch.utils.CallManager;
import com.shahanp.invisibletouch.utils.Log;
import com.shahanp.invisibletouch.utils.PhoneStateManager;

import java.util.HashMap;

public class InCallActivity extends SixPackActivity {
    public static final String NUMBER = "com.shahanp.invisibletouch.view.InCallActivity.NUMBER";
    private CallStatus mStatus;

    @Override
    protected void init() {
        super.init();
        mStatus = new CallStatus();
        if (getIntent().hasExtra(NUMBER)) {
            mStatus.mPhoneNumber = getIntent().getStringExtra(NUMBER);
        }
        InvisibleTouchApplication.getInstance().getCallManager().registerInCallScreen(this);

        setViewText(0,"Mute",null);
        setViewText(1,"Speaker",null);
        setViewText(4,"End call",null);
        setViewText(5, "Dial pad", null);

        Log.announce(mStatus.mPhoneNumber,true);
    }

    @Override
    public void onLongKeyOne() {

    }

    @Override
    public void onLongKeyTwo() {

    }

    @Override
    public void onLongKeyThree() {

    }

    @Override
    public void onLongKeyFour() {

    }

    @Override
    public void onLongKeyFive() {

    }

    @Override
    public void onLongKeySix() {

    }

    @Override
    public void onSwipeRight() {
        try {
            InvisibleTouchApplication.getInstance().getCallManager().answerCall();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSwipeLeft() {
        try {
            InvisibleTouchApplication.getInstance().getCallManager().endCall();
            finish();
            Log.announce("Call Ended",true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDoubleSwipeRight() {

    }

    @Override
    public void onDoubleSwipeLeft() {

    }

    @Override
    public void onSwipeUp() {

    }

    @Override
    public void onDoubleSwipeUp() {

    }

    @Override
    public void onSwipeDown() {

    }

    @Override
    public void onDoubleSwipeDown() {

    }

    @Override
    public void onVolumeDownKeyShortPress() {

    }

    @Override
    public void onVolumeDownKeyLongPress() {

    }

    @Override
    public void onVolumeUpKeyShortPress() {

    }

    @Override
    public void onVolumeUpKeyLongPress() {

    }

    @Override
    public void onCameraKeyShortPress() {

    }

    @Override
    public void onCameraKeyLongPress() {

    }

    @Override
    public void onScreenLongPress() {
        HashMap<String, String> params = new HashMap<String, String>(1);
        params.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                String.valueOf(AudioManager.STREAM_VOICE_CALL));
        Log.announce(ScreenHelper.getInCallActivityScreenHelper(mStatus.mPhoneNumber), true, params);
    }

    @Override
    public void onKeyOne() {
        InvisibleTouchApplication.getInstance().getCallManager().toggleMicMute();
        super.onKeyOne();
    }

    @Override
    public void onKeyTwo() {
        CallManager cm = InvisibleTouchApplication.getInstance().getCallManager();
        if (cm.isSpeakerOn())
            cm.turnOnSpeaker(false);
        else
            cm.turnOnSpeaker(true);
        super.onKeyTwo();
    }

    @Override
    public void onKeyFive() {
        try {
            InvisibleTouchApplication.getInstance().getCallManager().endCall();
            finish();
            Log.announce("Call Ended",true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onKeySix() {
        Intent i = new Intent(this, DialPadActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        super.onKeySix();
    }

    private class CallStatus {
        String mPhoneNumber;
        String mName;
        Long mDuration;
    }
}