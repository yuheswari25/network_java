import java.io.*;
import java.net.*;

class rarpClient {
    public static void main(String[] args) {
        try {
            InetAddress abc = InetAddress.getLocalHost();
            Socket clsct = new Socket(abc, 4000);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream din = new DataInputStream(clsct.getInputStream());
            DataOutputStream dout = new DataOutputStream(clsct.getOutputStream());
            
            System.out.println("Enter the Physical Address (MAC):");
            String str1 = in.readLine();
            dout.writeBytes(str1 + '\n');
            
            String str = din.readLine();
            System.out.println("The Logical address is (IP): " + str);
            
            in.close();
            din.close();
            dout.close();
            clsct.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
