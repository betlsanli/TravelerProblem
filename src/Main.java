import java.io.*;

public class Main {

    private static Graph graph = null ;
    private static String[] cities = null;

    public static void main(String[] args) throws IOException {
        readNetwork();
        System.out.println(graph);
    }

    private static void readNetwork() throws IOException {
        File input = new File("transportation_network.txt");
        BufferedReader bf = new BufferedReader(new FileReader(input));
        String type = bf.readLine();
        while(type != null){
            if(type.equals("Airway")){
                readMatrix("A", bf);
            }
            else if(type.equals("Railway")){
                readMatrix("R", bf);
            }
            else if(type.equals("Highway")){
                readMatrix("H", bf);
            }
            type = bf.readLine();
        }
    }
    private static void readMatrix(String type, BufferedReader bf) throws IOException {
        if(graph == null){
            cities = bf.readLine().replace("Cities", "").trim().split(" ");
            graph = new Graph(cities.length);
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