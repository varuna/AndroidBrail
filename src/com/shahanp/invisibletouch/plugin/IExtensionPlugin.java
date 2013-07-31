package com.shahanp.invisibletouch.plugin;


import android.content.Intent;

public interface IExtensionPlugin {
    void startInterface(Intent intent);
    MetaData getExtensionMetaData();

    class MetaData{
        String _name;
        String _description;
    }
}
