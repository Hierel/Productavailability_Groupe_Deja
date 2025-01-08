package fr.isen.projet.product_availability.resources;

import fr.isen.projet.product_availability.interfaces.models.Subcategory;
import fr.isen.projet.product_availability.interfaces.services.SubCategoryService;
import fr.isen.projet.product_availability.services.SubCategoryServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subcategories")
public class SubcategoryResource {

    private final SubCategoryService subcategoryService = new SubCategoryServiceImpl();

    // Récupérer toutes les sous-catégories
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSubcategories() {
        try {
            List<Subcategory> subcategories = subcategoryService.getAllSubcategory();
            if (subcategories.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No subcategories found").build();
            }
            return Response.ok(subcategories).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving subcategories").build();
        }
    }

    // Récupérer une sous-catégorie par ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubcategoryById(@PathParam("id") String id) {
        try {
            Subcategory subcategory = subcategoryService.getOneSubcategory(id);
            if (subcategory == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Subcategory not found").build();
            }
            return Response.ok(subcategory).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving subcategory").build();
        }
    }

    // Créer une nouvelle sous-catégorie
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSubcategory(Subcategory subcategory) {
        try {
            String id = subcategoryService.addSubcategory(subcategory);
            if (id != null) {
                return Response.status(Response.Status.CREATED).entity("Subcategory created with ID: " + id).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("Failed to create subcategory").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating subcategory").build();
        }
    }

    // Mettre à jour une sous-catégorie
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSubcategory(@PathParam("id") String id, Subcategory subcategory) {
        try {
            subcategory.id_subcategory = id; // Assigner l'ID à l'objet subcategory pour qu'il soit mis à jour
            subcategoryService.updateSubcategory(subcategory);
            return Response.ok("Subcategory updated successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating subcategory").build();
        }
    }

    // Supprimer une sous-catégorie
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSubcategory(@PathParam("id") String id) {
        try {
            subcategoryService.removeSubcategory(id);
            return Response.status(Response.Status.NO_CONTENT).build(); // HTTP 204 - No Content
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting subcategory").build();
        }
    }
}
