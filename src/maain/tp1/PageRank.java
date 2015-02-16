package maain.tp1;

import java.util.Arrays;

public class PageRank {

    public PageRank() {
    }

    /**
     *  Retourne la liste des probabilités d'être sur chaque sommet en partant de la liste de probabilité p
     * @param m matrice des probabilités
     * @param p
     * @param nb_pas
     * @return
     */
    public float[] rank(Matrice m, float[] p, int nb_pas){
        float[] result = p.clone();
        for (int i=0;i<nb_pas;i++){
            result=m.mult_vect_transp(result);
            m.print_float_table(result);
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

     /**
      * Retourne la liste des probabilités d'être sur chaque sommet en partant de la liste de probabilité p
      * pour l'algorithme zap
     * @param m matrice des probabilités
     * @param p
     * @param nb_pas
     * @return
     */
    public float[] rank_zap(Matrice m, float[] p, int nb_pas, float zap){
        float[] result = p.clone();
        for (int i=0;i<nb_pas;i++){
            result=m.mult_vect_transp_zap(result, zap);
            m.print_float_table(result);
        }
        return result;
    }

    public float[] rank_from_sommet_zap(Matrice m, int nb_pas, int num_sommet, float zap){
        float[] p=new float[m.getN()];
        Arrays.fill(p,0);
        p[num_sommet]=1;
        return this.rank_zap(m,p,nb_pas,zap);
    }

    public float[] rank_zero_zap(Matrice m, int nb_pas, float zap){
        return this.rank_from_sommet_zap(m,nb_pas,0, zap);
    }


}
