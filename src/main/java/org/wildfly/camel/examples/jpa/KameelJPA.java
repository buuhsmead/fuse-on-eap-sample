package org.wildfly.camel.examples.jpa;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.springframework.stereotype.Component;


@ContextName("camel-klok")
@Component
public class KameelJPA extends RouteBuilder {



    @Override
    public void configure() throws Exception {

        // Route to generate orders and persist them to the database
        from("timer:new-order?delay=0s&period=10s").routeId("camel-klok-timer-jpa-producer")
                .bean("orderService", "generateOrder")
                .toF("jpa:%s", Persoon.class.getName())
                .log("Inserted new Persoon ${body.id}");


        from("jpa:org.wildfly.camel.examples.jpa.Persoon").routeId("camel-klok-jpa-consumer")
                .log("JPA inhoud ${body.id}.");
    }
}
