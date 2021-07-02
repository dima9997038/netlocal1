import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class NetLoc
{

    public static void sendPingRequest(String ipAddress)
            throws UnknownHostException, IOException
    {
        InetAddress geek = InetAddress.getByName(ipAddress);
        System.out.println("Sending Ping Request to " + ipAddress);
        if (geek.isReachable(100))
            System.out.println("Host is reachable");
        else
            System.out.println("Sorry ! We can't reach to this host");
    }

    public static void main(String args[]) throws Exception
    {
        String localip="";
        // Returns the instance of InetAddress containing 
        // local host name and address 
        InetAddress localhost = InetAddress.getLocalHost();
        localip=localhost.getHostAddress();
        System.out.println("System IP Address : " +
                (localhost.getHostAddress()).trim());

        // Find public IP address 
        String systemipaddress = "";
        try
        {
            URL url_name = new URL("http://bot.whatismyipaddress.com");

            BufferedReader sc =
                    new BufferedReader(new InputStreamReader(url_name.openStream()));

            // reads system IPAddress 
            systemipaddress = sc.readLine().trim();

        }
        catch (Exception e)
        {
            systemipaddress = "Cannot Execute Properly";
        }
        System.out.println("Public IP Address: " + systemipaddress +"\n");
        sendPingRequest(localip);
        System.out.println(localip);


        String[] mas= localip.split("\\.");
        for (int i = 106; i < 110; i++) {
            String iptest=mas[0]+"."+mas[1]+"."+mas[2]+"."+i;

            sendPingRequest(iptest);
        }



    }
} 