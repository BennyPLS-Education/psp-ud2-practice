package ui;

import horse.Horse;
import road.obstacle.Obstacle;
import race.Race;
import road.obstacle.Road;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ui.ASCII.RESET;

public class Progress {
    private final Road road;
    private final Race race;
    private final Map<Integer, Obstacle> obstacles = new HashMap<>();
    
    public Progress(Road road, Race race) {
        this.road = road;
        this.race = race;
        
        for (Obstacle obstacle : road.getObstacles()) {
            obstacles.put((int) Math.round(road.getObstaclePercentage(obstacle) * 100), obstacle);
        }
    }
    
    public void print() {
        List<Horse> horses = race.getParticipants();
        
        System.out.println(RESET);
        
        for (Horse horse : horses) {
            printHorse(horse);
        }
    }
    
    private void printHorse(Horse horse) {
        
        setUpColor(horse);
        
        System.out.print("|" + "-".repeat(101) + "|");
        
        System.out.println(RESET);
        
        setUpColor(horse);
        
        System.out.print("|");
        for (int i = 0; i < 101; i++) {
            if (Math.round(road.getPercentage(horse) * 100) == i) {
                System.out.print("O");
            } else if (obstacles.containsKey(i)) {
                System.out.print(obstacles.get(i++).getSymbol());
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("|");
        
        System.out.println(RESET);
        
        setUpColor(horse);
        
        System.out.println("|" + "-".repeat(101) + "|" + RESET);
        
        System.out.println(RESET);
    }
    
    private static void setUpColor(Horse horse) {
        System.out.print(horse.color);
        System.out.print(ASCII.BRIGHT_BLACK_BACKGROUND);
    }
}
