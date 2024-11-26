package org.utn.interfaces;

import org.utn.recursos.Article;
import org.utn.recursos.Book;
import org.utn.recursos.GeneralAcademicResources;
import org.utn.recursos.ResearchWork;

import java.util.List;

public interface Classifiable {

    List<String> getCategoriesClassification();
    void setCategories(String category);

}
