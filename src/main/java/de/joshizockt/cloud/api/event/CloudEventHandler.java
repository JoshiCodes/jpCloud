package de.joshizockt.cloud.api.event;

public @interface CloudEventHandler {
    int priority() default 1;
}
