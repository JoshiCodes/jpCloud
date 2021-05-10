package de.joshizockt.cloud.api.event;

public class CloudEvent {

    private boolean cancelable;

    public CloudEvent() {this(false);}

    public CloudEvent(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public boolean isCancelable() {
        return cancelable;
    }

}
