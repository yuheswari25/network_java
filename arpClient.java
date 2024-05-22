import java.io.*;
import java.net.*;

class arpClient {
    public static void main(String[] args) {
        try {
            InetAddress abc = InetAddress.getLocalHost();
            Socket clsct = new Socket(abc, 1390);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream din = new DataInputStream(clsct.getInputStream());
            DataOutputStream dout = new DataOutputStream(clsct.getOutputStream());

            System.out.println("Enter the Logical address (IP):");
            String str1 = in.readLine();
            dout.writeBytes(str1 + '\n');

            String str = din.readLine();
            System.out.println("The Physical Address is: " + str);

            in.close();
            din.close();
            dout.close();
            clsct.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
