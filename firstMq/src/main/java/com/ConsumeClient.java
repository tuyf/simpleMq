package com;

public class ConsumeClient {
    public static void main(String[] args)  throws Exception{
        MqClient client = new MqClient();

        String message = client.consume();

        System.out.println("接受到的消息为：" + message);
    }
}
