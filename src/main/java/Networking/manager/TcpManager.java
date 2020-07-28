package Networking.manager;

import de.joshizockt.cloud.Main;
import de.joshizockt.cloud.api.Logger;
import de.joshizockt.cloud.base.main.Base;
import de.joshizockt.cloud.core.main.Core;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;

public class TcpManager {

    private int port;

    private ServerSocket server;
    private Socket base;
    private final boolean acceptClients = true;

    public TcpManager(int port) {
        this.port = port;
    }

    /**
     * Only for the Core Server
     */
    @Deprecated
    public void startCore() throws IOException {
        Logger.log("Starting Core-TCP Server with Port " + port + "..");
        server = new ServerSocket(port);
        while(acceptClients) {
            try {
                String data = null;
                Socket client = server.accept();
                String address = client.getInetAddress().getHostAddress();
                Logger.warn("New Connection from " + address);
                if(Core.getServerManager().getAllowedIPs().contains(address)) {
                    Logger.log("Connection is allowed. Access granted.");
                    PrintWriter printWriter =
                            new PrintWriter(
                                    new OutputStreamWriter(
                                            client.getOutputStream()));

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(client.getInputStream()));
                    try {
                        while ((data = in.readLine()) != null) {
                            Logger.log("\r\nMessage from " + address + ": " + data);
                            JSONObject js = validateBaseMessage(data);
                            printWriter.write(js.toString());
                            printWriter.flush();
                        }
                    } catch(SocketException ex) { }
                } else {
                    PrintWriter printWriter =
                            new PrintWriter(
                                    new OutputStreamWriter(
                                            client.getOutputStream()));
                    printWriter.println("[Error] You are not allowed to connect with this jpCloud Server!");
                    printWriter.println("Download jpCloud today: " + Main.download);
                    printWriter.flush();
                    Logger.err("Address is not allowed! Closing Client connection..");
                    client.close();
                }
            } catch (IOException ex) { Logger.err(ex.getMessage()); }
        }
    }

    private JSONObject validateBaseMessage(String data) {
        JSONObject object = new JSONObject();

        String[] args = data.split(" ");

        if(args[0].equals("info")) {
            if(args[1].equalsIgnoreCase("server")) {

            } else if(args[1].equalsIgnoreCase("proxy")) {

            } else if(args[1].equalsIgnoreCase("base")) {
                String b = args[2];
                object.put("Search", "Base;" + b);
                if(Core.getServerManager().getBaseById(b) == null) {
                    object.put("Found", "null");
                    return object;
                } else {
                    object.put("Found", Core.getServerManager().getBaseById(b).toJSONObject());
                    return object;
                }
            }
        }
        object.put("Found", "null");
        return object;
    }


    /**
     * Only for the Base Server
     */
    public void startBase() throws IOException {
        try (Socket socket = new Socket(Base.getConfig().getString("Host"), Base.getConfig().getInteger("Port"))) {

            this.base = socket;

        }
    }

    public String toCore(String data) throws IOException {
        PrintWriter print =
                    new PrintWriter(
                            new OutputStreamWriter(
                                    base.getOutputStream()));
        print.println("info base 5yu957");
        print.flush();

        InputStream input = base.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String time = reader.readLine();

        base.close();

        System.out.println(":");
        System.out.println(time);
        return time;
    }

}
