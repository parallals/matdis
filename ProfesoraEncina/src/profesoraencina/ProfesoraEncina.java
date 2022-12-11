package profesoraencina;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ProfesoraEncina {
    public static void main(String args[]){
        String filename = "ProfesoraEncina.txt";
        Scanner sc = null;
        if(args.length>0){
            filename = args[0];
        }else{
            System.out.println("Ingrese nombre de archivo:");
            sc = new Scanner(System.in);
            filename = sc.next();
        }
        File f = new File(filename);
        try{
            sc = new Scanner(f);
        } catch(FileNotFoundException e){
            System.err.println("Error al leer archivo.");
        }
        
        int n = 0;
        String[] entrenadores = null;
        String[][] contactos = null;
        String[] confianza = null;
        String[][] aux = null;
        int m = 0;
        while (sc.hasNextLine()){
            n = Integer.parseInt(sc.nextLine());
            aux = new String[n][n];
            entrenadores = new String[n];
            confianza = new String[n];
            for(int i=0 ; i<n ; i++){
                String datos = sc.nextLine();
                String[] d = datos.split(" ");
                entrenadores[i] = d[0];
                aux[i][0] = d[1]; 
                for(int j=0 ; j<Integer.parseInt(d[1]) ; j++){
                    aux[i][1+j] = d[2+j];
                }
                m = m + Integer.parseInt(d[1]);
                confianza[i] = d[2+Integer.parseInt(d[1])];
            }
            contactos = new String[m][2];
            m = 0;
            for(int i=0 ; i<n ; i++){
                for(int j=0 ; j<Integer.parseInt(aux[i][0]) ; j++){
                    contactos[m][0] = entrenadores[i];
                    contactos[m][1] = aux[i][1+j];
                    m = m+1;
                }
            }
        }
        //Imprimir
        System.out.println("entrenadores: "+n);
        for(int i=0 ; i<n ; i++){
        System.out.println(entrenadores[i]);
        }
        System.out.println("Contactos: "+m);
        for(int i=0 ; i<m ; i++){
            System.out.println(contactos[i][0]+" llama a "+contactos[i][1]);
        }
        System.out.println("Confianza: ");
        for(int i=0 ; i<n ; i++){
            System.out.println(confianza[i]);
        }
        
        Grafo digrafo = new Grafo();
        for(int i=0; i<entrenadores.length ; i++){
            digrafo.vertices.add(new Vertice());
        }
        for(int i=0; i<entrenadores.length ; i++){
            digrafo.vertices.get(i).id = i;  // Indice del entrenador en nuestro arreglo
            for(int j=0 ; j<contactos.length ; j++){
                if(contactos[j][0].equals(entrenadores[i])){
                    for(int k=0 ; k<entrenadores.length ; k++){
                        if(contactos[j][1].equals(entrenadores[k])){
                            digrafo.vertices.get(i).outVecinos.add(digrafo.vertices.get(k));
                        }
                    }
                }
            }
            for(int j=0 ; j<contactos.length ; j++){
                if(contactos[j][1].equals(entrenadores[i])){
                    for(int k=0 ; k<entrenadores.length ; k++){
                        if(contactos[j][0].equals(entrenadores[k])){
                            digrafo.vertices.get(i).inVecinos.add(digrafo.vertices.get(k));
                        }
                    }
                }
            }
        }
    }
}
// ProfesoraEncina.txt