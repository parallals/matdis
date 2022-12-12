package profesoraencina;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ProfesoraEncina {
    public static void main(String args[]){
/*_____________________________LECTURA DEL .TXT__________________________________*/
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
        String[] desconfianza = null;
        String[][] aux = null;
        int m = 0;
        while (sc.hasNextLine()){
            n = Integer.parseInt(sc.nextLine());
            aux = new String[n][n];
            entrenadores = new String[n];
            desconfianza = new String[n];
            for(int i=0 ; i<n ; i++){
                String datos = sc.nextLine();
                String[] d = datos.split(" ");
                entrenadores[i] = d[0];
                aux[i][0] = d[1]; 
                for(int j=0 ; j<Integer.parseInt(d[1]) ; j++){
                    aux[i][1+j] = d[2+j];
                }
                m = m + Integer.parseInt(d[1]);
                desconfianza[i] = d[2+Integer.parseInt(d[1])];
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
/*________________________PASANDO ARREGLOS A CLASE GRAFO_______________________________*/
        Grafo digrafo = new Grafo();
        for(int i=0; i<entrenadores.length ; i++){
            digrafo.vertices.add(new Vertice());
        }
        for(int i=0; i<entrenadores.length ; i++){
            digrafo.vertices.get(i).nombre = i;  // Indice del entrenador en nuestro arreglo
            for(int j=0 ; j<contactos.length ; j++){
                if(contactos[j][0].equals(entrenadores[i])){
                    for(int k=0 ; k<entrenadores.length ; k++){
                        if(contactos[j][1].equals(entrenadores[k])){
                            //System.out.println(entrenadores[i] + " llama a " + entrenadores[k]);
                            digrafo.vertices.get(i).VecindadSalida.add(digrafo.vertices.get(k));
                        }
                    }
                }
                if(contactos[j][1].equals(entrenadores[i])){
                    for(int k=0 ; k<entrenadores.length ; k++){
                        if(contactos[j][0].equals(entrenadores[k])){
                            //System.out.println(entrenadores[i] + " es llamada por " + entrenadores[k]);
                            digrafo.vertices.get(i).VecindadEntrada.add(digrafo.vertices.get(k));
                        }
                    }
                }
            }
        }
        for(int i=0; i<entrenadores.length ; i++){
            if(desconfianza[i].equals("yes")){
                for(int j=0; j<digrafo.vertices.get(i).VecindadEntrada.size() ; j++){
                    boolean flag = false;
                    int idEliminado;
                    for(int k=0 ; k<digrafo.vertices.get(i).VecindadSalida.size() ; k++){
                        if(digrafo.vertices.get(i).VecindadEntrada.get(j).nombre  == digrafo.vertices.get(i).VecindadSalida.get(k).nombre ){
                            flag = true;
                        }
                    }
                    idEliminado = digrafo.vertices.get(i).VecindadEntrada.get(j).nombre;
                    if(flag == false){
                        for(int k=0 ; k<digrafo.vertices.get(idEliminado).VecindadSalida.size() ; k++){
                            if(digrafo.vertices.get(idEliminado).VecindadSalida.get(k).nombre  == digrafo.vertices.get(i).nombre ){
                                digrafo.vertices.get(idEliminado).VecindadSalida.remove(k);
                                break;
                            }
                        }
                        digrafo.vertices.get(i).VecindadEntrada.remove(j);
                        j = j-1;
                    }
                }
            }
        }
/*_______________________________IMPRIMIR GRAFO __________________________________*/
/*
        for(int i=0 ; i<digrafo.vertices.size() ; i++){
            System.out.println(entrenadores[digrafo.vertices.get(i).id]);
            for(int j=0 ; j<digrafo.vertices.get(i).inVecinos.size() ; j++){
                System.out.print(entrenadores[digrafo.vertices.get(i).inVecinos.get(j).id] + "       ");
            }
            System.out.println();
            for(int j=0 ; j<digrafo.vertices.get(i).outVecinos.size() ; j++){
                System.out.print(entrenadores[digrafo.vertices.get(i).outVecinos.get(j).id] + "       ");
            }
            System.out.println("\n_______________________________________________________");
        }
*/
/*_______________________________GRAFO TERMINADO__________________________________*/
      AlgoritmoKujaru.algoritmoKujaru(digrafo);
        
        
    }
}
// ProfesoraEncina.txt