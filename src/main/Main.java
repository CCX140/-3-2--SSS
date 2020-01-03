package main;

public class Main {

    public static void main (String[] args){
        /*int n = 5;
        Algo algo = new Algo(n);*/

        Graph test = new Graph(30,"/main/graphs/test30");
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
        System.out.println(algo.run());

    }
}
