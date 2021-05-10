package de.joshizockt.cloud.core.commands;

import de.joshizockt.cloud.api.CloudColor;
import de.joshizockt.cloud.core.main.Core;
import de.joshizockt.cloud.core.utils.CoreMessenger;
import de.joshizockt.cloud.core.utils.MessengerType;

public class CoreHelpCommand extends CoreCommand {

    public CoreHelpCommand() {
        super("See the Help Message", "help");
    }

    @Override
    public void execute(String label, String[] args) {
        System.out.println(CloudColor.YELLOW_BOLD + Core.messenger.getMessage(MessengerType.CONSOLE, "HelpCommand.HeaderFooter"));
        System.out.println(" " + CloudColor.RESET);
        for (CoreCommand command : Core.getCommandManager().getCommands()) {
            System.out.println(CloudColor.CYAN + command.getCommands().get(0) + CloudColor.WHITE_BOLD + " | " + CloudColor.CYAN + command.getUsage());
        }
        System.out.println(" ");
        System.out.println(CloudColor.YELLOW_BOLD + Core.messenger.getMessage(MessengerType.CONSOLE, "HelpCommand.HeaderFooter"));
        System.out.println(CloudColor.RESET);
    }

}
