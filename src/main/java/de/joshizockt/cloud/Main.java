package de.joshizockt.cloud;

import de.joshizockt.cloud.base.main.Base;
import de.joshizockt.cloud.core.main.Core;
import de.joshizockt.cloud.api.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static final String download = "DOWNLOADLINK SOON";

    public static void main(String args[]) throws Exception {

        System.out.println("\r\n" +
                "                      /$$$$$$  /$$                           /$$\r\n" +
                "                     /$$__  $$| $$                          | $$\r\n" +
                "       /$$  /$$$$$$ | $$  \\__/| $$  /$$$$$$  /$$   /$$  /$$$$$$$\r\n" +
                "      |__/ /$$__  $$| $$      | $$ /$$__  $$| $$  | $$ /$$__  $$\r\n" +
                "       /$$| $$  \\ $$| $$      | $$| $$  \\ $$| $$  | $$| $$  | $$\r\n" +
                "      | $$| $$  | $$| $$    $$| $$| $$  | $$| $$  | $$| $$  | $$\r\n" +
                "      | $$| $$$$$$$/|  $$$$$$/| $$|  $$$$$$/|  $$$$$$/|  $$$$$$$\r\n" +
                "      | $$| $$____/  \\______/ |__/ \\______/  \\______/  \\_______/\r\n" +
                " /$$  | $$| $$                                                  \r\n" +
                "|  $$$$$$/| $$                                                  \r\n" +
                " \\______/ |__/                                                  \r\n" +
                "");

        String wrongArg = "Wrong Argument! Please type: \n" +
                          "[1]     CORE \n" +
                          "[2]     BASE \n \n \n" +
                          "";

        if(args.length >= 1) {
            if(args[0].equalsIgnoreCase("--TYPE=CORE")) {
                Core.start(args);
            } else if(args[0].equalsIgnoreCase("--TYPE=BASE")) {
                Base.start(args);
            } else {
                Logger.err(wrongArg);
                return;
            }
        } else {
            Logger.err(wrongArg);
            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String line;
                while((line = reader.readLine()) != null) {
                    if(line.equalsIgnoreCase("1")) {
                        reader.close();
                        Core.start(args);
                    } else if(line.equalsIgnoreCase("2")) {
                        reader.close();
                        Base.start(args);
                    } else {
                        Logger.err(wrongArg);
                        return;
                    }
                }

            } catch(Exception ex) { Logger.err(ex.getMessage()); return; }
        }

    }

}
