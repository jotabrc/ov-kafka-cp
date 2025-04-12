package io.github.jotabrc.ov_kafka_cp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Deprecated
@Getter
@AllArgsConstructor
public enum Topic {

    INVENTORY_ADD_ITEM("inventory-add-item"),
    INVENTORY_UPDATE_NAME("inventory-update-name"),
    INVENTORY_ADD_ORDER("inventory-add-order"),
    INVENTORY_UPDATE_STOCK("inventory-update-item-stock"),
    INVENTORY_CANCEL_ORDER("inventory-cancel-order");

    private final String topic;
}
