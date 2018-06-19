package com.zyj.searchstudy.elasticsearch;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.script.mustache.MustachePlugin;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MyTransPortClient extends PreBuiltTransportClient {
    public MyTransPortClient(Settings settings, Class<? extends Plugin>... plugins) {
        super(settings, plugins);
    }

    public MyTransPortClient(Settings settings, Collection<Class<? extends Plugin>> plugins) {
        super(settings, plugins);
    }

    public MyTransPortClient(Settings settings, Collection<Class<? extends Plugin>> plugins, HostFailureListener hostFailureListener) {
        super(settings, plugins, hostFailureListener);
    }


    public  static Collection<Class<? extends Plugin>> addPlugins(Collection<Class<? extends Plugin>> collection,
                                                                    Class<? extends Plugin>... plugins) {
        return addPlugins(collection, Arrays.asList(plugins));
    }

    public static Collection<Class<? extends Plugin>> addPlugins(Collection<Class<? extends Plugin>> collection,
                                                                    Collection<Class<? extends Plugin>> plugins) {
        ArrayList<Class<? extends Plugin>> list = new ArrayList<>(collection);
        for (Class<? extends Plugin> p : plugins) {
            if (list.contains(p)) {
                throw new IllegalArgumentException("plugin already exists: " + p);
            }
            if(p == MustachePlugin.class){
                continue;
            }

            list.add(p);
        }
        return list;
    }


}
