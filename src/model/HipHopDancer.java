package model;

public class HipHopDancer extends Dancer {
    private int power; // 0-10

    public HipHopDancer(String id, String name, int energy, int power) {
        super(id, name, energy, DanceStyle.HIP_HOP);
        this.power = power;
    }

    @Override
    public void performDance(CompetitionState state, Performance performance) {
        // Tăng điểm kỹ thuật dựa trên power
        performance.setTechnicalScore(performance.getTechnicalScore() + power * 8);
        this.energy -= 20;
    }

    @Override
    public boolean canPerform() {
        return energy > 0;
    }

    public int getPower() { return power; }
}
