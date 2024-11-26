package org.utn.gestores.impl;

import org.utn.interfaces.Classifiable;

import java.util.ArrayList;
import java.util.List;

public class ClassificationOfResourcesImpl implements Classifiable {
    private final List<String> classificationList;
    public ClassificationOfResourcesImpl() {
        classificationList = new ArrayList<>();
        classificationList.add("Magazine");
        classificationList.add("Article");
        classificationList.add("Book");
    }
    @Override
    public List<String> getCategoriesClassification() {
        return classificationList;
    }

    @Override
    public void setCategories(String category) {
        classificationList.add(category);
    }
}
