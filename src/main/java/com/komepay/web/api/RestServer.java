package com.komepay.web.api;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;

import java.util.function.Consumer;

/*
 * @author <a href="mailto:julien@julienviet.com">Julien Viet</a>
 */
public class RestServer extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        Consumer<Vertx> runner = vertx -> {
            try {

                    vertx.deployVerticle(RestServer.class.getName());

            } catch (Throwable t) {
                t.printStackTrace();
            }
        };
        try {
            Vertx vertx = Vertx.vertx(options);
            runner.accept(vertx);
        } catch (Throwable t) {
            t.printStackTrace();
        }



    }

    @Override
    public void start() throws Exception {

        // Build the Jax-RS hello world deployment
        VertxResteasyDeployment deployment = new VertxResteasyDeployment();
        deployment.start();
        deployment.getRegistry().addPerInstanceResource(APIService.class);

        // Start the front end server using the Jax-RS controller
        vertx.createHttpServer()
                .requestHandler(new VertxRequestHandler(vertx, deployment))
                .listen(8080, ar -> {
                    System.out.println("Server started on port "+ ar.result().actualPort());
                });

    }
}

