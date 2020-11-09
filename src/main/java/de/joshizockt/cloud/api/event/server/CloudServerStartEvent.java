package de.joshizockt.cloud.api.event.server;


import de.joshizockt.cloud.api.serverobject.ServerObject;

public class CloudServerStartEvent extends CloudServerEvent {

    public CloudServerStartEvent(ServerObject server) {
        super(server, false);
    }

}
