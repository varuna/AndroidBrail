package com.varunarl.invisibletouch.plugin;


import com.varunarl.invisibletouch.internal.BaseActivity;

public interface IExtensionPlugin {
    void startInterface(BaseActivity context);

    MetaData getExtensionMetaData();

    class MetaData {
        public String _name;
        public String _description;
    }
}
