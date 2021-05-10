package de.joshizockt.cloud.base.main;

import com.google.gson.JsonObject;
import de.joshizockt.cloud.api.CloudColor;
import de.joshizockt.cloud.api.CloudConfig;
import de.joshizockt.cloud.api.Logger;
import de.joshizockt.cloud.api.serverobject.ServerObject;
import de.joshizockt.cloud.core.utils.ServerAction;
import de.joshizockt.cloud.networking.NetworkClient;
import de.joshizockt.cloud.networking.NetworkManager;
import de.joshizockt.cloud.networking.NetworkType;
import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class Base {

    private static CloudConfig config;

    private static NetworkManager networkManager;

    public static void start(String[] args) throws Exception {

        config = new CloudConfig("config.json", "base", true);
        if(config.get("Key") == null) {
            config.set("Key", genKey());
        }

        Logger.log(CloudColor.GREEN + "Successfull started this BASE.");

        networkManager = new NetworkManager(NetworkType.BASE_SERVER);
        NetworkClient c;
        try {
            c = networkManager.createClient(config.getString("Host"), config.getInteger("Port"), config.getString("Key"));
        } catch (ConnectException e) {
            e.printStackTrace();
            Logger.err("Cannot connect to Core! Please Restart the Base and check the config to try again!");
            return;
        } catch(IOException e) {
            if(e.getMessage().equalsIgnoreCase("nobasekeyfound")) {
                Logger.err("Execute following Command in the Core: " + CloudColor.RED_BOLD + "addkey " + config.getString("Key"));
            } else {
                Logger.err("..." + e.getMessage());
            }
            return;
        }
        c.send(c.create(ServerAction.START_SERVER, new ServerObject("Test-server")));

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
