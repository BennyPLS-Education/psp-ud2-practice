package road;

import horse.Horse;
import road.obstacle.Obstacle;

import java.util.*;

public class Road {
    private static final Random random = new Random();
    
    /* The length is represented in meters */
    private final double length;
    
    /* The position of each horse in the road in km */
    private final HashMap<Horse, Double> positions = new HashMap<>();
    
    private final Map<Double, Obstacle> obstacles = new HashMap<>();
    
    public Road(double length) {
        this.length = length;
        
        if (length <= 0) {
            throw new IllegalArgumentException("The length must be greater than 0");
        }
    }
    
    public void add(Horse horse) {
        positions.put(horse, 0D);
    }
    
    
    public void update(Horse horse) {
        var oldPosition = positions.get(horse);
        var newPosition = oldPosition + horse.getKmPerSecond();
        
        if (newPosition > length) {
            newPosition = length;
        }
        
        synchronized (positions) {
            positions.put(horse, newPosition);
        }
        
        horse.velocityVariation();
        
        var obstacles = getObstaclesBetween(oldPosition, newPosition);
        for (Obstacle obstacle : obstacles) {
            obstacle.affect(horse, this);
        }
        
        if (positions.get(horse) >= length && horse.getVelocity() > 0) {
            horse.interrupt();
        } else if ((positions.get(horse) >= length && horse.getVelocity() < 0)) {
            positions.put(horse, length);
        }
    }
    
    public double getPercentage(Horse horse) {
        return positions.get(horse) / length;
    }
    
    public List<Horse> getHorsesByPosition() {
        List<Horse> horseList = new ArrayList<>(positions.keySet());
        
        horseList.sort((first, second) -> {
            var result = Double.compare(positions.get(first), positions.get(second));
            
            if (result == 0) {
                var timeResult = -first.getFinishedAt().compareTo(second.getFinishedAt());
                if (timeResult == 0) {
                    var velocityResult = Double.compare(first.getVelocity(), second.getVelocity());
                    if (velocityResult == 0) {
                        return Double.compare(first.getLastVariation(), second.getLastVariation());
                    } else {
                        result = velocityResult;
                    }
                } else {
                    result = timeResult;
                }
            }
            
            return result;
        });
        
        return horseList.reversed();
    }
    
    public Map<Horse, Double> getPositions() {
        return positions;
    }
    
    public double getPosition(Horse horse) {
        return positions.get(horse);
    }
    
    public void modPosition(Horse horse, double position) {
        positions.put(horse, positions.get(horse) + position);
    }
    
    public List<Obstacle> getObstacles() {
        return new ArrayList<>(obstacles.values());
    }
    
    public List<Obstacle> getObstaclesBetween(double start, double end) {
        return obstacles.entrySet().stream()
            .filter(entry -> entry.getKey() >= start && entry.getKey() <= end)
            .map(Map.Entry::getValue)
            .toList();
    }
    
    public void addObstacle(Obstacle obstacle, double position) {
        obstacles.put(position, obstacle);
    }
    
    public double getObstaclePercentage(Obstacle obstacle) {
        return obstacles.entrySet().stream()
                   .filter(entry -> entry.getValue().equals(obstacle))
                   .findFirst()
                   .map(Map.Entry::getKey)
                   .orElse(0D) / length;
    }
    
    public double getLength() {
        return length;
    }
    
    public void autogeneratedObstacles() {
        var quantity = random.nextInt(0, 11);
        var lengthMargin = length / 10;
        
        for (int i = 0; i < quantity; i++) {
            var obstacle = Obstacle.getRandomObstacle();
            var position = random.nextDouble(lengthMargin, length - lengthMargin);
            
            addObstacle(obstacle, position);
        }
    }
}