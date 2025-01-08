package fr.isen.projet.product_availability.interfaces.services;

import java.util.List;
import fr.isen.projet.product_availability.interfaces.models.Product;

//begin of modifiable zone(Javadoc).......C/f9bd319e-84f6-403d-ac02-d6f47462d5e2

//end of modifiable zone(Javadoc).........E/f9bd319e-84f6-403d-ac02-d6f47462d5e2
public interface ProductService {
//begin of modifiable zone(Javadoc).......C/f7de80da-cf6a-4bf2-92d1-52de2e5d18fe

//end of modifiable zone(Javadoc).........E/f7de80da-cf6a-4bf2-92d1-52de2e5d18fe
    Product getOneProduct(final int id_product);

//begin of modifiable zone(Javadoc).......C/f9cf77da-da6e-4d18-a9c3-4d9037e526fa

//end of modifiable zone(Javadoc).........E/f9cf77da-da6e-4d18-a9c3-4d9037e526fa
    List<Product> getAllProduct();

//begin of modifiable zone(Javadoc).......C/ac626dab-81c7-413d-bf51-13c6bf9eb454

//end of modifiable zone(Javadoc).........E/ac626dab-81c7-413d-bf51-13c6bf9eb454
    void updateProduct(final Product product);

//begin of modifiable zone(Javadoc).......C/47cc45b9-5858-4eed-b791-fd60d6d1bc9f

//end of modifiable zone(Javadoc).........E/47cc45b9-5858-4eed-b791-fd60d6d1bc9f
    void removeProduct(final int id_product);

//begin of modifiable zone(Javadoc).......C/30c609db-a93a-49da-9c2a-afc2f3b4666a

//end of modifiable zone(Javadoc).........E/30c609db-a93a-49da-9c2a-afc2f3b4666a
    int addProduct(final Product product);

}
