package com.company;

import com.company.edu.EchoClient;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        EchoServer echoServer = new EchoServer(8787);
        echoServer.run();

        EchoClient.connectTo(8787).run();
    }
}
