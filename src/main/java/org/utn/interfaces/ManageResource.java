package org.utn.interfaces;

import org.utn.recursos.Article;
import org.utn.recursos.Book;
import org.utn.recursos.GeneralAcademicResources;
import org.utn.recursos.ResearchWork;

import java.util.List;

public interface ManageResource {

    void createResource( GeneralAcademicResources generalAcademicResourcesList );

    void filter(GeneralAcademicResources generalAcademicResourcesList);
    Book getBookByEditorial(GeneralAcademicResources generalAcademicResourcesList, String editorial);
    Article getArticleByMagazine(GeneralAcademicResources generalAcademicResourcesList, String magazine);
    ResearchWork getResearchWorkByResearchLine(GeneralAcademicResources generalAcademicResourcesList, String line);

    void orderResource(GeneralAcademicResources generalAcademicResourcesList );

    void generateReport(GeneralAcademicResources generalAcademicResources);

}
