package org.utn.recursos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class AcademicResource {

    @JsonProperty("identifier")
    protected String identifier;
    @JsonProperty("title")
    protected String title;
    @JsonProperty("createDate")
    protected String createDate;
    @JsonProperty("author")
    protected String author;

    public AcademicResource() {

    }

    public abstract double calculateRelevance();

    public abstract void showDetails();

    public AcademicResource(String author, String createDate, String identifier, String title) {
        this.author = author;
        this.createDate = createDate;
        this.identifier = identifier;
        this.title = title;
    }


}
