import com.fasterxml.jackson.annotation.JsonValue;

public enum PortDirection {
    OUTGOING,
    INCOMING,
    BIDIRECTIONAL;

    @JsonValue
    public int getRepresentation() {
        switch (this) {
            case INCOMING: return -1;
            case OUTGOING: return 1;
            default: return 0;
        }
    }
}
