package de.joshizockt.cloud.api.groups;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BaseObject {

    private String name;
    private String absoluteName;
    private String id;
    private int ram;

    private List<ServerObject> servers;

    public BaseObject(String name, String id) {
        this.name = name;
        this.id = id;
        this.absoluteName = name + "_" + id;
        this.ram = 0;
        servers = new ArrayList<>();
    }

    public BaseObject setRam(int ram) {
        this.ram = ram;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getRam() {
        return ram;
    }

    public String getAbsoluteName() {
        return absoluteName;
    }

    public JSONObject toJSONObject() {
        JSONObject o = new JSONObject();
        o.put("ID", id);
        o.put("Name", name);
        o.put("Ram", ram);
        return o;
    }

}
