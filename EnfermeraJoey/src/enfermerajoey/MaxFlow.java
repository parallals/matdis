package enfermerajoey;

import java.util.ArrayList;
import java.util.Arrays;


public class MaxFlow {
}

 class Grafo {
    /* representaremos los grafos con matrices de adyacencia, donde los los
      números representarán el flujo de la arista que conecta a un nodo 
      (el número de la fila) con otro (el número de la columna)*/
    private int[][] g;
    public Grafo(int[][] g){
        this.g = g;
    }
    // Algoritmo de Búsqueda en Anchura. Buscará caminos que vayan desde
    // el sumidero hasta la fuente. Devolverá true si el destino fue
    // visitado
    private boolean bfs(int fuente, int sumidero, ArrayList<Integer> desc){
        ArrayList<Boolean> nodosVisitados = new ArrayList();
        for(int i = 0; i < g.length; i++){
            nodosVisitados.add(i, Boolean.FALSE);
        }
        ArrayList<Integer> camino = new ArrayList();
        camino.add(0, fuente); 
        System.out.println(camino);
        nodosVisitados.set(fuente, Boolean.TRUE);     

        while (!camino.isEmpty()){
            int nodo = camino.get(0);
            camino.remove(0);
            for(int i = 0; i < g[nodo].length; i++){                    
                if (g[nodo][i] > 0 && !nodosVisitados.get(i)){
                    nodosVisitados.set(i, Boolean.TRUE);
                    camino.add(i);
                    desc.set(i, nodo);        
                    System.out.println(camino);
                }                    
            }         
            System.out.println("heree");
        }
        System.out.println(camino);
        return nodosVisitados.get(sumidero);
    }

    // Algoritmo Ford-Fulkerson. Devuelve el flujo máximo del grafo
    public int getFlujoMaximo(int fuente, int sumidero){
        int flujoMaximo = 0;
        int flujo = Integer.MIN_VALUE;
        int n = fuente;            
        ArrayList<Integer> desc = new ArrayList();
        for(int i = 0; i < g.length; i++){
            desc.add(g.length-1);
        }
        while(bfs(fuente, sumidero, desc)){
            System.out.println(desc);
            flujo = Integer.MIN_VALUE;
            while(fuente != n){
                int m = desc.get(n);
                flujo = Integer.min(flujo, g[m][n]);
                n = m;
            }
            int k = sumidero;
            int l;
            flujoMaximo+=flujo;
            while(fuente!=k){
                 l = desc.get(k);
                 g[k][l] += flujo;
                 g[l][k] -= flujo;
                 k = desc.get(k);

            }
        }
        return flujoMaximo;                 
    }
}

