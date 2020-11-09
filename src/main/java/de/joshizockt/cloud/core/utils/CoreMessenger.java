package de.joshizockt.cloud.core.utils;

import de.joshizockt.cloud.api.CloudConfig;

import javax.annotation.Nullable;

public class CoreMessenger {

    private CloudConfig config;

    public CoreMessenger() {
        config = new CloudConfig("core.json", "msg", true);
        config = new CloudConfig("core.json", "msg");
    }

    @Nullable
    public String getMessage(MessengerType type, String key) {
        return config.getString(type.name() + "." + key);
    }

}

