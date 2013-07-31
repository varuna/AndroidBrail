package com.shahanp.invisibletouch.plugin;


import android.content.Intent;

import com.shahanp.invisibletouch.internal.BaseActivity;

public interface IExtensionPlugin {
    void startInterface(BaseActivity context, Intent intent);

    MetaData getExtensionMetaData();

    class MetaData {
        public String _name;
        public String _description;
    }
}
