package com.maain;

import java.util.Arrays;

public class PageRank {

    public PageRank() {
    }

    /**
     *
     * @param m matrice des probabilités
     * @param p
     * @param nb_pas
     * @return
     */
    public float[] rank(Matrice m, float[] p, int nb_pas){
        float[] result = p.clone();
        for (int i=0;i<nb_pas;i++){
            result=m.mult_vect_transp(result);
            System.out.println(result.toString());
        }
        return result;
    }

    public float[] rank_from_sommet(Matrice m, int nb_pas, int num_sommet){
        float[] p=new float[m.getN()];
        Arrays.fill(p,0);
        p[num_sommet]=1;
        return this.rank(m,p,nb_pas);
    }

    public float[] rank_zero(Matrice m, int nb_pas){
        return this.rank_from_sommet(m,nb_pas,0);
    }


}
