package org.Temirjohn.Task_4;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Task_4 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8081);
        Socket input = serverSocket.accept();
        Scanner in = new Scanner(input.getInputStream());
        while (in.hasNext()) {
            System.out.println(in.nextLine());
        }
        in.close();
        input.close();
        serverSocket.close();
    }

//    private static void Client(Socket socket) {
//        try (socket; IN)
//    }
}
