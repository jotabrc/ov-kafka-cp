package io.github.jotabrc.ov_kafka_cp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Topic {

    INVENTORY_ADD_ITEM("inventory-add-item");

    private final String topic;
}
