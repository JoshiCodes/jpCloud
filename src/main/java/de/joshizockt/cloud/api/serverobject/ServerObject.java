package de.joshizockt.cloud.api.serverobject;

public class ServerObject {

    public static final int SUBSERVER = 0, PROXY = 1, BASE = 2, CORE = 3;

    String name;
    int type;
    boolean stat;

    public ServerObject(String name, int type) {
        this.name = name;
        if(type != SUBSERVER && type != PROXY && type != BASE && type != CORE) {
            return;
        }
        this.type = type;
    }

    public ServerObject setStatic(boolean stat) {
        this.stat = stat;
        return this;
    }

    public String getName() {
        return name;
    }
    public int getType() {
        return type;
    }

}
