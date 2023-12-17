public class Edge{

    private String toCity;
    private TransportationType type;

    public Edge(String toCity, TransportationType type){
        this.toCity = toCity;
        this.type = type;
    }

    public String toCity() {
        return toCity;
    }

    public TransportationType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.abbreviation + "-" + toCity + " ";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Edge){
            return toCity.equals(((Edge) obj).toCity) && type == ((Edge) obj).type;
        }
        return false;
    }
}
