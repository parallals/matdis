package bianca;

public class AlgoritmoDijkstra {
    
    public static void Dijkstra(String[] ciudades, String[][] rutas, int[] entrenadores, String ciudadInicial, String ciudadFinal){
        
         int[] distancias = new int[ciudades.length];
         boolean[] vistos = new boolean[ciudades.length];
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
                 if(vistos[i]==false){
                     menor = distancias[i];
                     indiceVertice = i;
                     break;
                 }
             }
             for(int i=0 ; i<distancias.length ; i++){
                 if(distancias[i]<menor && vistos[i]==false){
                     menor = distancias[i];
                     indiceVertice = i;
                 }
             }
             vistos[indiceVertice] = true;
             
             int indiceAdyacente = 0;
             int indicePeso = 0;
             for(int i=0 ; i<rutas.length ; i++){
                if(rutas[i][0].equals(ciudades[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(rutas[i][1].equals(ciudades[j])){
                            indiceAdyacente = j;
                            indicePeso = i;
                        }
                    }
                }
                if(distancias[indiceAdyacente] > distancias[indiceVertice]+entrenadores[indicePeso]){
                    distancias[indiceAdyacente] = distancias[indiceVertice]+entrenadores[indicePeso];
                }
             }
             for(int i=0 ; i<rutas.length ; i++){
                if(rutas[i][1].equals(ciudades[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(rutas[i][0].equals(ciudades[j])){
                            indiceAdyacente = j;
                            indicePeso = i;
                        }
                    }
                }
                if(distancias[indiceAdyacente] > distancias[indiceVertice]+entrenadores[indicePeso]){
                    distancias[indiceAdyacente] = distancias[indiceVertice]+entrenadores[indicePeso];
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
