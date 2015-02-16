package com.maain.tp1;

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
//        m.print_float_table(m.mult_vect_transp(vect));
//        m.print_float_table(m.mult_vect_transp_zap(vect, 0.125f));
}
