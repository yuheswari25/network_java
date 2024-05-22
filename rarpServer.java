import java.io.*;
import java.net.*;

class rarpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(4000);
            System.out.println("Server is running...");

           
            String[] mac = {"6A:08:AA:C2:A4:E4", "8A:BC:E3:FA:08:AA"};
            String[] ip = {"165.165.80.80", "165.165.79.1"};

            while (true) {
             
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

              
                DataInputStream din = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

             
                String receivedMac = din.readLine();
                System.out.println("Received MAC: " + receivedMac);

              
                String response = "IP Address not found";
                for (int i = 0; i < mac.length; i++) {
                    if (receivedMac.equals(mac[i])) {
                        response = ip[i];
                        break;
                    }
                }

               
                dout.writeBytes(response + '\n');

             
                din.close();
                dout.close();
                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
