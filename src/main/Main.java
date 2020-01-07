package main;

public class Main {

    public static void main (String[] args){
        /*int n = 5;
        Algo algo = new Algo(n);*/

        Graph test = new Graph(30,"/main/graphs/test30");
        Graph test2 = new Graph(5,"/main/graphs/graph5");
        Graph g1 = new Graph(30,"/main/graphs/graph30");
        Graph g2 = new Graph(50,"/main/graphs/graph50");
        Graph g3 = new Graph(55,"/main/graphs/graph55");
        Graph g4 = new Graph(60,"/main/graphs/graph60");
        Graph g5 = new Graph(65,"/main/graphs/graph65");
        Graph g6 = new Graph(70,"/main/graphs/graph70");
        Graph g7 = new Graph(75,"/main/graphs/graph75");
        Graph g8 = new Graph(80,"/main/graphs/graph80");
        Graph g9 = new Graph(85,"/main/graphs/graph85");
        Graph g10 = new Graph(90,"/main/graphs/graph90");
        Graph g11= new Graph(100,"/main/graphs/graph100");
        Graph g12= new Graph(200,"/main/graphs/graph200");
        Graph g13= new Graph(200,"/main/graphs/graph200_2");

        Algo algo = new Algo(test);
        System.out.println("Graphe test : " + algo.runNtimes((int) (10*Math.pow(2,30/2))));

        algo = new Algo(g1);
        System.out.println("Graphe a 30 sommets : " + algo.runNtimes((int) (10*Math.pow(2,30/2))));

        algo = new Algo(g2);
        System.out.println("Graphe a 50 sommets : " +algo.runNtimes((int) (10*Math.pow(2,50/2))));

        algo = new Algo(g3);
        System.out.println("Graphe a 55 sommets : " + algo.runNtimes((int) (10*Math.pow(2,55/2))));

        algo = new Algo(g4);
        System.out.println("Graphe a 60 sommets : " + algo.runNtimes((int) (10*Math.pow(2,60/2))));

        algo = new Algo(g5);
        System.out.println("Graphe a 65 sommets : " + algo.runNtimes((int) (10*Math.pow(2,65/2))));

        algo = new Algo(g6);
        System.out.println("Graphe a 70 sommets : " + algo.runNtimes((int) (10*Math.pow(2,70/2))));

        algo = new Algo(g7);
        System.out.println("Graphe a 75 sommets : " + algo.runNtimes((int) (10*Math.pow(2,75/2))));

        algo = new Algo(g8);
        System.out.println("Graphe a 80 sommets : " + algo.runNtimes((int) (10*Math.pow(2,80/2))));

        algo = new Algo(g9);
        System.out.println("Graphe a 85 sommets : " + algo.runNtimes((int) (10*Math.pow(2,85/2))));

        algo = new Algo(g10);
        System.out.println("Graphe a 90 sommets : " + algo.runNtimes((int) (10*Math.pow(2,90/2))));

        algo = new Algo(g11);
        System.out.println("Graphe a 100 sommets : " + algo.runNtimes((int) (10*Math.pow(2,100/2))));

        algo = new Algo(g12);
        System.out.println("Graphe a 200 sommets : " + algo.runNtimes((int) (10*Math.pow(2,200/2))));

        algo = new Algo(g13);
        System.out.println("Graphe a 200 sommets : " + algo.runNtimes((int) (10*Math.pow(2,200/2))));

    }
}
