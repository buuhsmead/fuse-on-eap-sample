package org.wildfly.camel.jpa;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.springframework.stereotype.Component;


@ContextName("camel-klok")
@Component
public class HelloTimer extends RouteBuilder {


    @BeanInject
    SpringDing springDing;

    @Override
    public void configure() {

        from("timer://foo?fixedRate=true&period=10000").routeId("camel-klok-timer")
                .log("Hallo from Timer " + springDing.getHallo())
        ;

    }
}
