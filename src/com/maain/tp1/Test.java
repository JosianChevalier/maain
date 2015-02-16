package com.maain.tp1;

import java.io.*;

public class Test {

    /*
        Test de l'exercice 1
     */
    public void test_exercice_1(){
        System.out.println("Exercice 1 :");

        Integer[] L = {0, 3, 5, 5, 6};
        Float[] C = {3f, 5f, 8f, 1f, 2f, 3f};
        Integer[] I = {1, 2, 3, 0, 2, 1};
        Matrice m = new Matrice(C, I, L);

//        Matrice m=new Matrice(4);
//        m.changeValue(0,1,3);
//        m.changeValue(0, 2, 5);
//        m.changeValue(0, 3, 8);
//        m.changeValue(1,0,1);
//        m.changeValue(1,2,2);
//        m.changeValue(3,1,3);

        float[] vect = {3, 2, 7, 5};

        System.out.println("\nMatrice : " + m.toString());
        System.out.print("Vecteur : ");
        m.print_float_table(vect);
        System.out.print("Result : ");
        m.print_float_table(m.mult_vect(vect));
        System.out.println();
    }

    public boolean test_file(String filename){
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            int node_aux = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                int x = Integer.parseInt[values[0];
                if(node_aux == null){
                    node_aux = Integer.parseInt(values[0]);
                } else if(node_aux != Integer.parseInt(changeValue)) {

                }
            }
            br.close();
       } catch(IOException e){
            System.out.println("'" + filename + "' doesn't exist.");
       } 
       return true;
    }
}
