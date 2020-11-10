package de.joshizockt.cloud.base.main;

import de.joshizockt.cloud.api.CloudColor;
import de.joshizockt.cloud.api.CloudConfig;
import de.joshizockt.cloud.api.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class Base {

    private static CloudConfig config;

    public static void start(String[] args) throws Exception {

        config = new CloudConfig("config.json", "base", true);
        if(config.get("Key") == null) {
            config.set("Key", genKey());
        }

        Logger.log(CloudColor.GREEN + "Successfull started this BASE.");

        // KEEP THE BASE ALIVE
        Thread.currentThread().join();

    }

    public static CloudConfig getConfig() {
        return config;
    }

    private static String genKey() {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String key = "";
        for(int i = 0; i < 40; i++) {
            if(ThreadLocalRandom.current().nextInt(1, 2+1) == 1) {
                key = key + abc.charAt(ThreadLocalRandom.current().nextInt(0, abc.length()));
            } else key = key + numbers.charAt(ThreadLocalRandom.current().nextInt(0, numbers.length()));
        }
        return key;
    }

}
