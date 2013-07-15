package com.varunarl.invisibletouch.internal;

import com.varunarl.invisibletouch.utils.Contact;

public class ScreenHelper {

    //SCREEN LONG PRESS HELPER STRINGS
    public static String MAIN_MENU_SCREEN_HELPER = "You are now in main menu.";
    public static String FAVOURITE_SCREEN_HELPER = "You are now in favourite contacts screen.";
    public static String CALL_LOG_SCREEN_HELPER = "You are now in call log screen.";
    public static String CONTACTS_SCREEN_HELPER = "You are now in contacts screen.";
    public static String DIAL_PAD_SCREEN_HELPER = "You are now in dial pad screen.";
    public static String LOCK_SCREEN_HELPER = "You are now in lock screen.";
    public static String SETTINGS_SCREEN_HELPER = "You are now in settings screen.";
    public static String ACCESSIBILITY_SETTINGS_SCREEN_HELPER = "You are now in accessibility settings screen.";

    //BUTTON LONG PRESS HELPER STRINGS
    public static String SETTINGS_TEXT_TO_SPEECH_TOGGLE = "Tap on this will enable or disable the text to speech feature.";
    public static String SETTINGS_VIBRATION_TOGGLE = "Tap on this will enable or disable the vibration feature.";
    public static String SETTINGS_TEXT_TO_SPEECH_VOLUME = "Tap on this and use the volume controls to increase or decrease the text to speech volume.";
    public static String SETTINGS_TEXT_TO_SPEECH_SPEED = "Tap on this and use the volume controls to increase or decrease the text to speech speed.";
    public static String SETTINGS_TEXT_TO_SPEECH_PITCH = "Tap on this and use the volume controls to increase or decrease the text to speech pitch.";
    public static String SETTINGS_RESET_FACTORY_DEFAULTS = "Tap on this to reset the factory default settings.";
    public static String SETTINGS_ACCESSIBILITY = "Tap on this to modify your accessibility settings.";
    public static String SETTINGS_SYSTEM_RECOVERY = "Tap on this to enable or disable the system recovery settings.";
    public static String SETTINGS_MORE = "Tap on this to activate more settings.";

    //PRIVATE LONG PRESS HELPER STRINGS : Used with static functions
    private static String INCALL_SCREEN_HELPER = "You are now in a call with ";
    private static String CONTACT_DETAILS_SCREEN_HELPER = "Current contact details are; ";

    public static String getInCallActivityScreenHelper(String number) {
        return INCALL_SCREEN_HELPER + number;
    }

    public static String getContactDetailsActivityScreenHelper(Contact contact) {
        return CONTACT_DETAILS_SCREEN_HELPER + contact.toString();
    }

}
