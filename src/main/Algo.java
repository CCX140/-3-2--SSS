package main;
public class Algo {

    private boolean[][][][] contrainte;
    private boolean[][] unaire;
    private boolean[] listVar;
    private int nbUnaire;
    private int nbBinaire;
    private int n;

    /***
     *
     * @param n
     */
    public Algo(int n){
        n = n;
        contrainte = new boolean[n][n][3][3];
        unaire = new boolean[n][3];
        listVar = new boolean[n];

        for(int i =0;i<n;i++){
            listVar[i] = true;
        }

        nbBinaire = 0;
        nbUnaire = 0;
    }

    /***
     *
     * @param var1
     * @param var2
     * @param coul1
     * @param coul2
     */
    public void addBinaire(int var1, int var2, int coul1, int coul2){
        if( var1 < n && var1 > 0 && var2 < n && var2 > 0 ){
            if( coul1 >= 0 && coul1 < 3 && coul2 >= 0 && coul2 < 3){
                contrainte[var1-1][var2-1][coul1][coul2] = true;
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
        if( var1 < n && var1 > 0 && var2 < n && var2 > 0 ){
            if( coul1 >= 0 && coul1 < 3 && coul2 >= 0 && coul2 < 3){
                contrainte[var1-1][var2-1][coul1][coul2] = false;
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
        if(var < n && var > 0 && coul >= 0 && coul < 3){
            unaire[var][coul] = false;
            nbUnaire--;
        }
    }

    /***
     *
     * @return
     */
    public boolean run(){
        return true;
    }

}
