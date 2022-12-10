package enfermerajoey;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EnfermeraJoey {
    public static void main(String args[]){
        String filename = "EnfermeraJoey.txt";
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
        int n = 0, m = 0;
        String[] ciudades = null;
        String[][] rutas = null;
        while (sc.hasNextLine()){
            n = Integer.parseInt(sc.nextLine());
            ciudades = new String[n];
            for(int i=0 ; i<n ; i++){
                ciudades[i] = sc.nextLine();
            }
            m = Integer.parseInt(sc.nextLine());
            rutas = new String[m][2];
            for(int i=0 ; i<m ; i++){
                String ruta = sc.nextLine();
                String[] r = ruta.split(" ");
                rutas[i][0] = r[0];
                rutas[i][1] = r[1];
           }
        }
        //Imprimir
        System.out.println("Ciudades: "+n);
        for(int i=0;i<n;i++){
        System.out.println(ciudades[i]);
        }
        System.out.println("Rutas: "+m);
        for(int i=0;i<m;i++){
            System.out.println("De "+rutas[i][0]+" a "+rutas[i][1]);
        }
    }
}