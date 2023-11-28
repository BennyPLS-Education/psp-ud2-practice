package ui;

import horse.Horse;
import race.Race;
import race.Road;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static ui.ASCII.RESET;
import static ui.Formats.*;

public class Leaderboard {
    private final Road road;
    private List<Horse> lastLeaderboard;
    private final Race race;
    private Date startTime;
    private static final Map<Integer, ASCII> colors = Map.of(
        1, ASCII.BRIGHT_GREEN,
        2, ASCII.BRIGHT_YELLOW,
        3, ASCII.BRIGHT_RED
    );
    
    public Leaderboard(Road road, Race race) {
        this.road = road;
        this.race = race;
    }
    
    
    public void print() {
        if (startTime == null) {
            startTime = new Date();
        }
        
        List<Horse> horses = road.getHorsesByPosition();
        System.out.print(ASCII.CLEAR);
        
        System.out.println("\t\t\t\t\t Leaderboard of " + (int) (road.getLength() * 1000) + "m Race");
        System.out.println("\t      " + "-".repeat(70));
        System.out.println("\t       Num Change  Name        Percentage  Velocity   Variation    Finished");
        
        for (int i = 0, horsesSize = horses.size(); i < horsesSize; i++) {
            printHorse(horses.get(i), i + 1);
        }
        
        lastLeaderboard = horses;
    }
    
    private void printHorse(Horse horse, int position) {
        
        System.out.printf("\t\t%s%d%s. ",
            colors.getOrDefault(position, RESET),
            position,
            RESET
        );
        
        if (lastLeaderboard != null) {
            positionVariation(horse, position);
        }
        
        System.out.print("\t");
        
        System.out.print(horse.color);
        
        System.out.printf("%-" + (race.getLongestNameLength() + 4) + "s", horse.getName());
        
        System.out.printf("%-7s %-10s ",
            PERCENTAGE_FORMAT.format(road.getPercentage(horse)),
            VELOCITY_FORMAT.format(horse.getVelocity())
        );
        
        System.out.print(RESET);
        
        variationStats(horse);
        
        System.out.print(RESET + " ");
        
        if (horse.isFinished()) {
            var timeDiff = horse.getFinishedAt().getTime() - startTime.getTime();
            System.out.printf("%s %s%s%s", TIME_FORMAT.format(new Date(timeDiff)), ASCII.GREEN, "✓", RESET);
        } else {
            System.out.printf("%s%s%s", ASCII.RED, "    ✗   ", RESET);
        }
        
        System.out.println("        ");
    }
    
    private static void variationStats(Horse horse) {
        boolean isPositiveVariation = horse.getLastVariation() > 0;
        boolean isPositiveVelocityModifier = horse.getVelocityModifier() >= 1;
        
        System.out.printf("%s%s %-5s%s %s%-3sx",
            horse.getLastVariation() == 0 ? "" :
                isPositiveVariation ? ASCII.GREEN : ASCII.RED,
            horse.getLastVariation() == 0 ? "=" :
                horse.getLastVariation() > 0 ? "↑" : "↓",
            DOUBLE_FORMAT.format(horse.getLastVariation()),
            RESET,
            horse.getVelocityModifier() == 1 ? "" :
                isPositiveVariation == isPositiveVelocityModifier ? ASCII.GREEN : ASCII.RED,
            DOUBLE_FORMAT.format(horse.getVelocityModifier())
        );
    }
    
    private void positionVariation(Horse horse, int position) {
        var positionDiff = lastLeaderboard.indexOf(horse) - position + 1;
        
        if (positionDiff > 0) {
            System.out.printf(" %s%s%s %s", RESET, ASCII.GREEN, "↑", Math.abs(positionDiff));
        } else if (positionDiff < 0) {
            System.out.printf(" %s%s%s %s", RESET, ASCII.RED, "↓", Math.abs(positionDiff));
        }
        
        System.out.print(RESET);
    }
}
