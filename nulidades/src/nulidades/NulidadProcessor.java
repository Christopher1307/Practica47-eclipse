package nulidades;

import java.util.Scanner;

public class NulidadProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.- Generar nulidades");
            System.out.println("2.- Salir");
            System.out.println("Elige una de las opciones: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    Procesos.procesarNulidades();
                    break;
                case 2:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida, por favor elige de nuevo.");
            }
        }
    }
}


