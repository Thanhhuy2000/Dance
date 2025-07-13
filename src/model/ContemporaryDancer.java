package model;

import java.util.Random;

public class ContemporaryDancer extends Dancer {
    private static final Random rand = new Random();

    public ContemporaryDancer(String id, String name, int energy, int power, double grace, int emotion) {
        super(id, name, energy, DanceStyle.CONTEMPORARY, power, grace, emotion);
    }

    @Override
    public void performDance(CompetitionState state, Performance performance) {
        // Công thức tính điểm cho Contemporary: emotion ảnh hưởng mạnh nhất
        int technicalBonus = power * 5 + (int)(grace * 25) + emotion * 4;
        int artisticBonus = (int)(grace * 35) + power * 3 + emotion * 5;
        int emotionalBonus = emotion * 8 + power * 2 + (int)(grace * 15);
        
        // Thêm yếu tố ngẫu nhiên cho Contemporary
        int randomBonus = rand.nextInt(10);
        
        performance.setTechnicalScore(performance.getTechnicalScore() + technicalBonus + randomBonus);
        performance.setArtisticScore(performance.getArtisticScore() + artisticBonus + randomBonus);
        performance.setEmotionalScore(performance.getEmotionalScore() + emotionalBonus + randomBonus);
        
        this.energy -= 20;
    }

    @Override
    public boolean canPerform() {
        return energy > 0;
    }
} 