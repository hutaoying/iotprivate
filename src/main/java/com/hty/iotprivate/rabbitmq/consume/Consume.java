package com.hty.iotprivate.rabbitmq.consume;

import com.hty.iotprivate.rabbitmq.utils.ConnUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consume {

    /**
     * 在这里一步小心看到一到面试题，routing_key 和 binding_key 的最大长度是多少？
     * 同样也是255
     */
    // 设置queue的名称，注意这个名字的长度是有限制的      if(queue.length() > 255)  是要小于255的
    public static final String QUEUE_NAME = "simple_worker";


    public static void main(String[] args) throws Exception, TimeoutException {
        // 这个里面我就不写那么多的注释了

        Connection conn = ConnUtils.getConn();
        final Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);


        Consumer consumer = new DefaultConsumer(channel){
            /**
             * Called when a <code><b>basic.deliver</b></code> is received for this consumer.
             * 即处理投递过来的消息的
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 这个是和我们在productor中获取的是一样的
                long deliveryTag = envelope.getDeliveryTag();
                /**
                 *  TODO 假装这个地方是我们的逻辑处理 ,
                 *
                 *  doSomething();
                 */
                String msg = new String(body,"utf-8");
                System.out.println("我是消息，这个地方我们获取到了消息并不代表，我们进行了应答，消息为："+msg);
                /**
                 * 这里我们分别测试  成功应答和拒绝应答，我们通过rabbitmq的web控制台来查看
                 *
                 * 注意：rabbitmq的消费端的可靠性的保证，1、当consumer挂掉了（channel断掉）则将消息重回队列并投递给其他的消费者
                 * 但是这里面是有可能造成重复的消费的，
                 * 假若我们考虑这种场景：当consumer1在处理完逻辑之后，发送应答由于网络中断，这个应答并没有到达broker那么channel
                 * 断开，所以这个时候消息会重回队列，会被投递给其他的消费者进行消费，所以这个时候的机制我们仍然可以维护一份消息id的
                 * 状态表，同理这个表依旧不可能太大
                 */

                /**
                 * multiple 如果为true的话，则代表只要deliverTag小于当前的一律都被确认，deliveryTay是在同一个channel是主键递增的
                 *          如果为false的话，那么则代表只确定当前的
                 */
                boolean multiple = false;

                // 1、成功应答
                channel.basicAck(deliveryTag,false);
            }
        };

        /**
         * 这个地方我们关闭自动应答，自动应答模式：如果消息投递到了，不管你consumer 处理是否完成，则broker任务消息已经被消费了，然后
         * 就会删除消息，所以这里我们开启手动应答，这又这样我们在handleDelivery中的应答才能生效
         */
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
