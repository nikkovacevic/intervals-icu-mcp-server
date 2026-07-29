package auth;

import io.vertx.ext.web.Router;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class AuthenticationFilter {

    @ConfigProperty(name = "claude.auth.token")
    String expectedToken;

    public void init(@Observes Router router) {
        router.route("/mcp*").order(-100).handler(rc -> {
            String authHeader = rc.request().getHeader("Authorization");
            String queryToken = rc.request().getParam("token");

            boolean validHeader = authHeader != null && authHeader.equals("Bearer " + expectedToken);
            boolean validParam = queryToken != null && queryToken.equals(expectedToken);

            if (!validHeader && !validParam) {
                rc.response()
                        .setStatusCode(401)
                        .end("Unauthorized: Missing or invalid token");
                return;
            }

            rc.next();
        });
    }
}
