package es.deusto.BSPQ20_E2.Netflix.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import es.deusto.BSPQ20_E2.Netflix.client.gui.Login;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 * Main class.
 * @author Annette
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    @SuppressWarnings("unchecked")
    public static final String BASE_URI = "http://localhost:10072/myapp/";
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in es.deusto.spq package
        final ResourceConfig rc = new ResourceConfig().packages("es.deusto.spq");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
		final HttpServer server = startServer();
		Login l = new Login();
		l.setVisible(true);
		LOGGER.info(String.format(
				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
				BASE_URI));
		System.in.read();
		server.stop();

  
    }
}

