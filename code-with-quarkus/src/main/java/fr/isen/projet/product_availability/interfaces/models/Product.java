package fr.isen.projet.product_availability.interfaces.models;

import java.util.Date;
import fr.isen.projet.product_availability.interfaces.models.enums.STATE_PRODUCT;

//begin of modifiable zone(Javadoc).......C/36e8059a-f56d-47b1-af9b-38a5d296da5e

//end of modifiable zone(Javadoc).........E/36e8059a-f56d-47b1-af9b-38a5d296da5e
public class Product {
//begin of modifiable zone(Javadoc).......C/b86ca1a9-647f-4209-8cb1-5da7440b9848

//end of modifiable zone(Javadoc).........E/b86ca1a9-647f-4209-8cb1-5da7440b9848
    public int id_product;

//begin of modifiable zone(Javadoc).......C/d9db6fbe-8a04-4b8a-b2eb-85ddaf659677

//end of modifiable zone(Javadoc).........E/d9db6fbe-8a04-4b8a-b2eb-85ddaf659677
    public String name;

//begin of modifiable zone(Javadoc).......C/8b7eb592-cc34-4d47-aae6-cb37eeace483

//end of modifiable zone(Javadoc).........E/8b7eb592-cc34-4d47-aae6-cb37eeace483
    public float unit_price;

//begin of modifiable zone(Javadoc).......C/eaa2dfda-bd98-4aac-9a21-b30c3e343167

//end of modifiable zone(Javadoc).........E/eaa2dfda-bd98-4aac-9a21-b30c3e343167
    public STATE_PRODUCT status;

//begin of modifiable zone(Javadoc).......C/b2726e0f-845f-4eb7-b2f9-798ef89ece57

//end of modifiable zone(Javadoc).........E/b2726e0f-845f-4eb7-b2f9-798ef89ece57
    public Subcategory subcategory;

//begin of modifiable zone(Javadoc).......C/663c5156-d428-45aa-89e5-bda329554770

//end of modifiable zone(Javadoc).........E/663c5156-d428-45aa-89e5-bda329554770
    public int stock;

//begin of modifiable zone(Javadoc).......C/7f904ccc-9c83-46de-90fa-16ca40fd6665

//end of modifiable zone(Javadoc).........E/7f904ccc-9c83-46de-90fa-16ca40fd6665
    public int id_provider;

//begin of modifiable zone(Javadoc).......C/bdbe4a1c-c944-406a-988a-f608ae66d758

//end of modifiable zone(Javadoc).........E/bdbe4a1c-c944-406a-988a-f608ae66d758
    public Date date_added;

//begin of modifiable zone(Javadoc).......C/fb7bf0e0-2f50-4b3c-a373-b0c3fd970aba

//end of modifiable zone(Javadoc).........E/fb7bf0e0-2f50-4b3c-a373-b0c3fd970aba
    public Date date_creation;

//begin of modifiable zone(Javadoc).......C/884b6090-2272-4ac7-a13c-443b4cd231a8

//end of modifiable zone(Javadoc).........E/884b6090-2272-4ac7-a13c-443b4cd231a8
    public Date date_updated;

}
