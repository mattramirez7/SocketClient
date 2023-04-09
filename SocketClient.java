import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        String message = "";
        for (int i = 2; i < args.length; i++) {
            message += args[i] + " ";
        }
        message = message.trim();

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Connected to " + host + ":" + port);

            if (!message.isEmpty()) {
                System.out.println("Sending message: " + message);
                out.println(message);
            }

            System.out.print("Printing socket output: ");
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
