package model;

public abstract class Dancer {
    protected String id;
    protected String name;
    protected int energy;
    protected DanceStyle style;
    protected double totalScore = 0; // Tổng điểm biểu diễn

    public enum DanceStyle { HIP_HOP, BALLET, CONTEMPORARY }

    public Dancer(String id, String name, int energy, DanceStyle style) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.style = style;
    }

    public abstract void performDance(CompetitionState state, Performance performance);
    public abstract boolean canPerform();
    public void rest() {
        this.energy = Math.min(100, this.energy + 10);
    }
    // Getter/setter
    public String getId() { return id; }
    public String getName() { return name; }
    public int getEnergy() { return energy; }
    public DanceStyle getStyle() { return style; }
    public void setEnergy(int energy) { this.energy = energy; }
    public double getTotalScore() { return totalScore; }
    public void addScore(double score) { this.totalScore += score; }
}
