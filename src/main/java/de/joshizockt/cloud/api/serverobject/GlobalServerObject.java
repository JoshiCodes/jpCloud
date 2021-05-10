package de.joshizockt.cloud.api.serverobject;

public class GlobalServerObject {

    String name;
    ServerType type;
    BaseObject base;
    boolean stat;

    GlobalServerObject(String name, ServerType type, BaseObject base) {
        this.name = name;
        this.type = type;
        this.base = base;
    }

    GlobalServerObject(String name, ServerType type) {
        this.name = name;
        this.type = type;
    }

    public GlobalServerObject setStatic(boolean stat) {
        this.stat = stat;
        return this;
    }

    public BaseObject getBase() {
        return base;
    }

    public String getName() {
        return name;
    }
    public ServerType getType() {
        return type;
    }
    public boolean isStatic() {
        return stat;
    }

}
