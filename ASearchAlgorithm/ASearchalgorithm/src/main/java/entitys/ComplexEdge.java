package entitys;


import org.jgrapht.graph.DefaultEdge;

public class ComplexEdge extends DefaultEdge {

    private double reDistance = 0;
    private double imDistance = 0;

    public ComplexEdge(double imDistance, double reDistance) {
        this.imDistance = imDistance;
        this.reDistance = reDistance;
    }

    public double getReDistance() {
        return reDistance;
    }

    public void setReDistance(double reDistance) {
        this.reDistance = reDistance;
    }

    public double getImDistance() {
        return imDistance;
    }

    public void setImDistance(double imDistance) {
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