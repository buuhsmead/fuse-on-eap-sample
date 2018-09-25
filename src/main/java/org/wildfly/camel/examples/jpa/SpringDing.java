package org.wildfly.camel.examples.jpa;


import org.springframework.stereotype.Component;


@Component("SpringDing")
public class SpringDing {

    public SpringDing() {
        System.err.println("ctor SpringDing : Springframework has scanned and found.");
    }

    public String getHallo() {
        return "Hallo habseflats pommetje";
    }
}
