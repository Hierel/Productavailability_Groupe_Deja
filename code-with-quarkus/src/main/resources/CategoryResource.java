package fr.isen.projet.product_availability.resource;

import fr.isen.projet.product_availability.entity.Category;
import fr.isen.projet.product_availability.service.CategoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject
    CategoryService categoryService;

    @GET
    public List<Category> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @GET
    @Path("/{id}")
    public Category getCategoryById(@PathParam("id") int id) {
        return categoryService.getOneCategory(id);
    }

    @POST
    public Response addCategory(Category category) {
        int result = categoryService.addCategory(category);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCategory(@PathParam("id") int id, Category category) {
        category.setIdCategory(id);
        categoryService.updateCategory(category);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeCategory(@PathParam("id") int id) {
        categoryService.removeCategory(id);
        return Response.noContent().build();
    }
}