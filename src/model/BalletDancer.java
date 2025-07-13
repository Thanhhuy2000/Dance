package model;

public class BalletDancer extends Dancer {

    public BalletDancer(String id, String name, int energy, int power, double grace, int emotion) {
        super(id, name, energy, DanceStyle.BALLET, power, grace, emotion);
    }

    @Override
    public void performDance(CompetitionState state, Performance performance) {
        // Công thức tính điểm cho Ballet: grace ảnh hưởng mạnh nhất
        int technicalBonus = (int)(grace * 40) + power * 4 + emotion * 2;
        int artisticBonus = (int)(grace * 50) + power * 2 + emotion * 4;
        int emotionalBonus = emotion * 6 + (int)(grace * 20) + power * 2;
        
        performance.setTechnicalScore(performance.getTechnicalScore() + technicalBonus);
        performance.setArtisticScore(performance.getArtisticScore() + artisticBonus);
        performance.setEmotionalScore(performance.getEmotionalScore() + emotionalBonus);
        
        this.energy -= 20;
    }

    @Override
    public boolean canPerform() {
        return energy > 0;
    }
} 