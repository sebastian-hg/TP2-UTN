package org.utn.gestores.impl;

import org.utn.gestores.AdvancedResourceFiltering;
import org.utn.recursos.Article;
import org.utn.recursos.Book;
import org.utn.recursos.GeneralAcademicResources;
import org.utn.recursos.ResearchWork;
import java.util.List;
import java.util.Scanner;

public class AdvancedResourceFilteringImpl implements AdvancedResourceFiltering {

    @Override
    public void filter(GeneralAcademicResources generalAcademicResourcesList) {
        Scanner scanner = new Scanner(System.in);

        // Mostrar el menú de opciones
        System.out.println("Seleccione un criterio de filtrado:");
        System.out.println("1. Obtener todos los libros");
        System.out.println("2. Obtener todos los Articulos");
        System.out.println("3. Obtener todos los Tranajo de investigación");
        System.out.println("4. Filtrar libro por editorial");
        System.out.println("5. Filtrar articulo por revista");
        System.out.println("6. Filtrar trabajo de investigación por su línea de investigación");
        System.out.println("0. Salir");

        int opcion = scanner.nextInt();
        scanner.nextLine();


        switch (opcion) {
            case 1:
                System.out.println("Detalle de todos los libros");
                generalAcademicResourcesList.getBookList()
                        .forEach(Book::showDetails);
            case 2:
                System.out.println("Detalle de todos los Articulos");
                generalAcademicResourcesList.getArticleList()
                        .forEach(Article::showDetails);
                break;
            case 3:
                System.out.println("Detalle de todos las investigaciones");
                generalAcademicResourcesList.getResearchWorkList()
                        .forEach(ResearchWork::showDetails);
            case 4:
                System.out.println("Ingrese el nombre de la editorial:");
                String editorial = scanner.nextLine();
                Book bookByEditorial = getBookByEditorial(generalAcademicResourcesList, editorial);
                System.out.println("Elija la informacion que desea ver:");
                if (bookByEditorial != null) {
                    bookByEditorial.showDetails();
                } else {
                    System.out.println("No se encontró ningún libro con esa editorial.");
                }
                break;

            case 5:
                System.out.println("Ingrese el nombre de la revista:");
                String revista = scanner.nextLine();
                Article articleByMagazine = getArticleByMagazine(generalAcademicResourcesList, revista);
                if (articleByMagazine != null) {
                    articleByMagazine.showDetails();
                } else {
                    System.out.println("No se encontró ningún artículo con esa revista.");
                }
                break;

            case 6:
                System.out.println("Ingrese la línea de investigación:");
                String lineaInvestigacion = scanner.nextLine();
                ResearchWork researchWorkByLine = getResearchWorkByResearchLine(generalAcademicResourcesList, lineaInvestigacion);
                if (researchWorkByLine != null) {
                    researchWorkByLine.showDetails();
                } else {
                    System.out.println("No se encontró ningún trabajo con esa línea de investigación.");
                }
                break;

            case 0:
                System.out.println("Saliendo...");
                break;

            default:
                System.out.println("Opción no válida, intente nuevamente.");
        }

    }

    @Override
    public List<Article> getAllArticles(GeneralAcademicResources generalAcademicResourcesList) {
        return generalAcademicResourcesList.getArticleList() != null
                ? generalAcademicResourcesList.getArticleList()
                : List.of();
    }

    @Override
    public List<Book> getAllBooks(GeneralAcademicResources generalAcademicResourcesList) {
        return generalAcademicResourcesList.getBookList() != null
                ? generalAcademicResourcesList.getBookList()
                : List.of();
    }

    @Override
    public List<ResearchWork> getAllResearchWork(GeneralAcademicResources generalAcademicResourcesList) {
        return generalAcademicResourcesList.getResearchWorkList() != null
                ? generalAcademicResourcesList.getResearchWorkList()
                : List.of();
    }

    @Override
    public Book getBookByEditorial(GeneralAcademicResources generalAcademicResourcesList, String editorial) {
        return generalAcademicResourcesList.getBookList().stream()
                .filter(book -> book.getEditorial().equalsIgnoreCase(editorial))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Article getArticleByMagazine(GeneralAcademicResources generalAcademicResourcesList, String magazine) {
        return generalAcademicResourcesList.getArticleList().stream()
                .filter(article -> article.getMagazine().equalsIgnoreCase(magazine))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ResearchWork getResearchWorkByResearchLine(GeneralAcademicResources generalAcademicResourcesList, String line) {
        return generalAcademicResourcesList.getResearchWorkList().stream()
                .filter(researchWork -> researchWork.getResearchLine().equalsIgnoreCase(line))
                .findFirst()
                .orElse(null);
    }

}