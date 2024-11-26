package org.utn.gestores.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.utn.recursos.GeneralAcademicResources;
import org.utn.gestores.ReportGeneration;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.util.Scanner;

public class ReportGenerationImpl implements ReportGeneration {

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

}