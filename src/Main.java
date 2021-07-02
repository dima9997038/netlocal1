import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) {
        try {
            try {
                String ip1 = InetAddress.getLocalHost().getHostName();
                System.out.println("My name is " + ip1 );
                System.out.print("Current IP address : ");
                final DatagramSocket socket = new DatagramSocket();
                socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                byte[] ip = socket.getLocalAddress().getAddress();
                String StrIp = socket.getLocalAddress().getHostAddress();
                System.out.println(StrIp);

                Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();

                while (networks.hasMoreElements()) {

                    NetworkInterface network = networks.nextElement();
                    byte[] mac = network.getHardwareAddress();

                    if (mac != null) {
                        System.out.print("Current MAC address : ");

                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < mac.length; i++) {
                            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                        }
                        System.out.println(sb.toString());
                    }
                }
                InetAddress Myaddress = InetAddress.getByAddress(ip);
                for (int i = 1; i <= 255; i++) {

                    ip[3] = (byte) i;
                    InetAddress address = InetAddress.getByAddress(ip);

                    if ( (!address.equals(Myaddress)) && (address.isReachable(10))) {

                        String output = address.toString().substring(1);
                        System.out.println(output + " is on the network, name: " + address.getHostName());
                    }
                }

            } catch (SocketException e1) {
                e1.printStackTrace();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}