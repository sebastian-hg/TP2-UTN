package org.utn.recursos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Book extends AcademicResource{

    @JsonProperty("pageNumbers")
    private int pageNumbers;
    @JsonProperty("editorial")
    private String editorial;

    @Override
    public double calculateRelevance() {
        return 8;
    }

    @Override
    public void showDetails() {
        System.out.println("=== Detalles del Libro ===");
        System.out.println("Identificador: " + identifier);
        System.out.println("Título: " + title);
        System.out.println("Fecha de creación: " + createDate);
        System.out.println("Autor: " + author);
        System.out.println("Número de páginas: " + pageNumbers);
        System.out.println("Editorial: " + editorial);
        System.out.println("Es libro digital?: " + isDigitalBook());
        System.out.println("relevancia: " + calculateRelevance());

        System.out.println("==========================");
    }

    private boolean isDigitalBook() {
        return editorial != null && editorial.equalsIgnoreCase("Digital");
    }

    public Book() {
        super();
    }
    public Book(String author, String createDate, String identifier, String title, String editorial, int pageNumbers) {
        super(author, createDate, identifier, title);
        this.editorial = editorial;
        this.pageNumbers = pageNumbers;
    }
}