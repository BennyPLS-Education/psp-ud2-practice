import obstacle.BoostPad;
import race.Race;
import race.Road;
import utils.ThreadUtility;

public class Main {
    public static void main(String[] args) {
        Road road;
        
        if (args.length >= 2) {
            ThreadUtility.milis = Integer.parseInt(args[0]);
            road = new Road(Double.parseDouble(args[1]));
        } else {
            road = new Road(0.5);
        }

        road.autogeneratedObstacles();
        
        var race = getRace(args, road);
        
        race.start();
        
        if (args.length == 4 && Boolean.parseBoolean(args[3])) {
            main(args);
        }
    }
    
    private static Race getRace(String[] args, Road road) {
        var race = new Race(road);
        
        if (args.length >= 3) {
            try {
                var field = race.getClass().getDeclaredField("topThreeFinishedAsk");
                field.setAccessible(true);
                field.set(race, Boolean.parseBoolean(args[2]));
            } catch (Exception ignored) {}
            
            
        }
        
        race.addParticipants(
            "Andreu Gracia"
        );
        
        return race;
    }
}