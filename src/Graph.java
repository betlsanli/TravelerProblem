import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Graph {
    private HashMap<String, ArrayList<Edge>> network;
    private ArrayList<String> cities;


    public Graph(String[] cities){
        network = new HashMap<>();
        this.cities = new ArrayList<>(Arrays.asList(cities));
    }

    public void addEdges(String from, String to, String type){
        network.computeIfAbsent(from, v -> new ArrayList<>()).add(new Edge(to, type));

//        assuming matrix is correct, edges will be bidirectional when edges of to is being added anyway
//        network.computeIfAbsent(to, v -> new ArrayList<>()).add(new Edge(from, type));
    }

    public ArrayList<String> query2(String from, String to, int n){
        boolean[] visited = new boolean[cities.size()];
        ArrayList<String> allPaths =new ArrayList<>();
        query2dfs(from, to, n, visited, from + " ", allPaths);
        return allPaths;
    }

    private void query2dfs(String from, String to, int n, boolean[] visited, String currentPath, ArrayList<String> allPaths){
        visited[cities.indexOf(from)] = true;

        if(from.equals(to) && n == -1){
            allPaths.add(currentPath);
        }
        else{
            for(Edge edge : network.get(from)){
                if(!visited[cities.indexOf(edge.toCity())]){
                    query2dfs(edge.toCity(), to, n-1, visited, currentPath + edge, allPaths);
                }
            }
        }
        visited[cities.indexOf(from)] = false; //backtrack
    }

    @Override
    public String toString() {
        return "network=" + network;
    }
}
