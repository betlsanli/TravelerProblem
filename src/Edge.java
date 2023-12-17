public class Edge {

    private String toCity;
    private String type;

    public Edge(String toCity, String type){
        this.toCity = toCity;
        this.type = type;
    }

    public String toCity() {
        return toCity;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + "-" + toCity + " ";
    }
}
