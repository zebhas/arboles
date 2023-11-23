/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolcl1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Nodo {
    String valor;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(String valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public void reconstruirArbol(String preOrden, String inOrden) {
        String[] preOrdenArray = preOrden.split(",");
        String[] inOrdenArray = inOrden.split(",");

        raiz = reconstruirArbolRec(preOrdenArray, inOrdenArray, 0, inOrdenArray.length - 1, 0);
    }

    private Nodo reconstruirArbolRec(String[] preOrdenArray, String[] inOrdenArray, int inStart, int inEnd, int preIndex) {
        if (inStart > inEnd) {
            return null;
        }

        Nodo nodo = new Nodo(preOrdenArray[preIndex]);

        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrdenArray[i].equals(preOrdenArray[preIndex])) {
                inIndex = i;
                break;
            }
        }

        nodo.izquierdo = reconstruirArbolRec(preOrdenArray, inOrdenArray, inStart, inIndex - 1, preIndex + 1);
        nodo.derecho = reconstruirArbolRec(preOrdenArray, inOrdenArray, inIndex + 1, inEnd, preIndex + inIndex - inStart + 1);

        return nodo;
    }

    public void generarArchivoJSON(String nombreArchivo) {
        try (FileWriter file = new FileWriter("data/" + nombreArchivo)) {
            List<String> nodosJSON = obtenerNodosEnFormatoJSON(raiz);

            file.write("[\n");
            for (int i = 0; i < nodosJSON.size(); i++) {
                file.write(nodosJSON.get(i));
                if (i < nodosJSON.size() - 1) {
                    file.write(",\n");
                }
            }
            file.write("\n]");
            System.out.println("¡Archivo JSON generado con éxito!");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo JSON: " + e.getMessage());
        }
    }

    private List<String> obtenerNodosEnFormatoJSON(Nodo nodo) {
        List<String> resultado = new ArrayList<>();
        obtenerNodosEnFormatoJSONRec(nodo, resultado);
        return resultado;
    }

   private void obtenerNodosEnFormatoJSONRec(Nodo nodo, List<String> resultado) {
    if (nodo == null) {
        resultado.add("null");
        return;
    }

    StringBuilder nodoJSON = new StringBuilder();
    nodoJSON.append("{ \"valor\": \"").append(nodo.valor).append("\", ");

    if (nodo.izquierdo != null) {
        nodoJSON.append("\"izquierdo\": ");
        obtenerNodosEnFormatoJSONRec(nodo.izquierdo, resultado);
    } else {
        nodoJSON.append("\"izquierdo\": null");
    }

    nodoJSON.append(", ");

    if (nodo.derecho != null) {
        nodoJSON.append("\"derecho\": ");
        obtenerNodosEnFormatoJSONRec(nodo.derecho, resultado);
    } else {
        nodoJSON.append("\"derecho\": null");
    }

    nodoJSON.append("}");
    resultado.add(nodoJSON.toString());
}
}
