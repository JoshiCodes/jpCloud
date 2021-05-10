package de.joshizockt.cloud.api.event.server;

import de.joshizockt.cloud.api.serverobject.ServerObject;
import de.joshizockt.cloud.api.event.CloudEvent;

public class CloudServerEvent extends CloudEvent {

    private ServerObject server;

    public CloudServerEvent(ServerObject object) {
        this(object, false);
    }

    public CloudServerEvent(ServerObject object, boolean cancelable) {
        super(true);
        this.server = object;
    }

    public ServerObject getServer() {
        return server;
    }

}
