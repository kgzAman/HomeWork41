package server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        EchoServer echoServer = new EchoServer(8787);
        echoServer.run();

    }
}
