package com.buymore.transaction.api.adapters.client;

import com.buymore.transaction.api.core.exceptions.HttpRestClientExceptionHandler;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("v2")
@RegisterRestClient
@RegisterProvider(HttpRestClientExceptionHandler.class)
public interface BrandAuthorizationClient {

    @GET
    @Path("authorize")
    public BrandResponse getAuthorization();


}
