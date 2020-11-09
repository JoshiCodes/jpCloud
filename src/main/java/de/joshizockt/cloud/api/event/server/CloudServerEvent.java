package de.joshizockt.cloud.api.event.server;

import de.joshizockt.cloud.api.serverobject.ServerObject;
import de.joshizockt.cloud.api.event.CloudEvent;

public class CloudServerEvent extends CloudEvent {

    ServerObject server;

    public CloudServerEvent(ServerObject server) {
        this.server = server;
    }
    public CloudServerEvent(ServerObject server, boolean cancelabel) {
        super(cancelabel); this.server = server;
    }

}
