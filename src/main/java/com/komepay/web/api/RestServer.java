package com.komepay.web.api;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;


public class RestServer extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new VertxRestAPI());
    }


}


