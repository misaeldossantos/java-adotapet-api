/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adotapet.api.pets;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author aula
 */
@Path("pets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class PetResource {

    @PersistenceContext(unitName = "AdotaPetDS")
    private EntityManager entityManager;

    @GET
    @Path("{id}")
    public Pet getPet(@PathParam("id") Long id) {
        return entityManager.find(Pet.class, id);
    }

    @POST
    public Pet addPet(Pet pet) {
        entityManager.persist(pet);
        return pet;
    }

    @PUT
    @Path("{id}")
    public Pet updatePet(@PathParam("id") Long id, Pet pet) {
       pet.setId(id);
       entityManager.merge(pet);
       return pet;
    }

    @DELETE
    @Path("{id}")
    public void removePet(@PathParam("id") Long id) {
        entityManager.remove(getPet(id));
    }

    @GET
    public List<Pet> getPets() {
       return entityManager.createQuery("SELECT l FROM Pet l", Pet.class).getResultList();
    }
}
