package grafos;
import java.util.ArrayList;
import grafos.GrafoPesado;

public class arbolGenMin {
    private ArrayList<GrafoPesado> ListaVertices;
	public int[][] AlgPrim(int[][] Matriz) {  //Llega la matriz a la que le vamos a aplicar el algoritmo
        boolean[] marcados = new boolean[ListaVertices.size()]; //Creamos un vector booleano, para saber cuales est�n marcados
        GrafoPesado vertice = ListaVertices.get(0); //Le introducimos un nodo aleatorio, o el primero
        return AlgPrim(Matriz, marcados, vertice, new int[Matriz.length][Matriz.length]); //Llamamos al m�todo recursivo mand�ndole 
    }                                                                                     //un matriz nueva para que en ella nos 
                                                                                          //devuelva el �rbol final
    private int[][] AlgPrim(int[][] Matriz, boolean[] marcados, GrafoPesado grafoPesado, int[][] Final) {
        marcados[ListaVertices.indexOf(grafoPesado)] = true;//marcamos el primer nodo
        int aux = -1;
        if (!TodosMarcados(marcados)) { //Mientras que no todos est�n marcados
            for (int i = 0; i < marcados.length; i++) { //Recorremos s�lo las filas de los nodos marcados
                if (marcados[i]) {
                    for (int j = 0; j < Matriz.length; j++) {
                        if (Matriz[i][j] != 0) {        //Si la arista existe
                            if (!marcados[j]) {         //Si el nodo no ha sido marcado antes
                                if (aux == -1) {        //Esto s�lo se hace una vez
                                    aux = Matriz[i][j];
                                } else {
                                    aux = Math.min(aux, Matriz[i][j]); //Encontramos la arista m�nima
                                }
                            }
                        }
                    }
                }
            }
            //Aqu� buscamos el nodo correspondiente a esa arista m�nima (aux)
            for (int i = 0; i < marcados.length; i++) {
                if (marcados[i]) {
                    for (int j = 0; j < Matriz.length; j++) {
                        if (Matriz[i][j] == aux) {
                            if (!marcados[j]) { //Si no ha sido marcado antes
                                Final[i][j] = aux; //Se llena la matriz final con el valor
                                Final[j][i] = aux;//Se llena la matriz final con el valor
                                return AlgPrim(Matriz, marcados, ListaVertices.get(j), Final); //se llama de nuevo al m�todo con
                                                                                               //el nodo a marcar
                            }
                        }
                    }
                }
            }
        }
        return Final;
    }
    public boolean TodosMarcados(boolean[] vertice) { //M�todo para saber si todos est�n marcados
        for (boolean b : vertice) {
            if (!b) {
                return b;
            }
        }
        return true;
    }
}
