package model;

import java.util.Random;

public class ContemporaryDancer extends Dancer {
    private int emotion; // 0-10
    private static final Random rand = new Random();

    public ContemporaryDancer(String id, String name, int energy, int emotion) {
        super(id, name, energy, DanceStyle.CONTEMPORARY);
        this.emotion = emotion;
    }

    @Override
    public void performDance(CompetitionState state, Performance performance) {
        // Tăng điểm cảm xúc ngẫu nhiên dựa trên emotion
        int bonus = rand.nextInt(emotion * 10 + 1);
        performance.setEmotionalScore(performance.getEmotionalScore() + bonus);
        this.energy -= 20;
    }

    @Override
    public boolean canPerform() {
        return energy > 0;
    }

    public int getEmotion() { return emotion; }
}
