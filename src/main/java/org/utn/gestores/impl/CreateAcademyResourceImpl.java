package org.utn.gestores.impl;

import org.utn.recursos.Article;
import org.utn.recursos.Book;
import org.utn.recursos.GeneralAcademicResources;
import org.utn.recursos.ResearchWork;
import org.utn.gestores.CreateAcademyResource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CreateAcademyResourceImpl implements CreateAcademyResource {


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

    private void addWordsList(String words, List<String> list) {
        if (words != null && !words.isEmpty()) {
            // Divide la cadena por comas, elimina espacios en blanco y añade a la lista
            for (String palabra : words.split(",")) {
                list.add(palabra.trim());
            }
        }
    }
}
