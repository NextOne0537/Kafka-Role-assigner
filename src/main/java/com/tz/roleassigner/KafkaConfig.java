package com.tz.roleassigner;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author Timofey
 * @since 31.07.2021
 */

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public NewTopic userRoleToDecide(){
        return TopicBuilder.name("${spring.kafka.topicToListen}")
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }
    @Bean
    public NewTopic userRoleDecided(){
        return TopicBuilder.name("${spring.kafka.topicToSend")
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }



}
