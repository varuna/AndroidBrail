package com.shahanp.invisibletouch.internal;


import android.content.Context;
import android.content.res.XmlResourceParser;

import com.shahanp.invisibletouch.R;
import com.shahanp.invisibletouch.utils.Log;
import com.shahanp.invisibletouch.utils.Log.Level;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

public class PluginManager {

    private Context mContext;
    private HashMap<String,Class> mPluginMap;
    public PluginManager(InvisibleTouchApplication context) {
        mContext = context;
        initPlugins();
    }

    private void initPlugins()
    {
        XmlResourceParser config = mContext.getResources().getXml(R.xml.pluginconfig);
        try {
            config.nextToken();
            Log.announce(config.getAttributeCount()+"", false);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
