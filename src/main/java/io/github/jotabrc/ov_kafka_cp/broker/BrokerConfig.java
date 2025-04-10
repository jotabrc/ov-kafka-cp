package io.github.jotabrc.ov_kafka_cp.broker;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "broker")
@PropertySource("classpath:broker.properties")
public class BrokerConfig {

    private String bootstrapServers;
    private String groupId;
    private String keyDeserializer;
    private String valueDeserializer;
    private String keySerializer;
    private String valueSerializer;
}
