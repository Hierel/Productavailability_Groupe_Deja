package fr.isen.projet.product_availability.resource;

import fr.isen.projet.product_availability.interfaces.models.Category;
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

    /**
     * Récupère toutes les catégories non marquées comme supprimées (bDelete = false).
     *
     * @return Liste des catégories actives.
     */
    @GET
    public List<Category> getAllCategories() {
        return categoryService.getAllCategory();
    }

    /**
     * Récupère une catégorie par son ID.
     *
     * @param id ID de la catégorie à récupérer.
     * @return La catégorie correspondante ou une réponse 404 si elle n'existe pas.
     */
    @GET
    @Path("/{id}")
    public Response getCategoryById(@PathParam("id") String id) {
        Category category = categoryService.getOneCategory(id);
        if (category == null || category.bDelete) {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }
        return Response.ok(category).build();
    }

    /**
     * Ajoute une nouvelle catégorie.
     *
     * @param category Objet catégorie à ajouter.
     * @return Réponse avec l'ID de la nouvelle catégorie créée.
     */
    @POST
    public Response addCategory(Category category) {
        String result = categoryService.addCategory(category);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    /**
     * Met à jour une catégorie existante.
     *
     * @param id       ID de la catégorie à mettre à jour.
     * @param category Objet contenant les nouvelles données.
     * @return Réponse 200 si la mise à jour est réussie, ou 404 si la catégorie n'existe pas.
     */
    @PUT
    @Path("/{id}")
    public Response updateCategory(@PathParam("id") String id, Category category) {
        Category existingCategory = categoryService.getOneCategory(id);
        if (existingCategory == null || existingCategory.bDelete) {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }
        category.id_category = id;
        categoryService.updateCategory(category);
        return Response.ok("Category updated successfully").build();
    }

    /**
     * Supprime une catégorie en la marquant comme supprimée (bDelete = true).
     *
     * @param id ID de la catégorie à supprimer.
     * @return Réponse 204 si la suppression est réussie, ou 404 si la catégorie n'existe pas.
     */
    @DELETE
    @Path("/{id}")
    public Response removeCategory(@PathParam("id") String id) {
        Category category = categoryService.getOneCategory(id);
        if (category == null || category.bDelete) {
            return Response.status(Response.Status.NOT_FOUND).entity("Category not found").build();
        }
        categoryService.removeCategory(id);
        return Response.noContent().build();
    }
}
