package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractOrderCreator {
    AbstractOrder order;
    static Logger logger = LoggerFactory.getLogger(AbstractOrder.class);

    AbstractOrderCreator(AbstractOrder order) {
        this.order = order;
    }

    abstract AbstractOrder create();

}
