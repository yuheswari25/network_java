import java.io.*;
import java.net.*;

class tcpChatClient {
    public static void main(String[] args) throws Exception {
        Socket Clt = null;
        PrintWriter toServer = null;
        BufferedReader fromUser = null, fromServer = null;

        try {
            if (args.length > 1) {
                System.out.println("Usage: java tcpchatclient [hostipaddr]");
                System.exit(-1);
            }

            if (args.length == 0) {
                Clt = new Socket(InetAddress.getLocalHost(), 4000);
            } else {
                Clt = new Socket(InetAddress.getByName(args[0]), 4000);
            }

            toServer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(Clt.getOutputStream())), true);
            fromServer = new BufferedReader(new InputStreamReader(Clt.getInputStream()));
            fromUser = new BufferedReader(new InputStreamReader(System.in));

            String CltMsg, SrvMsg;
            System.out.println("Type \"end\" to Quit");

            while (true) {
                System.out.print("\nMessage to Server: ");
                CltMsg = fromUser.readLine();
                toServer.println(CltMsg);

                if (CltMsg.equals("end")) {
                    break;
                }

                SrvMsg = fromServer.readLine();
                System.out.println("Client <<< " + SrvMsg);
            }
        } catch (Exception E) {
            System.out.println(E.getMessage());
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
                if (Clt != null) {
                    Clt.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
