/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ciacformazione.cloud.services;

import it.ciacformazione.cloud.business.UtenteStore;
import it.ciacformazione.cloud.entity.Utente;
import java.net.URI;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author tss
 */
@Path("/utenti")
public class UtentiResources {
    @Inject
    UtenteStore store;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Utente utente, @Context UriInfo uriInfo) {
        Utente saved = store.save(utente);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path("/" + saved.getId())
                .build();       
        return Response.ok(uri).build();
    }
    
    @GET
    @RolesAllowed({"users"})
    public List<Utente> findAll() {
        return store.all();
    }
    
    @GET
    @Path("{id}")
    public Utente find(@PathParam("id") int id) {
        return store.find(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void update(@PathParam("id") int id, Utente utente) {
        utente.setId(id);
        store.save(utente);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        store.remove(id);
    }
}
