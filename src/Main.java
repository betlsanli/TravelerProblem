import java.io.*;
import java.util.ArrayList;

public class Main {

    private static Graph graph = null ;
    private static String[] cities = null;
    private static final File outputFile = new File("output.txt");
    private static final File transportation = new File("transportation_network.inp");
    private static final File queries = new File("queries.inp");
    private static  FileWriter fw;

    public static void main(String[] args) throws IOException {
        readNetwork();
        fw = new FileWriter(outputFile);
        readQueries();
        fw.close();
    }

    private static void readQueries() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(queries));
        String query = bf.readLine();
        while (query != null){
            String[] temp = query.split(" ");
            try{
                switch (temp[0]){
                    case "Q1":
                        String res = graph.query1(temp[1], temp[2], getConditions(temp));
                        fw.write(query + "\n");
                        if(res == null)
                            fw.write("Path not found\n");
                        else
                            fw.write(res + "\n");
                        break;
                    case "Q2":
                        ArrayList<String> result = graph.query2(temp[1], temp[2], Integer.parseInt(temp[3]));
                        fw.write(query + "\n");
                        if(result.isEmpty())
                            fw.write("Path not found\n");
                        else
                            fw.write(result + "\n");
                        break;
                    case "Q3":
                        result = graph.query3(temp[1], temp[2], TransportationType.getTransportationType(temp[3].charAt(0)));
                        fw.write(query + "\n");
                        if(result.isEmpty())
                            fw.write("Path not found\n");
                        else
                            fw.write(result + "\n");
                        break;
                    case "ADD":

                        fw.write(query + "\n");
                        if(temp[1].equals("Path")){
                            graph.addEdges(temp[2], temp[3], TransportationType.getTransportationType(temp[4].charAt(0)));
                            fw.write("Path added\n");
                        }
                        else if(temp[1].equals("City")){
                            graph.addCity(temp[2]);
                            fw.write("City added\n");
                        }

                        break;
                }
            }catch(IllegalOperationException ioe){
                fw.write(ioe.getMessage() + "\n");
            }
            query = bf.readLine();
        }
    }
    private static int[] getConditions(String[] temp){
        int size = temp.length - 3;
        int[] conditions = new int[size];
        for(int i = 3; i < temp.length; i++){
            int index = 0;
            if(temp[i].charAt(0) == 'A')
                index = TransportationType.AIRWAY.index;
            if(temp[i].charAt(0) == 'R')
                index = TransportationType.RAILWAY.index;
            if(temp[i].charAt(0) == 'H')
                index = TransportationType.HIGHWAY.index;
            conditions[index] = temp[i].charAt(1) - '0';
        }
        return conditions;
    }

    private static void readNetwork() throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(transportation));
        String type = bf.readLine();
        while(type != null){
            switch (type) {
                case "Airway" -> readMatrix(TransportationType.AIRWAY, bf);
                case "Railway" -> readMatrix(TransportationType.RAILWAY, bf);
                case "Highway" -> readMatrix(TransportationType.HIGHWAY, bf);
            }
            type = bf.readLine();
        }
        bf.close();
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