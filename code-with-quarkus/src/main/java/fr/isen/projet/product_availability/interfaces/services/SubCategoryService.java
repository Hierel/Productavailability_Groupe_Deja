package fr.isen.projet.product_availability.interfaces.services;

import fr.isen.projet.product_availability.interfaces.models.Subcategory;

//begin of modifiable zone(Javadoc).......C/b54334eb-c262-473b-aaab-c4872bbd9b89

//end of modifiable zone(Javadoc).........E/b54334eb-c262-473b-aaab-c4872bbd9b89
public interface SubCategoryService {
//begin of modifiable zone(Javadoc).......C/e53262c4-f858-40ce-91c9-7cc4a33ff95e

//end of modifiable zone(Javadoc).........E/e53262c4-f858-40ce-91c9-7cc4a33ff95e
    Subcategory getOneSubcategory(final int id_subcategory);

//begin of modifiable zone(Javadoc).......C/b776f894-f27a-4c44-988b-a117a4891801

//end of modifiable zone(Javadoc).........E/b776f894-f27a-4c44-988b-a117a4891801
    Subcategory getAllSubcategory();

//begin of modifiable zone(Javadoc).......C/0216473d-fdfe-492d-bee3-e3b5a8b532a6

//end of modifiable zone(Javadoc).........E/0216473d-fdfe-492d-bee3-e3b5a8b532a6
    int addSubcategory(final Subcategory Subcategory);

//begin of modifiable zone(Javadoc).......C/54e1ebf5-53d5-4645-8b11-09aa65290aa1

//end of modifiable zone(Javadoc).........E/54e1ebf5-53d5-4645-8b11-09aa65290aa1
    void updateSubcategory(final Subcategory subcategory);

//begin of modifiable zone(Javadoc).......C/df5ae65e-05d4-4127-b8f7-d7a7b03cbed8

//end of modifiable zone(Javadoc).........E/df5ae65e-05d4-4127-b8f7-d7a7b03cbed8
    void removeSubcategory(final int id_subcategory);

}
