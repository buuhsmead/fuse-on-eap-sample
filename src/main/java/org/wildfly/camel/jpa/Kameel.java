package org.wildfly.camel.jpa;

import com.fasterxml.jackson.core.JsonParseException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;

// https://access.redhat.com/documentation/en-us/red_hat_jboss_fuse/6.2/html/apache_camel_development_guide/restservices
// https://dzone.com/articles/restful-apis-with-camel-and-wildfly-swarm


@ContextName("camel-klok")
@Component
public class Kameel extends RouteBuilder  {

    @Override
    public void configure() {


        /**
         * Configure an error handler to trap instances where the data posted to
         * the REST API is invalid
         */
        // just example : using plain/text in example
        onException(JsonParseException.class)
                .handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.TEXT_PLAIN))
                .setBody().constant("Invalid json data");



        restConfiguration()
                .contextPath("/kameel")
                .component("undertow")
                .bindingMode(RestBindingMode.off)
                .enableCORS(true)
                .apiContextPath("/api-docs")
                .apiProperty("api.title", "WildFly Swarm Camel REST API")
                .apiProperty("api.version", "1.2.3")
                .apiProperty("base.path", "/kameel")
                .apiProperty("cors", "true")
        ;


        rest("/bla")
                .description("An example of the Camel REST DSL")
                .produces(MediaType.TEXT_PLAIN)
                .get("/hello")
                    .description("A simple get HELLO endpoint with the REST DSL")
                    .to("direct:hello")
                .get("/bye")
                    .description("A simple get BYE endpoint with the REST DSL")
                    .to("direct:bye");


        // endpoint takes into account the contextPath of restConfiguration
        // endpoint is http://localhost:8080/kameel/hello
        from("rest:get:hello").routeId("camel-klok-rest")
                .transform().constant("Hi from Kameel!");




        from("direct:hello")
                .transform().constant("Hello World");
        from("direct:bye")
                .transform().constant("Bye World");

    }
}
