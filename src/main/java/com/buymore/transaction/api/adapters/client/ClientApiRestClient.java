package com.buymore.transaction.api.adapters.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/client")
public interface ClientApiRestClient {


    @GET
    @Path("load/{clientId}")
    public ClientResponse loadClient(@PathParam("clientId") String clientId);

}
