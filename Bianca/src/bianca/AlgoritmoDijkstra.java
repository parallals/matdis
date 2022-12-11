package bianca;

import java.util.ArrayList;

public class AlgoritmoDijkstra {
    
    public static void Dijkstra(String[] nodos, String[][] aristas, int[] pesos, String nodoInicial, String nodoFinal){
        
         int[] distancias = new int[nodos.length];
         boolean[] vistos = new boolean[nodos.length];
         ArrayList<String> ciudadesRecorridas = new ArrayList();
         
         for(int i=0 ; i<distancias.length ; i++){
             distancias[i] = Integer.MAX_VALUE-1;          
             vistos[i] = false;
         }
         for(int i=0 ; i<aristas.length ; i++){
             if(aristas[i][0].equals(nodoInicial)){
                 for(int j=0 ; j<distancias.length ; j++){
                     if(aristas[i][1].equals(nodos[j])){
                         distancias[j] = pesos[i];
                     }
                 }
             }else if(aristas[i][1].equals(nodoInicial)){
                 for(int j=0 ; j<distancias.length ; j++){
                     if(aristas[i][0].equals(nodos[j])){
                         distancias[j] = pesos[i];
                     }
                 }
             }
             if(nodos[i].equals(nodoInicial)){
                 distancias[i] = 0;
                 vistos[i] = true;
             }
         }
         
         boolean noTodosVistos;
         int aux = 0;
         for(int i=0 ; i<vistos.length ; i++){
             if(vistos[i] == false){
                 aux = 1;
                 break;
             }
         }
         if(aux==1){
             noTodosVistos = true;
         }else{
             noTodosVistos = false;
         }
         
         while(noTodosVistos){
             int menor = Integer.MAX_VALUE;
             int indiceVertice = 0;
             for(int i=0 ; i<distancias.length ; i++){
                 if(distancias[i]<menor && vistos[i]==false){
                     menor = distancias[i];
                     indiceVertice = i;
                 }
             }
             vistos[indiceVertice] = true;
             ciudadesRecorridas.add(nodos[indiceVertice]);             
             for(int i=0 ; i<aristas.length ; i++){
                if(aristas[i][0].equals(nodos[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(aristas[i][1].equals(nodos[j]) && vistos[j]==false){
                            if(distancias[j] > distancias[indiceVertice]+pesos[i]){
                                distancias[j] = distancias[indiceVertice]+pesos[i];
                            }
                        }
                    }
                }
             }
             for(int i=0 ; i<aristas.length ; i++){
                if(aristas[i][1].equals(nodos[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(aristas[i][0].equals(nodos[j]) && vistos[j]==false){
                            if(distancias[j] > distancias[indiceVertice]+pesos[i]){
                                distancias[j] = distancias[indiceVertice]+pesos[i];
                            }
                        }
                    }
                }
             }
             aux = 0;
             for(int i=0 ; i<vistos.length ; i++){
                 if(vistos[i] == false){
                     aux = 1;
                     break;
                 }
             }
             if(aux==1){
                 noTodosVistos = true;
             }else{
                 noTodosVistos = false;
             }
         }
         
         for(int i=0 ; i<nodos.length ; i++){
            System.out.println(distancias[i]);   
         }
    }
}
