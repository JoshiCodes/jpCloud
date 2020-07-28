package de.joshizockt.cloud.core.main;

import Networking.manager.TcpManager;
import de.joshizockt.cloud.api.CloudConfig;
import de.joshizockt.cloud.api.IdManager;
import de.joshizockt.cloud.api.groups.BaseObject;
import de.joshizockt.cloud.core.commands.CoreHelpCommand;
import de.joshizockt.cloud.core.utils.CoreCommandManager;
import de.joshizockt.cloud.api.Logger;
import de.joshizockt.cloud.core.utils.CoreServerManager;

public class Core {

    private static TcpManager tcpManager;

    private static CoreServerManager serverManager;
    private static CoreCommandManager commandManager;

    private static CloudConfig config;

    public static void start(String[] args) throws Exception {

        Logger.log("Starting CORE");

        config = new CloudConfig("config.json", "core");

        if(!config.fileExists() || !config.valueExists("Port")) {
            Logger.err("Error while loading Core config.");
            System.exit(0);
        }

        int port = config.getInteger("Port");
        tcpManager = new TcpManager(port);

        commandManager = new CoreCommandManager();
        serverManager = new CoreServerManager();

        commandManager.addCommand(new CoreHelpCommand());

        Logger.log("CORE was successfull started.");


        IdManager.init();

        serverManager.registerBaseObject(new BaseObject("testBase", IdManager.gen()).setRam(1024));

        tcpManager.startCore();
        commandManager.loadCommands();

    }

    public static CoreCommandManager getCommandManager() {
        return commandManager;
    }

    public static CoreServerManager getServerManager() {
        return serverManager;
    }

}
