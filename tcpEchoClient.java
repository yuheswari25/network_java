import java.net.*;
import java.io.*;

public class tcpEchoClient {
    public static void main(String[] args) throws IOException {
        BufferedReader fromServer = null, fromUser = null;
        PrintWriter toServer = null;
        Socket sock = null;
        try {
            if (args.length == 0)
                sock = new Socket(InetAddress.getLocalHost(), 4000);
            else
                sock = new Socket(InetAddress.getByName(args[0]), 4000);

            fromServer = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            fromUser = new BufferedReader(new InputStreamReader(System.in));
            toServer = new PrintWriter(sock.getOutputStream(), true);
            
            String Usrmsg, Srvmsg;
            System.out.println("Type \"bye\" to quit");
            
            while (true) {
                System.out.print("Enter msg to server: ");
                Usrmsg = fromUser.readLine();
                if (Usrmsg == null || Usrmsg.equals("bye")) {
                    toServer.println("bye");
                    break;
                } else {
                    toServer.println(Usrmsg);
                }
                Srvmsg = fromServer.readLine();
                System.out.println(Srvmsg);
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } finally {
            try {
                if (fromUser != null) {
                    fromUser.close();
                }
                if (fromServer != null) {
                    fromServer.close();
                }
                if (toServer != null) {
                    toServer.close();
                }
                if (sock != null) {
                    sock.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
