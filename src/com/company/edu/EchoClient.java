package com.company.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class EchoClient {
    private final int port;
    private final String host;

    private EchoClient(String host, int port ) {
        this.host = host;
        this.port = port;
    }
    public static EchoClient connectTo(int port){
        var localHost="127.0.0.1";
        return new EchoClient(localHost,port);
    }
    public void run(){
        System.out.println("Напиши 'Bye' что-бы выйти%n%n%n");
        try (var socket = new Socket(host,port)) {
            Scanner scanner = new Scanner(System.in,"UTF-8");
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            try (scanner;writer){
                while (true){
                    System.out.println("Напишите сообщение: ");
                    String message = scanner.nextLine();
                    writer.write(message);
                    writer.write(System.lineSeparator());
                    writer.flush();
                    if("bye".equals(message.toLowerCase())){
                        return;
                    }
                }

            }catch (NoSuchElementException ex){
                System.out.printf("Connection dropped!%n");
            }
        }catch (IOException e ){
            var mas = "Can't connect to %s:%s!%n";
            System.out.printf(mas,host,port);
            e.printStackTrace();
        }
    }
}
