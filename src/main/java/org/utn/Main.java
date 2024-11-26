package org.utn;

import org.utn.gestores.impl.*;
import lombok.AllArgsConstructor;
import org.utn.interfaces.Classifiable;
import org.utn.interfaces.Deserializable;
import org.utn.recursos.GeneralAcademicResources;

import java.util.Scanner;

@AllArgsConstructor
public class Main {

    // dependencias
    private Deserializable deserializable;
    private Classifiable.ResourceManager resourceManager;

    private GeneralAcademicResources initialize () {
        System.out.println(deserializable.getInitialData());
        GeneralAcademicResources generalAcademicResources = deserializable.getInitialData();
        return generalAcademicResources;
    }


    public void start() {

        Scanner scanner = new Scanner(System.in);
        Integer mainOption = 99;
        GeneralAcademicResources generalAcademicResources = initialize();

        do {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Registro de recursos académicos");
            System.out.println("2. Clasificación de recursos");
            System.out.println("3. Evaluación de relevancia");
            System.out.println("4. Generación de informes");
            System.out.println("5. Filtrado avanzado de recursos");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");


            int option = scanner.nextInt();
            mainOption = option;

            switch (option) {
                case 1:
                    System.out.println("Has elegido: Opción 1");
                    resourceManager.createResource(generalAcademicResources);
                    break;
                case 2:
                    System.out.println("Has elegido: Clasificación de recursos");

                    break;
                case 3:
                    System.out.println("Has elegido: Evaluación de relevancia");
                    // Llama a la funcionalidad específica de relevanceEvaluation
                    break;
                case 4:
                    System.out.println("Has elegido: Generación de informes");
                    resourceManager.generateReport(generalAcademicResources);

                    break;
                case 5:
                    System.out.println("Has elegido: Filtrado avanzado de recursos");
                    resourceManager.filter(generalAcademicResources);
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción entre 0 y 5.");
                    break;
            }

        } while (mainOption != 0);
    }
    public static void main(String[] args) {

        Deserializable deserializable = new DeserializableImpl();
        Classifiable.ResourceManager resourceManager = new ResourceManagerImpl();

        Main mainApp = new Main(deserializable,resourceManager);
        mainApp.start();
    }
}