package org.wildfly.camel.examples.jms;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.springframework.stereotype.Component;


@ContextName("camel-klok")
@Component
public class Kameel extends RouteBuilder  {

    @Override
    public void configure() throws Exception {
        restConfiguration().setComponent("undertow");

        from("rest:get:hello").routeId("camel-klok-rest")
                .transform().constant("Bye World");
    }
}
