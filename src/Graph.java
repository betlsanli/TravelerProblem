import java.util.ArrayList;

public class Graph {
    private ArrayList<ArrayList<String>> matrix;
    private String[] cities;


    public Graph(int cap, String[] cities){
        matrix = new ArrayList<ArrayList<String>>(cap);
        this.cities = cities;
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

    @Override
    public String toString() {
        return "matrix=" + matrix ;
    }
}
