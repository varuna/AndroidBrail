package com.shahanp.invisibletouch.view;

import com.shahanp.invisibletouch.internal.InvisibleTouchApplication;
import com.shahanp.invisibletouch.internal.PluginManager;
import com.shahanp.invisibletouch.internal.SixPackActivity;
import com.shahanp.invisibletouch.plugin.IExtensionPlugin;

import java.util.ArrayList;
import java.util.List;

public class ExtensionApplicationsActivity extends SixPackActivity {

    List<IExtensionPlugin> pluginsList = new ArrayList<IExtensionPlugin>();
    @Override
    protected void init() {
        super.init();
        PluginManager mgr = InvisibleTouchApplication.getInstance().getPluginManager();
        List<PluginManager.Plugin> plugins = mgr.getPlugins();
        for (int i = 0; i < mgr.getPluginsCount() && i < 6; i++) {
            try {
                IExtensionPlugin plugin = (IExtensionPlugin) (plugins.get(i).getPluginClass().newInstance());
                setViewText(i, plugin.getExtensionMetaData()._name, plugin.getExtensionMetaData()._description);
                pluginsList.add(plugin);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onKeyOne() {
        super.onKeyOne();
        pluginsList.get(0).startInterface(this,null);
    }

    @Override
    public void onKeyTwo() {
        super.onKeyTwo();
    }

    @Override
    public void onKeyThree() {
        super.onKeyThree();
    }

    @Override
    public void onKeyFour() {
        super.onKeyFour();
    }

    @Override
    public void onKeyFive() {
        super.onKeyFive();
    }

    @Override
    public void onKeySix() {
        super.onKeySix();
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
