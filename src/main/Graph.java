package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Graph {

    private boolean[][] adjacencyList;
    private int n;

    public Graph(int n, String path){
        adjacencyList = new boolean[n][n];
        this.n = n;

        try {
            InputStream flux = getClass().getResourceAsStream(path);
            InputStreamReader lecture = new InputStreamReader(flux);
            BufferedReader buff = new BufferedReader(lecture);

            String line;

            for (int i = 0; i < n; i++) {

                line = buff.readLine();

                for (int j = 0; j < n; j++) {
                    if (line.charAt(j) == '1') {
                        adjacencyList[j][i] = true;
                    }
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }

    private void printGraph(){
        String line;
        System.out.println("graph : ");
        for (int i = 0; i < n;i++){
            line = "";
            for (int j = 0; j < n;j++){
                if(adjacencyList[j][i]){
                    line = line + "1";
                }
                else {
                    line = line + "0";
                }
            }
            System.out.println(i + " " +line);
        }
    }

    public boolean[][] getAdjacencyList() {
        return adjacencyList;
    }

    public int getSize() {
        return n;
    }
}
