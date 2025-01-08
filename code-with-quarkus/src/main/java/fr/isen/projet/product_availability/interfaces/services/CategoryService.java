package fr.isen.projet.product_availability.interfaces.services;

import java.util.List;
import fr.isen.projet.product_availability.interfaces.models.Category;

//begin of modifiable zone(Javadoc).......C/26fab067-521a-48e5-8eb3-4cb3c6e3a497

//end of modifiable zone(Javadoc).........E/26fab067-521a-48e5-8eb3-4cb3c6e3a497
public interface CategoryService {
//begin of modifiable zone(Javadoc).......C/207f2442-033b-48a5-a04e-89d1df01a545

//end of modifiable zone(Javadoc).........E/207f2442-033b-48a5-a04e-89d1df01a545
    Category getOneCategory(final int id_category);

//begin of modifiable zone(Javadoc).......C/a70a754c-bc4b-46a8-aa31-b620f419b447

//end of modifiable zone(Javadoc).........E/a70a754c-bc4b-46a8-aa31-b620f419b447
    List<Category> getAllCategory();

//begin of modifiable zone(Javadoc).......C/60c290af-dfc7-46bb-a279-30dfe90c8af8

//end of modifiable zone(Javadoc).........E/60c290af-dfc7-46bb-a279-30dfe90c8af8
    int addCategory(final Category category);

//begin of modifiable zone(Javadoc).......C/bf651d1d-c7a5-4d10-8981-cc7618119ec0

//end of modifiable zone(Javadoc).........E/bf651d1d-c7a5-4d10-8981-cc7618119ec0
    void updateCategory(final Category category);

//begin of modifiable zone(Javadoc).......C/8dd0fe16-6d50-49f5-8d22-e4ffcca4e168

//end of modifiable zone(Javadoc).........E/8dd0fe16-6d50-49f5-8d22-e4ffcca4e168
    void removeCategory(final int id_category);

}
