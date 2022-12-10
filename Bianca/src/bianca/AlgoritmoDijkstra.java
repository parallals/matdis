package bianca;

public class AlgoritmoDijkstra {
    
    public static void Dijkstra(String[] ciudades, String[][] rutas, int[] entrenadores, String ciudadInicial, String ciudadFinal){
        
         int[] distancias = new int[ciudades.length];
         boolean[] vistos = new boolean[ciudades.length];
         
         for(int i=0 ; i<distancias.length ; i++){
             distancias[i] = -1;           //Usaremos -1 como reemplazo de infinito.
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
         boolean noTodosVistos = true;
         int aux = 0;
         for(int i=0 ; i<vistos.length ; i++){
             if(vistos[i] == false){
                 aux = 1;
             }
         }
         if(aux==1){
             noTodosVistos = true;
         }else {
             noTodosVistos = false;
         }
         
         
         while(noTodosVistos){
             
             
             
             
             
             
             
             
             
            aux = 0;
            for(int i=0 ; i<vistos.length ; i++){
                if(vistos[i] == false){
                    aux = 1;
                }
            }
            if(aux==1){
                noTodosVistos = true;
            }else {
                noTodosVistos = false;
            }
         }
    }
}
