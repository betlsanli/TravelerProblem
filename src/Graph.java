import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<ArrayList<String>> matrix;
    private ArrayList<String> cities;


    public Graph(int cap, String[] cities){
        matrix = new ArrayList<ArrayList<String>>(cap);
        this.cities = new ArrayList<>(Arrays.asList(cities));
        initMatrix(cap);
    }

    private void initMatrix(int size){
        for(int i = 0; i < size; i++){
            ArrayList<String> row = new ArrayList<>(size);
            for(int j = 0; j < size; j++){
                row.add(null);
            }
            matrix.add(row);
        }
    }

    public void addEdges(int from, int to, String type){
        addEdge(from,to,type);
        addEdge(to,from,type);
    }

    private void addEdge(int from, int to, String type){
        ArrayList<String> list = matrix.get(from);
        String types = list.get(to);
        if(types == null){
            list.set(to, type);
        }
        else if(!types.contains(type)){
            list.set(to, types+type);
        }
    }

    private int indexOf(String city){
        return cities.indexOf(city);
    }

    public ArrayList<String> query2(String from, String to, int n){
        boolean[] visited = new boolean[cities.size()];
        ArrayList<String> allPaths =new ArrayList<>();
        query2dfs(indexOf(from), indexOf(to), n, visited, from + " ", allPaths);
        return allPaths;
    }
    private void query2dfs(int from, int to, int n, boolean[] visited, String currentPath, ArrayList<String> allPaths){
        visited[from] = true;

        if(from == to && n == -1){
            allPaths.add(currentPath);
        }
        else{
            for(int i = 0; i < cities.size(); i++){
                if(i == from)
                    continue;
                String edges = matrix.get(from).get(i);
                if(edges != null && !visited[i]){
                    for(int j = 0; j < edges.length(); j++){
                        query2dfs(i , to, n-1, visited, currentPath + edges.charAt(j) + " " + cities.get(i) + " ", allPaths);
                    }
                }
            }
        }
        visited[from] = false;
    }

    @Override
    public String toString() {
        return "matrix=" + matrix ;
    }
}
