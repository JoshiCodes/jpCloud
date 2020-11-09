package de.joshizockt.cloud.api.serverobject;

public class BaseObject extends ServerObject {
    String host;
    public BaseObject(String name, String host) {
        super(name, BASE);
        this.host = host;
    }
    public String getHost() {
        return host;
    }
}
