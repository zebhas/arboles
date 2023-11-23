/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

public class ArbolCLI {
    private Scanner in;
    private ArchivoProperties archivoProperties;
    private ArbolBinario arbolBinario;

    public ArbolCLI() {
        in = new Scanner(System.in);
        archivoProperties = new ArchivoProperties();
        arbolBinario = new ArbolBinario();
    }

    public void mainMenu() {
        boolean finish = false;
        while (!finish) {
            Screen.clear();

            System.out.println("------------------------------------------");
            System.out.println("-           Siembra de árboles           -");
            System.out.println("------------------------------------------");

            System.out.println("EL sistema para la plantación de árboles binarios\n");

            System.out.println("Menú principal:");
            System.out.println("-----------------");
            System.out.println("1. Cargar archivo con semillas");
            System.out.println("2. Salir");
            System.out.print("\nSeleccione una opción: ");
            int opt1 = in.nextInt();

            switch (opt1) {
                case 1:
                    recibirArchivo();
                    break;
                case 2:
                    finish = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void recibirArchivo() {
        boolean finish = false;
        while (!finish) {
            Screen.clear();
            System.out.println("Recuerde que el archivo a cargar");
            System.out.println("debe ser un archivo properties");
            System.out.println("que tenga la propiedad in-orden,");
            System.out.println("la propiedad pre-orden (donde los ");
            System.out.println("elementos estén separados por comas) y");
            System.out.println("que esté guardado en la carpeta data.");
            System.out.println("");
            System.out.println("Introduzca el nombre del archivo:");
            System.out.println("----------------------------------------------------");

            try {
                in.nextLine(); // Limpiar el buffer
                String nombreArchivo = in.nextLine();
                archivoProperties.cargarArchivo(nombreArchivo);
                String preOrden = archivoProperties.obtenerPreOrden();
                String inOrden = archivoProperties.obtenerInOrden();

                if (preOrden != null && inOrden != null) {
                    arbolBinario.reconstruirArbol(preOrden, inOrden);
                    arbolBinario.generarArchivoJSON("arbolPlantado.json)");
                    System.out.println("¡Árbol plantado con éxito!");
                    System.out.println("Para verlo, diríjase a /data/arbolPlantado.json");
                    System.out.println("Nota: ejecute Refresh (Clic derecho - Refresh) sobre la carpeta /data/ para actualizar y visualizar el archivo JSON");
                    System.out.println("Presione 1 para salir");
                    in.next();
                    finish = true;
                } else {
                    System.out.println("No se pudo cargar el archivo correctamente.");
                }

            } catch (Exception e) {
                System.out.println("Hubo un problema cargando el archivo:");
                System.out.println(e.getMessage());
                e.printStackTrace();
                finish = true;
            }
        }
    }
}
