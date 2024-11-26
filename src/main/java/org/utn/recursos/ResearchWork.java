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
public class ResearchWork extends AcademicResource {

    @JsonProperty("authors")
    private List<String> authors;
    @JsonProperty("researchLine")
    private String researchLine;

    private boolean hasFinancing() {
        return authors.size() > 1 ? false : true;
    }

    @Override
    public double calculateRelevance() {
        return 10;
    }

    @Override
    public void showDetails() {
        System.out.println("=== Detalles de la instigación===");
        System.out.println("Identificador: " + identifier);
        System.out.println("Título: " + title);
        System.out.println("Fecha de creación: " + createDate);
        System.out.println("Autor Principal: " + author);
        System.out.println("Tiene financiación : " + hasFinancing());
        System.out.println("Calcular Relevancia : " + calculateRelevance());

        if (authors != null && !authors.isEmpty()) {
            System.out.println("Autores: " + String.join(", ", authors));
        } else {
            System.out.println("Autores: No disponibles");
        }
        System.out.println("Línea de Investigación: " + (researchLine != null ? researchLine : "No disponible"));
        System.out.println("=============================");

    }

    public ResearchWork() {
    }

    public ResearchWork(String author, String createDate, String identifier, String title, List<String> authors, String researchLine) {
        super(author, createDate, identifier, title);
        this.authors = authors;
        this.researchLine = researchLine;
    }
}