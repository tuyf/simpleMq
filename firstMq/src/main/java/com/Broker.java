package com;

import java.util.concurrent.ArrayBlockingQueue;

public class Broker {
    //队列存储消息数量
    private final static int MAX_SIZE = 3;

    //保存数据的容器
    private static ArrayBlockingQueue<String> messageQueue = new
            ArrayBlockingQueue<String>(MAX_SIZE);

    //生产消息
    public static void produce(String msg){
        if(messageQueue.offer(msg)){
            System.out.println("成功向消息中心投递消息："+ msg + " 当前暂存数量：" + messageQueue.size());
        }else{
            System.out.println("消息中心达到最大负荷");
        }
        System.out.println("===================");
    }

    //消费消息
    public static String consume(){
        String msg = messageQueue.poll();
        if(msg != null){
            System.out.println("已经消费消息："+ msg + " 当前暂存的消息数量是：" + messageQueue.size());
        }else{
            System.out.println("消息中心没有可以消费的消息");
        }
        System.out.println("===================");
        return msg;
    }

}
