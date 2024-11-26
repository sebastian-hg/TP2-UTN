package org.utn.recursos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Article extends AcademicResource{
    @JsonProperty("keyWords")
    private List<String> keyWords;
    @JsonProperty("magazine")
    private String magazine;

    public Article() {
    }

    public Article(String author, String createDate, String identifier, String title, List<String> keyWords, String magazine) {
        super(author, createDate, identifier, title);
        this.keyWords = keyWords;
        this.magazine = magazine;
    }

    private int countKeyWords() {
        return keyWords.size();
    }

    @Override
    public double calculateRelevance() {
        return 4;
    }

    @Override
    public void showDetails() {
        System.out.println("=== Detalles del Articulo ===");
        System.out.println("Identificador: " + identifier);
        System.out.println("Título: " + title);
        System.out.println("Fecha de creación: " + createDate);
        System.out.println("Autor: " + author);
        System.out.println("Revista: " + magazine);
        System.out.println("relevancia: " + calculateRelevance());
        System.out.println("Numero palabras Claves: " + countKeyWords());

        if (keyWords != null && !keyWords.isEmpty()) {
            System.out.println("Palabras clave: " + String.join(", ", keyWords));
        } else {
            System.out.println("Palabras clave: No disponibles");
        }

        System.out.println("=============================");
    }
}