package com.shahanp.invisibletouch.plugin;

import android.content.Intent;

import com.shahanp.invisibletouch.internal.BaseActivity;

public class AlarmExtensionPlugin implements IExtensionPlugin {


    @Override
    public void startInterface(BaseActivity context, Intent intent) {
        Intent i = new Intent(context, AlarmExtension.class);
        if (intent != null)
            i.putExtras(intent);
        context.startActivity(i);
    }

    @Override
    public MetaData getExtensionMetaData() {
        MetaData metaData = new MetaData();
        metaData._name = "Alarm";
        metaData._description = "Alarm management";

        return metaData;
    }
}
