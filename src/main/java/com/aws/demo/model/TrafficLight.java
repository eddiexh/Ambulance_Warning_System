package com.aws.demo.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TrafficLight {
        public void run() {
            try {
                int serverPort = 8888;
                InetAddress host = InetAddress.getByName("172.20.10.7");
                System.out.println("Connecting to server on port " + serverPort);

                Socket socket = new Socket(host,serverPort);
                //Socket socket = new Socket("127.0.0.1", serverPort);
                if(socket.isConnected()) {
                    System.out.println("Just connected to " + socket.getRemoteSocketAddress());
                    PrintWriter toServer =
                            new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader fromServer =
                            new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                    toServer.println("Hello from " + socket.getLocalSocketAddress());
                }
            }
            catch(UnknownHostException ex) {
                ex.printStackTrace();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

    //extend green light
    //change rad light

    //recover light
}
