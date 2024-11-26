package org.utn.interfaces;

import org.utn.recursos.Article;
import org.utn.recursos.Book;
import org.utn.recursos.GeneralAcademicResources;
import org.utn.recursos.ResearchWork;

import java.util.List;

public interface Classifiable {

    List<String> getCategoriesClassification();
    void setCategories(String category);

    interface ResourceManager {

        void createResource( GeneralAcademicResources generalAcademicResourcesList );

        void filter(GeneralAcademicResources generalAcademicResourcesList);
        List<Article> getAllArticles(GeneralAcademicResources generalAcademicResourcesList);
        List<Book> getAllBooks(GeneralAcademicResources generalAcademicResourcesList);
        List<ResearchWork> getAllResearchWork(GeneralAcademicResources generalAcademicResourcesList);
        Book getBookByEditorial(GeneralAcademicResources generalAcademicResourcesList, String editorial);
        Article getArticleByMagazine(GeneralAcademicResources generalAcademicResourcesList, String magazine);
        ResearchWork getResearchWorkByResearchLine(GeneralAcademicResources generalAcademicResourcesList, String line);


        void orderResource(GeneralAcademicResources generalAcademicResourcesList );

        void generateReport(GeneralAcademicResources generalAcademicResources);



    }
}
