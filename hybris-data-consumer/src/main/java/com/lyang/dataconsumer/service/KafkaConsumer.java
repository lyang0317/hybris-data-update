package com.lyang.dataconsumer.service;

import com.lyang.dataconsumer.translator.DruidSqlParserRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * FileName: KafkaConsumer
 * Author:   lujy7
 * Date:     2020/1/19 14:27
 * Description:
 */
@Component
public class KafkaConsumer {

    @Autowired
    private DruidSqlParserRouter druidSqlParserRouter;

    @KafkaListener(topics = {"loggerTopic"})
    public void receiveMessage(String message){
        System.out.println("message : " + message);
        druidSqlParserRouter.process(message);
    }

}
