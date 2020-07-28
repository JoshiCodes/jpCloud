package de.joshizockt.cloud.core.utils;

import com.sun.istack.internal.Nullable;
import de.joshizockt.cloud.api.CloudConfig;
import de.joshizockt.cloud.api.Logger;
import de.joshizockt.cloud.api.groups.BaseObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CoreServerManager {

    private CloudConfig config;

    public CoreServerManager() throws Exception {
        config = new CloudConfig("bases.json", "core");
    }

    public List<String> getAllowedIPs() {
        String[] args = config.getString("AllowedIPs").split(";");
        for(String ip : args) {
            Logger.warn(ip);
        }
        return Arrays.asList(args);
    }

    public boolean registerBaseObject(BaseObject object) {
        JSONArray bases = getBasesArray();
        Iterator i = bases.iterator();
        while(i.hasNext()) {
            Object o = i.next();
            if(o instanceof JSONObject) {
                JSONObject j = (JSONObject)o;
                if(((String)j.get("Name")).equals(object.getName()) || ((String)j.get("ID")).equals(object.getId())) {
                    return false;
                }
            }
        }
        JSONObject json = new JSONObject();
        json.put("ID", object.getId());
        json.put("Name", object.getName());
        json.put("Ram", object.getRam());
        bases.add(json);
        config.set("Bases", bases);
        return true;
    }

    public CloudConfig getConfig() {
        return config;
    }

    private JSONArray getBasesArray() {
        JSONArray bases = config.getArray("Bases");
        return bases;
    }

    public List<BaseObject> getBases() {
        List<BaseObject> objects = new ArrayList<>();
        JSONArray array = getBasesArray();
        Iterator i = array.iterator();
        while(i.hasNext()) {
            Object o = i.next();
            if(o instanceof JSONObject) {
                JSONObject j = (JSONObject)o;
                String id = (String)j.get("ID");
                String name = (String)j.get("Name");
                BaseObject base = new BaseObject(name, id);
                if(j.get("Ram") != null) {
                    base.setRam(Integer.parseInt(String.valueOf(j.get("Ram"))));
                }
                objects.add(base);
                Logger.log("Registered BASE " + base.getAbsoluteName());
            }
        }
        return objects;
    }

    @Nullable
    public BaseObject getBaseByName(String name) {
        for(BaseObject o : getBases()) {
            if(o.getName().equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }

    @Nullable
    public BaseObject getBaseById(String id) {
        for(BaseObject o : getBases()) {
            if(o.getId().equalsIgnoreCase(id)) {
                return o;
            }
        }
        return null;
    }

}
