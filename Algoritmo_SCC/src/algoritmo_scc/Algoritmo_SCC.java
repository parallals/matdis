package algoritmo_scc;
import java.util.ArrayList;
import java.util.*;

class Vertice{
    //Elementos básicos del grafo
    public ArrayList<Vertice> inVecinos = new ArrayList<Vertice>();
    public ArrayList<Vertice> outVecinos = new ArrayList<Vertice>();
    int id;  //para identificar

    //Variables auxiliares para hacer el SCC
    Boolean visited = false;
    Boolean assigned = false;
}

class Grafo{
    //Los Vertices del grafo:
    public ArrayList<Vertice> vertices = new ArrayList<Vertice>();

    //Funciones y otros para SCC:

    //Aqui se guardarán las SCC:
    public HashMap<Integer, ArrayList<Vertice>> scc;;

    //Arreglo auxiliar para el algoritmo:
    private ArrayList<Vertice> l;      

    public void visit(Vertice u){
        //Si u ya fue visitado no se hace nada
        if(u.visited){
            return;
        }
        //Si u no ha sido visitado
        u.visited = true;                               //Se marca u como visitado
        for(int i = 0; i < u.outVecinos.size(); i++){   //Por cada out vecino v de u se hace visit(v)
            visit(u.outVecinos.get(i));
        }
        l.add(0, u);                                    //prepend u a l
    }

    public void assign(Vertice u, Vertice root){
        //Si u ya tiene root no se hace nada
        if(u.assigned){         
            return;
        }
        //Si no existe el componente con la raiz root se crea uno y se pone a root ahí
        if(!scc.containsKey(root.id)){
            scc.put(root.id, new ArrayList<Vertice>());
        }
        scc.get(root.id).add(u);                        //Se añade u al componente con raiz root
        for(int i = 0; i < u.inVecinos.size(); i++){
            assign(u.inVecinos.get(i), root);           //Por cada in vecino v de u se hace assign(v, root)
        }
    }   
    
    public void sacarScc(){
        //Paso 1
        scc = new HashMap<Integer, ArrayList<Vertice>>();   //Las key del hashmap son el id del vertice raiz del componente
        l = new ArrayList<Vertice>();                       //Let l be empty

        //Para cada vertice u del grafico marca u como no visitado
        for(int i = 0; i < vertices.size(); i++){
            vertices.get(i).visited = false;
            vertices.get(i).assigned = false;
        }

        //Paso 2
        //Para cada vertice u del grafico se hace visit(u)
        for(int i = 0; i < vertices.size(); i++){
            visit(vertices.get(i));
        }

        //Paso 3
        //para cada elemento u de L en orden se hace assign(u, u)
        for(int i = 0; i < l.size(); i++){
            assign(l.get(i), l.get(i));
        }
    }
}
public class Algoritmo_SCC {

    public static void main(String[] args) {
        
    }
    
}
