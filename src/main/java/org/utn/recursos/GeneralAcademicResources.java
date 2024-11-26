package org.utn.recursos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString
public class GeneralAcademicResources {

    @JsonProperty("articleList")
    private List<Article> articleList;
    @JsonProperty("researchWorkList")
    private List<ResearchWork> researchWorkList;
    @JsonProperty("bookList")
    private List<Book> bookList;

    public GeneralAcademicResources() {
    }
}
