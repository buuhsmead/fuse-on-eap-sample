package org.wildfly.camel.examples.jms;


import org.springframework.stereotype.Component;

import javax.inject.Named;

@Component("SpringDing")
public class SpringDing {

    public SpringDing() {
        System.err.println("ctor SpringDing");
    }

    public String getHallo() {
        return "Hallo habseflats";
    }
}
