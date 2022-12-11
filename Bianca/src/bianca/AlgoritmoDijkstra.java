package bianca;

import java.util.ArrayList;

public class AlgoritmoDijkstra {
    
    public static void Dijkstra(String[] ciudades, String[][] rutas, int[] entrenadores, String ciudadInicial, String ciudadFinal){
        
         int[] distancias = new int[ciudades.length];
         boolean[] vistos = new boolean[ciudades.length];
         ArrayList<String> ciudadesRecorridas = new ArrayList();
         
         for(int i=0 ; i<distancias.length ; i++){
             distancias[i] = Integer.MAX_VALUE-1;          
             vistos[i] = false;
         }
         for(int i=0 ; i<rutas.length ; i++){
             if(rutas[i][0].equals(ciudadInicial)){
                 for(int j=0 ; j<distancias.length ; j++){
                     if(rutas[i][1].equals(ciudades[j])){
                         distancias[j] = entrenadores[i];
                     }
                 }
             }else if(rutas[i][1].equals(ciudadInicial)){
                 for(int j=0 ; j<distancias.length ; j++){
                     if(rutas[i][0].equals(ciudades[j])){
                         distancias[j] = entrenadores[i];
                     }
                 }
             }
             if(ciudades[i].equals(ciudadInicial)){
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
             ciudadesRecorridas.add(ciudades[indiceVertice]);             
             for(int i=0 ; i<rutas.length ; i++){
                if(rutas[i][0].equals(ciudades[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(rutas[i][1].equals(ciudades[j]) && vistos[j]==false){
                            if(distancias[j] > distancias[indiceVertice]+entrenadores[i]){
                                distancias[j] = distancias[indiceVertice]+entrenadores[i];
                            }
                        }
                    }
                }
             }
             for(int i=0 ; i<rutas.length ; i++){
                if(rutas[i][1].equals(ciudades[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(rutas[i][0].equals(ciudades[j]) && vistos[j]==false){
                            if(distancias[j] > distancias[indiceVertice]+entrenadores[i]){
                                distancias[j] = distancias[indiceVertice]+entrenadores[i];
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
         
         for(int i=0 ; i<ciudades.length ; i++){
            System.out.println(distancias[i]);   
         }
    }
}
