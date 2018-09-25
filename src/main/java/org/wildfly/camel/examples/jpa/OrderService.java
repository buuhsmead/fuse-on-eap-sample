package org.wildfly.camel.examples.jpa;


import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class OrderService {

    private final AtomicInteger counter = new AtomicInteger();
    private final Random amount = new Random();

    public Persoon generateOrder() {
        Persoon order = new Persoon();
//        order.setItem(counter.incrementAndGet() % 2 == 0 ? "Camel" : "ActiveMQ");
//        order.setAmount(amount.nextInt(10) + 1);
//        order.setDescription(counter.get() % 2 == 0 ? "Camel in Action" : "ActiveMQ in Action");
        order.setNaam("Thomas");
        return order;
    }
}
