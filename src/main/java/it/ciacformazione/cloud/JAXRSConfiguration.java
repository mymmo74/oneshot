package it.ciacformazione.cloud;

import java.util.Set;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
// ci sono tre annotazione in più
// dice che c'è una sona istanza per tutta l'applicazione
@ApplicationScoped
// MP-JWT configura MicroProfile con JSON Web Token
@LoginConfig(authMethod = "MP-JWT", realmName = "TCK-MP-JWT")
@ApplicationPath("resources")
// Dichiarazione dei gruppi
@DeclareRoles({"users"})
public class JAXRSConfiguration extends Application {
    
    // Riscriviamo la classe per il multipart feature
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(MultiPartFeature.class);
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        // bisogna indicare tutti i servizi REST della nostra applicazione
        resources.add(it.ciacformazione.cloud.services.DocumentiResource.class);
        resources.add(it.ciacformazione.cloud.services.PingResource.class);
        resources.add(it.ciacformazione.cloud.services.SecurityResource.class);
        resources.add(it.ciacformazione.cloud.services.UtentiResources.class);
    }

    
}