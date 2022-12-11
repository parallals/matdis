package bianca;


public class AlgoritmoDijkstra {
    
    public static void Dijkstra(String[] ciudades, String[][] rutas, int[] entrenadores, String ciudadInicial, String ciudadFinal){
         String[] padres = new String[ciudades.length];
         int[] distancias = new int[ciudades.length];
         boolean[] vistos = new boolean[ciudades.length];
         for(int i=0 ; i<distancias.length ; i++){
             distancias[i] = Integer.MAX_VALUE - 1;          
             vistos[i] = false;
             padres[i] = null;
         }
         for(int i=0 ; i<rutas.length ; i++){
             if(rutas[i][0].equals(ciudadInicial)){
                 for(int j=0 ; j<distancias.length ; j++){
                     if(rutas[i][1].equals(ciudades[j])){
                         distancias[j] = entrenadores[i];
                         padres[j]=ciudadInicial;
                     }
                 }
             }else if(rutas[i][1].equals(ciudadInicial)){
                 for(int j=0 ; j<distancias.length ; j++){
                     if(rutas[i][0].equals(ciudades[j])){
                         distancias[j] = entrenadores[i];
                         padres[j]=ciudadInicial;
                     }
                 }
             }
         }
         for(int i=0 ; i<ciudades.length ; i++){
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
             for(int i=0 ; i<rutas.length ; i++){
                if(rutas[i][0].equals(ciudades[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(rutas[i][1].equals(ciudades[j])){
                            if(distancias[j] > distancias[indiceVertice]+entrenadores[i]){
                                distancias[j] = distancias[indiceVertice]+entrenadores[i];
                                padres[j] = ciudades[indiceVertice];
                            }
                        }
                    }
                }
             }
             for(int i=0 ; i<rutas.length ; i++){
                if(rutas[i][1].equals(ciudades[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(rutas[i][0].equals(ciudades[j])){
                            if(distancias[j] > distancias[indiceVertice]+entrenadores[i]){
                                distancias[j] = distancias[indiceVertice]+entrenadores[i];
                                padres[j] = ciudades[indiceVertice];
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
             if(ciudades[i].equals(ciudadInicial)){
                  int indiceVertice = i;
             }
             for(int j=0 ; j<ciudades.length ; j++){
                if(!ciudades[j].equals(ciudades[i])){
                   System.out.print("\n" + ciudades[i] + " -> ");
                   System.out.print(ciudades[j] + " \t\t ");
                   System.out.print(distancias[j] + "\n" );
                   imprimirPadres(j, padres, ciudades);
                }
            }
        }
    }
    private static void imprimirPadres(int j, String[] padres, String[] ciudades){
        int indicePadre = 0;
        if(padres[j]==null){
            return;
        }
        for(int i=0 ; i<ciudades.length ; i++){
            if(padres[j].equals(ciudades[i])){
                indicePadre = i;
            }
        }
        imprimirPadres(indicePadre, padres, ciudades);
        System.out.println(ciudades[j] + " ");
    }

}
