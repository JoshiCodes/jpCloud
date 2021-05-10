package de.joshizockt.cloud.api.serverobject;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class BaseObject extends GlobalServerObject {
    String ip;
    public BaseObject(String name) throws UnknownHostException {
        super(name, ServerType.BASE, null);
        this.ip = InetAddress.getLocalHost().getHostAddress();
    }
    public BaseObject setIp(String ip) {
        this.ip = ip;
        return this;
    }
    public String getIp() {
        return ip;
    }
}
