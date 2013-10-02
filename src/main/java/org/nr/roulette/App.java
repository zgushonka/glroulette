package org.nr.roulette;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import casino.Croupie;

/**
 * Utility class which can create {@link org.glassfish.jersey.server.ApplicationHandler} instance and provides support
 * for running this sample from command line.
 *
 */
public class App {

    Croupie cr = Croupie.newInstance();
    
    
    private static String BASE_URI = "http://localhost:portnumber/casino/";

    public static void main(String[] args) {
        
        // args[0] application port
        // args[1] mode: "true" -> isManual, anyOther or missing -> not manual
        
        boolean isManual = false;
        
        try {
            if (args.length > 0)
            {
                BASE_URI = BASE_URI.replaceFirst("portnumber", args[0]);
            } else
            {
                BASE_URI = BASE_URI.replaceFirst("portnumber", "9999");
            }            
            
            if (args.length > 1)
            {
                isManual = Boolean.valueOf(args[1]);
            }
            
            System.out.println("isManual" + isManual);
            
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), createApp());

            
            
            // Concurrent timer implementation
            final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    if (!server.isStarted())
                    {
                        // If the HTTP server is not running kill the Executor thread also
                        service.shutdown();
                    }
                    Croupie.newInstance().performGameMove();
                    
                }
            }, 0, 5, TimeUnit.SECONDS);
            
            
            System.out.println("Listening URI is " + BASE_URI);
            System.out.println("Rules can be found on " + BASE_URI + "rules");
            System.out.println("Game stats can be obtained from " + BASE_URI + "stats");
            System.out.println("The WADL file can be found on " + BASE_URI + "application.wadl");
            System.out.println(String.format("Application started.%nHit enter to stop it..."));
            System.in.read();
            server.stop();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ResourceConfig createApp() {
        return new ResourceConfig().
                register(new JettisonFeature()).
                packages("org.nr.roulette");
    }
}
