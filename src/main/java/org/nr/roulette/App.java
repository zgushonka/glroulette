package org.nr.roulette;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Utility class which can create {@link org.glassfish.jersey.server.ApplicationHandler} instance and provides support
 * for running this sample from command line.
 *
 */
public class App {

    
    
    private static String BASE_URI = "http://localhost:portnumber/casino/";

    public static void main(String[] args) {
        try {
            if (args.length > 0)
            {
                BASE_URI = BASE_URI.replaceFirst("portnumber", args[0]);
            } else
            {
                BASE_URI = BASE_URI.replaceFirst("portnumber", "9999");
            }            
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), createApp());
            System.out.println("Listening URI is " + BASE_URI);
            System.out.println("Rules can be found on " + BASE_URI + "rules");
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
