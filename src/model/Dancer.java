package model;

public abstract class Dancer {
    protected String id;
    protected String name;
    protected int energy;
    protected DanceStyle style;
    protected double totalScore = 0; // Tổng điểm biểu diễn
    protected int power; // 0-10
    protected double grace; // 0-1
    protected int emotion; // 0-10

    public enum DanceStyle { HIP_HOP, BALLET, CONTEMPORARY }

    public Dancer(String id, String name, int energy, DanceStyle style, int power, double grace, int emotion) {
        this.id = id;
        this.name = name;
        this.energy = energy;
        this.style = style;
        this.power = power;
        this.grace = grace;
        this.emotion = emotion;
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
    public int getPower() { return power; }
    public double getGrace() { return grace; }
    public int getEmotion() { return emotion; }
} 