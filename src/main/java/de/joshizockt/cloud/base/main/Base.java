package de.joshizockt.cloud.base.main;

import de.joshizockt.cloud.api.CloudConfig;
import de.joshizockt.cloud.api.Logger;

public class Base {

    private static CloudConfig config;

    public static void start(String[] args) throws Exception {

        Logger.log("Starting BASE-1");

        config = new CloudConfig("config.json", "base", true);

        Logger.log("Started BASE-1");


    }

    public static CloudConfig getConfig() {
        return config;
    }
}
