public class Edge {
    private String toCity;
    private String type;

    public Edge(String toCity, String type){
        this.toCity = toCity;
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " " + toCity + " ";
    }
}
