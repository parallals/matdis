package algoritmo_scc;
import java.util.ArrayList;
import java.util.*;
/*_________________________CLASE VERTICE___________________________________*/
class Vertice{
    
    public ArrayList<Vertice> VecindadSalida = new ArrayList<Vertice>();
    public ArrayList<Vertice> VecindadEntrada = new ArrayList<Vertice>();
    int nombre;
    Boolean visitado = false;  // Variables para el Strongly Connected Components ya inicializadas
    Boolean asignado = false;
}
/*_________________________CLASE GRAFO___________________________________*/
class Grafo{
    public ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    
/*_______________PROPIEDADES DEL STRONGLY CONNECTED COMPONENTS___________________*/
    public HashMap<Integer, ArrayList<Vertice>> scc = new HashMap<Integer, ArrayList<Vertice>>(); // almacenamiento de los Strongly Connected Components
    private ArrayList<Vertice> aux = new ArrayList<Vertice>();  // Arreglo auxiliar para el algoritmo
/*_______________METODOS DEL STRONGLY CONNECTED COMPONENTS___________________*/
    // Metodo visitar recursivo llamado por StronglyConnectedComponents()
    private void visitar(Vertice vertice){
        if(vertice.visitado){
            return;
        }
        vertice.visitado = true;
        for(int i = 0; i < vertice.VecindadSalida.size(); i++){
            visitar(vertice.VecindadSalida.get(i));
        }
        aux.add(0, vertice);
    }
    // Metodo asignar recursivo llamado por StronglyConnectedComponents()
    private void asignar(Vertice u, Vertice padres){
        if(u.asignado){         
            return;
        }
        if(!scc.containsKey(padres.nombre)){
            scc.put(padres.nombre, new ArrayList<Vertice>());
        }
        scc.get(padres.nombre).add(u);
        for(int i = 0; i < u.VecindadEntrada.size(); i++){
            asignar(u.VecindadEntrada.get(i), padres);
        }
    }   
    // Metodo que llamaremos una vez realizara los llamados necesarios a visitar() y asignar()
    public void StronglyConnectedComponents(){
        // visitar() para todos los vertices
        for(int i = 0; i < vertices.size(); i++){
            visitar(vertices.get(i));
        }
        // Y ahora usamos asignar() para todos los vertices, en el orden que visitar nos ha dado en aux
        for(int i = 0; i < aux.size(); i++){
            asignar(aux.get(i), aux.get(i));
        }
    }
}
