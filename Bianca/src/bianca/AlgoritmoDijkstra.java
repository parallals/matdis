package bianca;

public class AlgoritmoDijkstra {
    
    public static void Dijkstra(String[] ciudades, String[][] rutas, int[] entrenadores, String ciudadInicial, String ciudadFinal){
        
         int[] distancias = new int[ciudades.length];
         boolean[] vistos = new boolean[ciudades.length];
         for(int i=0 ; i<distancias.length ; i++){
             distancias[i] = -1;
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
            }
        } 
    }
}
