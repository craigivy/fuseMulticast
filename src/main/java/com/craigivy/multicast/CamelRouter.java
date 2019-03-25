package com.craigivy.multicast;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;


/**
 * A simple Camel REST DSL route that implements the greetings service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // @formatter:off
        from("file:in")
        .multicast().parallelProcessing()
        .to("file:out1", "direct:tojson", "file:out3");

        from("direct:tojson")
        .marshal()
        .xmljson()
        .to("file:out2?fileName=sample-${date:now:yyyyMMdd}.json");
        // @formatter:on
    }

}