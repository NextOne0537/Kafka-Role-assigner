package com.tz.roleassigner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Timofey
 * @since 31.07.2021
 */

@Slf4j
@Service
@KafkaListener(topics = "${spring.kafka.topicToListen}")
public class Listener implements MessageListener <ConsumerRecord<String,String>>{

    @Autowired
    private final KafkaTemplate <String,String> kafkaTemplate;

    @Value("${spring.kafka.topicToSend}")
    private String topicToSend;

    public Listener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @KafkaHandler(isDefault = true)
    public void listen(ConsumerRecord<String, String> record) {
        log.debug( "got: "+record.key());
        String decidedRole = decideRole();
        log.info(record.key()+" decided role:" +decidedRole);

        try {
            kafkaTemplate.send(topicToSend, record.key(), decidedRole);
        } catch (Exception e) {
            log.error("Couldn't send message to messaging system");
            throw new RuntimeException(e);
        }

    }
    public String decideRole (){
        return (Math.random()<0.5) ? "APPROVED" : "REJECTED";
    }
}
