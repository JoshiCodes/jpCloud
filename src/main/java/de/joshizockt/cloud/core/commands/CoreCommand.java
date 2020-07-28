package de.joshizockt.cloud.core.commands;

import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.objects.annotations.Constructor;

import java.util.Arrays;
import java.util.List;

public class CoreCommand {

    private List<String> commands;
    private String usage;

    protected String[] args;

    public CoreCommand(String desc, String command) {
        this.commands = Arrays.asList(command);
        this.usage = desc;
    };

    public CoreCommand(String desc, String... commands) {
        this.commands = Arrays.asList(commands);
        this.usage = desc;
    };

    public void execute() {};

    @Deprecated
    public void fire(String[] args) {
        this.args = args;
        execute();
    }

    public List<String> getCommands() {
        return commands;
    }

    public String getUsage() {
        return usage;
    }

}
