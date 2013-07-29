package com.shahanp.invisibletouch.view.sub;

import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;
import com.shahanp.invisibletouch.internal.ScreenHelper;
import com.shahanp.invisibletouch.internal.SinglePackActivity;
import com.shahanp.invisibletouch.utils.Contact;
import com.shahanp.invisibletouch.utils.ContactManager;
import com.shahanp.invisibletouch.utils.Log;

public class ContactsDetailsActivity extends SinglePackActivity {

    private int NAME_VIEW_ID = 1000;
    private int NUMBER_VIEW_ID = 1001;
    private LinearLayout mRootView;
    private Contact mContact;

    @Override
    protected void init() {
        Intent i = getIntent();
        mContact = i.getParcelableExtra(ContactManager.INTENT_FLAG_CONTACT);
        super.init();
    }

    @Override
    public void onSwipeRight() {
        InvisibleTouchApplication.getInstance().getCallManager().makeCall(mContact.getPhone(), this);
    }

    @Override
    public void onSwipeLeft() {
        this.finish();
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
        Log.announce(ScreenHelper.getContactDetailsActivityScreenHelper(mContact),true);
    }

    @Override
    public void onKeyOne() {
    }

    @Override
    public void onLongKeyOne() {

    }

    @Override
    protected void onAttachView(int id, View view) {
        TextView name;
        TextView phone;
        if (mRootView == null) {
            mRootView = (LinearLayout) view.getParent();
            mRootView.removeAllViews();
            mRootView.setOrientation(LinearLayout.VERTICAL);
            mRootView.setGravity(Gravity.CENTER);
            mRootView.setBackgroundColor(Color.BLACK);
            name = new TextView(this);
            phone = new TextView(this);
            name.setGravity(Gravity.CENTER);
            phone.setGravity(Gravity.CENTER);
            name.setTextColor(Color.GRAY);
            phone.setTextColor(Color.GRAY);
            name.setId(NAME_VIEW_ID);
            phone.setId(NUMBER_VIEW_ID);
            name.setText(mContact.getName());
            phone.setText(mContact.getPhone());
            mRootView.addView(name);
            mRootView.addView(phone);
        } else {
            name = (TextView) mRootView.findViewById(NAME_VIEW_ID);
            phone = (TextView) mRootView.findViewById(NUMBER_VIEW_ID);
            name.setText(mContact.getName());
            phone.setText(mContact.getPhone());
        }

    }

}