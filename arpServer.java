import java.io.*;
import java.net.*;

class arpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1390);
            System.out.println("Server is running...");

            String[] ip = {"165.165.80.80", "165.165.79.1"};
            String[] mac = {"6A:08:AA:C2:A4:E4", "8A:BC:E3:FA:08:AA"};

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                
                DataInputStream din = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

                
                String receivedIp = din.readLine();
                System.out.println("Received IP: " + receivedIp);

                
                String response = "MAC Address not found";
                for (int i = 0; i < ip.length; i++) {
                    if (receivedIp.equals(ip[i])) {
                        response = mac[i];
                        break;
                    }
                }

                dout.writeBytes(response + '\n');

                din.close();
                dout.close();
                clientSocket.close();
                serverSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
