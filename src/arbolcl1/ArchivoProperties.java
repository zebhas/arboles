/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolcl1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArchivoProperties {
    private String preOrden;
    private String inOrden;

    public void cargarArchivo(String nombre) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + nombre))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    if (partes[0].startsWith("preorden")) {
                        preOrden = partes[1];
                    } else if (partes[0].startsWith("inorden")) {
                        inOrden = partes[1];
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String obtenerPreOrden() {
        return preOrden;
    }

    public String obtenerInOrden() {
        return inOrden;
    }
}