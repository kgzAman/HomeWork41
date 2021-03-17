package com.company;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EchoServer {
    private final int port;

    EchoServer(int port) {
        this.port = port;
    }

    static EchoServer bindToPort(int prot){
        return new EchoServer(prot);
    }

    public void  run(){
        try (ServerSocket server = new ServerSocket(port)){
            try ( var clientSocket = server.accept()){
                handle(clientSocket);
            }

        } catch (IOException e) {
            var formatMsg = "Вероятнее всего порт %s занят. %n";
            System.out.printf(formatMsg,port);
            e.printStackTrace();
        }
    }

    private void handle(Socket socket) throws IOException{
     InputStream input = socket.getInputStream();
     InputStreamReader isr = new InputStreamReader(input, "UTF-8");
     var scanner = new Scanner(isr);
     try (scanner){
         while (true){
             String message = scanner.nextLine().strip();
             System.out.printf("Got: %s%n",message);
             if("bye".equals(message.toLowerCase())){
                 System.out.printf(" Bye Bye!%n");
                 return;
             }
         }
     }catch (NoSuchElementException ex){
         System.out.println("Client dropped the connection!");
     }
    }

}
