package com.buymore.transaction.api.core.security;

import com.buymore.transaction.api.adapters.client.ClientApiRestClient;
import com.buymore.transaction.api.core.exceptions.BuymoreTransactionApiException;
import com.buymore.transaction.api.core.utils.json.ObjectMapperFactory;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.util.KeyUtils;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PublicKey;

@Provider
public class AuthSecurityFilter implements ContainerRequestFilter {

    @ConfigProperty(name = "JWT_BUYMORE_PUBLIC_KEY")
    Instance<String> JWT_BUYMORE_PUBLIC_KEY;

    JsonWebToken jwtToken;

    @Inject
    JWTValidation jwtValidation;

    @Inject
    JWTParser jwtParser;

    @Context
    HttpServerRequest request;

    @Inject
    AuthSecurityContext securityContext;

    @Inject
    ClientApiRestClient clientApiRestClient;

    @Inject
    Logger LOG;

    @Inject
    ObjectMapperFactory mapperFactory;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        verifyToken(requestContext);
        buildCustomSecurityContext();
    }

    private void verifyToken(ContainerRequestContext requestContext){
        jwtToken = jwtValidation.verifyToken(requestContext, jwtParser, JWT_BUYMORE_PUBLIC_KEY.get());
    }

    PublicKey generatePublicKey(String publicKey){
        try {
            return KeyUtils.decodePublicKey(publicKey);
        } catch (GeneralSecurityException e) {
            throw new BuymoreTransactionApiException("Invalid publicKey", e);
        }
    }
    private void buildCustomSecurityContext(){
        securityContext.initialize();
        securityContext.getInstance().get().jsonWebToken = jwtToken;
        securityContext.getInstance().get().ipAdress = request.remoteAddress().toString();
        securityContext.getInstance().get().clientId = jwtToken.getClaim("azp");
    }
}
