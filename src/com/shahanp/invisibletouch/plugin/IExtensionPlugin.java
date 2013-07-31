package com.shahanp.invisibletouch.plugin;


import com.shahanp.invisibletouch.internal.BaseActivity;

public interface IExtensionPlugin {
    void startInterface(BaseActivity context);

    MetaData getExtensionMetaData();

    class MetaData {
        public String _name;
        public String _description;
    }
}
