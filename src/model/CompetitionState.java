package model;

import java.util.ArrayList;
import java.util.List;

public class CompetitionState {
    private List<Team> teams;
    private int currentRound;
    private List<String> actionLog;
    private static final int MAX_ROUNDS = 8;

    public CompetitionState(List<Team> teams) {
        this.teams = teams;
        this.currentRound = 1;
        this.actionLog = new ArrayList<>();
    }

    public boolean isFinished() {
        return currentRound > MAX_ROUNDS;
    }

    public void nextRound() {
        currentRound++;
    }

    public void logAction(String action) {
        actionLog.add(action);
    }

    public int getCurrentRound() { return currentRound; }
    public List<Team> getTeams() { return teams; }
    public List<String> getActionLog() { return actionLog; }
} 