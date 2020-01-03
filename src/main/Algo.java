package main;

import java.util.ArrayList;

import static main.Constants.R;
import static main.Constants.G;
import static main.Constants.B;

public class Algo {

    private boolean[][][][] contrainte;
    private boolean[][] unaire;
    private boolean[] listVar;
    private int nbUnaire;
    private int nbBinaire;
    private int n;

    /***
     *
     * @param g
     */
    public Algo(Graph g){
        n = g.getSize();
        contrainte = new boolean[n][n][3][3];
        unaire = new boolean[n][3];
        listVar = new boolean[n];

        for(int i =0;i<n;i++){
            listVar[i] = true;
        }

        nbBinaire = 0;
        nbUnaire = 0;

        //initialisation des contraintes
        boolean[][] adjencyList = g.getAdjacencyList();

        for(int i = 0;i < n;i++){
            for(int j = 0;j < g.getSize();j++) {
                if(adjencyList[j][i]){
                    addBinaire(j,i,R, R);
                    addBinaire(j,i,B, B);
                    addBinaire(j,i,G, G);
                }
            }
        }
    }

    /***
     *
     * @param var1
     * @param var2
     * @param coul1
     * @param coul2
     */
    public void addBinaire(int var1, int var2, int coul1, int coul2){
        if( var1 < n && var1 >= 0 && var2 < n && var2 >= 0 ){
            if( coul1 >= 0 && coul1 < 3 && coul2 >= 0 && coul2 < 3){
                contrainte[var1][var2][coul1][coul2] = true;
                nbBinaire++;
            }
        }
    }

    /***
     *
     * @param var
     * @param coul
     */
    public void addUnaire(int var, int coul){
        if(var < n && var > 0 && coul >= 0 && coul < 3){
            unaire[var][coul] = true;
            nbUnaire++;
        }
    }

    /***
     *
     * @param var1
     * @param var2
     * @param coul1
     * @param coul2
     */
    public void removeBinaire(int var1, int var2, int coul1, int coul2){
        if( var1 < n && var1 >= 0 && var2 < n && var2 >= 0 ){
            if( coul1 >= 0 && coul1 < 3 && coul2 >= 0 && coul2 < 3){
                contrainte[var1][var2][coul1][coul2] = false;

                nbBinaire--;
            }
        }
    }

    /***
     *
     * @param var
     * @param coul
     */
    public void removeUnaire(int var, int coul){
        if(var < n && var >= 0 && coul >= 0 && coul < 3){
            unaire[var][coul] = false;
            nbUnaire--;
        }
    }

    /***
     *
     * @return true si le graphe est 3 coloriable
     */
    public boolean run(){
        boolean go = true;
        ArrayList<Couple> listCouple1 = new ArrayList<>();
        ArrayList<Couple> listCouple2 = new ArrayList<>();

        while(go){
            if (nbUnaire > 0) { // si il y a des contraintes unaire, on applique les cas 1,2 et 3
                for (int i = 0; i < n; i++) { //compte le nombre de contraintes unaires associées à la variable i
                    int cpt = 0;
                    if (unaire[i][R])
                        cpt++;
                    if (unaire[i][G])
                        cpt++;
                    if (unaire[i][B])
                        cpt++;

                    /* CAS 1 */
                    if (cpt == 3)
                        return false; // Si x apparait dans 3 contraintes unaires differentes le graphe n'est pas coloriable

                    /* CAS 2 */
                    if (cpt == 2) { //Si x apparait dans exactement 2 contraintes unaires
                        int coul1 = -1; //premiere couleur
                        int coul2 = -1; //deuxieme couleur
                        int coul3 = -1; //couleur dans aucune des deux contraintes

                        for (int j = 0; j < 3; j++) { //recupere les couleurs des deux contraintes dans les variables coul1 ,2 et 3
                            if (!unaire[i][j]) {
                                coul3 = j;
                            }
                            else {
                                if (coul1 == -1) {
                                    coul1 = j;
                                } else {
                                    coul2 = j;
                                }
                            }
                        }

                        for(int j = 0;j<n;j++){ //on supprime les contraintes binaires contenant (x,coul1) ou (x,coul2)
                            for(int k = 0;k < 3;k++){
                                if(contrainte[i][j][coul1][k]){ //si la contrainte binaire contient (x,coul1) on la supprime
                                    contrainte[i][j][coul1][k] = false;
                                    nbBinaire--;
                                }
                                if(contrainte[i][j][coul2][k]){ //si la contrainte binaire contient (x,coul2) on la supprime
                                    contrainte[i][j][coul2][k] = false;
                                    nbBinaire--;
                                }
                                if(contrainte[i][j][coul3][k]){ //si la contrainte binaire contient (x,coul3) on la supprime puis on ajoute une contrainte unaire (j,k)
                                    contrainte[i][j][coul3][k] = false;
                                    nbBinaire--;
                                    if(!unaire[j][k]) {
                                        unaire[j][k] = true;
                                        nbUnaire++;
                                    }
                                }

                                if(contrainte[j][i][k][coul1]){ //si la contrainte binaire contient (x,coul1) on la supprime
                                    contrainte[j][i][k][coul1] = false;
                                    nbBinaire--;
                                }
                                if(contrainte[j][i][k][coul2]){ //si la contrainte binaire contient (x,coul2) on la supprime
                                    contrainte[j][i][k][coul2] = false;
                                    nbBinaire--;
                                }
                                if(contrainte[j][i][k][coul3]){ //si la contrainte binaire contient (x,coul3) on la supprime puis on ajoute une contrainte unaire (j,k)
                                    contrainte[j][i][k][coul3] = false;
                                    nbBinaire--;
                                    if(!unaire[j][k]) {
                                        unaire[j][k] = true;
                                        nbUnaire++;
                                    }
                                }
                            }
                        }

                        //on supprime les contraintes unaires
                        unaire[i][coul1] = false;
                        nbUnaire--;
                        unaire[i][coul2] = false;
                        nbUnaire--;
                    }

                    /* CAS 3 */
                    if (cpt == 1) { //Si x apparait dans exactement 1 contrainte unaire
                        int coul1 = -1; //premiere couleur qui n'est pas dans la contrainte
                        int coul2 = -1; //deuxieme couleur qui n'est pas dans la contrainte
                        int coul3 = -1; //couleur dans la contrainte

                        for (int j = 0; j < 3; j++) { //recupere la couleur de la contrainte dans la variable coul3 et les autres dans coul1 et 2
                            if (unaire[i][j]) {
                                coul3 = j;
                            }
                            else {
                                if (coul1 == -1) {
                                    coul1 = j;
                                } else {
                                    coul2 = j;
                                }
                            }
                        }

                        for(int j = 0;j<n;j++) { //on supprime les contraintes binaires contenant (x,coul3)
                            for (int k = 0; k < 3; k++) {
                                if(contrainte[i][j][coul3][k]){
                                    contrainte[i][j][coul3][k] = false;
                                    nbBinaire--;
                                }
                                if(contrainte[j][i][k][coul3]){
                                    contrainte[j][i][k][coul3] = false;
                                    nbBinaire--;
                                }
                            }
                        }

                        // stockage des variables et couleurs des couples a creer

                        listCouple1 = new ArrayList<>(); //reinitialisation des liste pour la creation des couples
                        listCouple2 = new ArrayList<>();

                        for(int j = 0;j<n;j++) { //on recupere les contraintes contenant (x,coul1) ou (x,coul2) pour creer les couples
                            for (int k = 0; k < 3; k++) {
                                if(contrainte[i][j][coul1][k] || contrainte[j][i][k][coul1]){
                                    listCouple1.add(new Couple(j,k));
                                }
                                if(contrainte[i][j][coul2][k] || contrainte[j][i][k][coul2]){
                                    listCouple2.add(new Couple(j,k));
                                }
                            }
                        }

                        int nbcouple = min(listCouple1.size(),listCouple2.size());
                        System.out.println("taille liste 1 : "+listCouple1.size()+ " taille liste 2 : "+listCouple2.size());
                        for(int j = 0;j<nbcouple;j++){
                            // on cree les couples p (on ajoute une contraite binaire [(y,B)(z,C)])
                            if(listCouple1.get(j).var != listCouple2.get(j).var && listCouple1.get(j).coul != listCouple2.get(j).coul) {
                                if (!contrainte[listCouple1.get(j).var][listCouple2.get(j).var][listCouple1.get(j).coul][listCouple2.get(j).coul]) {
                                    contrainte[listCouple1.get(j).var][listCouple2.get(j).var][listCouple1.get(j).coul][listCouple2.get(j).coul] = true;
                                    nbBinaire++;
                                }
                            }
                            else{ // si les couples sont identiques, on ajoute une contrainte unaire aulieu d'une binaire
                                if(!unaire[listCouple1.get(j).var][listCouple1.get(j).coul]){
                                    unaire[listCouple1.get(j).var][listCouple1.get(j).coul] = true;
                                    nbUnaire++;
                                }
                            }

                            // puis on supprime les contraintes binaires contenant (x,coul1) ou (x,coul2)
                            if(contrainte[i][listCouple1.get(j).var][coul1][listCouple1.get(j).var]){
                                contrainte[i][listCouple1.get(j).var][coul1][listCouple1.get(j).var] = false;
                                nbBinaire --;
                            }
                            if(contrainte[listCouple1.get(j).var][i][listCouple1.get(j).var][coul1]){
                                contrainte[listCouple1.get(j).var][i][listCouple1.get(j).var][coul1] = false;
                                nbBinaire --;
                            }
                            if(contrainte[i][listCouple2.get(j).var][coul2][listCouple2.get(j).var]){
                                contrainte[i][listCouple2.get(j).var][coul2][listCouple2.get(j).var] = false;
                                nbBinaire --;
                            }
                            if(contrainte[listCouple2.get(j).var][i][listCouple2.get(j).var][coul2]){
                                contrainte[listCouple2.get(j).var][i][listCouple2.get(j).var][coul2] = false;
                                nbBinaire --;
                            }
                        }


                    }
                }
            }
            else { // Si il n'y a pas de contraintes unaires
                if(nbBinaire > 0 ){ // si il y a des contraintes binaire

                }
                else { // Si il n'y a plus de contraintes on sort de la boucle et on retourne vrai
                    go = false;
                }
            }
        }
        return true;
    }

    public static int min(int a,int b){
        if(a <= b){
            return a;
        }
        return  b;
    }

}
