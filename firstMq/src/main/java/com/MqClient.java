package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MqClient {

    //生产消息
    public static void produce(String message) throws Exception{
        Socket socket = new Socket(InetAddress.getLocalHost(),BrokerServer.Service_Port);

        try(
            PrintWriter out = new PrintWriter(socket.getOutputStream())){
                out.println();
                out.flush();
            }
        }

        //消费消息
//        public static String consume() throws Exception {
        public  String consume() throws Exception {
            Socket socket = new Socket(InetAddress.getLocalHost(),BrokerServer.Service_Port);
            try(
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream())
                    ){
                out.println("CONSUME");
                out.flush();

                String message = in.readLine();

                return message;
            }
        }


    public static void main(String[] args) {

    }
}
