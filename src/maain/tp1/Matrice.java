package maain.tp1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Matrice {
    LinkedList<Float> C;
    LinkedList<Integer> L;
    LinkedList<Integer> I;
    int n = 0;

    /**
     * Instancier une matrice avec les vecteurs CIL
     *
     * @param C
     * @param I
     * @param L
     */
    Matrice(Float[] C, Integer[] I, Integer[] L) {
        this.C = new LinkedList<Float>(Arrays.asList(C));
        this.L = new LinkedList<Integer>(Arrays.asList(L));
        this.I = new LinkedList<Integer>(Arrays.asList(I));
        this.n = L.length - 1;
    }

    /**
     * Instancier une matrice avec uniquement sa taille, pour rajouter des éléments non nuls par la suite
     *
     * @param size
     */
    Matrice(int size) {
        this.C = new LinkedList<Float>();
        this.L = new LinkedList<Integer>();
        this.I = new LinkedList<Integer>();
        this.n = size;
        for (int i = 0; i <= this.n; i++) {
            this.L.add(0);
        }
    }

    /**
     * Changer une case de la matrice
     *
     * @param line
     * @param column
     * @param value
     */
    public void changeValue(int line, int column, float value) {
        int line_start = L.get(line), line_end = L.get(line + 1), index = 0; //On récupère en temps constant les positions
        //de début et de fin d'une ligne à l'aide de L
        Iterator<Integer> it = I.subList(line_start, I.size()).iterator();
        boolean found = false; //found indique si l'on a déjà trouvé la position de la case dans C

        if (line_end - line_start > 0) { //Si la ligne a au moins une valeur non vide, on cherche la case à modifier
            int current;
            while (!found && index < line_end - line_start) { //tant que l'on a pas trouvé la case et que la ligne n'est pas entièrement parcourue
                current = it.next();


                if (current == column) {
                    this.C.set(line_start + index, value); //Si l'on trouve la colone recherchée dans la section de I
                    // correspondant à cette ligne, alors assigne value à la valeur correspondante dans C
                    found = true;

                } else if (current > column) { //si on a atteint une colonne d'indice plus élevé sans la trouver, alors
                    // on insère la valeur juste avant la case courrante

                    this.C.add(line_start + index, value);
                    this.I.add(line_start + index, column);
                    this.modify_L(line + 1); //on modifie L en conséquence de l'insertion
                    found = true;
                }
                index++;
            }
        }
        if (!found) {//si la ligne est vide, on insère la valeur de la case.
            this.C.add(line_start + index, value);
            this.I.add(line_start + index, column);
            this.modify_L(line + 1); //on modifie L en conséquence de l'insertion
        }
    }

    /**
     * Incrémente les valeurs de L d'index supérieurs ou égaux à index_from
     *
     * @param index_from
     */
    private void modify_L(int index_from) {

        ListIterator<Integer> it_L = L.subList(index_from, L.size()).listIterator();
        int current_value;
        while (it_L.hasNext()) {
            current_value = it_L.next();
            it_L.set(current_value + 1);
        }
    }

    /**
     * Obtenir la valeur de la case à la ligne line et colonne column.
     *
     * @param line
     * @param column
     * @return
     */
    public Float getValue(int line, int column) {
        int line_start = L.get(line), line_end = L.get(line + 1), index = 0;
        Float result = 0f; //le résultat est 0 par défault

        if (line_end - line_start > 0) { //Si la ligne a au moins une valeur non vide, on cherche si le résultat est différent de 0
            Iterator<Integer> it = I.subList(line_start, I.size()).iterator();
            boolean found = false; //found indique si l'on a déjà trouvé la position de la case dans C
            int current;

            while (!found && index < line_end - line_start) { //tant que l'on a pas trouvé la case et que la ligne n'est pas entièrement parcourue
                current = it.next();

                if (current == column) {
                    result = this.C.get(line_start + index); //Si l'on trouve la colone recherchée dans la section de I
                    // correspondant à cette ligne, alors assigne la valeur correspondante dans C à result
                    found = true;

                } else if (current > column) { //si on a atteint une colonne d'indice plus élevé sans la trouver, alors la case vaut 0
                    found = true;
                }
                index++;
            }
        }
        return result;
    }

    public float[][] getMatrice() {
        float[][] mat = new float[n][n];

        for (int i = 0, k = 1; i < I.size(); i++) {
            if (i >= L.get(k)) {
                do {
                    k++;
                } while (L.get(k).equals(L.get(k - 1)));
            }
            mat[k - 1][I.get(i)] = C.get(i);
        }
        return mat;
    }

    public void print_float_table(float[] vecteur) {
        String str = "";
        for (int i = 0; i < vecteur.length; i++) {
            str += vecteur[i];
            str += (i == vecteur.length - 1) ? "" : ", ";
        }
        System.out.println("[ " + str + "]");
    }


    public int getN() {
        return n;
    }

    /*
     * Ex1
     */
    public float[] mult_vect(float[] vecteur) {
        float[] result = new float[n];
        for (int i = 1; i < L.size(); i++) {
            for (float j = L.get(i - 1); j < L.get(i); j++) {
                result[i - 1] += C.get((int) j) * vecteur[I.get((int) j)];
            }
        }
        return result;
    }

    /*
     * Ex2
     */
    public float[] mult_vect_transp(float[] vecteur) {
        float[] result = new float[n];

        for (int i = 1; i < L.size(); i++) {
            for (float j = L.get(i - 1); j < L.get(i); j++) {
                result[I.get((int) j)] += C.get((int) j) * vecteur[i - 1];
            }
        }
        return result;
    }

    /*
    *   Exo 5
    */

    public float[] mult_vect_transp_zap(float[] vecteur, float zap) {
        float[] result = this.mult_vect_transp(vecteur);

        // après avoir calculé le produit par la transposée, on modifie chaque valeur du vecteur afin de prendre en compte
        //le facteur de zap
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] * (1 - zap) + (zap / this.n);
        }

        return result;
    }

    @Override
    public String toString() {
        return "Matrice{" +
                "C=" + C +
                ", L=" + L +
                ", I=" + I +
                ", n=" + n +
                '}';
    }
}
