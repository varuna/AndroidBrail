package com.shahanp.invisibletouch.internal;


import android.content.Context;
import android.content.res.XmlResourceParser;

import com.shahanp.invisibletouch.R;
import com.shahanp.invisibletouch.utils.Log;

import java.util.ArrayList;
import java.util.List;

public class PluginManager {

    private Context mContext;
    private List<Plugin> mPluginMap;

    public PluginManager(InvisibleTouchApplication context) {
        mContext = context;
        initPlugins();
    }

    private List<Plugin> parse(XmlResourceParser config) {
        List<Plugin> result = new ArrayList<Plugin>();
        try {
            config.next();
            int event = config.getEventType();
            int pluginId = 1000;
            while (event != XmlResourceParser.END_DOCUMENT) {
                if (event == XmlResourceParser.START_TAG && config.getName().equals("plugin")) {
                    Plugin p = new Plugin();
                    p.setId(Integer.parseInt(config.getAttributeValue(0)));
                    p.setName(config.getAttributeValue(1));
                    p.setPluginClass(Class.forName(mContext.getPackageName() + ".plugin." + config.getAttributeValue(2)));
                    result.add(p);
                    Log.announce("Plugin found : " + p.mName, Log.Level.INFO);
                }
                event = config.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Plugin getPlugin(int id) {
        for (Plugin p : mPluginMap) {
            if (p.getId() == id)
                return p;
        }
        return null;
    }

    public int getPluginsCount(){
        return mPluginMap.size();
    }

    public List<Plugin> getPlugins(){
        return mPluginMap;
    }

    private void initPlugins() {
        XmlResourceParser config = mContext.getResources().getXml(R.xml.pluginconfig);
        mPluginMap = parse(config);
    }

    public class Plugin {
        private int _id;
        private String mName;
        private Class mClass;

        public int getId() {
            return _id;
        }

        void setId(int _id) {
            this._id = _id;
        }

        public String getName() {
            return mName;
        }

        void setName(String mName) {
            this.mName = mName;
        }

        public Class getPluginClass() {
            return mClass;
        }

        void setPluginClass(Class mClass) {
            this.mClass = mClass;
        }
    }

}
