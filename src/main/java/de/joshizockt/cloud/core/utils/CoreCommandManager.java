package de.joshizockt.cloud.core.utils;

import de.joshizockt.cloud.core.commands.CoreCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CoreCommandManager {

    private static List<CoreCommand> commands;

    public CoreCommandManager() {
        commands = new ArrayList<CoreCommand>();
    }

    public void addCommand(CoreCommand command) {
        if(!commands.contains(command)) {
            commands.add(command);
        }
    }

    public void delCommand(CoreCommand command) {
        if(commands.contains(command)) {
            commands.remove(command);
        }
    }

    public List<CoreCommand> getCommands() {
        return commands;
    }

    public void loadCommands() {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while((line = reader.readLine()) != null) {
                if(line.length() != 0) {
                    for(CoreCommand command : commands) {
                        command.fire(line.split(" "));
                    }
                }
            }

        } catch(Exception ex) { System.err.println(ex.getMessage()); }

    }

}

