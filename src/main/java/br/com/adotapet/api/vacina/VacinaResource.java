/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adotapet.api.vacina;

import br.com.adotapet.api.core.utils.ResponseMessage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 *
 * @author aula
 */
@Path("vacinas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class VacinaResource {

    @PersistenceContext(unitName = "AdotaPetPU")
    private EntityManager entityManager;

    @GET
    @Path("{id}")
    public Vacina getVacina(@PathParam("id") Long id) {
        return entityManager.find(Vacina.class, id);
    }

    @POST
    public Response addVacina(Vacina vacina) {
        Response response = validaVacina(vacina);
        if(response != null) {
            return response;
        }
        entityManager.persist(vacina);
        return Response.ok(vacina).build();
    }

    @PUT
    @Path("{id}")
    public Response updateVacina(@PathParam("id") Long id, Vacina vacina) {
       Response response = validaVacina(vacina);
       if(response != null) {
           return response;
       }
       vacina.setId(id);
       entityManager.merge(vacina);
       return Response.ok(vacina).build();
    }


    @DELETE
    @Path("{id}")
    public void removeVacina(@PathParam("id") Long id) {
        entityManager.remove(getVacina(id));
    }

    @GET
    public List<Vacina> getVacinas() {
       return entityManager.createQuery("SELECT l FROM Vacina l", Vacina.class).getResultList();
    }
    
    private Response validaVacina(Vacina vacina) {
        // Regra de negócio 3: vacina deve ter pelo menos 2 doses
        if(vacina.getQtdDoses() < 2) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ResponseMessage.build("A vacina deve ter pelo menos 2 doses", ResponseMessage.Type.ERROR))
                    .build();
        }
        
        return null;        
    }
}
