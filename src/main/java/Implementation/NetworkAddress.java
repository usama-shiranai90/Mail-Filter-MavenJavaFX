package Implementation;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.stream.IntStream;

public class NetworkAddress {
    private InetAddress address;

    public NetworkAddress() {
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public String getPCMacAddress() {
        try {

            System.out.println("Current IP address : " + address.getHostAddress());
            NetworkInterface network = NetworkInterface.getByInetAddress(address);
            byte[] mac = network.getHardwareAddress();
            StringBuilder networkBuild = new StringBuilder();
            IntStream.iterate(0, i -> i+1).limit(mac.length).forEach(value -> {
                networkBuild.append(String.format("%02X%s", mac[value], (value < mac.length - 1) ? "-" : ""));
            });

            return networkBuild.toString();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
