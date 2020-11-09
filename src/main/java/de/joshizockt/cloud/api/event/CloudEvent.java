package de.joshizockt.cloud.api.event;

public class CloudEvent {

    boolean cancelable;

    public CloudEvent() {}
    public CloudEvent(boolean cancelable) { this.cancelable = cancelable; }

}
