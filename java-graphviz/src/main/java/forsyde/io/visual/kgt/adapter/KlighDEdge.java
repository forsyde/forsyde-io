package forsyde.io.visual.kgt.adapter;

import java.util.Objects;
import java.util.Optional;

public class KlighDEdge {

    private String srcPort = null;
    private KlighDNodeView target;
    private String dstPort = null;

    public KlighDEdge(KlighDNodeView target) {
        this.target = target;
    }

    public KlighDEdge(String srcPort, KlighDNodeView target) {
        this.srcPort = srcPort;
        this.target = target;
    }

    public KlighDEdge(KlighDNodeView target, String dstPort) {
        this.target = target;
        this.dstPort = dstPort;
    }

    public KlighDEdge(String srcPort, KlighDNodeView target, String dstPort) {
        this.srcPort = srcPort;
        this.target = target;
        this.dstPort = dstPort;
    }

    public Optional<String> getSrcPort() {
        return Optional.ofNullable(srcPort);
    }

    public Optional<String> getDstPort() {
        return Optional.ofNullable(dstPort);
    }

    public KlighDNodeView getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KlighDEdge)) return false;
        KlighDEdge that = (KlighDEdge) o;
        return Objects.equals(getSrcPort(), that.getSrcPort()) && Objects.equals(target, that.target) && Objects.equals(getDstPort(), that.getDstPort());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSrcPort(), target, getDstPort());
    }

    public void setSrcPort(String srcPort) {
        this.srcPort = srcPort;
    }

    public void setDstPort(String dstPort) {
        this.dstPort = dstPort;
    }
}
