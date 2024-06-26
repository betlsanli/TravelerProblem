import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Graph {
    private final HashMap<String, ArrayList<Edge>> network;
    private final ArrayList<String> cities;


    public Graph(String[] cities){
        network = new HashMap<>();
        this.cities = new ArrayList<>(Arrays.asList(cities));
    }

    public void addEdges(String from, String to, TransportationType type){
        if(!cities.contains(from))
            throw new IllegalOperationException("Graph does not have the city " + from);
        if(!cities.contains(to))
            throw new IllegalOperationException("Graph does not have the city " + to);
        ArrayList<Edge> edges = network.computeIfAbsent(from, v -> new ArrayList<>());
        Edge edge = new Edge(to, type);
        if(!edges.contains(edge))
            edges.add(edge);
        edges = network.computeIfAbsent(to, v -> new ArrayList<>());
        edge = new Edge(from, type);
        if(!edges.contains(edge))
            edges.add(edge);
    }
    public void addCity(String city){
        if(cities.contains(city))
            throw new IllegalOperationException("City already exists");
        cities.add(city);
    }

    public String query1(String from, String to, int[] conditions){
        if(!cities.contains(from))
            throw new IllegalOperationException("Graph does not have the city " + from);
        if(!cities.contains(to))
            throw new IllegalOperationException("Graph does not have the city " + to);
        boolean[] visited = new boolean[cities.size()];
        return query1dfs(from, to, conditions, visited, from + " ");
    }
    private String query1dfs(String from, String to, int[] conditions, boolean[] visited, String path){
        visited[cities.indexOf(from)] = true;

        if (from.equals(to) && checkConditions(conditions)){
            return path;
        }

        ArrayList<Edge> edges = network.get(from);
        Collections.shuffle(edges);

        for(Edge edge : edges){
            if(!visited[cities.indexOf(edge.toCity())] && conditions[edge.getType().index] > 0){
                conditions[edge.getType().index]--;
                String res = query1dfs(edge.toCity(), to, conditions,visited,path + edge);
                conditions[edge.getType().index]++; //backtrack
                if (res != null) {
                    return res;
                }
            }
        }
        visited[cities.indexOf(from)] = false;//backtrack
        return null;
    }

    private boolean checkConditions(int[] conditions){
        for(int i : conditions){
            if(i != 0)
                return false;
        }
        return true;
    }

    public ArrayList<String> query2(String from, String to, int n){
        if(!cities.contains(from))
            throw new IllegalOperationException("Graph does not have the city " + from);
        if(!cities.contains(to))
            throw new IllegalOperationException("Graph does not have the city " + to);
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

    public ArrayList<String> query3(String from,String to, TransportationType type){
        if(!cities.contains(from))
            throw new IllegalOperationException("Graph does not have the city " + from);
        if(!cities.contains(to))
            throw new IllegalOperationException("Graph does not have the city " + to);
        boolean[] visited = new boolean[cities.size()];
        ArrayList<String> allPaths =new ArrayList<>();
        query3dfs(from, to, type, visited, from + " ", allPaths);
        return allPaths;
    }
    private void query3dfs(String from, String to, TransportationType type, boolean[] visited, String currentPath, ArrayList<String> allPaths){
        visited[cities.indexOf(from)] = true;

        if(from.equals(to)){
            allPaths.add(currentPath);
        }
        else{
            for(Edge edge : network.get(from)){
                if(!visited[cities.indexOf(edge.toCity())] && edge.getType() == type){
                    query3dfs(edge.toCity(), to, type, visited, currentPath + edge, allPaths);
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
