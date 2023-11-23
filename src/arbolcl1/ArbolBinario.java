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
    if (preOrden == null || inOrden == null) {
        System.out.println("Los recorridos preorden e inorden no pueden ser nulos.");
        return;
    }

    String[] preOrdenArray = preOrden.split(",");
    String[] inOrdenArray = inOrden.split(",");

    if (preOrdenArray.length != inOrdenArray.length) {
        System.out.println("Los recorridos preorden e inorden no tienen la misma longitud.");
        return;
    }

    raiz = reconstruirArbolRec(preOrdenArray, inOrdenArray, 0, inOrdenArray.length - 1, new int[]{0});
}

private Nodo reconstruirArbolRec(String[] preOrdenArray, String[] inOrdenArray, int inStart, int inEnd, int[] preIndex) {
    if (inStart > inEnd) {
        return null;
    }

    Nodo nodo = new Nodo(preOrdenArray[preIndex[0]]);
    preIndex[0]++;

    if (inStart == inEnd) {
        return nodo;
    }

    int inIndex = buscarIndiceInorden(inOrdenArray, inStart, inEnd, nodo.valor);

    nodo.izquierdo = reconstruirArbolRec(preOrdenArray, inOrdenArray, inStart, inIndex - 1, preIndex);
    nodo.derecho = reconstruirArbolRec(preOrdenArray, inOrdenArray, inIndex + 1, inEnd, preIndex);

    return nodo;
}

private int buscarIndiceInorden(String[] inOrdenArray, int start, int end, String valor) {
    for (int i = start; i <= end; i++) {
        if (inOrdenArray[i].equals(valor)) {
            return i;
        }
    }
    return -1;
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
        resultado.add("{ \"valor\": null, \"izquierdo\": null, \"derecho\": null }");
    } else {
        StringBuilder nodoJSON = new StringBuilder();
        nodoJSON.append("{ \"valor\": \"").append(nodo.valor).append("\", ");

        // Verificar el nodo izquierdo
        if (nodo.izquierdo != null) {
            nodoJSON.append("\"izquierdo\": \"").append(nodo.izquierdo.valor).append("\", ");
        } else {
            nodoJSON.append("\"izquierdo\": null, ");
        }

        // Verificar el nodo derecho
        if (nodo.derecho != null) {
            nodoJSON.append("\"derecho\": \"").append(nodo.derecho.valor).append("\"");
        } else {
            nodoJSON.append("\"derecho\": null");
        }

        nodoJSON.append("}");
        resultado.add(nodoJSON.toString());

        obtenerNodosEnFormatoJSONRec(nodo.izquierdo, resultado);
        obtenerNodosEnFormatoJSONRec(nodo.derecho, resultado);
    }
}
   public void imprimirInorden() {
    System.out.println("Recorrido Inorden del Árbol:");
    imprimirInordenRec(raiz);
    System.out.println();
}

private void imprimirInordenRec(Nodo nodo) {
    if (nodo != null) {
        imprimirInordenRec(nodo.izquierdo);
        System.out.print(nodo.valor + " ");
        imprimirInordenRec(nodo.derecho);
    }
}
}
