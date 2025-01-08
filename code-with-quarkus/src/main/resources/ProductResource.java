
package fr.isen.projet.product_availability.resource;

import fr.isen.projet.product_availability.entity.Product;
import fr.isen.projet.product_availability.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }

    @GET
    @Path("/{id}")
    public Product getProductById(@PathParam("id") int id) {
        return productService.getOneProduct(id);
    }

    @POST
    public Response addProduct(Product product) {
        int result = productService.addProduct(product);
        return Response.status(Response.Status.CREATED).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") int id, Product product) {
        product.setIdProduct(id);
        productService.updateProduct(product);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeProduct(@PathParam("id") int id) {
        productService.removeProduct(id);
        return Response.noContent().build();
    }
}
