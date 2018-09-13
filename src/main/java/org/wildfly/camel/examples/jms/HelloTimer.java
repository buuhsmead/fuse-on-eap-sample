package org.wildfly.camel.examples.jms;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;


@ContextName("camel-klok")
@Component
public class HelloTimer extends RouteBuilder {


    @BeanInject
    SpringDing springDing;

    @Override
    public void configure() throws Exception {

        from("timer://foo?fixedRate=true&period=10000").routeId("camel-klok-timer")
                .log("Hallo from Timer " + springDing.getHallo())
        ;

    }
}
