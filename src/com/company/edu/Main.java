package com.company.edu;

import com.company.EchoServer;

import java.io.IOException;

public class Main {



        public static void main(String[] args) throws IOException {


            EchoClient.connectTo(8787).run();
        }
    }
