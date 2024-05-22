import java.io.*;
import java.net.*;

public class HTTPClient {
    public static void main(String[] args) {
        String hostName = "www.google.com";
        int portNumber = 80;

        try {
            // Create a socket to connect to the specified host and port
            Socket socket = new Socket(hostName, portNumber);
            
            // Create a PrintWriter for sending the GET request to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            // Create a BufferedReader for reading the response from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            // Send the HTTP GET request
            out.println("GET / HTTP/1.1\nHost: www.google.com\n\n");
            
            // Read and print the response from the server
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            
            // Close the resources
            in.close();
            out.close();
            socket.close();
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
