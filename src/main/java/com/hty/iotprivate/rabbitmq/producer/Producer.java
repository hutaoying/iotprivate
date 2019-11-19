package com.hty.iotprivate.rabbitmq.producer;

import com.hty.iotprivate.rabbitmq.utils.ConnUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq 是3.7.7的版本
 */
public class Producer {

    /**
     * 在这里一步小心看到一到面试题，routing_key 和 binding_key 的最大长度是多少？
     * 同样也是255
     */

    // 设置queue的名称，注意这个名字的长度是有限制的      if(queue.length() > 255)  是要小于255的
    public static final String QUEUE_NAME = "simple_worker";


    public static void main(String[] args) throws Exception, TimeoutException {
        // 获取一个连接
        Connection conn = ConnUtils.getConn();
        // 创建一个channel，和exchange进行打交道的一直是这个货
        Channel channel = conn.createChannel();
        // 开启持久化
        boolean durable = true ;
        // 关闭排他，如果这个地方为true的话将会开启一个独占队列，只对首次申明他的连接可以，且在连接断开时自动删除
        boolean exclusive = false;
        /**
         *  true if we are declaring an autodelete queue (server will delete it when no longer in use)，
         *  当这个队列长时间没有使用的话将会被删除
         */
        boolean autoDelete = false;
        /**
         * 最后一个参数是 Map<String, Object> arguments 这个参数我们在这里先不做说明，后面说道rabbitmq的高级特性的时候会说道，
         * 可以提前预告一下，可以设置哪些东西：①：优先级队列；②：延迟队列……
         *
         * 这里有一个疑问：我们每次重复运行这条语句，会不会把队列给覆盖了呀，这里是不会的我们看一下相关代码
         * talk is cheap，show me the code    -- linus Torvalds
         * void recordQueue(AMQP.Queue.DeclareOk ok, RecordedQueue q) {
         *   this.recordedQueues.put(ok.getQueue(), q);
         * }
         * private final Map<String, RecordedQueue> recordedQueues = Collections.synchronizedMap(new LinkedHashMap<String, RecordedQueue>());
         * 这是一个加了锁的map，现在不用担心重复的申明队列了吧
         */
        channel.queueDeclare(QUEUE_NAME,durable,exclusive,autoDelete,null);

        /**
         * rabbitmq的可靠性的一个实现方式，消息发送到达broker的可靠性的实现
         * 生产者的应答 这里我们不讲事务模式，开启事务模式的话，性能将会降低250倍
         * http://www.rabbitmq.com/confirms.html#publisher-confirms-ordering
         *In most cases, RabbitMQ will acknowledge messages to publishers in the same order they were published
         * (this applies for messages published on a single channel). However, publisher acknowledgements
         * are emitted asynchronously and can confirm a single message or a group of messages. The exact moment
         * when a confirm is emitted depends on the delivery mode of a message (persistent vs. transient) and
         * the properties of the queue(s) the message was routed to (see above). Which is to say that different
         * messages can be considered ready for acknowledgement at different times. This means that acknowledgements
         * can arrive in a different order compared to their respective messages. Applications should not depend
         * on the order of acknowledgements when possible.
         * 上面的一段话的意思是：大多数情况下，mq将以与发布时间相同的顺序进行确认，但是这适用于单频道上发布的消息，但是
         * 不同的消息可以在不同的时刻进行确认，所以应用程序不应该尽可能的依赖预确认的顺序
         */
        channel.confirmSelect();// 将channel 置为confirm 模式
        /**
         * When in confirm mode, returns the sequence number of the next message to be published.
         * 当时confirm模式的时候，我们可以拿到发送的消息的序列号
         *  其实这个就是deliverTag
         * if (nextPublishSeqNo > 0) {
         *  // 在这里维护了一个未应答的set，维护未应答消息的状态以及生成SeqNo(deliveryTay)
         *    unconfirmedSet.add(getNextPublishSeqNo());
         *    nextPublishSeqNo++;
         * }
         * 上面也说过了，异步的confirm，响应的顺序并不一定是严格的按照消息的投递的顺序的，同时如果消息长时间没有响应，
         * 也可能是消息没有投递到，这个时候我们就可以在内存中维护一份消息id的状态表，当然这个表肯定不会太大，太大则意味着这要么你的
         * 系统的mq的吞吐量不行，要么网络延迟大，系统都这样了，多了也就不提了。
         *
         * 当然这样会造成消息的重复消费，在我的前一篇的博客中关于可靠性的分析当中，我提到了就算这边没有消息的重复投递，在C
         * 端依旧是有可能造成消息的重复的，因为存在几率C在消费了消息之后，发送ack的工程中网络中断，那么这个消息将会被重入队列
         * 在requeue为true的情况下
         */
        long nextPublishSeqNo = channel.getNextPublishSeqNo();
        System.out.println("我倒要看看你和deliverTay到底是不是同一个家伙，nextPublishSeqNo = "+nextPublishSeqNo);


        String msg = "我就一个测试消息，你想咋地";
        // 这个地方我们还没有使用到路由，下面我们会一一说明五种模式中的几种路由的使用的方法
        String exchange = "";
        // routing key 我们直接指定为队列的名字
        /**顺便提一提，这rabbitmq java客户端没有注释这是真蛋疼
         * {@link com.rabbitmq.client.AMQP.BasicProperties} 具体可以查看这个里面的设置
         */
        channel.basicPublish(exchange,QUEUE_NAME,null,msg.getBytes());


        channel.addConfirmListener(new ConfirmListener() {
            /**
             * 第一个参数是在同一个channel中是唯一的，防止重复投递
             * @param deliveryTag
             * @param multiple 如果为true则表名小于当前的deliverTag的都被确认
             * @throws IOException
             */
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("消息已经送达到broker deliverTay："+ deliveryTag );
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("这个在broken异常，或者无法投递消息时出现 deliverTay："+ deliveryTag );
            }
        });
        // 这里我们休眠5秒钟否则当channel和connection关闭后，无法接受到应答的消息
        Thread.sleep(5000);
        channel.close();
        conn.close();
    }
}
