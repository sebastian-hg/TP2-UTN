package org.utn.gestores.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.utn.gestores.ResourceManager;
import org.utn.interfaces.Classifiable;
import org.utn.interfaces.Evaluable;
import org.utn.interfaces.FilterResource;
import org.utn.recursos.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ResourceManagerImpl implements ResourceManager, Classifiable, FilterResource {

    @Override
    public void createResource(GeneralAcademicResources generalAcademicResourcesList) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> mapIdentifier = new HashMap<>();
        mapIdentifier.put(1, "Research Work");
        mapIdentifier.put(2, "Book");
        mapIdentifier.put(3, "Article");

        if (generalAcademicResourcesList.getArticleList() == null) {
            generalAcademicResourcesList.setArticleList(new ArrayList<>());
        }
        if (generalAcademicResourcesList.getBookList() == null) {
            generalAcademicResourcesList.setBookList(new ArrayList<>());
        }
        if (generalAcademicResourcesList.getResearchWorkList() == null) {
            generalAcademicResourcesList.setResearchWorkList(new ArrayList<>());
        }

        System.out.println("Tipo de Recurso Academico: \n,1. Research Work\n2. Book\n3. Article");
        int optionIdentifier = scanner.nextInt();

        // Consumir el salto de línea pendiente después de nextInt()
        scanner.nextLine(); // Esto es lo que falta para evitar que se salte el siguiente nextLine()

        System.out.println("Ingrese la fecha de creación (formato: yyyy-MM-dd):");
        String dateInput = scanner.nextLine();
        LocalDate createDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Ingrese el autor:");
        String author = scanner.nextLine();

        System.out.println("Ingrese el título:");
        String title = scanner.nextLine();

        switch (optionIdentifier) {
            case 1:
                List<String> authorList = new ArrayList<>();
                authorList.add(author);
                ResearchWork newResearchWork = new ResearchWork(author, dateInput, mapIdentifier.get(optionIdentifier), title, authorList, "");
                generalAcademicResourcesList.getResearchWorkList().add(newResearchWork);
                break; // Asegúrate de agregar break en cada case
            case 2:
                System.out.println("Nombre de editorial");
                String editorial = scanner.nextLine();
                System.out.println("Cantidad de paginas");
                int numberPages = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea después de nextInt()
                Book book = new Book(author, dateInput, mapIdentifier.getOrDefault(optionIdentifier, ""), title, editorial, numberPages);
                generalAcademicResourcesList.getBookList().add(book);
                break;
            case 3:
                System.out.println("Agregue la revista");
                String magazine = scanner.nextLine();
                System.out.println("Palabras Clave (colocar seguidas de coma (,))");
                String keyWords = scanner.nextLine();
                List<String> keyWordsList = new ArrayList<>();
                addWordsList(keyWords, keyWordsList);
                Article newArticle = new Article(author, dateInput, mapIdentifier.getOrDefault(optionIdentifier, ""), title, keyWordsList, magazine);
                generalAcademicResourcesList.getArticleList().add(newArticle);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    @Override
    public void generateReport(GeneralAcademicResources generalAcademicResources) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- Generación de Informes -----");
        System.out.println("1. Generar informe de libros");
        System.out.println("2. Generar informe de artículos");
        System.out.println("3. Generar informe de trabajos de investigación");
        System.out.println("0. Volver al menú principal");
        System.out.print("Elija una opción: ");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Generando informe de libros...");
                getReportByBooks(generalAcademicResources);
                System.out.println("El informe de libros ha sido generado: Books.json");
                break;
            case 2:
                System.out.println("Generando informe de artículos...");
                getReportByArticles(generalAcademicResources);
                System.out.println("El informe de artículos ha sido generado: Article.json");
                break;
            case 3:
                System.out.println("Generando informe de trabajos de investigación...");
                getReportByResearchWork(generalAcademicResources);
                System.out.println("El informe de trabajos de investigación ha sido generado: ResearchWork.json");
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción no válida. Por favor, elija una opción entre 0 y 3.");
                break;
        }
    }

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
                break;
            case 2:
                System.out.println("Detalle de todos los Articulos");
                generalAcademicResourcesList.getArticleList()
                        .forEach(Article::showDetails);
                break;
            case 3:
                System.out.println("Detalle de todos las investigaciones");
                generalAcademicResourcesList.getResearchWorkList()
                        .forEach(ResearchWork::showDetails);
                break;
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

    @Override
    public void orderResource(GeneralAcademicResources generalAcademicResourcesList ){

    }

    private void getReportByBooks(GeneralAcademicResources generalAcademicResources) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("/home/sebastianhernandez/Documentos-utn/Books.json"), generalAcademicResources.getBookList());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getReportByArticles(GeneralAcademicResources generalAcademicResources) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("/home/sebastianhernandez/Documentos-utn/Article.json"), generalAcademicResources.getArticleList());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getReportByResearchWork(GeneralAcademicResources generalAcademicResources) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("/home/sebastianhernandez/Documentos-utn/ResearchWork.json"), generalAcademicResources.getResearchWorkList());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getReportByGeneralAcademicResource(GeneralAcademicResources generalAcademicResources) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            objectMapper.writeValue(new File("/home/sebastianhernandez/Documentos-utn/Resources.json"), generalAcademicResources);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addWordsList(String words, List<String> list) {
        if (words != null && !words.isEmpty()) {
            // Divide la cadena por comas, elimina espacios en blanco y añade a la lista
            for (String palabra : words.split(",")) {
                list.add(palabra.trim());
            }
        }
    }


    @Override
    public List<String> getCategoriesClassification() {
        return List.of();
    }

    @Override
    public void setCategories(String category) {

    }

    @Override
    public boolean evaluate(AcademicResource academicResource) {
        return false;
    }
}
