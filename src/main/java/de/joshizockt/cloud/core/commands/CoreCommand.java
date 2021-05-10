package de.joshizockt.cloud.core.commands;

import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.objects.annotations.Constructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CoreCommand {

    private final List<String> commands;
    private final String usage;

    public CoreCommand(String desc, String command) {
        this.commands = Collections.singletonList(command);
        this.usage = desc;
    };

    public CoreCommand(String desc, String... commands) {
        this.commands = Arrays.asList(commands);
        this.usage = desc;
    };

    public void execute(String label, String[] args) {};

    public List<String> getCommands() {
        return commands;
    }

    public String getUsage() {
        return usage;
    }

}
