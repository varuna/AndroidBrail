package com.shahanp.invisibletouch.internal;

import com.shahanp.invisibletouch.utils.Contact;

public class ScreenHelper {

    //SCREEN ACTIVATE HELPER STRINGS
    public static String MAIN_MENU_ACTIVATE = "Main menu.";
    public static String FAVOURITE_ACTIVATE = "Favourite contacts.";
    public static String CALL_LOG_ACTIVATE = "Call log. ";
    public static String CONTACTS_ACTIVATE = "Contacts.";
    public static String DIAL_PAD_ACTIVATE = "Dial pad.";
    public static String LOCK_ACTIVATE = "Screen Locked.";
    public static String SETTINGS_ACTIVATE = "Settings.";
    public static String ACCESSIBILITY_ACTIVATE  = "Accessibility settings.";


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
    public static String SETTINGS_KEYTONES = "Tap on this to enable or disable the keypad tones.";

    //VOLUME DOWN LONG PRESS HELPER STRINGS
    public static String MAIN_MENU_SCREEN_HELPER =
            "Main menu."         + "Six Packs Screen."
            + "Button One "      + "Favourite Contacts."
            + "Button Two "      + "Call Log."
            + "Button Three "    + "Settings."
            + "Button Four "     + "Dial Pad."
            + "Button Five "     + "Contacts."
            + "Button Six "      + "Others.";

    public static String FAVOURITE_SCREEN_HELPER =
            "Favoutite Contacts."         + "Six Packs Screen."
            + "Possible to manage six favourite contacts"
            + "Button One "      + "Favoutite Contact 1."
            + "Button Two "      + "Favoutite Contact 2."
            + "Button Three "    + "Favoutite Contact 3."
            + "Button Four "     + "Favoutite Contact 4."
            + "Button Five "     + "Favoutite Contact 5."
            + "Button Six "      + "Favoutite Contact 6.";


    public static String CALL_LOG_SCREEN_HELPER=
            "Call Log."          + "Six Packs Screen."
            + "Button One "      + "Name."
            + "Button Two "      + "Phone Number."
            + "Button Three "    + "Call Type."
            + "Button Four "     + "Date and time."
            + "Button Five "     + "Dial."
            + "Button Six "      + "Remove record.";

    public static String SETTINGS_SCREEN_HELPER=
            "Settings."          + "Six Packs Screen."
            + "Button One "      + "Accessibility."
            + "Button Two "      + "Vibrations enable or disable."
            + "Button Three "    + "Key pad tone on off."
            + "Button Four "     + "Auto recovery enable or disable."
            + "Button Five "     + "Reset settings."
            + "Button Six "      + "More settings.";

    public static String DIAL_PAD_SCREEN_HELPER=
            "Dial Pad."          + "Six Packs Screen."
            + "Use six buttons as a braille type writer. And swipe to pass the value. Double swipe to dial"
            + "Dial 0 1 0 1 0 0 0 1 to exit Invisible touch.";

    public static String CONTACTS_SCREEN_HELPER=
            "Contacts."          + "Six Packs Screen."
            + "Button One "      + "Update Contacts."
            + "Button Two "      + "Dial."
            + "Button Three "    + "Delete contact."
            + "Button Four "     + "New Contact."
            + "Button Five "     + "Show Details."
            + "Button Six "      + "Add to favourite.";


    public static String OTHERS_SCREEN_HELPER=
            "Others."          + "Six Packs Screen."
            + "Button One "      + "Action 1."
            + "Button Two "      + "Action 2."
            + "Button Three "    + "Action 3."
            + "Button Four "     + "Action 4."
            + "Button Five "     + "Action 5."
            + "Button Six "      + "Action 6.";


    public static String LOCK_SCREEN_HELPER =
            "Locked Screen."     + "Single Pack Screen."+
            "Double swipe to unlock the screen.";

    public static String BOOLEAN_HELPER =
            "Confirmation Screen."     + "Single Pack Screen."
            + "Swipe Right to confirm."
            + "Swipe Left to return."
            + "Hold the screen for the question.";

    public static String ACCESSIBILITY_SCREEN_HELPER=
            "Accessibility Settings."          + "Six Packs Screen."
                    + "Button One "      + "Test to speech, volume Up and Down."
                    + "Button Two "      + "Test to speech, speed increase and decrease."
                    + "Button Three "    + "Test to speech, pitch Up and Down."
                    + "Button Four, Five & Six are empty.";

    //PRIVATE LONG PRESS HELPER STRINGS : Used with static functions
    private static String INCALL_SCREEN_HELPER = "You are now in a call with ";

    public static String getInCallActivityScreenHelper(String number) {
        return INCALL_SCREEN_HELPER + number;
    }

    public static String getContactDetailsActivityScreenHelper(Contact contact) {
        return contact.toString();
    }

}
