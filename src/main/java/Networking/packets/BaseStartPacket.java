package Networking.packets;

public class BaseStartPacket extends Packet {

    private String baseName;
    private int port;

    public BaseStartPacket(String baseName, int port) {
        super("StartBase");
        this.baseName = baseName;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getBaseName() {
        return baseName;
    }

}
