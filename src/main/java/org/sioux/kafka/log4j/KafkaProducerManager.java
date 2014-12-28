package org.sioux.kafka.log4j;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by suzhiyuan on 14/12/28.
 */
public class KafkaProducerManager extends Thread{

   public volatile boolean stop;

   private  Producer<Integer, String> producer;

   private final Properties props = new Properties();

   public String topic ="test";

   public KafkaProducerManager(){
       props.put("serializer.class", "kafka.serializer.StringEncoder");
       props.put("metadata.broker.list", "localhost:9093,localhost:9092");
       producer =new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));


    }

    public void  run(){
        int messageNo = 1;
        while(stop)
        {
            String messageStr = new String("Message_" + messageNo);
            try {

                 producer.send(new KeyedMessage<Integer, String>(topic, messageStr));
            }catch (Exception e){
                System.out.print("send err"+e.getMessage());

            }
            messageNo++;

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

  }

}
