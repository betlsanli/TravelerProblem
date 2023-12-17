public enum TransportationType {
    AIRWAY('A',0),
    RAILWAY('R',1),
    HIGHWAY('H',2);

    final char abbreviation;
    final int index;

    TransportationType(char c, int index){
        this.abbreviation = c;
        this.index = index;
    }
    public static TransportationType getTransportationType(char abbreviation){
        return switch (abbreviation) {
            case 'A' -> AIRWAY;
            case 'R' -> RAILWAY;
            case 'H' -> HIGHWAY;
            default -> null;
        };
    }
}
