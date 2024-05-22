import java.io.*;
import java.net.*;

class tcpFileClient {
    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            Socket clsct = new Socket("127.0.0.1", 1309);
            DataInputStream din = new DataInputStream(clsct.getInputStream());
            DataOutputStream dout = new DataOutputStream(clsct.getOutputStream());

            System.out.println("Enter the file name:");
            String str = in.readLine();
            dout.writeBytes(str + '\n');

            System.out.println("Enter the new file name:");
            String str2 = in.readLine();

            String str1;
            FileWriter f = new FileWriter(str2);

            while (true) {
                str1 = din.readLine();
                if (str1.equals("-1")) {
                    break;
                }
                System.out.println(str1);
                f.write(str1 + "\n");
            }

            f.close();
            clsct.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
