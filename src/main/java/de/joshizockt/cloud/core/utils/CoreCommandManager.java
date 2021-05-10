package de.joshizockt.cloud.core.utils;

import de.joshizockt.cloud.core.commands.CoreCommand;
import de.joshizockt.cloud.core.commands.CoreHelpCommand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CoreCommandManager {

    private static List<CoreCommand> commands;

    public CoreCommandManager() {
        commands = new ArrayList<>();
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
                    String[] args = line.split(" ");
                    boolean s = false;
                    for(CoreCommand command : commands) {
                        for(String cmd : command.getCommands()) {
                            if(args[0].equalsIgnoreCase(cmd)) {
                                command.execute(args[0], args);
                                s = true;
                            }
                        }
                    }
                    if(!s) new CoreHelpCommand().execute(args[0], args);
                }
            }

        } catch(Exception ex) { System.err.println(ex.getMessage()); }

    }

}

