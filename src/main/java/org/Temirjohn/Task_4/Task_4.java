package org.Temirjohn.Task_4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Task_4 {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(8081)) {
            while (true) {
                Socket client = serverSocket.accept();

                new Thread(() -> Client(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Client(Socket socket) {
        try (socket;
             InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int read = in.read(buffer);
            String request = new String(buffer, 0, read);
            System.out.println("Gets request: " + request);

            String responseBody = "<html><body><h1>Hello from Mergen Temirzhan!</h1></body></html>";
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html; charset=utf-8\r\n" +
                    "Content-Length: " + responseBody.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                    "Connection: close\r\n" +
                    "\r\n" +
                    responseBody;

            out.write(httpResponse.getBytes(StandardCharsets.UTF_8));
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
