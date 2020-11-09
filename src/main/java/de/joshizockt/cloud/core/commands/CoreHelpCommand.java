package de.joshizockt.cloud.core.commands;

import de.joshizockt.cloud.core.main.Core;
import de.joshizockt.cloud.core.utils.CoreMessenger;
import de.joshizockt.cloud.core.utils.MessengerType;

public class CoreHelpCommand extends CoreCommand {

    public CoreHelpCommand() {
        super("See the Help Message", "help");
    }

    @Override
    public void execute() {
        if(args[0].equalsIgnoreCase("help")) {
            System.out.println(Core.messenger.getMessage(MessengerType.CONSOLE, "HelpCommand.HeaderFooter"));
            System.out.println(" ");
            for(CoreCommand command : Core.getCommandManager().getCommands()) {
                System.out.println(command.getCommands().get(0) + " | " + command.getUsage());
            }
            System.out.println(" ");
            System.out.println(Core.messenger.getMessage(MessengerType.CONSOLE, "HelpCommand.HeaderFooter"));
        }
    }

}
