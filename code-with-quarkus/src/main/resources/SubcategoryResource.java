
package fr.isen.projet.product_availability.resource;

import fr.isen.projet.product_availability.entity.SubCategory;
import fr.isen.projet.product_availability.service.SubCategoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/subcategories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SubCategoryResource {

    @Inject
    SubCategoryService subCategoryService;

    @GET
    public List<SubCategory> getAllSubCategories() {
        return subCategoryService.getAllSubcategory();
    }

    @GET
    @Path("/{id}")
    public SubCategory getSubCategoryById(@PathParam("id") int id) {
        return subCategoryService.getOneSubcategory(id);
    }

    @POST
    public Response addSubCategory(SubCategory subCategory) {
        int result = subCategoryService.addSubcategory(subCategory);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateSubCategory(@PathParam("id") int id, SubCategory subCategory) {
        subCategory.setIdSubcategory(id);
        subCategoryService.updateSubcategory(subCategory);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeSubCategory(@PathParam("id") int id) {
        subCategoryService.removeSubcategory(id);
        return Response.noContent().build();
    }
}
