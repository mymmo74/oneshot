/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.ciacformazione.cloud.services;

import it.ciacformazione.cloud.JWTManager;
import it.ciacformazione.cloud.business.UtenteStore;
import it.ciacformazione.cloud.entity.Utente;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author tss
 */
@DenyAll
@Path("/auth")
public class SecurityResource {

    @Inject
    UtenteStore store;

    @PermitAll
    @GET
    public Response login() {

        try {
            String token = JWTManager.generateJWTString("token.json");
            System.out.println("------------ generated token ----------------");
            System.out.println("------------ curl command for test ----------");
            System.out.println("curl -i -H'Authorization: Bearer " + token 
                    + "' http://localhost:8080/cloud/resources/utenti");
        } catch (Exception ex) {
            Logger.getLogger(SecurityResource.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return Response.ok().build();
    }

    
    @PermitAll
    @POST
    public Response login(@FormParam("usr") String usr, 
            @FormParam("pwd") String pwd) {
        Optional<Utente> p = store.login(usr, pwd);
        p.ifPresent(a -> System.out.println(a.getNome()));
        JsonObject token = Json.createObjectBuilder().add("token",
                JWTManager.generateJWTString("token.json", 
                        p.get().getUser())).build();
        return p.isPresent() ? Response.ok().entity(token).build()
                : Response.status(Response.Status.UNAUTHORIZED).build();
    }
}