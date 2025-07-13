package model;

import java.util.Random;

public class HipHopDancer extends Dancer {
    private static final Random rand = new Random();

    public HipHopDancer(String id, String name, int energy, int power, double grace, int emotion) {
        super(id, name, energy, DanceStyle.HIP_HOP, power, grace, emotion);
    }

    @Override
    public void performDance(CompetitionState state, Performance performance) {
        // Công thức tính điểm cho HipHop: power ảnh hưởng mạnh nhất
        int technicalBonus = power * 8 + (int)(grace * 20) + emotion * 2;
        int artisticBonus = (int)(grace * 30) + power * 3 + emotion * 3;
        int emotionalBonus = emotion * 5 + power * 2 + (int)(grace * 10);
        
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