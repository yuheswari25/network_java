import java.io.*;
import java.net.*;

public class udpdnsserver {

    private static int indexOf(String[] array, String str) {
        str = str.trim();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        String[] hosts = {"yahoo.com", "gmail.com", "cricinfo.com", "facebook.com"};
        String[] ip = {"68.180.206.184", "209.85.148.19", "80.168.92.140", "69.63.189.16"};

        DatagramSocket serversocket = new DatagramSocket(1362);
        System.out.println("Press Ctrl + C to Quit");

        while (true) {
            byte[] receiveData = new byte[1024];
            DatagramPacket recvPacket = new DatagramPacket(receiveData, receiveData.length);
            serversocket.receive(recvPacket);

            String receivedHost = new String(recvPacket.getData(), 0, recvPacket.getLength()).trim();
            InetAddress ipAddress = recvPacket.getAddress();
            int port = recvPacket.getPort();
            
            System.out.println("Request for host " + receivedHost);

            String response;
            int index = indexOf(hosts, receivedHost);
            if (index != -1) {
                response = ip[index];
            } else {
                response = "Host Not Found";
            }

            byte[] sendData = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            serversocket.send(sendPacket);
        }
    }
}
