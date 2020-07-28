package de.joshizockt.cloud.base.main;

import Networking.manager.TcpManager;
import Networking.packets.BaseStartPacket;
import de.joshizockt.cloud.api.CloudConfig;
import de.joshizockt.cloud.api.Logger;

import javax.swing.*;

public class Base {

    private static CloudConfig config;
    private static TcpManager tcpManager;

    public static void start(String[] args) throws Exception {

        Logger.log("Starting BASE-1");

        config = new CloudConfig("config.json", "base");
        tcpManager = new TcpManager(config.getInteger("BasePort"));

        Logger.log("Started BASE-1");


        tcpManager.startBase();

        Logger.log("Trying to send example Data..");
        Logger.log(tcpManager.toCore("info base 5yu957"));

    }

    public static CloudConfig getConfig() {
        return config;
    }
}
