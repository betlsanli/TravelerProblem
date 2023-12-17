import java.io.*;
import java.util.ArrayList;

public class Main {

    private static Graph graph = null ;
    private static String[] cities = null;

    public static void main(String[] args) throws IOException {
        readNetwork();
        System.out.println(graph);
        ArrayList<String> paths = graph.query2("Istanbul", "Ankara", 1);
        System.out.println(paths);
    }

    private static void readNetwork() throws IOException {
        File input = new File("transportation_network.txt");
        BufferedReader bf = new BufferedReader(new FileReader(input));
        String type = bf.readLine();
        while(type != null){
            switch (type) {
                case "Airway" -> readMatrix("A", bf);
                case "Railway" -> readMatrix("R", bf);
                case "Highway" -> readMatrix("H", bf);
            }
            type = bf.readLine();
        }
    }
    private static void readMatrix(String type, BufferedReader bf) throws IOException {
        if(graph == null){
            cities = bf.readLine().replace("Cities", "").trim().split(" ");
            graph = new Graph(cities.length, cities);
        }
        else //reads cities line
            bf.readLine();
        for(int i = 0; i < cities.length; i++){
            String[] temp = bf.readLine().replace(cities[i], "").trim().split(" ");
            for(int j = 0; j < temp.length; j++){
                if(temp[j].equals("1")){
                    graph.addEdges(i,j,type);
                }
            }
        }
    }
}