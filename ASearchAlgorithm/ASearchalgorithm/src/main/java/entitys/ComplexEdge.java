package entitys;


import org.jgrapht.graph.DefaultEdge;

public class ComplexEdge extends DefaultEdge {

    private int reDistance = 0;
    private int imDistance = 0;

    public ComplexEdge(int imDistance, int reDistance) {
        this.imDistance = imDistance;
        this.reDistance = reDistance;
    }

    public int getReDistance() {
        return reDistance;
    }

    public void setReDistance(int reDistance) {
        this.reDistance = reDistance;
    }

    public int getImDistance() {
        return imDistance;
    }

    public void setImDistance(int imDistance) {
        this.imDistance = imDistance;
    }

    @Override
    public String toString() {
        return "DoubleEdge{" +
                "reDistance=" + reDistance +
                ", imDistance=" + imDistance +
                '}';
    }
}