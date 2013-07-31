package com.shahanp.invisibletouch.view;

import android.content.Intent;

import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;
import com.shahanp.invisibletouch.internal.PluginManager;
import com.shahanp.invisibletouch.internal.SixPackActivity;
import com.shahanp.invisibletouch.plugin.IExtensionPlugin;
import com.shahanp.invisibletouch.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class ExtensionApplicationsActivity extends SixPackActivity {

    private static final String PLUGIN_INDEX = "com.shahanp.invisibletouch.view.ExtensionApplicationsActivity.PLUGIN_INDEX";
    List<IExtensionPlugin> pluginsList = new ArrayList<IExtensionPlugin>();
    PluginManager mPluginManager;
    int pluginIndex;

    @Override
    protected void init() {
        super.init();

        pluginIndex = getIntent().getIntExtra(PLUGIN_INDEX, 0);
        mPluginManager = InvisibleTouchApplication.getInstance().getPluginManager();
        List<PluginManager.Plugin> plugins = mPluginManager.getPlugins();
        Log.announce("index : " + pluginIndex + " && count :" + plugins.size(), Log.Level.INFO);

        setViewText(0, "Empty", null);
        setViewText(1, "Empty", null);
        setViewText(2, "Empty", null);
        setViewText(3, "Empty", null);
        setViewText(4, "Empty", null);

        int idx = 0;
        for (int i = pluginIndex; i < mPluginManager.getPluginsCount() && i < pluginIndex + 6; i++) {
            try {
                IExtensionPlugin plugin = (IExtensionPlugin) (plugins.get(i).getPluginClass().newInstance());
                setViewText(idx++, plugin.getExtensionMetaData()._name, plugin.getExtensionMetaData()._description);
                pluginsList.add(plugin);
                Log.announce(plugin.getExtensionMetaData()._name, Log.Level.INFO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (mPluginManager.getPluginsCount() > pluginIndex + 6)
            setViewText(5, "More", "applications");
        else if (plugins.size() == pluginIndex + 6)
            setViewText(5, pluginsList.get(pluginIndex + 5).getExtensionMetaData()._name, pluginsList.get(pluginIndex + 5).getExtensionMetaData()._description);
        else
            setViewText(5, "Empty", null);
    }

    @Override
    public void onKeyOne() {
        super.onKeyOne();
        if (pluginsList.size() > 0)
            pluginsList.get(0).startInterface(this);
    }

    @Override
    public void onKeyTwo() {
        super.onKeyTwo();
        if (pluginsList.size() > 1)
            pluginsList.get(1).startInterface(this);
    }

    @Override
    public void onKeyThree() {
        super.onKeyThree();
        if (pluginsList.size() > 2)
            pluginsList.get(2).startInterface(this);
    }

    @Override
    public void onKeyFour() {
        super.onKeyFour();
        if (pluginsList.size() > 4)
            pluginsList.get(3).startInterface(this);
    }

    @Override
    public void onKeyFive() {
        super.onKeyFive();
        if (pluginsList.size() > 5)
            pluginsList.get(4).startInterface(this);
    }

    @Override
    public void onKeySix() {
        super.onKeySix();
        if (pluginIndex + 6 == mPluginManager.getPluginsCount()) {
            pluginsList.get(pluginIndex + 5).startInterface(this);
            return;
        }
        if (mPluginManager.getPluginsCount() > pluginIndex + 6) {
            Intent i = new Intent(this, ExtensionApplicationsActivity.class);
            i.putExtra(PLUGIN_INDEX, pluginIndex + 6);
            startActivity(i);
        }

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
    public void onSwipeRight() {

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

    }
}
