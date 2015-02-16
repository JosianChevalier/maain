package com.maain;

public class Main {

    public static void main(String [] args){

//        Integer[] L = {0, 3, 5, 5, 6};
//        Float[] C = {3f, 5f, 8f, 1f, 2f, 3f};
//        Integer[] I = {1, 2, 3, 0, 2, 1};
//        Matrice m2 = new Matrice(C, I, L);

        Matrice m=new Matrice(4);
        m.changeValue(0,1,3);
        m.changeValue(0, 2, 5);
        m.changeValue(0, 3, 8);
        m.changeValue(1,0,1);
        m.changeValue(1,2,2);
        m.changeValue(3,1,3);

        System.out.println(m.toString());

        float[] vect = {1, 1, 1, 1};
        m.print_float_table(m.mult_vect_transp(vect));
        m.print_float_table(m.mult_vect_transp_zap(vect, 0.125f));
        m.print_float_table(m.mult_vect(vect));
    }
}

