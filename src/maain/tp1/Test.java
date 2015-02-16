package maain.tp1;

import java.io.*;
import java.util.LinkedList;

public class Test {

    /*
        Test de l'exercice 1
     */
    public void test_exercice_1() {
        System.out.println("Exercice 1 :");

        Integer[] L = {0, 3, 5, 5, 6};
        Float[] C = {3f, 5f, 8f, 1f, 2f, 3f};
        Integer[] I = {1, 2, 3, 0, 2, 1};
        Matrice m = new Matrice(C, I, L);


        float[] vect = {3, 2, 7, 5};

        System.out.println("\nMatrice : " + m.toString());
        System.out.print("Vecteur : ");
        m.print_float_table(vect);
        System.out.print("Result : ");
        m.print_float_table(m.mult_vect(vect));
        System.out.println();
    }

    /*
        Test de l'exercice 2
     */
    public void test_exercice_2() {
        System.out.println("Exercice 2 :");

        Matrice m=new Matrice(4); // version alternative d'instancier une matrice
        m.changeValue(0,1,3);
        m.changeValue(0, 2, 5);
        m.changeValue(0, 3, 8);
        m.changeValue(1,0,1);
        m.changeValue(1,2,2);
        m.changeValue(3,1,3);

        float[] vect = {3, 2, 7, 5};

        System.out.println("\nMatrice : " + m.toString());
        System.out.print("Vecteur : ");
        m.print_float_table(vect);
        System.out.print("Result : ");
        m.print_float_table(m.mult_vect_transp(vect));
        System.out.println();
    }


    /*
            Test de l'exercice 3
     */

    public void test_exercice_3(){
        System.out.println("Exercice 3 :");
        Matrice[] m=new Matrice[5];
        try {
            m[0] = this.getMatrixFromFile("maain/tp1/resources/matrix.txt");
            m[1] = this.getMatrixFromFile("maain/tp1/resources/centrale.txt");
            m[2] = this.getMatrixFromFile("maain/tp1/resources/hamiltonien.txt");
            //m[3] = this.getMatrixFromFile("maain/tp1/resources/non_connexe.txt");
            m[4] = this.getMatrixFromFile("maain/tp1/resources/puit.txt");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            return;
        }

        //System.out.println("\nMatrice : " + m[0].toString());
        System.out.print("From 0 : ");
        System.out.print("Vecteur : ");
//        m.print_float_table(vect);
//        System.out.print("Result : ");
//        m.print_float_table(m.mult_vect_transp(vect));
//        System.out.println();
    }

    /*
            Fonctions de création d'une matrice à partir d'un fichier
     */

    public int matrix_size(String matrix_file) {
        int size = -1;
        try {
            BufferedReader br = new BufferedReader(new FileReader(matrix_file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                Integer x = Integer.parseInt(split[0]);
                Integer y = Integer.parseInt(split[1]);
                if(x > size){
                    size = x;
                }
                if(y > size){
                    size = y;
                }
            }

            br.close();
        } catch (IOException e) {
            System.out.println("'" + matrix_file + "' doesn't exist.");
        }

        return size;
    }

    public void push_matrice(Matrice m, int origin_node, LinkedList<Integer> values) {
        int size = values.size();
        if (size == 0) {
            return;
        }
        float value = 1 / (float) size;
        for (int i = 0; i < size; i++) {
            m.changeValue(origin_node, values.get(i), value);
        }
    }

    public Matrice getMatrixFromFile(String filename) throws IOException{
        Matrice m = new Matrice(this.matrix_size(filename)+1);
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            int pos = -1;
            LinkedList<Integer> values = new LinkedList<Integer>();
            while ((line = br.readLine()) != null) {
                String[] split = line.split(" ");
                Integer x = Integer.parseInt(split[0]);
                Integer y = Integer.parseInt(split[1]);
                if (pos == x) {
                    values.add(y);
                } else {
                    this.push_matrice(m, pos, values);
                    values = new LinkedList<Integer>();
                    values.add(y);
                    pos = x;
                }
            }
            this.push_matrice(m, pos, values);

            br.close();
        } catch (IOException e) {
            throw new IOException(e);
        }
        return m;
    }

    public boolean test_file(String filename) {
        System.out.println("\033[36m---------test_file---------!\033[0m");
        Matrice m;
        try {
            m=this.getMatrixFromFile(filename);
        } catch (IOException e) {
            System.out.println("'" + filename + "' doesn't exist.");
            return false;
        }

        float[][] mat = m.getMatrice();
        float[][] mat2 = {{0, 3, 3, 3}, {5, 0, 5, 0}, {0, 0, 0, 0}, {10, 0, 0, 0}};

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat2[i][j] != ((int) (10 * mat[i][j]))) {
                    System.out.println("M[" + i + "][" + j + "] = " + mat[i][j] + ", should equals : " + mat2[i][j] / 10);
                    return false;
                }
            }
        }
        System.out.println("\033[32m... ok !\033[0m");
        return true;
    }

}
