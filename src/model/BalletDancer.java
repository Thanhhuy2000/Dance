package model;

public class BalletDancer extends Dancer {
    private double grace; // 0-1

    public BalletDancer(String id, String name, int energy, double grace) {
        super(id, name, energy, DanceStyle.BALLET);
        this.grace = grace;
    }

    @Override
    public void performDance(CompetitionState state, Performance performance) {
        // Tăng điểm nghệ thuật dựa trên grace
        performance.setArtisticScore(performance.getArtisticScore() + (int)(grace * 100));
        this.energy -= 20;
    }

    @Override
    public boolean canPerform() {
        return energy > 0;
    }

    public double getGrace() { return grace; }
}
