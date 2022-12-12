package algoritmodijkstra;

public class AlgoritmoDijkstra {
    public static void Dijkstra(String[] vertices, String[][] nodos, int[] pesos, String verticeInicial){
         // Principales arreglos que ocuparemos
         String[] padres = new String[vertices.length];
         int[] distancias = new int[vertices.length];
         boolean[] vistos = new boolean[vertices.length];
         // Inicializacion de los arreglos
         for(int i=0 ; i<distancias.length ; i++){
             distancias[i] = Integer.MAX_VALUE - 1;          
             vistos[i] = false;
             padres[i] = null;
         }
         for(int i=0 ; i<nodos.length ; i++){
             if(nodos[i][0].equals(verticeInicial)){
                 for(int j=0 ; j<distancias.length ; j++){
                     if(nodos[i][1].equals(vertices[j])){
                         distancias[j] = pesos[i];
                         padres[j]=verticeInicial;
                     }
                 }
             }else if(nodos[i][1].equals(verticeInicial)){
                 for(int j=0 ; j<distancias.length ; j++){
                     if(nodos[i][0].equals(vertices[j])){
                         distancias[j] = pesos[i];
                         padres[j]=verticeInicial;
                     }
                 }
             }
         }
         for(int i=0 ; i<vertices.length ; i++){
             if(vertices[i].equals(verticeInicial)){
                 distancias[i] = 0;
                 vistos[i] = true;
             }
         }
         // Comprobamos que el grafo no sea Trivial
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
             // Buscamos el vertice con menor distancia
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
             // Buscamos sus vertices adyacentes y les asignamos nuevas distancias, ademas de guardar su padre
             for(int i=0 ; i<nodos.length ; i++){
                if(nodos[i][0].equals(vertices[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(nodos[i][1].equals(vertices[j])){
                            if(distancias[j] > distancias[indiceVertice]+pesos[i]){
                                distancias[j] = distancias[indiceVertice]+pesos[i];
                                padres[j] = vertices[indiceVertice];
                            }
                        }
                    }
                }
             }
             for(int i=0 ; i<nodos.length ; i++){
                if(nodos[i][1].equals(vertices[indiceVertice])){
                    for(int j=0 ; j<distancias.length ; j++){
                        if(nodos[i][0].equals(vertices[j])){
                            if(distancias[j] > distancias[indiceVertice]+pesos[i]){
                                distancias[j] = distancias[indiceVertice]+pesos[i];
                                padres[j] = vertices[indiceVertice];
                            }
                        }
                    }
                }
             }
             // Revisamos si todos sus vertices ya fueron vistos, de ser asi se rompe el while
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
         // Imprimimos las distancias
        for(int i=0 ; i<vertices.length ; i++){
             if(vertices[i].equals(verticeInicial)){
             }
             for(int j=0 ; j<vertices.length ; j++){
                if(!vertices[j].equals(vertices[i])){
                   System.out.print("\n" + vertices[i] + " -> " + vertices[j] + " \t "+distancias[j] + "\n" );
                   imprimirPadres(j, padres, vertices);
                }
            }
        }
    }
    // Metodo recursivo que llama sucesivamente a los padres de un vertice hasta llegar a un vertice cuyo padre es null
    private static void imprimirPadres(int vertice, String[] padres, String[] vertices){
        int indicePadre = 0;
        if(padres[vertice]==null){
            return;
        }
        for(int i=0 ; i<vertices.length ; i++){
            if(padres[vertice].equals(vertices[i])){
                indicePadre = i;
            }
        }
        imprimirPadres(indicePadre, padres, vertices);
        System.out.println(vertices[vertice] + " ");
    }
}
