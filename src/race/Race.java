package race;

import horse.Horse;
import road.Road;
import ui.ASCII;
import ui.Leaderboard;
import ui.Progress;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.ThreadUtility.waitTime;

public class Race {
    private final ArrayList<Horse> participants = new ArrayList<>();
    private final Leaderboard leaderboard;
    private final Progress progress;
    private final Road road;
    private boolean topThreeFinishedAsk = false;
    private int longestNameLength = 0;
    
    public Race(Road road) {
        this.road = road;
        this.leaderboard = new Leaderboard(road, this);
        this.progress = new Progress(road, this);
    }
    
    public void start() {
        for (Horse participant : participants) {
            participant.start();
        }
        
        waitTime();
        System.out.println("Race started!");
        
        UI();
        
        synchronized (this) {
            this.notifyAll();
        }
        
        mainLoop();
    }
    
    private void mainLoop() {
        while (!allFinished()) {
            waitTime();
            
            while (!allReady()) {
                if (allFinished()) break;
                waitTime();
            }
            
            UI();
            
            if (topThreeFinished() && !topThreeFinishedAsk) {
                System.out.print("\nVols acabar la cursa? (s/*) ");
                var input = new Scanner(System.in);
                
                if (input.nextLine().equals("s")) {
                    for (Horse participant : participants) {
                        participant.interrupt();
                    }
                    synchronized (this) {
                        this.notifyAll();
                    }
                    return;
                }
                
                topThreeFinishedAsk = true;
            }
            
            synchronized (this) {
                this.notifyAll();
            }
        }
        
        UI();
    }
    
    private void UI() {
        leaderboard.print();
        progress.print();
    }
    
    private boolean allReady() {
        for (Horse participant : participants) {
            if (!participant.isWaiting && !(participant.isFinished() || participant.isDead())) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean allFinished() {
        for (Horse participant : participants) {
            if (!participant.isFinished() && !participant.isDead()) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean topThreeFinished() {
        int finished = 0;
        
        for (Horse participant : participants) {
            if (participant.isFinished()) {
                finished++;
            }
        }
        
        return finished >= 3;
    }
    
    // Add participant/s
    
    private int colorIndex = 0;
    
    public void addParticipant(String horseName) {
        participants.add(new Horse(horseName, road, this, ASCII.values()[colorIndex++]));
        
        if (colorIndex == ASCII.getColorLength()) {
            colorIndex = 0;
        }
        
        if (horseName.length() > longestNameLength) {
            longestNameLength = horseName.length();
        }
    }
    
    public void addParticipants(String... horseNames) {
        for (String horseName : horseNames) {
            addParticipant(horseName);
        }
    }
    
    public int getLongestNameLength() {
        return longestNameLength;
    }
    
    public List<Horse> getParticipants() {
        return participants;
    }
}
