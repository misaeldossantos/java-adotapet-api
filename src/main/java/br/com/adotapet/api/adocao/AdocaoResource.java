/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adotaAdocao.api.adocao;

import br.com.adotapet.api.adocao.Adocao;

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
@Path("adocoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class AdocaoResource {

    @PersistenceContext(unitName = "AdotaPet")
    private EntityManager entityManager;

    @GET
    @Path("{id}")
    public Adocao getAdocao(@PathParam("id") Long id) {
        return entityManager.find(Adocao.class, id);
    }

    @POST
    public Adocao addAdocao(Adocao adocao) {
        entityManager.persist(adocao);
        return adocao;
    }

    @PUT
    @Path("{id}")
    public Adocao updateAdocao(@PathParam("id") Long id, Adocao adocao) {
       adocao.setId(id);
       entityManager.merge(adocao);
       return adocao;
    }

    @DELETE
    @Path("{id}")
    public void removeAdocao(@PathParam("id") Long id) {
        entityManager.remove(getAdocao(id));
    }

    @GET
    public List<Adocao> getAdocoes() {
       return entityManager.createQuery("SELECT l FROM Adocao l", Adocao.class).getResultList();
    }
}
