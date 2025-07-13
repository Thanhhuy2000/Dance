package model;

public class Performance {
    private String name;
    private int technicalScore;
    private int artisticScore;
    private int emotionalScore;

    public Performance(String name) {
        this.name = name;
        this.technicalScore = 0;
        this.artisticScore = 0;
        this.emotionalScore = 0;
    }

    public double calculateScore() {
        return (technicalScore + artisticScore + emotionalScore) / 3.0;
    }

    public boolean isCompleted() {
        return technicalScore > 0 && artisticScore > 0 && emotionalScore > 0;
    }

    // Getter/setter
    public String getName() { return name; }
    public int getTechnicalScore() { return technicalScore; }
    public int getArtisticScore() { return artisticScore; }
    public int getEmotionalScore() { return emotionalScore; }
    public void setTechnicalScore(int score) { this.technicalScore = score; }
    public void setArtisticScore(int score) { this.artisticScore = score; }
    public void setEmotionalScore(int score) { this.emotionalScore = score; }
} 