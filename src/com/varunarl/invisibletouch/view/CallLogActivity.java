package com.varunarl.invisibletouch.view;

import android.content.Intent;
import android.database.Cursor;
import android.provider.CallLog;
import android.text.format.DateUtils;
import android.widget.Toast;

import com.varunarl.invisibletouch.internal.InvisibleTouchApplication;
import com.varunarl.invisibletouch.internal.ScreenHelper;
import com.varunarl.invisibletouch.internal.SixPackActivity;
import com.varunarl.invisibletouch.utils.Contact;
import com.varunarl.invisibletouch.utils.Log;
import com.varunarl.invisibletouch.utils.Log.Level;

public class CallLogActivity extends SixPackActivity {

    private int CALL_REQUEST_CODE = 1;
    private Cursor mContacts;
    private CallLogContact mCurrentLog;

    private CallLogContact readContact(Cursor cursor) {
        String phoneNumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
        String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
        int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
        long duration = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DURATION));
        long timePassed = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
        CallLogContact contact = new CallLogContact();
        contact.setPhone(phoneNumber);
        contact.setName(name);
        contact.setDuration(duration);
        contact.setTimePassed(timePassed);
        contact.setType(type);
        return contact;
    }

    @Override
    public void onSwipeUp() {
        if (!mContacts.isLast()) {
            mContacts.moveToNext();
            mCurrentLog = readContact(mContacts);
        }
        setViewData();
    }

    @Override
    public void onDoubleSwipeUp() {
        onSwipeUp();
    }

    @Override
    public void onSwipeDown() {
        if (!mContacts.isFirst()) {
            mContacts.moveToPrevious();
            mCurrentLog = readContact(mContacts);
        }
        setViewData();

    }

    @Override
    public void onDoubleSwipeDown() {
        onSwipeDown();
    }

    @Override
    public void onSwipeRight() {
        InvisibleTouchApplication.getInstance().getCallManager().makeCall(mCurrentLog.getPhone(), this);
    }

    @Override
    public void onSwipeLeft() {
        finish();
    }

    @Override
    public void onDoubleSwipeRight() {
    }

    @Override
    public void onDoubleSwipeLeft() {
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
    protected void init() {
        this.mContacts = getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, null);
        this.mContacts.moveToFirst();
        mCurrentLog = readContact(mContacts);
        super.init();
        setViewData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CALL_REQUEST_CODE) {
            Log.announce("Hit", Level.INFO);
        }
    }

    @Override
    public void onCameraKeyShortPress() {
    }

    @Override
    public void onCameraKeyLongPress() {
    }

    @Override
    public void onScreenLongPress() {
        Log.announce(ScreenHelper.CALL_LOG_SCREEN_HELPER, true);

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
    public void onKeyOne() {
        Log.announce(mCurrentLog.getName(), true);

    }

    @Override
    public void onKeyTwo() {
        Log.announce(mCurrentLog.getPhone(), true);
    }

    @Override
    public void onKeyThree() {
        Log.announce(mCurrentLog.typeInString(), true);
    }

    @Override
    public void onKeyFour() {
        Log.announce(mCurrentLog.timeInString(), true);
    }

    @Override
    public void onKeyFive() {
        InvisibleTouchApplication.getInstance().getCallManager().makeCall(mCurrentLog.getPhone(), this);
    }

    @Override
    public void onKeySix() {
        //Delete record
    }

    private void setViewData()
    {
        setViewText(0,"Name",mCurrentLog.getName());
        setViewText(1,"Phone",mCurrentLog.getPhone());
        setViewText(2,"Type",mCurrentLog.typeInString());
        setViewText(3,"Time",mCurrentLog.timeInString());
        setViewText(4,"Redial",null);
        setViewText(5,"Remove record",null);
    }

    class CallLogContact extends Contact {
        Integer type;
        Long duration;
        Long timePassed;

        int getType() {
            return type;
        }

        void setType(int type) {
            this.type = type;
        }

        long getDuration() {
            return duration;
        }

        void setDuration(long duration) {
            this.duration = duration;
        }

        long getTimePassed() {
            return timePassed;
        }

        void setTimePassed(long timePassed) {
            this.timePassed = timePassed;
        }

        String durationInString() {
            if (duration != null)
                return DateUtils.formatElapsedTime(duration);
            else
                return "";
        }

        String timeInString() {
            if (timePassed != null){
                return (String) DateUtils.getRelativeTimeSpanString(timePassed,System.currentTimeMillis(),DateUtils.MINUTE_IN_MILLIS);
            }else
                return "";
        }

        String typeInString(){
            switch (type){
                case CallLog.Calls.INCOMING_TYPE:
                    return "Incomming call";
                case CallLog.Calls.MISSED_TYPE:
                    return "Missed call";
                case CallLog.Calls.OUTGOING_TYPE:
                    return "Outgoing call";
            }
            return null;
        }

    }

}
