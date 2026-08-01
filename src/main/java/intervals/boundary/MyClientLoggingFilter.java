package intervals.boundary;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.io.IOException;

@Provider
public class MyClientLoggingFilter implements ClientRequestFilter {

    private static final Logger log = Logger.getLogger(MyClientLoggingFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        log.debugf("Outgoing REST Call: %s %s %s",
                   requestContext.getMethod(),
                   requestContext.getUri(),
                   requestContext.getHeaders().getFirst("Authorization").toString()
        );
    }
}
