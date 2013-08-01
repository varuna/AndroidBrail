package com.shahanp.invisibletouch.plugin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmExtension extends PluginSixPackActivity {

    private final static String FIRE_ALARM = "com.shahanp.invisibletouch.plugin.alarmextension.FIRE_ALARM";
    private MODE mMode;
    private EDITTYPE mType;
    private ScheduleAlarm mSchedular, mCurrentAlarm;
    private List<Long> mAlarms;
    private int alarmId;

    @Override
    protected void init() {
        super.init();
        mSchedular = new ScheduleAlarm();
        String action = getIntent().getAction();
        if (action != null && action.equals(FIRE_ALARM)) {
            mMode = MODE.RINGING;
        } else {
            mMode = MODE.CONFIG;
        }
        mAlarms = new ArrayList<Long>();
        alarmId = initAlarms();
        if (mAlarms.size() > 0)
            mCurrentAlarm.editTime(mAlarms.get(alarmId));
    }

    private int initAlarms() {
        int i;
        for (i = 0; i < mSchedular.getAlarmCount(); i++)
            mAlarms.add(mSchedular.getAlarmFromPersistanceStorage(i));
        return i;
    }

    @Override
    public void onKeyOne() {
        super.onKeyOne();
        if (mMode == MODE.VIEW) {
            mMode = MODE.CONFIG;
            alarmId++;
        }
        if (mMode == MODE.CONFIG) {
            mType = EDITTYPE.YEAR;
        }
    }

    @Override
    public void onKeyTwo() {
        super.onKeyTwo();
        if (mMode == MODE.VIEW) {
            mMode = MODE.CONFIG;
        }
        if (mMode == MODE.CONFIG) {
            mType = EDITTYPE.MONTH;
        }
    }

    @Override
    public void onKeyThree() {
        super.onKeyThree();
        if (mMode == MODE.VIEW) {
            //DELETE Alarm
        }
        if (mMode == MODE.CONFIG) {
            mType = EDITTYPE.DATE;
        }
    }

    @Override
    public void onKeyFour() {
        super.onKeyFour();
        if (mMode == MODE.VIEW) {

        }
        if (mMode == MODE.CONFIG) {
            mType = EDITTYPE.HOURS;
        }
    }

    @Override
    public void onKeyFive() {
        super.onKeyFive();
        if (mMode == MODE.VIEW) {

        }
        if (mMode == MODE.CONFIG) {
            mType = EDITTYPE.MINUTES;
        }
    }

    @Override
    public void onKeySix() {
        super.onKeySix();
        if (mMode == MODE.VIEW) {
            //Free slot
        }
        if (mMode == MODE.CONFIG) {
            mSchedular.scheduleAlarm(this);
        }
    }

    @Override
    public void onSwipeLeft() {
        if (mMode == MODE.RINGING) {

        }
        if (mMode == MODE.CONFIG) {
            if (alarmId == mAlarms.size())
                mSchedular = new ScheduleAlarm();
            else
                initAlarms();
        }
        super.onSwipeLeft();
    }

    @Override
    public void onSwipeRight() {
        if (mMode == MODE.RINGING) {
            super.onSwipeLeft();
        }
    }

    @Override
    public void onSwipeUp() {
        if (mMode == MODE.VIEW) {
            if (alarmId + 1 != mAlarms.size())
                mCurrentAlarm.editTime(mAlarms.get(alarmId++));
        }
        if (mMode == MODE.CONFIG) {
            //New Alarm
            if (alarmId == mAlarms.size()) {
                mSchedular.setValue(mSchedular.getValue(mType) + 1, mType);
            } else {
                //Edit current
                mCurrentAlarm.setValue(mCurrentAlarm.getValue(mType) + 1, mType);
            }
        }
    }

    @Override
    public void onSwipeDown() {
        if (mMode == MODE.VIEW) {
            if (alarmId != 0)
                mCurrentAlarm.editTime(mAlarms.get(alarmId--));
        }
        if (mMode == MODE.CONFIG) {
            //New Alarm
            if (alarmId == mAlarms.size()) {
                mSchedular.setValue(mSchedular.getValue(mType) - 1, mType);
            } else {
                //Edit current
                mCurrentAlarm.setValue(mCurrentAlarm.getValue(mType) - 1, mType);
            }
        }
    }

    @Override
    public void onDoubleSwipeRight() {

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
    public void onDoubleSwipeLeft() {
        super.onSwipeLeft();
    }

    @Override
    public void onDoubleSwipeUp() {

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

    }

    private enum MODE {
        VIEW,
        CONFIG,
        RINGING
    }

    private enum EDITTYPE {
        MINUTES,
        HOURS,
        DATE,
        MONTH,
        YEAR
    }

    private class ScheduleAlarm {

        private final String ALARM_PERSISTANCE_STORAGE = "com.shahanp.invisibletouch.plugin.alarmextension.schedulealarm.PERSISTANCE_STORAGE";
        private final String ALARM_KEY_PREFIX = "ALARM_";
        private int SCHEDULE_REQUEST = 321;
        private int mMinutes;
        private int mHours;
        private int mDate;
        private int mMonth;
        private int mYear;

        public ScheduleAlarm() {
            mYear = Calendar.getInstance().get(Calendar.YEAR);
            mMonth = Calendar.getInstance().get(Calendar.MONTH);
            mDate = Calendar.getInstance().get(Calendar.DATE);
            mHours = Calendar.getInstance().get(Calendar.HOUR);
            mMinutes = Calendar.getInstance().get(Calendar.MINUTE);
        }

        public void setValue(int value, EDITTYPE type) {
            switch (type) {
                case MINUTES:
                    mMinutes = value >= 60 ? 0 : value <= 0 ? 0 : value;
                    break;
                case HOURS:
                    mHours = value >= 24 ? 0 : value <= 0 ? 0 : value;
                    break;
                case DATE:
                    mDate = mMonth > 7 ? mMonth % 2 == 0 ? value >= 31 ? 31 : value : value >= 30 ? 30 : value : mMonth % 2 == 1 ? value >= 31 ? 31 : value : value >= 30 ? 30 : value;
                    mDate = mMonth == 2 ? mYear % 4 == 0 ? mDate > 29 ? 29 : mDate : mDate > 28 ? 28 : mDate : mDate;
                    break;
                case MONTH:
                    mMonth = value >= 12 ? 12 : value <= 0 ? 0 : value;
                    break;
                case YEAR:
                    mYear = value < Calendar.getInstance().get(Calendar.YEAR) ? Calendar.getInstance().get(Calendar.YEAR) : value;
                    break;
            }
        }

        public int getValue(EDITTYPE type) {
            switch (type) {
                case MINUTES:
                    return mMinutes;
                case HOURS:
                    return mHours;
                case DATE:
                    return mDate;
                case MONTH:
                    return mMonth;
                case YEAR:
                    return mYear;
            }
            return -1;
        }

        private long getAlarm() {
            Date d = new Date(mYear, mMonth, mDate, mHours, mMinutes);
            return d.getTime();
        }

        public void scheduleAlarm(Context context) {
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent alarmIntent = new Intent(AlarmExtension.this, AlarmExtension.class);
            alarmIntent.setAction(FIRE_ALARM);
            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), ++SCHEDULE_REQUEST, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            am.set(AlarmManager.RTC_WAKEUP, getAlarm(), pi);
            addToPersistanceStorage(getAlarm());
        }

        private void addToPersistanceStorage(long time) {
            SharedPreferences preferences = AlarmExtension.this.getSharedPreferences(ALARM_PERSISTANCE_STORAGE, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(ALARM_KEY_PREFIX + getAlarmCount(), time);
            editor.apply();
            editor.commit();
            setAlarmCount(getAlarmCount() + 1);
        }

        private int getAlarmCount() {
            SharedPreferences preferences = AlarmExtension.this.getSharedPreferences(ALARM_PERSISTANCE_STORAGE, MODE_PRIVATE);
            return preferences.getInt(ALARM_KEY_PREFIX + "COUNT", 0);
        }

        private void setAlarmCount(int count) {
            SharedPreferences preferences = AlarmExtension.this.getSharedPreferences(ALARM_PERSISTANCE_STORAGE, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(ALARM_KEY_PREFIX + "COUNT", count);
            editor.apply();
            editor.commit();
        }

        private long getAlarmFromPersistanceStorage(int id) {
            SharedPreferences preferences = AlarmExtension.this.getSharedPreferences(ALARM_PERSISTANCE_STORAGE, MODE_PRIVATE);
            return preferences.getLong(ALARM_KEY_PREFIX + id, 0);
        }

        private void editTime(long time) {
            Date d = new Date(time);
            mMinutes = d.getMinutes();
            mMonth = d.getMonth();
            mHours = d.getHours();
            mDate = d.getDate();
            mYear = d.getYear();
        }
    }
}
