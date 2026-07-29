package auth;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @ConfigProperty(name = "claude.auth.token")
    String expectedToken;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString("Authorization");
        if (authHeader == null || !authHeader.equals("Bearer " + expectedToken)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Unauthorized: Invalid or missing token!")
                            .build()
            );
        }
    }
}
