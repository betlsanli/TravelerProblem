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
        paths = graph.query3("Istanbul", "Ankara", TransportationType.HIGHWAY);
        System.out.println(paths);
        int[] arr = {1,2,1};
        String path = graph.query1("Istanbul", "Ankara", arr);
        System.out.println(path);
    }

    private static void readNetwork() throws IOException {
        File input = new File("transportation_network.inp");
        BufferedReader bf = new BufferedReader(new FileReader(input));
        String type = bf.readLine();
        while(type != null){
            switch (type) {
                case "Airway" -> readMatrix(TransportationType.AIRWAY, bf);
                case "Railway" -> readMatrix(TransportationType.RAILWAY, bf);
                case "Highway" -> readMatrix(TransportationType.HIGHWAY, bf);
            }
            type = bf.readLine();
        }
    }
    private static void readMatrix(TransportationType type, BufferedReader bf) throws IOException {
        if(graph == null){
            cities = bf.readLine().replace("Cities", "").trim().split(" ");
            graph = new Graph(cities);
        }
        else //reads cities line
            bf.readLine();
        for (String city : cities) {
            String[] temp = bf.readLine().replace(city, "").trim().split(" ");
            for (int j = 0; j < temp.length; j++) {
                if (temp[j].equals("1")) {
                    graph.addEdges(city, cities[j], type);
                }
            }
        }
    }
}