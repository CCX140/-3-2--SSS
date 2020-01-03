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
                    if( !contrainte[i][j][R][R]){
                        contrainte[i][j][R][R] = true;
                        nbBinaire++;
                    }
                    if( !contrainte[i][j][G][G]){
                        contrainte[i][j][G][G] = true;
                        nbBinaire++;
                    }
                    if( !contrainte[i][j][B][B]){
                        contrainte[i][j][B][B] = true;
                        nbBinaire++;
                    }


                }
            }
        }
    }


    /***
     *
     * @return true si le graphe est 3 coloriable
     */
    public boolean run(){
        boolean go = true;
        ArrayList<Couple> listCouple1;
        ArrayList<Couple> listCouple2;

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

                        break;
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

                        //on supprime la contrainte (x,coul3)
                        unaire[i][coul3] = false;
                        nbUnaire--;

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
                            if(contrainte[i][listCouple1.get(j).var][coul1][listCouple1.get(j).coul]){
                                contrainte[i][listCouple1.get(j).var][coul1][listCouple1.get(j).coul] = false;
                                nbBinaire --;
                            }
                            if(contrainte[listCouple1.get(j).var][i][listCouple1.get(j).coul][coul1]){
                                contrainte[listCouple1.get(j).var][i][listCouple1.get(j).coul][coul1] = false;
                                nbBinaire --;
                            }
                            if(contrainte[i][listCouple2.get(j).var][coul2][listCouple2.get(j).coul]){
                                contrainte[i][listCouple2.get(j).var][coul2][listCouple2.get(j).coul] = false;
                                nbBinaire --;
                            }
                            if(contrainte[listCouple2.get(j).var][i][listCouple2.get(j).coul][coul2]){
                                contrainte[listCouple2.get(j).var][i][listCouple2.get(j).coul][coul2] = false;
                                nbBinaire --;
                            }
                        }
                        break;
                    }
                }
            }
            else { // Si il n'y a pas de contraintes unaires
                if(nbBinaire > 0 ){ // si il y a des contraintes binaire

                    /* CAS 4 */

                    //generation aléatoire avec une propa de 1/4
                    int rand = (int)(Math.random() * 4);

                    //trouve la premiere contrainte binaire
                    int var1 = -1;
                    int var2 = -1;
                    int coul1 = -1;
                    int coul2 = -1;

                    boolean found = false;
                    //trouve la premiere contrainte binaire [(x, A),(y, B)] et la place dans les variables ci dessus
                    for(int i = 0;i < n;i++){
                        for(int j = 0;j < n;j++){
                            for(int k = 0;k < 3;k++){
                                for(int l = 0;l < 3;l++){

                                    if(contrainte[i][j][k][l]){

                                        var1 = i;
                                        var2 = j;
                                        coul1 = k;
                                        coul2 = l;
                                        found = true;
                                    }
                                    if(found){
                                        break;
                                    }
                                }
                                if(found){
                                    break;
                                }
                            }
                            if(found){
                                break;
                            }
                        }
                        if(found){
                            break;
                        }
                    }

                    // ajoute des contraintes unaires selon le resultat du random

                    int coul3 = -1;
                    int coul4 = -1;
                    int coul5 = -1;
                    int coul6 = -1;

                    //on determine les couleurs de x et y qui ne sont pas dans la contrainte binaire pour ensuite creer les contraintes unaires
                    //on range ces couleurs dans coul3 et coul4 pour x et dans coul5 et coul6 pour y
                    if(coul1 == B){
                        coul3 = G;
                        coul4 = R;
                    }
                    else if(coul1 == G){
                        coul3 = B;
                        coul4 = R;
                    }
                    else if(coul1 == R){
                        coul3 = B;
                        coul4 = G;
                    }

                    if(coul2 == B){
                        coul5 = G;
                        coul6 = R;
                    }
                    else if(coul2 == G){
                        coul5 = B;
                        coul6 = R;
                    }
                    else if(coul2 == R){
                        coul5 = B;
                        coul6 = G;
                    }



                    // si la contraite est [(x, R),(y, B)]
                    if(rand==0){ // ajoute [(x, R)] et [(y, R)]
                        if(!unaire[var1][coul1]){
                            unaire[var1][coul1] = true;
                            nbUnaire++;
                        }
                        if(!unaire[var2][coul5]){
                            unaire[var2][coul5] = true;
                            nbUnaire++;
                        }
                    }
                    else if(rand==1){ //ajoute [(x, R)] et [(y, V )]
                        if(!unaire[var1][coul1]){
                            unaire[var1][coul1] = true;
                            nbUnaire++;
                        }
                        if(!unaire[var2][coul6]){
                            unaire[var2][coul6] = true;
                            nbUnaire++;
                        }
                    }
                    else if(rand==2){ //ajoute [(x, B)] et [(y, B)]
                        if(!unaire[var2][coul2]){
                            unaire[var2][coul2] = true;
                            nbUnaire++;
                        }
                        if(!unaire[var1][coul3]){
                            unaire[var1][coul3] = true;
                            nbUnaire++;
                        }
                    }
                    else if(rand==3){ // ajoute [(x, V )] et [(y, B)]
                        if(!unaire[var2][coul2]){
                            unaire[var2][coul2] = true;
                            nbUnaire++;
                        }
                        if(!unaire[var1][coul4]){
                            unaire[var1][coul4] = true;
                            nbUnaire++;
                        }
                    }
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
