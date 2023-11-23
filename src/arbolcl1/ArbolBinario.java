/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolcl1;

import java.io.FileWriter;
import java.io.IOException;

class Nodo {

    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int item) {
        valor = item;
        izquierdo = derecho = null;
    }
}

public class ArbolBinario {

    private Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public void reconstruir(String preOrden, String inOrden) {
        raiz = reconstruirArbol(preOrden, inOrden, 0, preOrden.length() - 1, 0, inOrden.length() - 1);
    }

    private Nodo reconstruirArbol(String preOrden, String inOrden, int preInicio, int preFin, int inInicio, int inFin) {
        if (preInicio > preFin || inInicio > inFin) {
            return null;
        }

        int valorRaiz = Integer.parseInt(preOrden.substring(preInicio, preInicio + 1));
        Nodo nodo = new Nodo(valorRaiz);

        int posicionRaiz = 0;
        for (int i = inInicio; i <= inFin; i++) {
            if (Integer.parseInt(inOrden.substring(i, i + 1)) == valorRaiz) {
                posicionRaiz = i;
                break;
            }
        }

        nodo.izquierdo = reconstruirArbol(preOrden, inOrden, preInicio + 1, preInicio + posicionRaiz - inInicio, inInicio, posicionRaiz - 1);
        nodo.derecho = reconstruirArbol(preOrden, inOrden, preInicio + posicionRaiz - inInicio + 1, preFin, posicionRaiz + 1, inFin);

        return nodo;
    }

    private String convertirEnJSON(Nodo nodo) {
        if (nodo == null) {
            return "null";
        }

        StringBuilder resultado = new StringBuilder();
        resultado.append("{")
                .append("\"valor\": ")
                .append(nodo.valor)
                .append(", \"izquierdo\": ")
                .append(convertirEnJSON(nodo.izquierdo))
                .append(", \"derecho\": ")
                .append(convertirEnJSON(nodo.derecho))
                .append("}");

        return resultado.toString();
    }

    public String imprimirEnJSON() {
        return convertirEnJSON(raiz);
    }

    public void crearArchivo(String info, String nombreArchivo) {
        try {
            FileWriter archivo = new FileWriter("data/" + nombreArchivo);
            archivo.write(info);
            archivo.close();
            System.out.println("El archivo " + nombreArchivo + " ha sido creado correctamente.");
        } catch (IOException e) {
            System.out.println("Hubo un error al crear el archivo: " + e.getMessage());
        }
    }
}
