package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer implements Runnable {

//    public static int Service_Port = 9999;
    public static int Service_Port = 8080;

    private  Socket socket = null;


    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try(
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream())

        ){
            while(true){
                String str = in.readLine();
                if(str == null){
                    continue;
                }
                System.out.println("接收到原始数据：" + str);

                if(str.equals("CONSUME")){
                    String message = Broker.consume();
                    out.print(message);
                    out.flush();
                }else{
                    Broker.produce(str);
                }
            }


        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(Service_Port);
        while(true){
            BrokerServer brokerServer = new BrokerServer(server.accept());
            new Thread(brokerServer).start();
        }
    }
}
