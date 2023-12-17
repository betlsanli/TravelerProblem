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
}
