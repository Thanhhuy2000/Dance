package controller;

import java.util.*;
import model.BalletDancer;
import model.CompetitionState;
import model.ContemporaryDancer;
import model.Dancer;
import model.HipHopDancer;
import model.Performance;
import model.Team;

public class CompetitionController {
    private CompetitionState state;
    private Scanner scanner = new Scanner(System.in);

    public void startCompetition() {
        System.out.println("=== Khởi động cuộc thi nhảy! ===");
        List<Team> teams = new ArrayList<>();
        System.out.print("Nhập số lượng đội: ");
        int numTeams = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= numTeams; i++) {
            teams.add(createTeamFromInput("Đội " + i));
        }
        state = new CompetitionState(teams);
        while (!state.isFinished()) {
            System.out.println("\n=== Lượt " + state.getCurrentRound() + " ===");
            for (Team team : state.getTeams()) {
                playRound(team);
            }
            state.nextRound();
            showMenu();
        }
        System.out.println("\n=== Kết thúc cuộc thi! ===");
        printFinalResults();
        printTopDancer(state.getTeams());
    }

    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Xem danh sách đội");
        System.out.println("2. Xem điểm từng đội");
        System.out.println("3. Tiếp tục");
        System.out.print("Chọn: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1: printTeams(); showMenu(); break;
            case 2: printTeamScores(); showMenu(); break;
            case 3: break;
            default: break;
        }
    }

    private void printTeams() {
        System.out.println("Danh sách các đội:");
        for (Team team : state.getTeams()) {
            System.out.println("- " + team.getName());
        }
    }

    private void printTeamScores() {
        System.out.println("Điểm từng đội:");
        for (Team team : state.getTeams()) {
            System.out.println("- " + team.getName() + ": " + team.getTotalScore());
        }
    }

    private Team createTeamFromInput(String defaultName) {
        System.out.print("Nhập tên cho " + defaultName + ": ");
        String name = scanner.nextLine();
        if (name.trim().isEmpty()) name = defaultName;
        Team team = new Team(name);
        System.out.println("Nhập thông tin 3 vũ công cho đội " + name + ":");
        for (int i = 1; i <= 3; i++) {
            System.out.println("--- Vũ công " + i + " ---");
            Dancer dancer = createDancerFromInput(i);
            team.addDancer(dancer);
        }
        return team;
    }

    private Dancer createDancerFromInput(int idx) {
        System.out.print("Tên vũ công: ");
        String name = scanner.nextLine();
        System.out.print("Chọn phong cách (1: HipHop, 2: Ballet, 3: Contemporary): ");
        int style = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập power (0-10): ");
        int power = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập grace (0-1, vd: 0.8): ");
        double grace = Double.parseDouble(scanner.nextLine());
        System.out.print("Nhập emotion (0-10): ");
        int emotion = Integer.parseInt(scanner.nextLine());
        int energy = 100;
        
        switch (style) {
            case 1:
                return new HipHopDancer(UUID.randomUUID().toString(), name, energy, power, grace, emotion);
            case 2:
                return new BalletDancer(UUID.randomUUID().toString(), name, energy, power, grace, emotion);
            case 3:
                return new ContemporaryDancer(UUID.randomUUID().toString(), name, energy, power, grace, emotion);
            default:
                System.out.println("Chọn sai, mặc định HipHop!");
                return new HipHopDancer(UUID.randomUUID().toString(), name, energy, 5, 0.5, 5);
        }
    }

    private void playRound(Team team) {
        System.out.println("Đội " + team.getName() + " biểu diễn:");
        List<Dancer> selected = selectDancers(team);
        double roundScore = 0;
        for (Dancer dancer : selected) {
            if (dancer.canPerform()) {
                Performance perf = new Performance("Freestyle");
                dancer.performDance(state, perf);
                double score = perf.calculateScore();
                dancer.addScore(score);
                team.addPerformance(perf);
                roundScore += score;
                state.logAction(dancer.getName() + " (" + dancer.getStyle() + ") biểu diễn.");
                System.out.println("- " + dancer.getName() + " (" + dancer.getStyle() + ") Energy: " + dancer.getEnergy() + ", Điểm lượt: " + score);
            }
        }
        if (selected.size() == 3) {
            roundScore *= 1.1;
            System.out.println("Combo 3 vũ công! Tổng điểm lượt được cộng thêm 10%.");
        }
        System.out.println("Tổng điểm lượt này: " + roundScore);
    }

    private List<Dancer> selectDancers(Team team) {
        List<Dancer> dancers = team.getDancers();
        List<Dancer> selected = new ArrayList<>();
        System.out.println("Chọn vũ công biểu diễn (nhập số thứ tự, cách nhau bởi dấu phẩy, tối đa 3):");
        for (int i = 0; i < dancers.size(); i++) {
            Dancer d = dancers.get(i);
            System.out.println((i+1) + ". " + d.getName() + " (" + d.getStyle() + ", Energy: " + d.getEnergy() + ")");
        }
        System.out.print("Nhập lựa chọn: ");
        String input = scanner.nextLine();
        String[] parts = input.split(",");
        for (String part : parts) {
            try {
                int idx = Integer.parseInt(part.trim()) - 1;
                if (idx >= 0 && idx < dancers.size()) {
                    Dancer d = dancers.get(idx);
                    if (d.canPerform()) selected.add(d);
                }
            } catch (Exception ignored) {}
        }
        if (selected.isEmpty()) {
            System.out.println("Không có vũ công hợp lệ, chọn lại!");
            return selectDancers(team);
        }
        return selected;
    }

    private void printFinalResults() {
        List<Team> teams = state.getTeams();
        // Sắp xếp các đội theo điểm từ cao đến thấp
        teams.sort((t1, t2) -> Double.compare(t2.getTotalScore(), t1.getTotalScore()));
        
        System.out.println("=== BẢNG XẾP HẠNG CUỐI CÙNG ===");
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.println((i+1) + ". " + team.getName() + ": " + team.getTotalScore() + " điểm");
        }
        
        // Xác định đội thắng
        if (teams.size() > 0) {
            double maxScore = teams.get(0).getTotalScore();
            List<Team> winners = new ArrayList<>();
            for (Team team : teams) {
                if (team.getTotalScore() == maxScore) {
                    winners.add(team);
                }
            }
            
            if (winners.size() == 1) {
                System.out.println("\n🏆 Đội chiến thắng: " + winners.get(0).getName());
            } else {
                System.out.print("\n🏆 Các đội hòa: ");
                for (Team t : winners) System.out.print(t.getName() + " ");
                System.out.println();
            }
        }
    }

    private void printTopDancer(List<Team> teams) {
        List<Dancer> all = new ArrayList<>();
        for (Team t : teams) all.addAll(t.getDancers());
        Dancer top = all.get(0);
        for (Dancer d : all) {
            if (d.getTotalScore() > top.getTotalScore()) top = d;
        }
        System.out.println("\n👑 Vũ công có tổng điểm cao nhất: " + top.getName() + " (" + top.getStyle() + ") với " + top.getTotalScore() + " điểm.");
    }
} 