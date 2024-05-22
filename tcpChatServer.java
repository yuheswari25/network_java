import java.io.*;
import java.net.*;

class tcpChatServer {
    public static void main(String[] args) throws Exception {
        PrintWriter toClient = null;
        BufferedReader fromUser = null, fromClient = null;
        ServerSocket Srv = null;
        Socket Clt = null;

        try {
            Srv = new ServerSocket(4000);
            System.out.println("\nServer started");
            Clt = Srv.accept();
            System.out.println("Client connected");

            toClient = new PrintWriter(new BufferedWriter(new OutputStreamWriter(Clt.getOutputStream())), true);
            fromClient = new BufferedReader(new InputStreamReader(Clt.getInputStream()));
            fromUser = new BufferedReader(new InputStreamReader(System.in));

            String CltMsg, SrvMsg;

            while (true) {
                CltMsg = fromClient.readLine();
                if (CltMsg == null || CltMsg.equals("end")) {
                    break;
                } else {
                    System.out.println("\nServer <<< " + CltMsg);
                    System.out.print("Message to Client: ");
                    SrvMsg = fromUser.readLine();
                    toClient.println(SrvMsg);
                }
            }

            System.out.println("\nClient Disconnected");
        } catch (Exception E) {
            System.out.println(E.getMessage());
        } finally {
            try {
                if (fromClient != null) {
                    fromClient.close();
                }
                if (toClient != null) {
                    toClient.close();
                }
                if (fromUser != null) {
                    fromUser.close();
                }
                if (Clt != null) {
                    Clt.close();
                }
                if (Srv != null) {
                    Srv.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
