/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.adotapet.api.pets;

import br.com.adotapet.api.core.utils.ResponseMessage;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author aula
 */
@Path("pets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class PetResource {

    @PersistenceContext(unitName = "AdotaPetPU")
    private EntityManager entityManager;

    @GET
    @Path("{id}")
    public Pet getPet(@PathParam("id") Long id) {
        return entityManager.find(Pet.class, id);
    }

    @POST
    public Response addPet(Pet pet) {
        Response response = validaPet(pet);
        if(response != null) {
            return response;
        }
        entityManager.persist(pet);
        return Response.ok(pet).build();
    }
    
    private Response validaPet(Pet pet) {
        // Regra de negócio 1: para um pet ser cadastrado precisa ter pelo menos 1 ano de vida
        LocalDate comparar = LocalDate.now().minusYears(1);
        if(pet.getDataNascimento().isAfter(comparar) && !pet.getDataNascimento().isEqual(comparar)) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ResponseMessage.build("Pet deve ter pelo menos 1 ano de vida", ResponseMessage.Type.ERROR))
                    .build();
        }
        
        // Regra de negócio 2: para um pet ser cadastrado deve ter sido vacina em alguma vacina
        if(pet.getVacinasAplicadas().isEmpty()) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ResponseMessage.build("O pet deve ter pelo menos 1 vacina para ser acolhido por nós", ResponseMessage.Type.ERROR))
                    .build();
        }
        
        return null;        
    }

    @PUT
    @Path("{id}")
    public Response updatePet(@PathParam("id") Long id, Pet pet) {
       Response response = validaPet(pet);
       if(response != null) {
           return response;
       }
       pet.setId(id);
       entityManager.merge(pet);
       return Response.ok(pet).build();
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
