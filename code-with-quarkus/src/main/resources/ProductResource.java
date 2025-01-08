package fr.isen.projet.product_availability.resources;

import fr.isen.projet.product_availability.interfaces.models.Product;
import fr.isen.projet.product_availability.interfaces.services.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @Path("/{id}")
    public Response getOneProduct(@PathParam("id") String id_product) {
        Product product = productService.getOneProduct(id_product);
        if (product != null) {
            return Response.ok(product).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Product not found with ID: " + id_product).build();
    }

    @GET
    public Response getAllProducts() {
        List<Product> products = productService.getAllProduct();
        return Response.ok(products).build();
    }

    @POST
    public Response addProduct(Product product) {
        if (product == null || product.id_product == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid product data").build();
        }
        String id = productService.addProduct(product);
        return Response.status(Response.Status.CREATED).entity("Product created with ID: " + id).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") String id_product, Product product) {
        Product existingProduct = productService.getOneProduct(id_product);
        if (existingProduct == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found with ID: " + id_product).build();
        }
        product.id_product = id_product; // Ensure the ID is consistent
        productService.updateProduct(product);
        return Response.ok("Product updated successfully").build();
    }

    @DELETE
    @Path("/{id}")
    public Response removeProduct(@PathParam("id") String id_product) {
        Product existingProduct = productService.getOneProduct(id_product);
        if (existingProduct == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Product not found with ID: " + id_product).build();
        }
        productService.removeProduct(id_product);
        return Response.ok("Product deleted successfully").build();
    }
}
