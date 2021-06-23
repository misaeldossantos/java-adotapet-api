/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adotapet.api.vacina;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author aula
 */
@Path("vacinas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class VacinaResource {

    @PersistenceContext(unitName = "AdotaPet")
    private EntityManager entityManager;

    @GET
    @Path("{id}")
    public Vacina getVacina(@PathParam("id") Long id) {
        return entityManager.find(Vacina.class, id);
    }

    @POST
    public Vacina addVacina(Vacina vacina) {
        entityManager.persist(vacina);
        return vacina;
    }

    @PUT
    @Path("{id}")
    public Vacina updateVacina(@PathParam("id") Long id, Vacina vacina) {
       vacina.setId(id);
       entityManager.merge(vacina);
       return vacina;
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
}
