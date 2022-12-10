package bianca;
import java.io.File;
import java.util.Scanner;

public class Bianca{
    public static void main(String args[]){
        String filename = "Bianca.txt";
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
        } catch(Exception e){
            System.err.println("Error al leer archivo.");
        }
        
        int n = 0, m = 0,k = 0;
        String[] ciudades = null;
        String[][] rutas = null;
        String[] camino = null;
        int[] entrenadores = null;
        while (sc.hasNextLine()){
            n = Integer.parseInt(sc.nextLine());
            ciudades = new String[n];
            for(int i=0;i<n;i++){
                ciudades[i] = sc.nextLine();
            }
            
            m = Integer.parseInt(sc.nextLine());
            rutas = new String[m][2];
            entrenadores = new int[m];
            for(int i=0;i<m;i++){
                String ruta = sc.nextLine();
                String[] r = ruta.split(" ");
                rutas[i][0] = r[0];
                rutas[i][1] = r[1];
                entrenadores[i] = Integer.parseInt(r[2]);
           }
           k = Integer.parseInt(sc.nextLine());
           camino = new String[k];
           for(int i=0;i<k;i++){
                camino[i] = sc.nextLine();
           }
        }
        //Imprimir
        System.out.println("Ciudades: "+n);
        for(int i=0;i<n;i++){
        System.out.println(ciudades[i]);
        }
        System.out.println("Rutas: "+m);
        for(int i=0;i<m;i++){
            System.out.println("De "+rutas[i][0]+" a "+rutas[i][1]+" con numeor de entrenadores "+entrenadores[i]);
        }
        System.out.println("Camino: "+k);
        for(int i=0;i<k;i++){
            System.out.println(camino[i]);
        }
    }
}