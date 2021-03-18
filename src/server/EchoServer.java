package server;


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
                System.out.println("Program is connected");
                handle(clientSocket);
            }

        } catch (IOException e) {
            var formatMsg = "Port is busy!";
            System.out.printf(formatMsg,port);
            e.printStackTrace();
        }
    }

    private void handle(Socket socket) throws IOException{

     InputStream input = socket.getInputStream();
     OutputStream outPut = socket.getOutputStream();

     PrintWriter writer = new PrintWriter(outPut);
     var scanner = new Scanner(new InputStreamReader(input, StandardCharsets.UTF_8));
     var sc1= new Scanner (System.in,StandardCharsets.UTF_8);


     try (scanner;writer;sc1){
         while (true){

             String message = scanner.nextLine().strip();
             System.out.printf("Got: %s%n",message);

             writer.write(message);
             writer.write(System.lineSeparator());
             writer.flush();
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
