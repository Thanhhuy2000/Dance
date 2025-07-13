package model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Dancer> dancers;
    private List<Performance> performances;
    private double totalScore;

    public Team(String name) {
        this.name = name;
        this.dancers = new ArrayList<>();
        this.performances = new ArrayList<>();
        this.totalScore = 0;
    }

    public void addDancer(Dancer dancer) {
        dancers.add(dancer);
    }

    public void addPerformance(Performance performance) {
        performances.add(performance);
        totalScore += performance.calculateScore();
    }

    public String getName() { return name; }
    public List<Dancer> getDancers() { return dancers; }
    public List<Performance> getPerformances() { return performances; }
    public double getTotalScore() { return totalScore; }
} 