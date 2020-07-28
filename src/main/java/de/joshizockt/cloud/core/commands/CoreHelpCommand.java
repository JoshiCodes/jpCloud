package de.joshizockt.cloud.core.commands;

import de.joshizockt.cloud.core.main.Core;

public class CoreHelpCommand extends CoreCommand {

    public CoreHelpCommand() {
        super("See the Help Message", "help");
    }

    @Override
    public void execute() {
        if(args[0].equalsIgnoreCase("help")) {
            System.out.println("——————————[ H E L P ]——————————");
            System.out.println(" ");
            for(CoreCommand command : Core.getCommandManager().getCommands()) {
                System.out.println(command.getCommands().get(0) + " | " + command.getUsage());
            }
            System.out.println(" ");
            System.out.println("——————————[ H E L P ]——————————");
        }
    }

}
