package de.joshizockt.cloud.api;

import de.joshizockt.cloud.core.main.Core;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class IdManager {

    public static final int DEFAULT_LENGHT = 6;

    private static final String abc = "abcdefghijklmnopqrstuvwxyz";
    private static final String numbers = "0123456789";

    private static List<String> ids;

    public static void init() {

        ids = new ArrayList<>();

        CloudConfig bc = Core.getServerManager().getConfig();
        JSONArray bcA = bc.getArray("Bases");
        Iterator bcI = bcA.iterator();
        while(bcI.hasNext()) {
            Object ob = bcI.next();
            if(ob instanceof JSONObject) {
                JSONObject o = (JSONObject)ob;
                String id = (String)o.get("ID");
                ids.add(id);
            }
        }

    }

    public static String gen() {
        return gen(DEFAULT_LENGHT);
    }

    public static String gen(int lenght) {
        String id = "";
        for(int i = 0; i < lenght; i++) {
            if(ThreadLocalRandom.current().nextInt(1, 2+1) == 1) {
                id = id + abc.charAt(ThreadLocalRandom.current().nextInt(0, abc.length()));
            } else id = id + numbers.charAt(ThreadLocalRandom.current().nextInt(0, numbers.length()));
        }
        if(!exists(id)) return id;
        else return gen(lenght);
    }

    public static boolean exists(String id) {
        return ids.contains(id);
    }

}
