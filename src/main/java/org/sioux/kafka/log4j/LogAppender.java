package org.sioux.kafka.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by suzhiyuan on 14/12/28.
 */
public class LogAppender extends AppenderSkeleton {

    private  volatile   boolean isInital = false;

    private  KafkaProducerManager producerManager ;



    @Override
    protected void append(LoggingEvent loggingEvent) {

    String messesage  = this.layout.format(loggingEvent);

    }

    @Override
    public void close() {
        if(producerManager!=null){
            producerManager.stop =true;
        }
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    static public void main(String args[]){

        KafkaProducerManager kafa = new KafkaProducerManager();

        kafa.start();

    }
}
