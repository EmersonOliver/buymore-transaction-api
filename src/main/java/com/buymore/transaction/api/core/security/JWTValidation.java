package com.buymore.transaction.api.core.security;

import com.buymore.transaction.api.core.exceptions.BuymoreTransactionApiException;
import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.algorithm.SignatureAlgorithm;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.auth.principal.ParseException;
import io.smallrye.jwt.util.KeyUtils;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.HttpHeaders;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequestScoped
public class JWTValidation {


    private List<UnauthorizedException> unauthorizedExceptions = new ArrayList<>();

    public JsonWebToken verifyToken(ContainerRequestContext requestContext, JWTParser jwtParser, String... publicKeys){
        return tokenVerify(jwtParser, getRawToken(requestContext), publicKeys);
    }
    JsonWebToken tokenVerify(JWTParser jwtParser, String token, String... publicKey){
        return tokenVerifyAll(jwtParser, token, 0, publicKey);
    }

    JsonWebToken tokenVerifyAll(JWTParser jwtParser, String token, int pkIndex, String... publicKey){
        try {
            return jwtParser.verify(token, generatePublicKey(publicKey[pkIndex]));
        } catch (ParseException e) {
            unauthorizedExceptions.add(new UnauthorizedException("Invalid Token Realms " + (pkIndex == 0 ? "INTERNET" : "INTRANET"), e));
            if(++pkIndex < publicKey.length){
                return tokenVerifyAll(jwtParser, token, pkIndex++, publicKey);
            }
            unauthorizedExceptions.get(0).printStackTrace();
            throw unauthorizedExceptions.get(1);
        }
    }

    PublicKey generatePublicKey(String publicKey){
        try {
            return KeyUtils.decodePublicKey(publicKey);
        } catch (GeneralSecurityException e) {
            throw new BuymoreTransactionApiException("Invalid PublicKey", e);
        }
    }

    static final String AUTH_TYPE = "Bearer ";

    String getRawToken(ContainerRequestContext requestContext){
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(token == null){
            throw new UnauthorizedException("Authorization Token not found");
        }
        return token.replace(AUTH_TYPE, "");
    }
}
