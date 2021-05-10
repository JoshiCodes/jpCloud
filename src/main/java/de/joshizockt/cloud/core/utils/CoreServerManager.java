package de.joshizockt.cloud.core.utils;

import de.joshizockt.cloud.api.CloudConfig;
import de.joshizockt.cloud.api.Logger;
import de.joshizockt.cloud.api.serverobject.BaseObject;
import de.joshizockt.cloud.api.serverobject.ServerType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CoreServerManager {

    private CloudConfig config;
    private List<BaseObject> bases;

    public CoreServerManager() throws Exception {
        config = new CloudConfig("bases.json", "core", true);
        bases = new ArrayList<>();
    }

    public List<String> getAllowedIPs() {
        String[] args = config.getString("AllowedIPs").split(";");
        return Arrays.asList(args);
    }

    public void registerBaseObject(BaseObject object) {
        JSONArray a = getBasesArray();
        JSONObject o = new JSONObject();
        o.put("Name", object.getName());
        o.put("IP", object.getIp());
        a.add(o);
        config.set("Bases", a);
    }

    public boolean isLegalBase(String host) {
        for(BaseObject base : getBases()) {
            if(base.getIp().equals(host)) {
                return true;
            }
        }
        return false;
    }

    public CloudConfig getConfig() {
        return config;
    }

    private JSONArray getBasesArray() {
        return config.getArray("Bases");
    }

    public void updateBases() throws UnknownHostException {
        bases = new ArrayList<>();
        bases.clear();
        JSONArray a = getBasesArray();
        Iterator i = a.iterator();

        while(i.hasNext()) {
            JSONObject o = (JSONObject) i.next();
            String name = (String) o.get("Name");
            String ip = (String) o.get("IP");
            BaseObject b = new BaseObject(name).setIp(ip);
            bases.add(b);
        }
    }

    public List<BaseObject> getBases() {
        return bases;
    }

    public void execute(JSONObject o) {

        ServerType type = ServerType.valueOf((String)o.get("servertype"));
        ServerAction action = ServerAction.valueOf((String)o.get("action"));
        String servername = (String)o.get("servername");
        System.out.println("Yippieh: " + action.name() + " / " + servername + "(" + type + ")");

    }

}
