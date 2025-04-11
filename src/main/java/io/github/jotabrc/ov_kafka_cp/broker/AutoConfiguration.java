package io.github.jotabrc.ov_kafka_cp.broker;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BrokerConfig.class)
public class AutoConfiguration {
}
