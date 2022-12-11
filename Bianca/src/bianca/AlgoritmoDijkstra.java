package bianca;


public class AlgoritmoDijkstra {
    
    public static void Dijkstra(String[] ciudades, String[][] rutas, int[] entrenadores, String ciudadInicial, String ciudadFinal){
         // Principales arreglos que ocuparemos
         String[] padres = new String[ciudades.length];
         int[] distancias = new int[ciudades.length];
         boolean[] vistos = new boolean[ciudades.length];
         // Inicializacion de los arreglos
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
         // Comprobamos que el grafo no se Trivial
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
         // Empieza la el ciclo Principal
         while(noTodosVistos){
             // Buscamos el vertice con menos distancia
             int menor = Integer.MAX_VALUE;
             int indiceVertice = 0;
             for(int i=0 ; i<distancias.length ; i++){
                 if(distancias[i]<menor && vistos[i]==false){
                     menor = distancias[i];
                     indiceVertice = i;
                 }
             }
             // Una vez encontrado lo guardamos y lo marcamos como visto
             vistos[indiceVertice] = true;
             // Buscamos sus vertices adyacentes y les asignamos nuevas distancias ademas de guardar su padre
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
             // Revisamos si todos sus vertices ya fueron vistos se ser asi, se rompe el while
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
             // Caso contrario, hacemos otra iteracion
         }
         // Imprimimos las ciudades a recorrer para llegar de ciudadInicial a ciudadFinal
         for(int j=0 ; j<ciudades.length ; j++){
            if(ciudades[j].equals(ciudadFinal)){
                imprimirPadres(j, padres, ciudades);
            }
        }
    }
    // Metodo recursivo que llama sucesivamente a los padres de un vertice hasta llegar a un vertice cuyo padre es null
    private static void imprimirPadres(int vertice, String[] padres, String[] ciudades){
        int indicePadre = 0;
        if(padres[vertice]==null){
            return;
        }
        for(int i=0 ; i<ciudades.length ; i++){
            if(padres[vertice].equals(ciudades[i])){
                indicePadre = i;
            }
        }
        imprimirPadres(indicePadre, padres, ciudades);
        System.out.println(ciudades[vertice] + " ");
    }

}
